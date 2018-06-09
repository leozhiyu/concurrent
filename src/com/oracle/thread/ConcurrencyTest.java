/**
 * @author:Leo
 * @create 2018/6/2
 * @desc
 */
package com.oracle.thread;

public class ConcurrencyTest {
    private static final long count = 100001;

    private static void concurrency() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int a = 0;
                for (int i = 0; i < count; i++) {
                    a+=5;
                }
            }
        });
        thread.start();

        int b = 0;
        for(long i = 0;i < count; i++) {
            b--;
        }
        thread.join();
        long time = System.currentTimeMillis() - start;
        System.out.println("concurrency : " + time + "ms,b = " + b);
    }

    public static void main(String[] args) {

    }
}
