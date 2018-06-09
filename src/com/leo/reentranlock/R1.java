/**
 * @author:Leo
 * @create 2018/6/4
 * @desc
 */
package com.leo.reentranlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class R1 {
    private static Lock lock = new ReentrantLock();

    public static void method1() {
        lock.lock();
        try {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(100);
                System.out.println(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void method2() {
        lock.lock();
        System.out.println("method2");
        lock.unlock();
    }

    public static void main(String[] args) {
        new Thread(()->method1(),"thread1").start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->method2(),"thread2").start();
    }
}
