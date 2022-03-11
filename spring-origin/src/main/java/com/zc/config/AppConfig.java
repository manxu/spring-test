package com.zc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.zc.importBean.MyFactoryBean;
import com.zc.importBean.MyImportSelector;
import com.zc.model.Person;
import com.zc.model.Red;

/**
 * @Date 2021/3/16 9:48
 * @Author zc
 */
@Configuration
@Import({Red.class, MyImportSelector.class}) //还有一个 ImportBeanDefinitionRegistrar
@EnableScheduling
public class AppConfig {

    //@Scope("prototype")
    //@Lazy
    @Bean
    public Person person(){
        System.out.println("person");
        return new Person();
    }

    @Bean
    public MyFactoryBean myFactoryBean(){
        return new MyFactoryBean();
    }

}
