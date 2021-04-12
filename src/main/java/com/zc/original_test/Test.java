package com.zc.original_test;

/**
 * @Date 2021/3/25 14:12
 * @Author zc
 */
public class Test {
    public static void main(String[] args) {

        Thread t = new Thread(()->{

            System.out.println("main");
            Thread demead = new Thread(()->{

                for(;;){
                    try {
                        Thread.sleep(5*1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("demead");

                }

            },"Ademeanxxx");
            demead.setDaemon(true);
            demead.start();

            try {
                Thread.sleep(20*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"Amainxxx");

        t.start();



        try {
            Thread.sleep(60*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("xx");

    }


}
