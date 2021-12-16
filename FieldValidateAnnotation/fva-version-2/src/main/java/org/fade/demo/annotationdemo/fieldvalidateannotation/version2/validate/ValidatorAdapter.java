package org.fade.demo.annotationdemo.fieldvalidateannotation.version2.validate;

/**
 * 验证器适配器
 *
 * @author fade
 * @date 2021/12/15
 */
public interface ValidatorAdapter extends Validator {

    /**
     * 判断当前适配器是否支持校验此类型
     * @param val 待校验的值
     * @return 是否支持
     * */
    boolean support(Object val);

}
