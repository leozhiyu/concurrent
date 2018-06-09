/**
 * @author:Leo
 * @create 2018/6/3
 * @desc
 */
package com.leo.volatilep;

import java.util.concurrent.atomic.AtomicBoolean;

public class VolatileTest {
    private static AtomicBoolean flag = new AtomicBoolean(true);

    private static void method() {
        System.out.println("method start");
        while (flag.get()) {

        }
        System.out.println("method end");
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(VolatileTest::method,"thread1").start();

        Thread.sleep(1000);
        flag.getAndSet(false);
    }
}
