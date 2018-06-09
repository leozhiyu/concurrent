/**
 * @author:Leo
 * @create 2018/6/1
 * @desc
 */
package com.oracle.thread;

public class DaemonThread extends Thread {
    @Override
    public void run() {
        while (true) {
            System.out.println("风吹鸡蛋壳");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DaemonThread daemonThread = new DaemonThread();
        daemonThread.setDaemon(true);
        System.out.println("是守护线程吗？" + daemonThread.isDaemon());
        daemonThread.start();

        Thread.sleep(10);
        Thread main = Thread.currentThread();
        System.out.println(main.getName() + "是守护线程吗？" + main.isDaemon());
    }
}
