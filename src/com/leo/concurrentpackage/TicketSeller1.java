/**
 * @author:Leo
 * @create 2018/6/5
 * @desc
 */
package com.leo.concurrentpackage;

import java.util.LinkedList;
import java.util.List;

public class TicketSeller1 {
    static List<String> list = new LinkedList<>();

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
                        System.out.println(list.remove(0));
                    }
                }
            }).start();
        }
    }
}
