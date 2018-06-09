/**
 * @author:Leo
 * @create 2018/6/7
 * @desc
 */
package com.concurrentart.chapter4;

import java.util.concurrent.TimeUnit;

public class Profiler {
    private static final ThreadLocal<Long> TIME_THREADLOCAL = new ThreadLocal<Long>(){
        @Override
        protected Long initialValue() {
            return System.currentTimeMillis();
        }
    };

    public static final void begin() {
        System.out.println(Thread.currentThread().getName());
        TIME_THREADLOCAL.set(System.currentTimeMillis());
    }

    public static final long end() {
        System.out.println(Thread.currentThread().getName());
        return System.currentTimeMillis() - TIME_THREADLOCAL.get();
    }

    public static void main(String[] args) throws InterruptedException {
        Profiler.begin();
        TimeUnit.SECONDS.sleep(1);
        System.out.println(Profiler.end());
    }
}
