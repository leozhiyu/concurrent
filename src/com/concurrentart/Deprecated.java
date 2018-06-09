/**
 * @author:Leo
 * @create 2018/6/6
 * @desc
 */
package com.concurrentart;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Deprecated {
    public static void main(String[] args) throws InterruptedException {
        DateFormat format = new SimpleDateFormat("HH:mm:ss");
        Thread printThread = new Thread(new Runnable() {
            @Override
            public void run() {
                DateFormat df = new SimpleDateFormat("HH:mm:ss");
                while (true) {
                    System.out.println(Thread.currentThread().getName() + " Run at " + df.format(new Date()));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        printThread.setDaemon(true);
        printThread.start();
        TimeUnit.SECONDS.sleep(3);
        printThread.suspend();
        System.out.println("suspend at " + format.format(new Date()));
        TimeUnit.SECONDS.sleep(3);
        printThread.resume();
        System.out.println("resume at " + format.format(new Date()));
        TimeUnit.SECONDS.sleep(3);
        printThread.stop();
        System.out.println("stop at " + format.format(new Date()));
        TimeUnit.SECONDS.sleep(3);
    }
}
