package org.fade.demo.operationrecorddemo.service.impl;

import org.fade.demo.operationrecord.annotation.OperationRecordAnnotation;
import org.fade.demo.operationrecord.expression.OperationRecordContext;
import org.fade.demo.operationrecorddemo.service.ExampleService;
import org.springframework.stereotype.Service;

/**
 * 例子Service实现类
 *
 * @author fade
 * @date 2022/04/06
 */
@Service
public class ExampleServiceImpl implements ExampleService {

    @Override
    @OperationRecordAnnotation(content = "'test #a'", operator = "", bizId = 0L)
    public void add() {
        OperationRecordContext.putVariable("a", "Hello World");
    }

}
