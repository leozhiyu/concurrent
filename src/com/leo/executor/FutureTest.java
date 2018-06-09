/**
 * @author:Leo
 * @create 2018/6/6
 * @desc
 */
package com.leo.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class FutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask(new Callable() {
            @Override
            public Object call() throws Exception {
                TimeUnit.MILLISECONDS.sleep(1000);
                return 100;
            }
        });

        new Thread(futureTask).start();

        //int result = futureTask.get();
        //System.out.println(result);
        System.out.println(futureTask.isDone());

    }
}
