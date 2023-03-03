package ru.alitro.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class ReflectUtils {

    public static Set<Class> getAllClassesByInterface(Class interfaceClass) {

        Set<Class> classes = new HashSet<>();

        for (String packageName : getAllPackages()) {
            classes.addAll(getClassesByPackageAndInterface(packageName, interfaceClass));
        }

        return classes;
    }

    private static Set<String> getAllPackages() {
        Set<String> packages = new HashSet<>();

        try {
            Method getPackagesMethod = ClassLoader.class.getDeclaredMethod("getPackages");
            getPackagesMethod.setAccessible(true);
            packages.addAll(Arrays.stream(((Package[]) getPackagesMethod.invoke(ClassLoader.getSystemClassLoader()))).map(Package::getName)
                    .collect(Collectors.toSet()));
            getPackagesMethod.setAccessible(false);
        } catch (Exception e) {
            // На данном этапе ошибок быть не может
        }

        return packages;
    }

    private static Set<Class> getClassesByPackageAndInterface(String packageName, Class interfaceClass) {
        Set<Class> classes = new HashSet<>();

        InputStream resourcesStream = ClassLoader.getSystemClassLoader().getResourceAsStream(packageName.replaceAll("[.]", "/"));
        if (resourcesStream != null) {
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new InputStreamReader(resourcesStream));
                classes.addAll(reader.lines().filter(line -> line.endsWith(".class")).map(line -> getClass(line, packageName)).filter(Objects::nonNull)
                        .filter(clazz -> Arrays.asList(clazz.getInterfaces()).contains(interfaceClass)).collect(Collectors.toSet()));
            } finally {
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                }
            }
        }

        return classes;
    }

    private static Class getClass(String className, String packageName) {
        String classFullName = packageName + "." + className.substring(0, className.lastIndexOf('.'));
        try {
            return Class.forName(classFullName);
        } catch (ClassNotFoundException e) {
            System.out.println("Class " + classFullName + " not found");
        }
        return null;
    }

    public static List<Field> getAllFields(Class clazz) {
        return Arrays.asList(clazz.getDeclaredFields());
    }

}
