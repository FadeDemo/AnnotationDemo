package org.fade.demo.annotationdemo.fieldvalidateannotation.version2.validate;

/**
 * 字符串验证器适配器
 *
 * @author fade
 * @date 2021/12/16
 */
public class StringValidatorAdapter extends AbstractValidatorAdapter {

    private final Validator stringValidator = new StringValidator();

    @Override
    public boolean isNotNull(Object val) {
        return stringValidator.isNotNull(val);
    }

    @Override
    public boolean isNull(Object val) {
        return stringValidator.isNull(val);
    }

    @Override
    public boolean isNotBlank(Object val) {
        return stringValidator.isNotBlank(val);
    }

    @Override
    public boolean isBlank(Object val) {
        return stringValidator.isBlank(val);
    }

    @Override
    public boolean isNotEmpty(Object val) {
        return stringValidator.isNotEmpty(val);
    }

    @Override
    public boolean isEmpty(Object val) {
        return stringValidator.isEmpty(val);
    }

    @Override
    public boolean support(Object val) {
        return val instanceof CharSequence;
    }

}
