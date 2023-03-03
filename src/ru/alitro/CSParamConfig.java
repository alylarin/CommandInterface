package ru.alitro;

import java.util.Arrays;
import java.util.List;

/**
 * Класс отвечающий за полное описание параметра
 */
public class CSParamConfig {

    /**
     * Наименование поля
     */
    private final String fieldName;

    /**
     * Тип поля
     */
    private final Class fieldType;

    /**
     * Полное наименование параметра
     */
    private final String fullName;

    /**
     * Краткое наименование параметра
     */
    private String shortName;

    /**
     * Описание параметра
     */
    private String description;

    /**
     * Обязательность наличия (по умолчанию - не обязателен)
     */
    private boolean isRequired;

    /**
     * Зависимость от наличия другого параметра
     */
    private List<String> dependencies;

    /**
     * Конфликтность от наличия другого параметра
     */
    private List<String> conflicts;

    /**
     * Произвольный обработчик значения параметра
     */
    private String handler;

    /**
     * Конструктор конфигурации параметра
     *
     * @param fieldName наименование поля
     * @param fieldType тип поля
     * @param fullName  полное наименование параметра
     */
    public CSParamConfig(String fieldName, Class fieldType, String fullName) {
        this.fieldName = fieldName;
        this.fieldType = fieldType;
        this.fullName = fullName;
    }

    /**
     * Возвращает наименование поля
     *
     * @return строка
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * Возвращает тип поля
     *
     * @return строка
     */
    public Class getFieldType() {
        return fieldType;
    }

    /**
     * Возвращает полное наименование параметра
     *
     * @return строка
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Возвращает краткое наименование
     *
     * @return строка
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * Возвращает описание параметра
     *
     * @return строка
     */
    public String getDescription() {
        return description;
    }

    /**
     * Возвращает обязательность наличия параметра
     *
     * @return булево значение
     */
    public boolean isRequired() {
        return isRequired;
    }

    /**
     * Возвращает строчный список зависимостей параметра
     *
     * @return массив строк (List)
     */
    public List<String> getDependencies() {
        return dependencies;
    }

    /**
     * Возвращает строчный список конфликтов параметра
     *
     * @return массив строк (List)
     */
    public List<String> getConflicts() {
        return conflicts;
    }

    /**
     * Возвращает наименование метода произвольного обработчика параметра
     *
     * @return строка
     */
    public String getHandler() {
        return handler;
    }

    /**
     * Устанавливает краткое наименование параметра
     *
     * @param shortName краткое наименование
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     * Возвращает признак наличия краткого наименования
     *
     * @return булево значение
     */
    public boolean hasShortName() {
        return shortName != null && !shortName.isEmpty();
    }

    /**
     * Устанавливает описание параметра
     *
     * @param description описание параметра
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Возвращает признак наличия описания
     *
     * @return булево значение
     */
    public boolean hasDescription() {
        return description != null && !description.isEmpty();
    }

    /**
     * Устанавливает обязательность наличия параметра
     *
     * @param required обязательность наличия
     */
    public void setRequired(boolean required) {
        isRequired = required;
    }

    /**
     * Устанавливает зависимости параметра
     *
     * @param dependencies зависимости (разделителем является запятая)
     */
    public void setDependencies(String dependencies) {
        this.dependencies = Arrays.asList(dependencies.split(","));
    }

    /**
     * Возвращает признак наличия зависимостей
     *
     * @return булево значение
     */
    public boolean hasDependencies() {
        return dependencies.size() != 0;
    }

    /**
     * Устанавливает конфликты параметра
     *
     * @param conflicts конфликты (разделителем является запятая)
     */
    public void setConflicts(String conflicts) {
        this.conflicts = Arrays.asList(conflicts.split(","));
    }

    /**
     * Возвращает признак наличия конфликтов
     *
     * @return булево значение
     */
    public boolean hasConflicts() {
        return conflicts.size() != 0;
    }

    /**
     * Устанавливает произвольный обработчик параметра
     *
     * @param methodName наименование метода произвольного обработчика
     */
    public void setHandler(String methodName) {
        this.handler = methodName;
    }

    /**
     * Возвращает признак наличия обработчика
     *
     * @return булево значение
     */
    public boolean hasHandler() {
        return handler != null && !handler.isEmpty();
    }

}
