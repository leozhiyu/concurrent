/**
 * @author:Leo
 * @create 2018/6/3
 * @desc
 */
package com.leo.synchronize;

public class ExceptionLock {

    private synchronized static void method() {
        int count = 0;

        while (true) {
            System.out.println(Thread.currentThread().getName() + " : " + count);
            count++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (count == 5) {
                try {
                    count = count/0;
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }


    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                method();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                method();
            }
        }).start();
    }
}
