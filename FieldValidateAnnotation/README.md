# 字段验证注解demo

### version-1

###### 当前存在的疑惑

* Spring AOP 当我使用的切入点表达式是 `@annotation(org.fade.demo.annotationdemo.fieldvalidateannotation.version1.annotation.FieldsValidate)` 和 `@within(org.fade.demo.annotationdemo.fieldvalidateannotation.version1.annotation.FieldsValidate)` 时不起作用
* Spring AOP 当我使用的切入点表达式是 `@target(org.fade.demo.annotationdemo.fieldvalidateannotation.version1.annotation.FieldsValidate)` 和 `@args(org.fade.demo.annotationdemo.fieldvalidateannotation.version1.annotation.FieldsValidate)` 时会报错 `Unable to proxy interface-implementing method` ，但明明切入点设置的是与无法代理的类的方法无关的

上述疑惑有关的解释：

* [Declaring a Pointcut](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#aop-pointcuts)
* [spring creates proxy for wrong classes when using aop class level annotation](https://stackoverflow.com/questions/52992365/spring-creates-proxy-for-wrong-classes-when-using-aop-class-level-annotation/53452483#53452483)

###### 当前存在的问题

* 由于Spring AOP 的限制，当前版本的切入点表达式是固定写死的，当需要切换项目时，要修改切入点表达式，不够灵活
* 实际校验的过程中，定义的 `FieldsValidate` 和 `FieldValidate` 注解的校验逻辑有较多相同的部分
* 针对字符串、集合、 `Map` 类型的校验仍然存在问题，如：

```java
// fixme 如果arg为空，则arg不是CharSequence的实例，那么此判断将不会执行
// 如果是CharSequence类型，开启了非空校验，但参数值为空，则抛出异常
if (arg instanceof CharSequence && fieldsValidate.isNotBlank() && 
        StrUtil.isBlank((CharSequence) arg)) {
    throw new RuntimeException(parameterName + "不能为空");
}
```

###### 存在的改进方向

* 切入点表达式可配置
* 将注解改成方法级别上的注解
* 优化校验逻辑
* 优化注解的设计方案