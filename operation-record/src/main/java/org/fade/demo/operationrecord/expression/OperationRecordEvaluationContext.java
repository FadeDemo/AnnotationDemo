package org.fade.demo.operationrecord.expression;

import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.core.ParameterNameDiscoverer;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Optional;

/**
 * 操作记录评估上下文
 *
 * @author fade
 * @date 2022/04/06
 */
public class OperationRecordEvaluationContext extends MethodBasedEvaluationContext {

    public OperationRecordEvaluationContext(Object rootObject, Method method, Object[] arguments, ParameterNameDiscoverer parameterNameDiscoverer) {
        super(rootObject, method, arguments, parameterNameDiscoverer);
        Map<String, Object> variables = OperationRecordContext.getVariables();
        Optional.ofNullable(variables).ifPresent(x -> x.forEach(this::setVariable));
    }

}
