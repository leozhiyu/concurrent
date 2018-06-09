/**
 * @author:Leo
 * @create 2018/6/4
 * @desc
 */
package com.leo.threadlocal;

public class ThreadLocal2 {
    public static volatile ThreadLocal<Human> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(threadLocal.get());
        }).start();

        new Thread(()->{
            threadLocal.set(new Human());
        }).start();
    }
}

class Human {
    String name = "zhangsan";
}
