package com.zc.test;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.zc.config.AppConfig;
import com.zc.model.Red;

/**
 * @Date 2021/3/16 9:47
 * @Author zc
 */
public class SpringMain {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext config = new AnnotationConfigApplicationContext(AppConfig.class);

        //Arrays.asList(config.getBeanNamesForType(Person.class)).forEach(item-> System.out.println(item));

        //System.out.println(config.getBean(Person.class));
        //System.out.println(config.getBean(Person.class));

        //System.out.println(config.getBeanDefinition("com.zc.model.Red"));
        //System.out.println(config.getBeanDefinition("com.zc.model.Blue"));
        //Yellow my = (Yellow) config.getBean("myFactoryBean");
        //BeanDefinition myFactoryBean = config.getBeanDefinition("myFactoryBean");
        //System.out.println(myFactoryBean);

        //Person person = config.getBean(Person.class);
        //person.aa();

        Red bean = config.getBean(Red.class);

        AtomicInteger c = new AtomicInteger();
        System.out.println(c.compareAndSet(0, 1));
        System.out.println(c.compareAndSet(1,2));



    }


}
