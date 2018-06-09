/**
 * @author:Leo
 * @create 2018/6/1
 * @desc
 */
package com.oracle.thread;

public class Priority extends Thread {

    @Override
    public void run() {
        for(int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " : " + i);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Priority p1 = new Priority();
        Priority p2 = new Priority();
        Priority p3 = new Priority();
        p1.setPriority(MAX_PRIORITY);
        p2.setPriority(MIN_PRIORITY);
        p1.start();
        p2.start();
        p3.start();

    }
}
