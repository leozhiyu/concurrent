/**
 * @author Leo
 * @version V1.0
 * @date 2018/07/27 10:41
 * @since 1.8
 */
package com.skywang.callablefuture;

import java.util.concurrent.*;

public class CallableTest1 {
    static class MyCallable implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            int sum = 0;
            for (int i = 0; i < 100; i++) {
                sum += i;
            }
            return Integer.valueOf(sum);
        }
    }
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService pool = Executors.newSingleThreadExecutor();
        Callable c1 = new MyCallable();
        Future<Integer> future = pool.submit(c1);
        Integer result = future.get();
        System.out.println(result);
        pool.shutdown();
    }
}
