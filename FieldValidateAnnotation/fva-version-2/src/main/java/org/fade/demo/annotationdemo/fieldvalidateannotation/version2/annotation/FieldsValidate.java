package org.fade.demo.annotationdemo.fieldvalidateannotation.version2.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author fade
 * @date 2021/12/13
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldsValidate {

    /**
     * {@link FieldValidate} 的列表
     * */
    FieldValidate[] value();

}
