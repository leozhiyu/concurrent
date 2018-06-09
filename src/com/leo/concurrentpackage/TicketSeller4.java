/**
 * @author:Leo
 * @create 2018/6/5
 * @desc
 */
package com.leo.concurrentpackage;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;

public class TicketSeller4 {
    static Queue<String> queue = new ConcurrentLinkedQueue<>();

    static {
        for (int i = 0; i < 10000; i++) {
            queue.add("票号：" + i);
        }
    }

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        String s = queue.poll();
                        if(s == null) {
                            break;
                        }else {
                            System.out.println(s);
                        }
                    }
                }
            }).start();
        }
    }
}
