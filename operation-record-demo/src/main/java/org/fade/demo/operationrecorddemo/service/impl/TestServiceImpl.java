package org.fade.demo.operationrecorddemo.service.impl;

import org.fade.demo.operationrecord.annotation.OperationRecordAnnotation;
import org.fade.demo.operationrecord.expression.OperationRecordContext;
import org.fade.demo.operationrecorddemo.service.TestService;
import org.springframework.stereotype.Service;

/**
 * 测试Service实现类
 *
 * @author fade
 * @date 2022/04/07
 */
@Service
public class TestServiceImpl implements TestService {

    @Override
    @OperationRecordAnnotation(content = "test nested invoke in another service: #{#a}", operator = "", bizId = 0L)
    public void add() {
        OperationRecordContext.putVariable("a", "Hello");
        // 测试抛出异常ThreadLocal是否清理正常
        throw new RuntimeException("test");
    }

}
