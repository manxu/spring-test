package com.zc.original_test;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Date 2021/3/23 17:44
 * @Author zc
 * 获取锁判断是否是当前线程，如果是返回true, (可重入锁)
 * 如果不是：
 *      非公平锁先尝试获取锁，如果获取不到，将该线程插入AbstractQueuedSynchronizer的链表中
 *      公平锁则是直接将该线程插入AbstractQueuedSynchronizer的链表中(tail 和next prev),
 *  lock()方法循环尝试从 AQS 链表中获取prev 。
 *
 */
public class FairTest {

    public static void main(String[] args) {
        final ReentrantLock lock = new ReentrantLock(true);
        new Thread(()->{
            lock.lock();
            System.out.println(1);
            try {
                Thread.sleep(100*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
        }, "t1").start();

        try {
            Thread.sleep(2*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread t2 = new Thread(()->{
            lock.lock();
            System.out.println(2);
            try {
                Thread.sleep(100*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
        }, "t2");
        t2.start();
        try {
            Thread.sleep(200000*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /*int i =2 ;
        for(;;){
            int finalI = i;
            Thread t3 = new Thread(()->{
                lock.lock();
                System.out.println(finalI);
                lock.unlock();
            }, "t" + i);
            i++;
            t3.start();
            if(i>2){
                break;
            }
        }*/



    }

}
