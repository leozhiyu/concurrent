/**
 * @author:Leo
 * @create 2018/6/6
 * @desc
 */
package com.leo.executor;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinPoolTest {
    static int[] nums = new int[1000000];
    static final int MAX_NUM = 50000;
    static Random r = new Random();

    static {
        for (int i = 0; i < nums.length; i++) {
            nums[i] = r.nextInt(100);
        }
        System.out.println(Arrays.stream(nums).sum());
    }

    /*static class AddTask extends RecursiveAction {
        int start,end;

        public AddTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            if (end - start <= MAX_NUM) {
                long sum = 0L;
                for(int i = start; i < end; i++) {
                    sum += nums[i];
                }
                System.out.println("from: " + start + "to: " + end + " = " + sum);
            } else {
                int middle = start + (end - start ) /2;
                AddTask sub1 =  new AddTask(start, middle);
                AddTask sub2 = new AddTask(middle, end);
                sub1.fork();
                sub2.fork();
            }
        }
    }*/

    static class AddTask extends RecursiveTask<Long> {
        int start,end;

        public AddTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            if (end - start <= MAX_NUM) {
                long sum = 0L;
                for(int i = start; i < end; i++) {
                    sum += nums[i];
                }
                return sum;
            }
            int middle = start + (end - start ) /2;
            AddTask sub1 =  new AddTask(start, middle);
            AddTask sub2 = new AddTask(middle, end);
            sub1.fork();
            sub2.fork();
            return sub1.join() + sub2.join();
        }
    }

    public static void main(String[] args) throws IOException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        AddTask addTask = new AddTask(0, nums.length);
        forkJoinPool.execute(addTask);
        long result = addTask.join();
        System.out.println(result);
        System.in.read();
    }

}
