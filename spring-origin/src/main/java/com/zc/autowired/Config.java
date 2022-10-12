package com.zc.autowired;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class Config {

    @Bean
    @Primary
    public AutowiredModel autoWiredModel1() {
        return new AutowiredModel("1");
    }

    @Bean
    public AutowiredModel autoWiredModel2() {
        return new AutowiredModel("2");
    }

    @Bean
    public AutowiredService autowiredService(){
        return new AutowiredService();
    }

}
