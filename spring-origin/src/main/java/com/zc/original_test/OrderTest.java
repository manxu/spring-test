package com.zc.original_test;

import java.util.Arrays;

public class OrderTest {

    public static void main(String[] args) {
        int[] or = new int[]{10, 18 , 14, 25, 9 , 38 , 20 , 17};
        bubble(or);
        insert(or);
    }

    /**
     * 冒泡排序：1. 依次比较相邻的元素，找出其中大的并通过交换放到最后，
     * 2. 除最后x(x为已找到的最大数，次大数...)个元素，剩下的元素再次执行步骤1，
     * 3. 重复步骤2,直到找到最后一个数。
     * 总结：相邻比较，找出最大数，次大数........
     */
    private static void bubble(int[] pa) {
        final int[] or = Arrays.copyOf(pa, pa.length);
        for(int i=0; i<or.length-1; i++) {
            for(int j=0; j < or.length-1-i; j++) {
                if(or[j]>or[j+1]) {
                    int temp = or[j];
                    or[j] = or[j+1];
                    or[j+1] = temp;
                }
            }
        }
        System.out.println("冒泡排序" + Arrays.toString(or));
    }


    /**
     * 插入排序：类似于摸牌。默认已摸到的排是有序的，然后把刚摸的牌和手里的依次比较，插入到合适位置。
     * 1. 取数，
     * 2. 和前相邻元素比较，如果小，则交换， 重复该步骤。如果大则结束。
     *  10 14 18 25
     *              9 递进比较交换（如果这个数比25大，是不是就省去了继续比较）。
     *            9  25
     *         9  18
     *      9  14
     *    9 10
     *
     * 总结：和前相邻元素比较，小则交换。
     * 这种排序会在数组前部形成一个有序的数组。如果原数组的顺序不太乱，则可能减少比较次数。
     * 而冒泡则需要比较所有元素(除最大，次大..)找到其中最大的，全比较。
     * 除非原数组是从大到小排列的（这时插入排序前部形成的有序数组则失去减少比较次数的作用，因为要比较全部元素），这时冒泡和插入排序效率基本一致。
     */
    private static void insert(int[] pa) {
        final int[] or = Arrays.copyOf(pa, pa.length);
        for(int i=0;i<or.length;i++) {
            for(int j=i-1;j>=0;j--) {
                if(or[j+1] < or[j]) {
                    int temp = or[j+1];
                    or[j+1] = or[j];
                    or[j] = temp;
                } else {
                    break;  //这是减少比较次数的关键
                }
            }
        }
        System.out.println("插入排序" + Arrays.toString(or));
    }



}
