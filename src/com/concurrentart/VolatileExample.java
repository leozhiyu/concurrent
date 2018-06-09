/**
 * @author:Leo
 * @create 2018/6/3
 * @desc
 */
package com.concurrentart;

public class VolatileExample {
    int a = 0;
    volatile  boolean flag = false;

    public void writer() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        a = 1;
        flag = true;
    }

    public void reader() {
        if (flag) {
            int i = a;
            System.out.println(i);
        }
        System.out.println(a);
    }

    public static void main(String[] args) {
        VolatileExample volatileExample = new VolatileExample();
        new Thread(new Runnable() {
            @Override
            public void run() {
                volatileExample.writer();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                volatileExample.reader();
            }
        }).start();
    }
}
