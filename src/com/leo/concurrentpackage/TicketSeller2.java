/**
 * @author:Leo
 * @create 2018/6/5
 * @desc
 */
package com.leo.concurrentpackage;

import java.util.Vector;

public class TicketSeller2 {
    static Vector<String> list = new Vector<>();

    static {
        for (int i = 0; i < 10000; i++) {
            list.add("票号：" + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (list.size() > 0) {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(list.remove(0));
                    }
                }
            }).start();
        }
    }
}
