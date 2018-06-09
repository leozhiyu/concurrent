/**
 * @author:Leo
 * @create 2018/6/4
 * @desc
 */
package com.leo.container;

import java.util.LinkedList;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Container2 {
    private LinkedList<Object> list = new LinkedList<>();

    private Lock lock = new ReentrantLock();

    private Condition producer = lock.newCondition();
    private Condition consumer = lock.newCondition();

    public void put(Object o) {

        try {
            lock.lock();
            while (list.size() == 10) {
                producer.await();
            }
            list.add(o);
            consumer.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public synchronized Object get(){
        Object o = null;
        try {
            lock.lock();
            while (list.size() == 0) {
                consumer.await();
            }
            o = list.removeFirst();
            producer.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return o;
    }

    public static void main(String[] args) {
        Container2 container2 = new Container2();
        // 创建消费者线程
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 5; i++) {
                        System.out.println(container2.get());
                    }
                }
            }).start();
        }


        // 创建生产者线程
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 25; j++) {
                        container2.put(Thread.currentThread().getName() +  " " + j);
                    }
                }
            },"p" + i).start();
        }
    }
}
