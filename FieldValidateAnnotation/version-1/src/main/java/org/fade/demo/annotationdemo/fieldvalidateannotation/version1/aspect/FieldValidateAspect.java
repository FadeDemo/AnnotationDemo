package org.fade.demo.annotationdemo.fieldvalidateannotation.version1.aspect;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.fade.demo.annotationdemo.fieldvalidateannotation.version1.annotation.FieldValidate;
import org.fade.demo.annotationdemo.fieldvalidateannotation.version1.annotation.FieldsValidate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

/**
 * 处理字段验证的切面
 * @author fade
 * @date 2021/12/08
 */
@Aspect
public class FieldValidateAspect {

    private static final Logger LOG = LoggerFactory.getLogger(FieldValidateAspect.class);

    @Pointcut("@annotation(org.fade.demo.annotationdemo.fieldvalidateannotation.version1.annotation.FieldsValidate)")
    public void pointcut(){}

    @Before("pointcut()")
    public void validate(JoinPoint joinPoint){
        // 不存在没有参数的情况
        Object[] args = joinPoint.getArgs();
        Signature signature = joinPoint.getSignature();
        if (signature instanceof MethodSignature) {
            MethodSignature methodSignature = (MethodSignature) signature;
            Method method = methodSignature.getMethod();
            Class<?>[] parameterTypes = method.getParameterTypes();
            Parameter[] parameters = method.getParameters();
            Annotation[][] parameterAnnotations = method.getParameterAnnotations();
            for (int i = 0; i < parameterAnnotations.length; i++) {
                Object arg = args[i];
                Annotation[] parameterAnnotation = parameterAnnotations[i];
                for (Annotation annotation: parameterAnnotation) {
                    if (annotation.annotationType().equals(FieldsValidate.class)) {
                        LOG.debug("检测到FieldsValidate注解，即将开始校验");
                        FieldsValidate fieldsValidate = (FieldsValidate) annotation;
                        String parameterName = parameters[i].getName();
                        // 如果开启非空校验，但参数值为空，则抛出异常
                        if (fieldsValidate.isNotNull() && ObjectUtil.isNull(arg)) {
                            throw new RuntimeException(parameterName + "不能为空");
                        }
                        // 如果开启空校验，但参数值为非空，则抛出异常
                        if (fieldsValidate.isNull() && ObjectUtil.isNotNull(arg)) {
                            throw new RuntimeException(parameterName + "必须为空");
                        }
                        // 如果是CharSequence类型，开启了非空校验，但参数值为空，则抛出异常
                        if (arg instanceof CharSequence && fieldsValidate.isNotBlank() &&
                                StrUtil.isBlank((CharSequence) arg)) {
                            throw new RuntimeException(parameterName + "不能为空");
                        }
                        // 如果是CharSequence类型，开启了空校验，但参数值为非空，则抛出异常
                        if (arg instanceof CharSequence && fieldsValidate.isBlank() &&
                                StrUtil.isNotBlank((CharSequence) arg)) {
                            throw new RuntimeException(parameterName + "必须为空");
                        }
                        // 如果是集合类型，开启了非空校验，但参数值为空，则抛出异常
                        if (arg instanceof Collection && fieldsValidate.isNotEmpty() &&
                                CollectionUtil.isEmpty((Collection<?>) arg)) {
                            throw new RuntimeException(parameterName + "不能为空");
                        }
                        // 如果是集合类型，开启了空校验，但参数值为非空，则抛出异常
                        if (arg instanceof Collection && fieldsValidate.isEmpty() &&
                                CollectionUtil.isNotEmpty((Collection<?>) arg)) {
                            throw new RuntimeException(parameterName + "必须为空");
                        }
                        // 如果是Map类型，开启了非空校验，但参数值为空，则抛出异常
                        if (arg instanceof Map && fieldsValidate.isNotEmpty() &&
                                CollectionUtil.isEmpty((Map<?, ?>) arg)) {
                            throw new RuntimeException(parameterName + "不能为空");
                        }
                        // 如果是Map类型，开启了空校验，但参数值为非空，则抛出异常
                        if (arg instanceof Map && fieldsValidate.isEmpty() &&
                                CollectionUtil.isNotEmpty((Map<?, ?>) arg)) {
                            throw new RuntimeException(parameterName + "必须为空");
                        }
                        FieldValidate[] fieldValidates = fieldsValidate.value();
                        if (CollectionUtil.isNotEmpty(Arrays.asList(fieldValidates))) {
                            if (ObjectUtil.isNull(arg)) {
                                throw new RuntimeException(parameterName + "不能为空");
                            }
                            Class<?> parameterType = parameterTypes[i];
                            for (FieldValidate fieldValidate: fieldValidates) {
                                String value = fieldValidate.value();
                                try {
                                    Field field = parameterType.getDeclaredField(value);
                                    Object o = field.get(arg);
                                    // 如果开启非空校验，但参数值为空，则抛出异常
                                    if (fieldValidate.isNotNull() && ObjectUtil.isNull(o)) {
                                        throw new RuntimeException(value + "不能为空");
                                    }
                                    // 如果开启空校验，但参数值为非空，则抛出异常
                                    if (fieldValidate.isNull() && ObjectUtil.isNotNull(o)) {
                                        throw new RuntimeException(value + "必须为空");
                                    }
                                    // 如果是CharSequence类型，开启了非空校验，但参数值为空，则抛出异常
                                    if (o instanceof CharSequence && fieldValidate.isNotBlank() &&
                                            StrUtil.isBlank((CharSequence) o)) {
                                        throw new RuntimeException(value + "不能为空");
                                    }
                                    // 如果是CharSequence类型，开启了空校验，但参数值为非空，则抛出异常
                                    if (o instanceof CharSequence && fieldValidate.isBlank() &&
                                            StrUtil.isNotBlank((CharSequence) o)) {
                                        throw new RuntimeException(value + "必须为空");
                                    }
                                    // 如果是集合类型，开启了非空校验，但参数值为空，则抛出异常
                                    if (o instanceof Collection && fieldValidate.isNotEmpty() &&
                                            CollectionUtil.isEmpty((Collection<?>) o)) {
                                        throw new RuntimeException(value + "不能为空");
                                    }
                                    // 如果是集合类型，开启了空校验，但参数值为非空，则抛出异常
                                    if (o instanceof Collection && fieldValidate.isEmpty() &&
                                            CollectionUtil.isNotEmpty((Collection<?>) o)) {
                                        throw new RuntimeException(value + "必须为空");
                                    }
                                    // 如果是Map类型，开启了非空校验，但参数值为空，则抛出异常
                                    if (o instanceof Map && fieldValidate.isNotEmpty() &&
                                            CollectionUtil.isEmpty((Map<?, ?>) o)) {
                                        throw new RuntimeException(value + "不能为空");
                                    }
                                    // 如果是Map类型，开启了空校验，但参数值为非空，则抛出异常
                                    if (o instanceof Map && fieldValidate.isEmpty() &&
                                            CollectionUtil.isNotEmpty((Map<?, ?>) o)) {
                                        throw new RuntimeException(value + "必须为空");
                                    }
                                } catch (NoSuchFieldException e1) {
                                    throw new RuntimeException(parameterName + "没有" + value + "字段");
                                } catch (IllegalAccessException e2) {
                                    throw new RuntimeException(e2.getMessage());
                                }
                            }
                        }
                        LOG.debug("校验通过");
                        break;
                    }
                }
            }
        }
    }

}
