package com.zc.original_test;

import org.springframework.aop.support.AopUtils;
import org.springframework.scheduling.annotation.ProxyAsyncConfiguration;
import org.springframework.transaction.interceptor.TransactionAttributeSourceAdvisor;

public class AopUtilsTest {

    public static void main(String[] args) {
        Class a = AopUtils.getTargetClass(new TransactionAttributeSourceAdvisor());
        System.out.println(a.getDeclaringClass());


    }



}
