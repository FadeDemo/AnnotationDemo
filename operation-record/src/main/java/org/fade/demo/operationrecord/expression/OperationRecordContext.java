package org.fade.demo.operationrecord.expression;

import cn.hutool.core.collection.CollectionUtil;

import java.util.*;

/**
 * 操作记录上下文
 *
 * @author fade
 * @date 2022/04/06
 */
public class OperationRecordContext {

    /**
     * <p>记录Spring EL中将要使用到的变量</p>
     * */
    private static final ThreadLocal<Stack<Map<String, Object>>> VALUE_MAP_STACK = new ThreadLocal<>();

    /**
     * <p>往 {@link ThreadLocal} 中存放变量</p>
     * @param key Spring EL 中的变量
     * @param value Spring EL 中变量的引用
     * */
    public static void putVariable(String key, Object value) {
        Objects.requireNonNull(key, "key can not be null");
        Objects.requireNonNull(value, "value can not be null");
        Optional.ofNullable(VALUE_MAP_STACK.get()).ifPresentOrElse(x -> x.push(Map.of(key, value)), () -> {
            Stack<Map<String, Object>> stack = new Stack<>();
            stack.push(Map.of(key, value));
            VALUE_MAP_STACK.set(stack);
        });
    }

    /**
     * <p>包含嵌套方法调用时的占位符</p>
     * */
    public static void putEmptySpan() {
        Optional.ofNullable(VALUE_MAP_STACK.get()).ifPresentOrElse(x -> x.push(Map.of()), () -> {
            Stack<Map<String, Object>> stack = new Stack<>();
            stack.push(Map.of());
            VALUE_MAP_STACK.set(stack);
        });
    }

    /**
     * <p>获取Spring EL将要使用的变量</p>
     * */
    public static Map<String, Object> getVariables() {
        Stack<Map<String, Object>> stack = VALUE_MAP_STACK.get();
        Map<String, Object> variables = new HashMap<>(16);
        while (CollectionUtil.isNotEmpty(stack)) {
            Map<String, Object> peek = stack.peek();
            if (CollectionUtil.isEmpty(peek)) {
                return variables;
            }
            variables.putAll(peek);
            stack.pop();
        }
        return variables;
    }

    /**
     * <p>清理 {@link ThreadLocal}</p>
     * */
    public static void clear() {
        Stack<Map<String, Object>> stack = VALUE_MAP_STACK.get();
        if (CollectionUtil.isNotEmpty(stack)) {
            stack.removeAll(stack.subList(stack.lastIndexOf(Map.of()), stack.size()));
        }
        if (CollectionUtil.isEmpty(stack)) {
            VALUE_MAP_STACK.remove();
        }
    }

}
