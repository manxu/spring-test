package com.zc.original_test;

import java.util.Arrays;

public class OrderTest {

    public static void main(String[] args) {
        int[] or = new int[]{10, 18 , 14, 25, 9 , 38 , 20 , 17, 24, 18};
        bubble(or);
        insert(or);
        fast(or);
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


    /**
     * 快速排序：时间最大n2 , 最小nlogn
     * 1. 取元素为基准数(基数不占用数组)，
     * 2. 分别从后找出比基准小的，从前找出比基准大的，然后交换这两个元素
     * 3. 重复步骤2 ，直到从后和从前相遇，将基准和该位置交换。
     * 4. 已上述的基准为 两个 子  数组 重复步骤1,2,3 直至子数组元素为1.
     * 总结： 与基数比较，将右边较小的和左边较大的交换，行程两个子数组，重复以上步骤。
     */
    private static void fast(int[] pa) {
        final int[] or = Arrays.copyOf(pa, pa.length);

        int mid, i =0, j = or.length-1; //中，左右开始
        fast(or, i, j);
        System.out.println("快速排序" + Arrays.toString(or));

    }

    private static void fast(int[] or, int i, int j) {
        if (i < j) {
            int mid = getMid(or, i, j);
            fast(or, i, mid);
            fast(or, mid + 1, j);
        }
    }


    private static int getMid(int[] or, int i, int j) {
        int base =  or[i];
        int baseIn = i;
        while (i < j) {
            while (j>i && base <= or[j]) {
                j--;
            }
            while (i<j && or[i] <= base) {
                i++;
            }
            //找到较小和较大的下标，交换
            swap(or, i, j);
        }
        //i 就是大小分界. 把基数放到这个分界处
        swap(or, baseIn, i);
        return i;

    }

    private static void swap(int[] or , int i , int j) {
        int temp = or[i];
        or[i] = or[j];
        or[j] = temp;
    }

}
