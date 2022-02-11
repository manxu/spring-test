package com.zc.original_test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ClassInittTest {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
             sdf.parse(null);
        } catch (ParseException e) {
            System.out.println("11");
        }



        Integer a = new Integer("1");
        System.out.println(Integer.valueOf(1).equals(a));

        //static final 会加入常量池，Test类不会引用Pa类，即不会初始化pa的static.
        System.out.println(Pa.s);
    }
}
class Pa{
    static  {
        System.out.println("pa");
    }

    static final String s ="a";
    

}