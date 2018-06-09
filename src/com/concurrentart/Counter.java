/**
 * @author:Leo
 * @create 2018/6/2
 * @desc
 */
package com.concurrentart;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

public class Counter {
    private AtomicInteger atomicInteger = new AtomicInteger(0);
    private int i = 0;
    private void safeCount() {
        atomicInteger.getAndIncrement();
        // 以下实际上就是 getAndIncrement 的实现原理
        /*for (;;) {
            int i = atomicInteger.get();
            boolean suc = atomicInteger.compareAndSet(i, ++i);
            if(suc) {
                break;
            }
        }*/
    }

    private void count() {
        i = i + 1;
    }

    public static void main(String[] args) {
        final Counter cas = new Counter();
        List<Thread> ts = new ArrayList<>(600);
        long start = System.currentTimeMillis();
        // 创建100个线程
        for(int j = 0; j < 100; j++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        cas.count();
                        cas.safeCount();
                    }
                }
            });
            ts.add(t);
        }
        // 启动线程
        for (Thread t : ts) {
            t.start();
        }

        // 等待所有线程都执行完成
        for (Thread t : ts) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Stack stack = new Stack();
        System.out.println(cas.i);
        System.out.println(cas.atomicInteger.get());
        System.out.println(System.currentTimeMillis() - start);
    }
}
