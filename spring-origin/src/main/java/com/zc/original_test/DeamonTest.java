package com.zc.original_test;

public class DeamonTest {


    public static void main(String[] args) {


        Thread c = new Thread(() -> {
            Thread de = new Thread(() -> {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("I am deamon");
                }
            });
            de.setDaemon(true);
            de.start();
            for (int i = 0; i < 20; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i);
            }





        });

        c.start();




    }



}
