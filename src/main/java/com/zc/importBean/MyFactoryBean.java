package com.zc.importBean;

import com.zc.model.Yellow;
import org.springframework.beans.factory.FactoryBean;

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
