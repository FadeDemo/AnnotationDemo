package org.fade.demo.operationrecorddemo.service.impl;

import org.fade.demo.operationrecord.annotation.OperationRecordAnnotation;
import org.fade.demo.operationrecord.expression.OperationRecordContext;
import org.fade.demo.operationrecorddemo.service.ExampleService;
import org.fade.demo.operationrecorddemo.service.TestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 例子Service实现类
 *
 * @author fade
 * @date 2022/04/06
 */
@Service
public class ExampleServiceImpl implements ExampleService {

    @Resource(name = "testServiceImpl")
    private TestService testService;

    @Override
    @OperationRecordAnnotation(content = "test: #{#a}#{#b}", operator = "", bizId = 0L)
    public void add() {
        OperationRecordContext.putVariable("a", "Hello World");
        OperationRecordContext.putVariable("b", "!");
        // 测试嵌套调用是否覆盖正常
//        nestInvokeInService();
        // 测试service嵌套调用是否正常
        testService.add();
        // 测试抛出异常ThreadLocal是否清理正常
//        throw new RuntimeException("test");
    }

    @Override
    @OperationRecordAnnotation(content = "method arg: #{arg}", operator = "", bizId = 0L)
    public void update(int arg) {
        // fixme 无法解析方法参数
    }

    public void nestInvokeInService() {
        OperationRecordContext.putVariable("a", "Nest invoke will rewrite variable");
    }

}
