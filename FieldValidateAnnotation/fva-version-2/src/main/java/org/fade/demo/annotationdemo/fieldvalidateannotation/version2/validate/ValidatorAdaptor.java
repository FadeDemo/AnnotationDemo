package org.fade.demo.annotationdemo.fieldvalidateannotation.version2.validate;

/**
 * 验证器适配器
 *
 * @author fade
 * @date 2021/12/15
 */
public interface ValidatorAdaptor {

    /**
     *
     * */
    boolean support();

    /**
     *
     * */
    void validate();

}
