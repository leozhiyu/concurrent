/**
 * @author:Leo
 * @create 2018/6/9
 * @desc
 */
package com.concurrentart.chapter5;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ProcessData {
    private static volatile boolean update = false;
    private static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    private static Lock w = rwl.writeLock();
    private static Lock r = rwl.readLock();
    public void processData() {
        r.lock();
        if (!update) {
            r.unlock();
            w.lock();
            try{
                if (!update) {
                    update = true;
                }
                r.lock();
            } finally {
                w.unlock();
            }
        }
        try{

        }finally {
            r.unlock();
        }
    }
    public static void main(String[] args) {
        Condition c = w.newCondition();
    }
}
