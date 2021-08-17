package com.zc.importBean;

import org.springframework.beans.factory.FactoryBean;

import com.zc.model.Yellow;

/**
 * @Date 2021/3/17 14:27
 * @Author zc
 */
public class MyFactoryBean implements FactoryBean {
    public Object getObject() {
        return new Yellow();
    }

    public Class<?> getObjectType() {
        return Yellow.class;
    }

    public boolean isSingleton() {
        return true;
    }
}
