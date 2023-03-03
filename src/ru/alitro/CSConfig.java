package ru.alitro;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс отвечающий за полное описание утилиты
 */
public class CSConfig {

    /**
     * Класс утилиты
     */
    private final Class<? extends CommandSupported> clazz;

    /**
     * Наименование утилиты
     */
    private final String name;

    /**
     * Описание утилиты
     */
    private final String description;

    /**
     * Массив конфигураций параметров
     */
    private final List<CSParamConfig> paramConfigs = new ArrayList<>();

    /**
     * Конструктор конфигурации утилиты
     *
     * @param clazz       класс утилиты
     * @param name        наименование утилиты
     * @param description описание утилиты
     */
    public CSConfig(Class<? extends CommandSupported> clazz, String name, String description) {
        this.clazz = clazz;
        this.name = name;
        this.description = description;
    }

    /**
     * Возвращает класса утилиты
     *
     * @return объект типа Class
     */
    public Class<? extends CommandSupported> getClazz() {
        return clazz;
    }

    /**
     * Возвращает наименование утилиты
     *
     * @return строка
     */
    public String getName() {
        return name;
    }

    /**
     * Возвращает описание утилиты
     *
     * @return строка
     */
    public String getDescription() {
        return description;
    }

    /**
     * Возвращает массив конфигураций параметров
     *
     * @return массив объектов типа CSParamConfig
     */
    public List<CSParamConfig> getParamConfigs() {
        return paramConfigs;
    }

    /**
     * Добавление конфигурации параметра
     *
     * @param paramConfig объект типа CSParamConfig
     * @see ru.alitro.CSParamConfig
     */
    public void addParamConfig(CSParamConfig paramConfig) {
        this.paramConfigs.add(paramConfig);
    }

}
