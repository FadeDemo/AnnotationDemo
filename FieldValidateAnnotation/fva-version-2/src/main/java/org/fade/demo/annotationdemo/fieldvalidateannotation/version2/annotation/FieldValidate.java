package org.fade.demo.annotationdemo.fieldvalidateannotation.version2.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>单独使用时适用于只校验一个参数或一个参数的一个字段的情况，当需要校验
 * 多个参数或参数的多个字段时，需与 {@link FieldsValidate} 搭配使用</p>
 * <p>当方法的参数大于1时， {@link #index()} 为必填，
 * {@link #name()} 可以选择不填，但是此时会丢失参数原本的名字信息</p>
 * <p>当校验方法参数的字段时， {@link #isField()} 必须设置为true，
 * 且 {@link #name()} 为必填</p>
 * @author fade
 * @date 2021/12/13
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldValidate {

    /**
     * 待校验的参数名或字段名
     * */
    String name() default "";

    /**
     * 待校验的参数声明位置或带校验的字段所属参数的声明位置
     * */
    int index() default 0;

    /**
     * 是否是参数的字段
     * */
    boolean isField() default false;

    /**
     * 是否开启不为null的校验
     * */
    boolean isNotNull() default false;

    /**
     * 是否开启为null的校验
     * */
    boolean isNull() default false;

    /**
     * 是否开启不为blank的校验
     * */
    boolean isNotBlank() default false;

    /**
     * 是否开启为blank的校验
     * */
    boolean isBlank() default false;

    /**
     * 是否开启不为empty的校验
     * */
    boolean isNotEmpty() default false;

    /**
     * 是否开启为empty的校验
     * */
    boolean isEmpty() default false;

}
