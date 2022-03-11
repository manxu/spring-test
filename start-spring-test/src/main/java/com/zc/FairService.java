package com.zc;

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
public class FairService {

    final ReentrantLock lock = new ReentrantLock(true);

    public boolean getLock() {
        return lock.tryLock();
    }

}
