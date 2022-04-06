package org.fade.demo.operationrecord.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.fade.demo.operationrecord.annotation.OperationRecordAnnotation;
import org.fade.demo.operationrecord.expression.OperationRecordContext;
import org.fade.demo.operationrecord.expression.OperationRecordEvaluationContext;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.lang.reflect.Method;

/**
 * 操作记录切面
 *
 * @author fade
 * @date 2022/04/06
 */
@Aspect
public class OperationRecordAspect {

    @Pointcut("@annotation(org.fade.demo.operationrecord.annotation.OperationRecordAnnotation)")
    public void pointcut() {

    }

    @Around("pointcut()")
    public Object execute(ProceedingJoinPoint proceedingJoinPoint) {
        Object ret = null;
        // 必须调用
        OperationRecordContext.putEmptySpan();
        try {
            ret = proceedingJoinPoint.proceed();
            Signature signature = proceedingJoinPoint.getSignature();
            if (signature instanceof MethodSignature) {
                MethodSignature methodSignature = (MethodSignature) signature;
                Method method = methodSignature.getMethod();
                if (method.isAnnotationPresent(OperationRecordAnnotation.class)) {
                    OperationRecordAnnotation annotation = method.getAnnotation(OperationRecordAnnotation.class);
                    String operator = annotation.operator();
                    String content = annotation.content();
                    long bizId = annotation.bizId();
                    ExpressionParser parser = new SpelExpressionParser();
                    Expression expression = parser.parseExpression(content, new TemplateParserContext());
                    OperationRecordEvaluationContext context = new OperationRecordEvaluationContext(null, method, proceedingJoinPoint.getArgs(), new DefaultParameterNameDiscoverer());
                    Object value = expression.getValue(context);
                    System.out.println(value);
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            OperationRecordContext.clear();
        }
        return ret;
    }

}
