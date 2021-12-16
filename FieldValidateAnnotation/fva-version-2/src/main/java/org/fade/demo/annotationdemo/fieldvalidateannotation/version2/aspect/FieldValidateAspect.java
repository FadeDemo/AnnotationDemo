package org.fade.demo.annotationdemo.fieldvalidateannotation.version2.aspect;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.fade.demo.annotationdemo.fieldvalidateannotation.version2.annotation.FieldValidate;
import org.fade.demo.annotationdemo.fieldvalidateannotation.version2.annotation.FieldsValidate;
import org.fade.demo.annotationdemo.fieldvalidateannotation.version2.validate.CommonValidatorAdaptor;
import org.fade.demo.annotationdemo.fieldvalidateannotation.version2.validate.StringValidatorAdapter;
import org.fade.demo.annotationdemo.fieldvalidateannotation.version2.validate.ValidatorAdapter;
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

    private final List<ValidatorAdapter> validatorAdapters;

    public FieldValidateAspect() {
        this.validatorAdapters = new ArrayList<>(16);
        ValidatorAdapter commonValidatorAdaptor = new CommonValidatorAdaptor();
        ValidatorAdapter stringValidatorAdapter = new StringValidatorAdapter();
        this.validatorAdapters.add(commonValidatorAdaptor);
        this.validatorAdapters.add(stringValidatorAdapter);
    }

    /**
     * 反射抛出的异常信息
     * */
    private static final String REFLECT_ERROR = "反射获取数据发生异常";

    /**
     * 参数校验不通过错误信息模板
     * */
    private static final String PARAMETER_ERROR = "参数[%s(类型)-%d(下标)-%s(参数名)]%s";

    /**
     * 参数所属字段校验不通过错误信息模板
     * */
    private static final String PARAMETER_FIELD_ERROR = "参数[%s(类型)-%d(下标)-%s(参数名)]的字段[%s(字段名)]%s";

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
                for (FieldValidate x: fieldValidates) {
                    // 校验的真正逻辑
                    Object val;
                    // 是否是参数
                    boolean isField = x.isField();
                    // 待校验的参数或带校验字段所属参数的位置
                    int index = x.index();
                    Assert.checkBetween(index, 0, args.length - 1);
                    // 参数值
                    Object arg = args[index];
                    Parameter parameter = parameters[index];
                    if (isField) {
                        Assert.notNull(arg);
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
                    doValidate(x, val, parameter);
                }
            }
        } else {
            throw new RuntimeException(REFLECT_ERROR);
        }
        LOG.debug("校验结束");
    }

    private void doValidate(FieldValidate x, Object val, Parameter parameter) {
        boolean isField = x.isField();
        int index = x.index();
        ValidatorAdapter validatorAdapter = this.getValidatorAdapter(val);
        String msg = null;
        // 开启不为null校验且校验不通过
        if (x.isNotNull() && !validatorAdapter.isNotNull(val)) {
            msg = "不能为null";
        }
        // 开启null校验且校验不通过
        if (x.isNull() && !validatorAdapter.isNull(val)) {
            msg = "必须为null";
        }
        // 开启不为empty校验且校验不通过
        if (x.isNotEmpty() && !validatorAdapter.isNotEmpty(val)) {
            msg = "不能为empty";
        }
        // 开启empty校验且校验不通过
        if (x.isEmpty() && !validatorAdapter.isEmpty(val)) {
            msg = "必须为empty";
        }
        // 开启不为blank校验且校验不通过
        if (x.isNotBlank() && !validatorAdapter.isNotBlank(val)) {
            msg = "不能为blank";
        }
        // 开启为blank校验且校验不通过
        if (x.isBlank() && !validatorAdapter.isBlank(val)) {
            msg = "必须为blank";
        }
        if (StrUtil.isNotBlank(msg)) {
            String clause;
            if (isField) {
                clause = String.format(PARAMETER_FIELD_ERROR, parameter.getType().toString(),
                        index,
                        parameter.getName(),
                        x.name(),
                        msg);
            } else {
                String parameterName = (StrUtil.isNotBlank(x.name())) ? x.name() : parameter.getName();
                clause = String.format(PARAMETER_ERROR, parameter.getType().toString(),
                        index,
                        parameterName,
                        msg);
            }
            throw new RuntimeException(clause);
        }
    }

    private ValidatorAdapter getValidatorAdapter(Object val) {
        for (ValidatorAdapter validatorAdapter: validatorAdapters) {
            if (validatorAdapter.support(val)) {
                return validatorAdapter;
            }
        }
        throw new RuntimeException("不存在合适的验证器适配器");
    }

    @Override
    public int getOrder() {
        return 0;
    }

}
