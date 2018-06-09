/**
 * @author:Leo
 * @create 2018/6/3
 * @desc
 */
package com.concurrentart;

public class DoubleCheckedLocking {
    private static Instance instance;
    public static Instance getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckedLocking.class) {
                if (instance == null) {
                    instance = new Instance();
                }
            }

        }
        return instance;
    }
}
