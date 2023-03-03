package ru.alitro.exceptions;

/**
 * Класс исключения для случаев, когда переданный аргумент и/или его значение некорректны
 */
public class IncorrectArgumentException extends Exception {
    private final String argName;
    private final String message;
    private final String[] addValues;

    public IncorrectArgumentException(String argName) {
        this.argName = argName;
        this.message = null;
        this.addValues = null;
    }

    public IncorrectArgumentException(String argName, String... addValues) {
        this.argName = argName;
        this.message = null;
        this.addValues = addValues;
    }

    public IncorrectArgumentException(String argName, String message) {
        this.argName = argName;
        this.message = message;
        this.addValues = null;
    }

    public IncorrectArgumentException(String argName, String message, String... addValues) {
        this.argName = argName;
        this.message = message;
        this.addValues = addValues;
    }

    public String toString() {
        if (message == null && addValues != null && addValues.length == 1) {
            return "Введено некорректное значение '" + addValues[0] + "' для аргумента '" + argName + "'";
        } else if (message == null) {
            return "Введён некорректный аргумент: '" + argName + "'";
        }  else if (message.contains("{}")) {
            return message.replace("{}", argName);
        } else {
            return message + argName;
        }
    }

}
