package com.qf.annoations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @program: brandshop
 * @description: 自定义注解 标记dto属性 对应的pojo
 * @author: money
 * @create: 2020-07-17 21:07
 */
@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface BeanCopyField {
    String value() default "";
}
