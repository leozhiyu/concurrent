/**
 * @author:Leo
 * @create 2018/6/6
 * @desc
 */
package com.concurrentart;

import java.io.IOException;

public class Daemon {
    public static void main(String[] args) throws IOException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("finally");
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
        System.in.read();
    }
}
