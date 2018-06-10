/**
 * @author:Leo
 * @create 2018/6/9
 * @desc
 */
package com.concurrentart.chapter8;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExchangerTest {
    private static final Exchanger<String> exgr = new Exchanger<>();

    private static ExecutorService threadPool = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                String a = "银行流水B";
                try {
                    exgr.exchange(a);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                String b = "银行流水B";
                try {
                    String a = exgr.exchange(b);
                    System.out.println(a.equals(b));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
