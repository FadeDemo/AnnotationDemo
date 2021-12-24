package org.fade.demo.annotationdemo.example.service;

import org.fade.demo.annotationdemo.example.entity.Example;
import org.fade.demo.annotationdemo.fieldvalidateannotation.version2.annotation.FieldValidate;
import org.fade.demo.annotationdemo.fieldvalidateannotation.version2.annotation.FieldsValidate;

import java.util.Collection;
import java.util.Map;

/**
 * @author fade
 * @date 2021/12/14
 */
public interface ExampleServiceV2 {

    /**
     * 测试切面是否正常运行
     * */
    void testPointCutNormal();

    /**
     * 测试参数是否不为null
     * @param arg 待校验的参数
     * */
    void testValidateParameterIsNotNull(Object arg);

    /**
     * 测试参数是否为null
     * @param arg 待校验的参数
     * */
    void testValidateParameterIsNull(Object arg);

    /**
     * 测试集合参数是否不为empty
     * @param arg 待校验的参数
     * */
    void testValidateCollectionParameterIsNotEmpty(Collection<Object> arg);

    /**
     * 测试 {@link Map} 参数是否不为empty
     * @param arg 待校验的参数
     * */
    void testValidateMapParameterIsNotEmpty(Map<?, ?> arg);

    /**
     * 测试 String 参数是否不为empty
     * @param arg 待校验的参数
     * */
    void testValidateStringParameterIsNotEmpty(String arg);

    /**
     * 测试 {@link Collection} 参数是否为empty
     * @param arg 带校验的参数
     * */
    void testValidateCollectionParameterIsEmpty(Collection<?> arg);

    /**
     * 测试 {@link Map} 参数是否为empty
     * @param arg 待校验的参数
     * */
    void testValidateMapParameterIsEmpty(Map<?, ?> arg);

    /**
     * 测试 {@link String} 参数是否为empty
     * @param arg 带校验的参数
     * */
    void testValidateStringParameterIsEmpty(String arg);

    /**
     * 测试 {@link String} 参数是否不为blank
     * @param arg 待校验的参数
     * */
    void testValidateStringParameterIsNotBlank(String arg);

    /**
     * 测试校验index!=0的参数
     * @param arg1 参数1
     * @param arg2 待校验的参数
     * */
    void testValidateNotFirstParameter(Object arg1, String arg2);

    /**
     * 测试校验所有参数
     * @param arg1 待校验的参数1
     * @param arg2 待校验的参数2
     * @param arg3 待校验的参数3
     * */
    void testValidateAllParameters(Object arg1, Map<?, ?> arg2, String arg3);

    /**
     * 测试校验参数内的属性
     * @param arg 待校验的参数
     * @see Example
     * */
    void testValidateParameterFields(Example arg);

    /**
     * 测试同时校验参数和参数内的属性
     * @param arg1 待校验的参数1
     * @param arg2 待校验的参数2
     * @see Example
     * */
    void testValidateParameterAndField(Example arg1, Object arg2);

    /**
     * 测试同时使用 {@link FieldsValidate} 和 {@link FieldValidate}
     * @param arg1 待校验的参数1
     * @param arg2 待校验的参数2
     * @see FieldsValidate
     * @see FieldValidate
     * */
    void testFieldsValidateAndFieldValidate(Object arg1, Object arg2);

}
