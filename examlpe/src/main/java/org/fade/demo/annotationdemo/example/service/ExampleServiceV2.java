package org.fade.demo.annotationdemo.example.service;

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

}
