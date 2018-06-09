/**
 * @author:Leo
 * @create 2018/6/3
 * @desc
 */
package com.leo.synchronize;

public class DeadLock {

    private static String a = "a";
    private static String b = "b";



    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (a) {
                    System.out.println("线程1拿到了a");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (b) {
                        System.out.println("线程1拿到了b");
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (b) {
                    System.out.println("线程2拿到了b");
                    synchronized (a) {
                        System.out.println("线程2拿到了a");
                    }
                }

            }
        }).start();
    }
}
