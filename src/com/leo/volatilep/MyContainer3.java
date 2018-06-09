/**
 * @author:Leo
 * @create 2018/6/3
 * @desc
 */
package com.leo.volatilep;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class MyContainer3 {
    private static List<Object> list = new ArrayList<>();

    private static void add(Object o) {
        list.add(o);
    }

    private static int size() {
        return list.size();
    }

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(1);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    latch.await();
                    System.out.println("线程2结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    if (i == 5) {
                        latch.countDown();
                    }
                    System.out.println(i);
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
