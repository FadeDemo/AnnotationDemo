package org.fade.demo.operationrecord.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 操作记录注解
 *
 * @author fade
 * @date 2022/04/06
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OperationRecordAnnotation {

    /**
     * <p>操作内容</p>
     * */
    String content();

    /**
     * <p>操作者</p>
     * */
    String operator();

    /**
     * <p>业务id</p>
     * */
    long bizId();

}
