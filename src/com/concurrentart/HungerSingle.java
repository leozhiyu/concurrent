/**
 * @author:Leo
 * @create 2018/6/3
 * @desc
 */
package com.concurrentart;

public class HungerSingle {

    private static final Instance instance = new Instance();

    private HungerSingle(){

    }

    public static Instance getInstance() {
        return instance;
    }
}
