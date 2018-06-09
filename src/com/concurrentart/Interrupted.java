/**
 * @author:Leo
 * @create 2018/6/6
 * @desc
 */
package com.concurrentart;

public class Interrupted {
    public static void main(String[] args) {
        Thread sleepThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"SleepThread");
        sleepThread.setDaemon(true);

        Thread busyThread = new Thread(()->{
            while (true) {
            }
        },"BusyThread");
        busyThread.setDaemon(true);

        sleepThread.start();
        busyThread.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        sleepThread.interrupt();
        busyThread.interrupt();
        System.out.println("sleepThread interrupted: " + sleepThread.isInterrupted());
        System.out.println("busyThread interrupted : " + busyThread.isInterrupted());
    }
}
