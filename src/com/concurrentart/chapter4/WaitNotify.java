/**
 * @author:Leo
 * @create 2018/6/7
 * @desc
 */
package com.concurrentart.chapter4;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class WaitNotify {
    static boolean flag = true;
    static Object lock = new Object();

    static class Wait implements Runnable{

        @Override
        public void run() {
            synchronized (lock) {
                while (flag) {
                    try {
                        System.out.println(Thread.currentThread() + " flag is true.wait @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread() + "flag is false, running @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }
        }
    }

    static class Notify implements Runnable {

        @Override
        public void run() {
            synchronized (lock) {
                    try {
                        System.out.println(Thread.currentThread() + " hold lock.motify @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                        lock.notifyAll();
                        flag = false;
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            synchronized (lock) {
                System.out.println(Thread.currentThread() + "hold lock again sleep @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread waitThread = new Thread(new Wait(), "waitThread");
        waitThread.start();

        TimeUnit.SECONDS.sleep(1);
        Thread notifyThread = new Thread(new Notify(), " Notify Thread");
        notifyThread.start();
    }
}
