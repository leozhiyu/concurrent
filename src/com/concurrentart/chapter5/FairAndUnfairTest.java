/**
 * @author:Leo
 * @create 2018/6/9
 * @desc
 */
package com.concurrentart.chapter5;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FairAndUnfairTest {

    private static Lock fairLock = new ReentrantLock2(true);
    private static Lock unfairLock = new ReentrantLock2(false);

    private static class ReentrantLock2 extends ReentrantLock {
        public ReentrantLock2(boolean fair) {
            super(fair);
        }
        public Collection<Thread> getQueuedThreads() {
            List<Thread> arrayList = new ArrayList<>(super.getQueuedThreads());
            Collections.reverse(arrayList);
            return arrayList;
        }
    }

    private static class Job extends Thread {
        private Lock lock;
        private Job(Lock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            System.out.println(lock);
        }
    }

    private static void testLock(Lock lock) {
        for (int i = 0; i < 5; i++) {
            new Job(lock).start();
        }
    }

    public static void main(String[] args) {
        testLock(fairLock);
        //testLock(unfairLock);
    }

}
