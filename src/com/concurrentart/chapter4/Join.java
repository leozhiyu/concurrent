/**
 * @author:Leo
 * @create 2018/6/7
 * @desc
 */
package com.concurrentart.chapter4;

public class Join {
    public static void main(String[] args) {
        Thread pre = Thread.currentThread();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Domino(pre), String.valueOf(i));
            thread.start();
            pre = thread;
        }
    }

    static class Domino implements Runnable {
        private Thread thread;

        public Domino(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " terminate.");
        }
    }
}
