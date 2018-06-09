/**
 * @author:Leo
 * @create 2018/6/2
 * @desc
 */
package com.concurrentart;

public class DeadLockDemo {
    private static String a = "a";
    private static String b = "b";

    private void deadLock() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (a) {
                    System.out.println("t1 线程拿到了A");
                    try {
                        Thread.currentThread().sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (b) {
                        System.out.println("t1 线程拿到了B");
                        System.out.println("1");
                    }
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (b) {
                    System.out.println("t2 线程拿到了B");
                    synchronized (a) {
                        System.out.println("t2 线程拿到了A");
                        System.out.println("2");
                    }
                }
            }
        });
        t1.start();
        t2.start();
    }

    public static void main(String[] args) {
        new DeadLockDemo().deadLock();
    }
}
