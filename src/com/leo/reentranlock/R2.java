/**
 * @author:Leo
 * @create 2018/6/4
 * @desc
 */
package com.leo.reentranlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class R2 {
    private static Lock lock = new ReentrantLock();

    public static void method1() {
        lock.lock();
        try {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(1000);
                System.out.println(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void method2() throws InterruptedException {
        if (lock.tryLock(5, TimeUnit.SECONDS)) {
            System.out.println("method2 ok");
            lock.unlock();
        } else {
            System.out.println("method2 fail");
        }
    }

    public static void main(String[] args) {
        new Thread(()->method1(),"thread1").start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()-> {
            try {
                method2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"thread2").start();
    }
}
