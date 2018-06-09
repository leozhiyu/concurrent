/**
 * @author:Leo
 * @create 2018/6/1
 * @desc
 */
package com.oracle.thread;

public class TestJoin extends Thread {
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println( this.getName() + " : " + i);
        }
        System.out.println("tj end");
    }
    public static void main(String[] args) {
        TestJoin tj = new TestJoin();
        tj.start();
        System.out.println("before join ");
        try {
            System.out.println(currentThread());
            currentThread().join(); // 主线程调用 tj 的 join 方法
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main end");
    }
}
