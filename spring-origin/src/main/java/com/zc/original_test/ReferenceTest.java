package com.zc.original_test;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import com.zc.model.Yellow;

public class ReferenceTest {


    public static void main(String[] args) {

        Yellow y = new Yellow();
        WeakReference<Yellow> soft = new WeakReference<>(y);
        /*int i = 0;
        while (soft.get()!=null){
            System.out.println(i++);
        }*/










    }


}
