package com.zc.autowired;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 测试@AutoWired 是否是按类型注入？
 * 1. 按类型查找，查不到报错。
 * 2. 查到多个，则 指定>优先>名称 即 @Qualifier > @Primary > 名称。
 */
public class AutoWiredSpringMain {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        final AutowiredService bean = context.getBean(AutowiredService.class);
        bean.me();

    }
}
