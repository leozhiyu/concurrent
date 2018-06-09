/**
 * @author:Leo
 * @create 2018/6/4
 * @desc
 */
package com.leo.threadlocal;

public class ThreadLocal1 {
    public volatile static Person person = new Person();

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(person.name);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                person.name = "leo";
            }
        }).start();
    }
}
class Person {
    String name = "zhangsan";
}
