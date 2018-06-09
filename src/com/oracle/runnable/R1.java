/**
 * @author:Leo
 * @create 2018/6/1
 * @desc
 */
package com.oracle.runnable;

public class R1 implements Runnable {
    private int data = 0;
    @Override
    public void run() {
        for (int i = 0 ; i < 10; i++) {
            System.out.println(Thread.currentThread().getName());
            data++;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



    public static void main(String[] args) {
        R1 r = new R1();
        Thread t1 = new Thread(r);
        t1.start();
        Thread t2 = new Thread(r);
        t2.start();
        System.out.println(r.data);
    }
}
