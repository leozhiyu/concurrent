/**
 * @author:Leo
 * @create 2018/6/8
 * @desc
 */
package com.concurrentart.chapter6;

import java.util.HashMap;
import java.util.UUID;

public class HashMapTest {
    static final HashMap<String,String> map = new HashMap<>(2);
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            map.put(UUID.randomUUID().toString(),"");
                        }
                    }, "ftf" + i).start();
                }
            }
        },"ftf");
        t.start();
        t.join();
    }
}
