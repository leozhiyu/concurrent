/**
 * @author:Leo
 * @create 2018/6/2
 * @desc
 */
package com.oracle.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class VolatileDemo {
    private int num = 0;
    private Lock lock = new ReentrantLock();
    private  void increment() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.lock();
        try {
            this.num++;
        }finally {
            lock.unlock();
        }
    }
    private int getNumber() {
        return num;
    }

    public static void main(String[] args) throws InterruptedException {
        final VolatileDemo volatileDemo = new VolatileDemo();

        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < 500; i++) {
            list.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    volatileDemo.increment();
                }
            }));
        }
        for (Thread t : list) {
            t.start();
        }

        for(Thread t : list) {
            t.join();
        }


        System.out.println(volatileDemo.getNumber());
    }
}
