package org.fade.demo.annotationdemo.example.service;

/**
 * 例子Service
 *
 * @author fade
 * @date 2021/12/09
 */
@Deprecated
public interface ExampleServiceV1 {

    /**
     * 测试校验字符串
     * @param arg1 {@link String}
     * @param arg2 {@link String}
     * @param arg3 {@link String}
     * @param arg4 {@link String}
     * */
    void testString(String arg1, String arg2, String arg3, String arg4);

}
