package org.fade.demo.annotationdemo.fieldvalidateannotation.version1.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author fade
 * @date 2021/12/08
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldsValidate {

    FieldValidate[] value() default {};

    /**
     * 是否启用非空校验，默认为false
     * */
    boolean isNotNull() default false;

    /**
     * 是否启用空校验，默认为false
     * */
    boolean isNull() default false;

    /**
     * 是否启用非空字符串校验，默认为false，仅当字段类型为{@link String}时起作用
     * */
    boolean isNotBlank() default false;

    /**
     * 是否启用空字符串校验，默认为false，仅当字段类型为{@link String}时起作用
     * */
    boolean isBlank() default false;

    /**
     * 是否启用非空集合校验，默认为false，仅当字段类型为{@link java.util.Collection}时起作用
     * */
    boolean isNotEmpty() default false;

    /**
     * 是否启用空集合校验，默认为false，仅当字段类型为{@link java.util.Collection}时起作用
     * */
    boolean isEmpty() default false;

}
