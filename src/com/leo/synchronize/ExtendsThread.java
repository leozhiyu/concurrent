/**
 * @author:Leo
 * @create 2018/6/3
 * @desc
 */
package com.leo.synchronize;

public class ExtendsThread {
    synchronized void method() {
        System.out.println("hello");
    }

    public static void main(String[] args) {
        new Son().method();
    }
}

class Son extends ExtendsThread {
    @Override
    public synchronized void method() {
        System.out.println("son");
        super.method();
    }
}
