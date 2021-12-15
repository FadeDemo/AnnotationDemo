package org.fade.demo.annotationdemo.fieldvalidateannotation.version2.validate;

/**
 * 验证器
 *
 * @author fade
 * @date 2021/12/15
 */
public interface Validator {

    /**
     * 校验是否不为null
     * */
    default void isNotNull() {
        
    }

    /**
     * 校验是否为null
     * */
    default void isNull() {

    }

    /**
     * 校验是否不为blank
     * */
    void isNotBlank();

    /**
     * 校验是否为blank
     * */
    void isBlank();

    /**
     * 校验是否不为empty
     * */
    void isNotEmpty();

    /**
     * 校验是否为empty
     * */
    void isEmpty();

}
