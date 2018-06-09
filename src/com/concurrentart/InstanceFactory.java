/**
 * @author:Leo
 * @create 2018/6/3
 * @desc
 */
package com.concurrentart;

public class InstanceFactory {

    private static class InstanceHolder {
        public static Instance instance = new Instance();
    }

    public Instance getInstance() {
        return InstanceHolder.instance;
    }
}
