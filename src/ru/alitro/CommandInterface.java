package ru.alitro;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ru.alitro.annotations.CSDescription;
import ru.alitro.annotations.CSParam;
import ru.alitro.exceptions.ArgumentNotFoundException;
import ru.alitro.exceptions.IncorrectArgumentException;
import ru.alitro.utils.LoggingUtils;
import ru.alitro.utils.ReflectUtils;

public class CommandInterface {

    private static final String UTILITY_PARAM_NAME = "utility";
    private static final String HELP_PARAM_NAME = "help";
    private static final List<String> systemParams = new ArrayList<>();

    static {
        systemParams.add(UTILITY_PARAM_NAME);
        systemParams.add(HELP_PARAM_NAME);
    }

    private static final List<CSConfig> configs = new ArrayList<>();

    public static void initialize(String[] arguments) {
        initConfigs();
        LoggingUtils.write("Проверка {} тест", ArgumentNotFoundException.class, "OK", "");
        try {
            checkArguments(arguments);
        } catch (IncorrectArgumentException | ArgumentNotFoundException e) {
            System.out.println(e);
        }
    }

    @SuppressWarnings("unchecked")
    private static void initConfigs() {
        Set<Class> implementedClasses = ReflectUtils.getAllClassesByInterface(CommandSupported.class);

        for (Class<? extends CommandSupported> clazz : implementedClasses) {
            configs.add(createConfig(clazz));
        }
    }

    private static CSConfig createConfig(Class<? extends CommandSupported> clazz) {
        String name = clazz.getAnnotation(CSDescription.class).name();
        String description = clazz.getAnnotation(CSDescription.class).description();

        CSConfig config = new CSConfig(clazz, name, description);

        for (Field field : ReflectUtils.getAllFields(clazz)) {
            config.addParamConfig(createParamConfig(field));
        }

        return config;
    }

    private static CSParamConfig createParamConfig(Field field) {
        CSParamConfig paramConfig = new CSParamConfig(field.getName(), field.getType(), field.getAnnotation(CSParam.class).fullName());

        if (systemParams.contains(field.getAnnotation(CSParam.class).fullName())) {
            throw new IllegalArgumentException("В качестве наименования параметра нельзя указывать системные наименования параметров!");
        }

        paramConfig.setShortName(field.getAnnotation(CSParam.class).shortName());
        paramConfig.setDescription(field.getAnnotation(CSParam.class).description());
        paramConfig.setRequired(field.getAnnotation(CSParam.class).isRequired());
        paramConfig.setDependencies(field.getAnnotation(CSParam.class).dependencies());
        paramConfig.setConflicts(field.getAnnotation(CSParam.class).conflicts());
        paramConfig.setHandler(field.getAnnotation(CSParam.class).handler());

        return paramConfig;
    }

    private static void checkArguments(String[] arguments) throws IncorrectArgumentException, ArgumentNotFoundException {
        Map<String, String> params = createParams(arguments);

        if (configs.size() > 1 && !params.containsKey(UTILITY_PARAM_NAME)) {
            throw new ArgumentNotFoundException(UTILITY_PARAM_NAME);
        }

        CSConfig config = null;
        if (params.containsKey(UTILITY_PARAM_NAME)) {
            String utilityName = params.get(UTILITY_PARAM_NAME);
            for (CSConfig csConfig : configs) {
                if (csConfig.getName().equalsIgnoreCase(utilityName)) {
                    config = csConfig;
                    break;
                }
            }

            config = null;

            if (config == null) {
                throw new IncorrectArgumentException(UTILITY_PARAM_NAME, new String[]{utilityName});
            }
        } else {
            config = configs.get(0);
        }

        for (CSParamConfig paramConfig : config.getParamConfigs()) {
            if (paramConfig.isRequired() && !(params.containsKey(paramConfig.getFullName()) || params.containsKey(paramConfig.getShortName()))) {
                throw new ArgumentNotFoundException(paramConfig.getFullName());
            }
        }
    }

    private static Map<String, String> createParams(String[] arguments) throws IncorrectArgumentException {
        Map<String, String> params = new HashMap<>();

        for (int i = 0; i < arguments.length; i++) {
            String key = arguments[i];
            String value;

            if (key.startsWith("--")) {
                key = key.substring(2);
                value = "true";
            } else if (key.startsWith("-")) {
                key = key.substring(1);
                value = arguments[++i];
            } else {
                throw new IncorrectArgumentException(key);
            }

            params.put(key, value);
        }

        return params;
    }

}
