/**
 * @author:Leo
 * @create 2018/6/4
 * @desc
 * 写一个固定容量同步容器，拥有 put 和 get 方法，以及 getCount 方法。能够支持 2 个生产者线程以及 10 个消费者线程的阻塞调用
 */
package com.leo.container;

import java.util.LinkedList;

public class Container1 {
    final private LinkedList<Object> list = new LinkedList<>();

    final private  int MAX = 10;

    private  int count = 0;

    public synchronized void put(Object o) {
        // 注意此处一定要用 while 而不能用 if
        // 否则，当生存者被唤醒时，一个线程刚唤醒，另一个线程却执行完了，又满了。
        // 如果是 if，唤醒之后不会再判断，
        // 如果是 while，则还会判断一次
        while (list.size() == MAX) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        list.add(o);
        ++count;
        this.notifyAll();  // 通知消费者进行消费
    }

    public synchronized Object get() {
        Object o = null;
        while (list.size() == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        o = list.removeFirst();
        count--;
        this.notifyAll();  // 通知生产者进行生产
        return o;
    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args) {
        Container1 container1 = new Container1();

        for (int i = 0; i <10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 5; i++) {
                        System.out.println(container1.get());
                    }
                }
            },"c" + i).start();
        }

        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 25; i++) {
                        container1.put(Thread.currentThread().getName() + " " + i);
                    }
                }
            },"p " + i).start();
        }
    }
}