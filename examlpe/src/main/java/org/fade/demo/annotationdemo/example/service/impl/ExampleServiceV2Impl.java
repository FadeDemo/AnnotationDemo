package org.fade.demo.annotationdemo.example.service.impl;

import org.fade.demo.annotationdemo.example.service.ExampleServiceV2;
import org.fade.demo.annotationdemo.fieldvalidateannotation.version2.annotation.FieldValidate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author fade
 * @date 2021/12/14
 */
@Service
public class ExampleServiceV2Impl implements ExampleServiceV2 {

    private static final Logger LOG = LoggerFactory.getLogger(ExampleServiceV2Impl.class);

    @Override
    @FieldValidate
    public void testPointCutNormal() {
        LOG.info("切面工作正常");
    }

    @Override
    @FieldValidate(isNotNull = true)
    public void testValidateParameterIsNotNull(Object arg) {
        LOG.info("校验通过");
    }

    @Override
    @FieldValidate(isNull = true)
    public void testValidateParameterIsNull(Object arg) {
        LOG.info("校验通过");
    }

}
