package ru.alitro.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CSParam {

    /**
     * Полное наименование параметра
     */
    String fullName();

    /**
     * Краткое наименование параметра
     */
    String shortName() default "";

    /**
     * Описание параметра
     */
    String description() default "";

    /**
     * Обязательность наличия (по умолчанию - не обязателен)
     */
    boolean isRequired() default false;

    /**
     * Зависимость от наличия другого параметра (в качестве значения необходимо указать полное наименование параметров через запятую)
     */
    String dependencies() default "";

    /**
     * Конфликтность от наличия другого параметра (в качестве значения необходимо указать полное наименование параметров через запятую)
     */
    String conflicts() default "";

    /**
     * Произвольный обработчик значения параметра (в качестве значения необходимо указать наименование метода)
     */
    String handler() default "";

}

