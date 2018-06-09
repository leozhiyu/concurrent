/**
 * @author:Leo
 * @create 2018/6/4
 * @desc
 */
package com.leo.reentranlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class R3 {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Thread t1 = new Thread(()->{
            lock.lock();
            try {
                System.out.println("t1 start");
                Thread.sleep(Integer.MAX_VALUE);
                System.out.println("t1 end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        });
        t1.start();

        Thread t2 = new Thread(()->{
            try {
                lock.lockInterruptibly();
                System.out.println("t2 start");
            } catch (InterruptedException e) {
                System.out.println("interupted");
                e.printStackTrace();
            }
            //lock.unlock();
        });
        t2.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.interrupt();  // 打断线程 2 的等待
    }
}
