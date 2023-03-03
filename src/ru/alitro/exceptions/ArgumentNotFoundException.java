package ru.alitro.exceptions;

/**
 * Класс исключения для случаев, когда необходимый аргумент не был найден
 */
public class ArgumentNotFoundException extends Exception {

    private final String argName;
    private final String message;

    public ArgumentNotFoundException(String argName) {
        this.argName = argName;
        this.message = null;
    }

    public ArgumentNotFoundException(String argName, String message) {
        this.argName = argName;
        this.message = message;
    }

    public String toString() {
        if (message == null) {
            return "Не найден аргумент '" + argName + "'";
        } else if (message.contains("{}")) {
            return message.replace("{}", argName);
        } else {
            return message + argName;
        }
    }

}
