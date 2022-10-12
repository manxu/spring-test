package com.zc.autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class AutowiredService {

    @Autowired
    @Qualifier("autoWiredModel2")
    private AutowiredModel autoWiredModel1;

    public void me() {
        System.out.println(autoWiredModel1.name);
    }

}
