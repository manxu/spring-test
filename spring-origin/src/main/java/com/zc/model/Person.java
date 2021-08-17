package com.zc.model;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Date 2021/3/16 9:51
 * @Author zc
 * bean初始化 相关：
 *   1. @Bean 属性 initMethod , destoryMethod
 *   2. InitializingBean , DisposableBean 接口
 *   3. @PostConstrucotr @PreDisposable
 *   4. BeanPostProcessor. postProcessBeforeInitialization he  postProcessAfterInitialzation
 */
public class Person /*implements BeanPostProcessor */{

    private String name;
    private String age;
    /**
     * name 和 type 都指定，则找同时符合的。
     */
    //@Resource(name ="yellow", type = Yellow.class)
    @Autowired //只按类型
    private Yellow yellow;


    public void aa(){
        yellow.aa();
    }
    //如果bean(如当前person)的 scope 是原型，则定时任务不生效
    /*@Scheduled(cron = "0/2 * * * * ?")
    public void xx(){
        System.out.println("xx");
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

   /* @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("xx");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }*/
}
