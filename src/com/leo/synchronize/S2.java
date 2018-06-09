/**
 * @author:Leo
 * @create 2018/6/1
 * @desc
 */
package com.leo.synchronize;

public class S2 {
    private static int count = 0;

    public synchronized static void method() {
        System.out.println("count : " + count);
    }

    // 方法一等价于这样，因为静态方法没有 this，拿到的是 class 对象
    public static void method2() {
        synchronized (S2.class) {
            System.out.println("count : " + count);
        }
    }
}
