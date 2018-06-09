/**
 * @author:Leo
 * @create 2018/6/1
 * @desc
 */
package com.oracle.thread;

public class TestSleep extends Thread{
    public void run() {
        try {
            Thread.sleep(6000);
            System.out.println("呵呵，我睡醒了！！！");
        } catch (InterruptedException e) {
            System.out.println("谁吵醒我跟谁急！！！");
            e.printStackTrace();
        }
        System.out.println("run 结束了!");
    }
    public static void main(String[] args) {
        TestSleep ts = new TestSleep();
        ts.start();
        ts.interrupt(); // 中断 ts 的睡眠
    }
}
