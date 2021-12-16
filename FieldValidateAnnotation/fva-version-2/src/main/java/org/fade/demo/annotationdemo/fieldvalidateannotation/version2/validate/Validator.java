package org.fade.demo.annotationdemo.fieldvalidateannotation.version2.validate;

import cn.hutool.core.util.ObjectUtil;

/**
 * 验证器
 *
 * @author fade
 * @date 2021/12/15
 */
public interface Validator {

    /**
     * 校验是否不为null
     * @param val 待校验的值
     * @return 校验结果
     * */
    default boolean isNotNull(Object val) {
        return ObjectUtil.isNotNull(val);
    }

    /**
     * 校验是否为null
     * @param val 待校验的值
     * @return 校验结果
     * */
    default boolean isNull(Object val) {
        return ObjectUtil.isNull(val);
    }

    /**
     * 校验是否不为blank
     * @param val 待校验的值
     * @return 校验结果
     * */
    default boolean isNotBlank(Object val) {
        return false;
    }

    /**
     * 校验是否为blank
     * @param val 待校验的值
     * @return 校验结果
     * */
    default boolean isBlank(Object val) {
        return false;
    }

    /**
     * 校验是否不为empty
     * @param val 待校验的值
     * @return 校验结果
     * */
    default boolean isNotEmpty(Object val) {
        return ObjectUtil.isNotEmpty(val);
    }

    /**
     * 校验是否为empty
     * @param val 待校验的值
     * @return 校验结果
     * */
    default boolean isEmpty(Object val) {
        return ObjectUtil.isEmpty(val);
    }

}
