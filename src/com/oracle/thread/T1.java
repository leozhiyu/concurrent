/**
 * @author:Leo
 * @create 2018/6/1
 * @desc
 */
package com.oracle.thread;

public class T1 extends Thread{
    private int data = 0;

    @Override
    public void run() {
        for (int i = 0 ; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " : " + i);
            data++;
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void printDate() {
        System.out.println(this.data);
    }

    public static void main(String[] args) {
        T1 t1 = new T1();
        t1.start();
        /*T1 t2 = new T1();
        t2.start();
        t1.printDate();
        t2.printDate();*/
    }

}
