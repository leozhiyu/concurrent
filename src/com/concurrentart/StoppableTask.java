/**
 * @author:Leo
 * @create 2018/6/3
 * @desc
 */
package com.concurrentart;

import java.util.concurrent.locks.ReentrantLock;

public class StoppableTask extends Thread{
    private volatile boolean pleaseStop;

    @Override
    public void run() {
        int i = 0;
        while (!pleaseStop) {
            System.out.println("hello" + i++);
        }
    }

    public void tellmeStop() {
        pleaseStop = true;
    }

    public static void main(String[] args) throws InterruptedException {
        StoppableTask stoppableTask = new StoppableTask();
        stoppableTask.start();
        Thread.sleep(100);
        stoppableTask.tellmeStop();
    }
}
