/**
 * @author:Leo
 * @create 2018/6/1
 * @desc
 */
package com.leo.synchronize;

public class S4 {

    private synchronized void method1() {
        System.out.println(Thread.currentThread().getName() + "  start...");
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " end...");
    }

    private void method2() {
        System.out.println(Thread.currentThread().getName() + " method2");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        S4 s = new S4();
        //new Thread(s::method1, "thead1").start();
        //new Thread(s::method2, "thead2").start();
        new Thread(()->s.method1(),"thread1").start();
        new Thread(()->s.method2(),"thread2").start();
    }
}
