/**
 * @author:Leo
 * @create 2018/6/3
 * @desc
 */
package com.concurrentart;

public class SafeLazyInitialization {
    private static Instance instance;
    public synchronized static Instance getInstance() {
        if (instance == null) {
            instance = new Instance();
        }
        return instance;
    }
}
