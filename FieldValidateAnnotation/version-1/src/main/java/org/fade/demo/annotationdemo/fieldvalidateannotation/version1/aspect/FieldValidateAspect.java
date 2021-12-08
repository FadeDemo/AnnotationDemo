package org.fade.demo.annotationdemo.fieldvalidateannotation.version1.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 处理字段验证的切面
 * @author fade
 * @date 2021/12/08
 */
@Aspect
public class FieldValidateAspect {

    @Pointcut("@annotation(org.fade.demo.annotationdemo.fieldvalidateannotation.version1.annotation.FieldsValidate)")
    public void pointcut(){}

    @Before("pointcut()")
    public void validate(JoinPoint joinPoint){

    }

}
