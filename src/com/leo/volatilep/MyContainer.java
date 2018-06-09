/**
 * @author:Leo
 * @create 2018/6/3
 * @desc
 */
package com.leo.volatilep;

import java.util.ArrayList;
import java.util.List;

public class MyContainer {

    private volatile static List<Object> list = new ArrayList<>();

    public static void add(Object o) {
        list.add(o);
    }

    public static int size() {
        return list.size();
    }

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 100; i++) {
                    add(new Object());
                    System.out.println(i);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (size() == 5) {
                        break;
                    }
                }
                System.out.println("线程 t2 结束");
            }
        }).start();
    }
}
