package org.fade.demo.annotationdemo.example.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.fade.demo.annotationdemo.example.entity.Example;
import org.fade.demo.annotationdemo.example.service.ExampleService;
import org.fade.demo.annotationdemo.fieldvalidateannotation.version1.annotation.FieldsValidate;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;

/**
 * 例子Service实现类
 *
 * @author fade
 * @date 2021/12/09
 */
@Service
@Slf4j
public class ExampleServiceImpl implements ExampleService {

    @Override
    public void testString(@FieldsValidate(isNull = true) String arg1, @FieldsValidate(isNotNull = true) String arg2, @FieldsValidate(isBlank = true) String arg3, @FieldsValidate(isNotBlank = true) String arg4) {
        log.info("字符串校验成功");
    }

}
