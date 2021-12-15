package org.fade.demo.annotationdemo.fieldvalidateannotation.version2.aspect;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.fade.demo.annotationdemo.fieldvalidateannotation.version2.annotation.FieldValidate;
import org.fade.demo.annotationdemo.fieldvalidateannotation.version2.annotation.FieldsValidate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author fade
 * @date 2021/12/13
 */
@Aspect
public class FieldValidateAspect implements Ordered {

    /**
     * 反射抛出的异常信息
     * */
    private static final String REFLECT_ERROR = "反射获取数据发生异常";

    private static final Logger LOG = LoggerFactory.getLogger(FieldValidateAspect.class);

    @Pointcut("@annotation(org.fade.demo.annotationdemo.fieldvalidateannotation.version2.annotation.FieldsValidate) || " +
            "@annotation(org.fade.demo.annotationdemo.fieldvalidateannotation.version2.annotation.FieldValidate)")
    public void annotationPointcut() {

    }

    @Before("annotationPointcut()")
    public void doValidate(JoinPoint joinPoint) {
        LOG.debug("校验开始");
        Signature signature = joinPoint.getSignature();
        if (signature instanceof MethodSignature) {
            MethodSignature methodSignature = (MethodSignature) signature;
            Method method = methodSignature.getMethod();
            FieldsValidate fieldsValidate = method.getAnnotation(FieldsValidate.class);
            FieldValidate fieldValidate = method.getAnnotation(FieldValidate.class);
            // 最后的校验都是通过FieldValidate实现的
            List<FieldValidate> fieldValidates = new ArrayList<>(16);
            if (ObjectUtil.isNotNull(fieldsValidate)) {
                fieldValidates.addAll(Arrays.asList(fieldsValidate.value()));
            }
            if (ObjectUtil.isNotNull(fieldsValidate)) {
                fieldValidates.add(fieldValidate);
            }
            if (CollectionUtil.isNotEmpty(fieldValidates)) {
                Parameter[] parameters = method.getParameters();
                Object[] args = joinPoint.getArgs();
                for (int i = 0; i < fieldValidates.size(); i++) {
                    FieldValidate x = fieldValidates.get(i);
                    // 校验的真正逻辑
                    Object val;
                    // 是否是参数
                    boolean isField = x.isField();
                    // 待校验的参数或带校验字段所属参数的位置
                    int index = x.index();
                    Assert.checkBetween(index, 0, args.length - 1);
                    // 参数值
                    Object arg = args[index];
                    if (isField) {
                        Assert.notNull(arg);
                        Parameter parameter = parameters[index];
                        Class<?> type = parameter.getType();
                        String name = x.name();
                        Assert.notBlank(name);
                        try {
                            Field field = type.getDeclaredField(name);
                            val = field.get(arg);
                        } catch (NoSuchFieldException | IllegalAccessException e) {
                            throw new RuntimeException(REFLECT_ERROR);
                        }
                    } else {
                        val = arg;
                    }

                }
            }
        } else {
            throw new RuntimeException(REFLECT_ERROR);
        }
        LOG.debug("校验结束");
    }

    @Override
    public int getOrder() {
        return 0;
    }

}
