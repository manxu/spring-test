package com.zc.original_test;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;

import org.springframework.util.StringUtils;

import com.zc.model.Yellow;
import com.zc.model.YellowSort;

/**
 * @Date 2021/3/25 14:12
 * @Author zc
 */
public class Test {


    public static void main(String[] args) {
        Boolean b = null;
        System.out.println(Boolean.TRUE.equals(b));




//        Yellow y = new Yellow();
//        System.out.println(y.c);
//        String c = null + "";
//        System.out.println(c);
//        List<YellowSort> s = new ArrayList<>();
//        s.add(new YellowSort(1,2L));
//        s.add(new YellowSort(1,1L));
//        s.add(new YellowSort(2,1L));
//        s.add(new YellowSort(2,3L));
//        s.add(new YellowSort(1,3L));
//        final List<YellowSort> collect = s.stream().sorted(Comparator.comparing(YellowSort::getA).thenComparing((Comparator.comparing(YellowSort::getB).reversed())))
//                .collect(Collectors.toList());
//        collect.stream().forEach(e-> System.out.println(e.getA()+""+e.getB()));

        /*Thread demead = new Thread(()->{
             int i=0;
            while (true){
                i++;
            }

        },"Ademeanxxx");
        demead.run();

        Thread demead1 = new Thread(()->{
            int i=0;
            while (true){
                i++;
            }

        },"Ademeanxxx");
        demead1.run();
*//*

        Thread t = new Thread(()->{

            System.out.println("main");

            try {
                Thread.sleep(20*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"Amainxxx");

        t.start();


*//*

        try {
            Thread.sleep(60*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("xx");*/

    }







}
