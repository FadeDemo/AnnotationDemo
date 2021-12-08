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

}
