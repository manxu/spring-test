package com.zc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 自定义一个spring boot start
 * 1. 写一个@Configuration 注解类， 类中 @Bean注入想要交给spring管理的实例
 * 2. 根目录下创建 META-INF/spring.factories，编写内容 org.springframework.boot.autoconfigure.EnableAutoConfiguration=
 * 你自己写的@Configuration 类 。
 */

@Configuration
public class StartConfiguration {

    @Bean
    public FairService fairService(){
        FairService c = new FairService();
        return c;
    }

}
