/**
 * @author:Leo
 * @create 2018/6/3
 * @desc
 */
package com.leo.synchronize;

import java.util.concurrent.TimeUnit;

public class Account {
    private String name;
    private double money;

    private synchronized void set(String name, double money) {
        this.name = name;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.money = money;
    }

    private double get() {
        return money;
    }

    public static void main(String[] args) {
        Account account = new Account();
        Thread t1 = new Thread(()->account.set("leo", 8.8),"thread1");
        t1.start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(account.get());

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(account.get());


    }
}
