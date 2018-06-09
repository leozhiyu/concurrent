/**
 * @author:Leo
 * @create 2018/6/4
 * @desc
 */
package com.leo.reentranlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class R4 extends Thread{
    public static Lock lock = new ReentrantLock(true);
    public void run() {
        for (int i = 0; i < 100;i++) {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " ,i  = " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        R4 r = new R4();
        Thread t1= new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();
    }
}
