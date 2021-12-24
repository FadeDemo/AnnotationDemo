package org.fade.demo.annotationdemo.example.service.impl;

import org.fade.demo.annotationdemo.example.entity.Example;
import org.fade.demo.annotationdemo.example.service.ExampleServiceV2;
import org.fade.demo.annotationdemo.fieldvalidateannotation.version2.annotation.FieldValidate;
import org.fade.demo.annotationdemo.fieldvalidateannotation.version2.annotation.FieldsValidate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;

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

    @Override
    @FieldValidate(isNotEmpty = true)
    public void testValidateCollectionParameterIsNotEmpty(Collection<Object> arg) {
        LOG.info("校验通过");
    }

    @Override
    @FieldValidate(isNotEmpty = true)
    public void testValidateMapParameterIsNotEmpty(Map<?, ?> arg) {
        LOG.info("校验通过");
    }

    @Override
    @FieldValidate(isNotEmpty = true)
    public void testValidateStringParameterIsNotEmpty(String arg) {
        LOG.info("校验通过");
    }

    @Override
    @FieldValidate(isEmpty = true)
    public void testValidateCollectionParameterIsEmpty(Collection<?> arg) {
        LOG.info("校验通过");
    }

    @Override
    @FieldValidate(isEmpty = true)
    public void testValidateMapParameterIsEmpty(Map<?, ?> arg) {
        LOG.info("校验通过");
    }

    @Override
    @FieldValidate(isEmpty = true)
    public void testValidateStringParameterIsEmpty(String arg) {
        LOG.info("校验通过");
    }

    @Override
    @FieldValidate(isNotBlank = true)
    public void testValidateStringParameterIsNotBlank(String arg) {
        LOG.info("校验通过");
    }

    @Override
    @FieldValidate(index = 1, isNotBlank = true)
    public void testValidateNotFirstParameter(Object arg1, String arg2) {
        LOG.info("校验通过");
    }

    @Override
    @FieldsValidate({
            @FieldValidate(isNotNull = true),
            @FieldValidate(isNotEmpty = true, index = 1),
            @FieldValidate(isNotBlank = true, index = 2)
    })
    public void testValidateAllParameters(Object arg1, Map<?, ?> arg2, String arg3) {
        LOG.info("校验通过");
    }

    @Override
    @FieldsValidate({
            @FieldValidate(name = "id", isField = true, isNotNull = true),
            @FieldValidate(name = "name", isField = true, isNotBlank = true),
            @FieldValidate(name = "list", isField = true, isNotEmpty = true),
            @FieldValidate(name = "map", isField = true, isNotEmpty = true)
    })
    public void testValidateParameterFields(Example arg) {
        LOG.info("校验通过");
    }

    @Override
    @FieldsValidate({
            @FieldValidate(isField = true, name = "id", isNotNull = true),
            @FieldValidate(index = 1, isNotNull = true)
    })
    public void testValidateParameterAndField(Example arg1, Object arg2) {
        LOG.info("校验通过");
    }

    @Override
    @FieldsValidate({
            @FieldValidate(isNotNull = true)
    })
    @FieldValidate(index = 1, isNotNull = true)
    public void testFieldsValidateAndFieldValidate(Object arg1, Object arg2) {
        LOG.info("校验通过");
    }

}
