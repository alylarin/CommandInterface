package ru.alitro.utils;

/**
 * Класс предназначен для корректного логирования и вывода информации пользователю
 */
public class LoggingUtils {

    public static void write(String message, String... values) {
        if (message.contains("{}")) {
            for (String value : values) {
                message = message.replaceFirst("\\{}", value);
            }
        } else if (message.contains("{1}")) {
            for (int i = 0; i < values.length; i++) {
                message = message.replaceAll("\\{" + (i + 1) + "}", values[i]);
            }
        }

        message = message.replaceAll("\\{\\d*}", "");

        //TODO Было бы неплохо переделать, т.к. сейчас все будет отображаться только в командной оболочке (а нужно ли если сама библиотека для CLI?)
        System.out.println(message);
    }

    public static void write(String message) {
        write(message, new String[] {});
    }

}
