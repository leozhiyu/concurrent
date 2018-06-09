/**
 * @author:Leo
 * @create 2018/6/3
 * @desc
 */
package com.leo.volatilep;

import java.util.ArrayList;
import java.util.List;

public class MyContainer2 {
    private static List<Object> list = new ArrayList<>();

    private static void add(Object o) {
        list.add(o);
    }

    private static int size() {
        return list.size();
    }

    public static void main(String[] args) {
        Object o = new Object();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (o) {
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("线程2结束");
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(i);
                    add(new Object());
                    synchronized (o) {
                        if (i == 5) {
                            o.notify();
                        }
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


    }
}
