package org.fade.demo.annotationdemo.fieldvalidateannotation.version2.validate;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;

/**
 * 字符串验证器
 * @author fade
 * @date 2021/12/16
 */
public class StringValidator implements Validator {

    @Override
    public boolean isNotBlank(Object val) {
        if (ObjectUtil.isNotNull(val)) {
            return val instanceof CharSequence &&
                    StrUtil.isNotBlank((CharSequence) val);
        }
        return false;
    }

    @Override
    public boolean isBlank(Object val) {
        if (ObjectUtil.isNotNull(val)) {
            return val instanceof CharSequence &&
                    StrUtil.isBlank((CharSequence) val);
        }
        return true;
    }

}
