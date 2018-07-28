/**
 * @author Leo
 * @version V1.0
 * @date 2018/07/27 11:34
 * @since 1.8
 */
package com.skywang.interrupt;

public class Demo1 {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new MyThread("t1");
        t1.start();
        Thread t2 = new MyThread("t2");
        t2.start();
        System.out.println("t1 is started");
        Thread.sleep(300);
        t1.interrupt();
        Thread.sleep(300);
        System.out.println("main" + t1.getState());
        Thread.sleep(300);
        System.out.println("main + " + t1.getState());
        System.out.println(t2.getState());
    }
}

class MyThread extends Thread{
    public MyThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        try {
            int i = 0;
            while (!isInterrupted()) {
                Thread.sleep(100);
                i++;
                System.out.println(this.getState());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
