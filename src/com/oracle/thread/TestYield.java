/**
 * @author:Leo
 * @create 2018/6/1
 * @desc
 */
package com.oracle.thread;

public class TestYield extends Thread {
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(currentThread() + " : i =" + i);
            if( i == 5 ){
                Thread.yield(); // 当 i = 5 的时候让位
                System.out.println( currentThread() + " 让位");
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        TestYield t1 = new TestYield();

        TestYield t2 = new TestYield();
        TestYield t3 = new TestYield();
        t3.setPriority(MAX_PRIORITY);
        t2.setPriority(MAX_PRIORITY-2);
        t1.start();
        t2.start();
        t3.sleep(10000);
        t3.start();

    }
}
