/**
 * @author:Leo
 * @create 2018/6/6
 * @desc
 */
package com.leo.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ParallelComputing {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 单线程计算素数
        long start = System.currentTimeMillis();
        getPrime(2,200000);
        long end = System.currentTimeMillis();
        System.out.println(end-start);

        // 并行计算素数
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        MyCallable m1 = new MyCallable(2,80000);
        MyCallable m2 = new MyCallable(80001, 130000);
        MyCallable m3 = new MyCallable(130001, 180000);
        MyCallable m4 = new MyCallable(180001, 200000);

        Future f1 = executorService.submit(m1);
        Future f2 = executorService.submit(m2);
        Future f3 = executorService.submit(m3);
        Future f4 = executorService.submit(m4);

        start = System.currentTimeMillis();
        f1.get();
        f2.get();
        f3.get();
        f4.get();
        end = System.currentTimeMillis();
        System.out.println(end-start);
        executorService.shutdown();
    }

    static class MyCallable implements Callable{

        int start,end;

        public MyCallable(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public Object call() throws Exception {
            return getPrime(start,end);
        }
    }

    private static boolean isPrime(int num) {
        for (int i = 2; i < num / 2; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    private static List<Integer> getPrime(int start, int end) {
        List<Integer> list = new ArrayList<>();
        for(int i = start; i <= end; i++) {
            if(isPrime(i)){
                list.add(i);
            }
        }
        return list;
    }
}
