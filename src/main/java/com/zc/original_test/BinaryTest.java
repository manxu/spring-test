package com.zc.original_test;


import java.util.BitSet;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Date 2021/3/29 10:45
 * @Author zc
 */
public class BinaryTest {

    public static void main(String[] args) {
        t10To2(3);
    }


    /**
     * 10进制转2进制
     */
    public static void t10To2(int value){
        int[] c = new int[10];
        int k = 0;
        for(;;){
            c[k] = (value%2);
            value = value/2;
            if(value==0){
                break;
            }
            k++;
        }

        for (int x = 0; x < c.length ; x++) {
            System.out.println(c[x]);
        }



    }


}
