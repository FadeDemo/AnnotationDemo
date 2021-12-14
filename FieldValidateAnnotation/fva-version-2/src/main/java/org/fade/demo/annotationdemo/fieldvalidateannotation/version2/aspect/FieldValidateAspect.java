package org.fade.demo.annotationdemo.fieldvalidateannotation.version2.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;

/**
 * @author fade
 * @date 2021/12/13
 */
@Aspect
public class FieldValidateAspect implements Ordered {

    private static final Logger LOG = LoggerFactory.getLogger(FieldValidateAspect.class);

    @Pointcut("@annotation(org.fade.demo.annotationdemo.fieldvalidateannotation.version2.annotation.FieldsValidate) || " +
            "@annotation(org.fade.demo.annotationdemo.fieldvalidateannotation.version2.annotation.FieldValidate)")
    public void annotationPointcut() {

    }

    @Before("annotationPointcut()")
    public void validate(JoinPoint joinPoint) {
        LOG.debug("校验开始");

        LOG.debug("校验结束");
    }

    @Override
    public int getOrder() {
        return 0;
    }

}
