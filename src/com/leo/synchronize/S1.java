/**
 * @author:Leo
 * @create 2018/6/1
 * @desc
 */
package com.leo.synchronize;

public class S1 {
    private int count = 0;

    private Object object = new Object();

    // 用新建的一个对象作为锁
    public void method() {
        synchronized (object) {
            System.out.println("count : " + ++count);
        }
    }

    // 方法二与方法三是等价的
    public synchronized void method2() {
        System.out.println("count : " + ++count);
    }

    public void method3() {
        synchronized (this) {
            System.out.println("count : " + ++count);
        }
    }

    public static void main(String[] args) {
        S1 s = new S1();
        s.method();
        s.method2();
        s.method3();
    }
}
