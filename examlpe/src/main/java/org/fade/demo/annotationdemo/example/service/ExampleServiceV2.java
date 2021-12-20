package org.fade.demo.annotationdemo.example.service;

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

}
