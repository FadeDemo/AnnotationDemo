package org.fade.demo.annotationdemo.fieldvalidateannotation.version2.validate;

/**
 * 常见的验证器适配器
 *
 * @author fade
 * @date 2021/12/16
 */
public class CommonValidatorAdaptor extends AbstractValidatorAdapter {

    private final Validator commonValidator = new CommonValidator();

    @Override
    public boolean isNotNull(Object val) {
        return commonValidator.isNotNull(val);
    }

    @Override
    public boolean isNull(Object val) {
        return commonValidator.isNull(val);
    }

    @Override
    public boolean isNotBlank(Object val) {
        return commonValidator.isNotBlank(val);
    }

    @Override
    public boolean isBlank(Object val) {
        return commonValidator.isBlank(val);
    }

    @Override
    public boolean isNotEmpty(Object val) {
        return commonValidator.isNotEmpty(val);
    }

    @Override
    public boolean isEmpty(Object val) {
        return commonValidator.isEmpty(val);
    }

    @Override
    public boolean support(Object val) {
        return !(val instanceof CharSequence);
    }

}
