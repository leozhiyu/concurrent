/**
 * @author:Leo
 * @create 2018/6/1
 * @desc
 */
package com.leo.synchronize;

public class S3 implements Runnable{
    private int count = 10;

    @Override
    public synchronized void run() {
        System.out.println(Thread.currentThread().getName() + " : " + --count);
    }

    public static void main(String[] args) {
        S3 s = new S3();
        for (int i = 0; i < 5; i++) {
            //S3 s = new S3(); 这种方式是创建了不同的对象，互不影响，输出的结果都是9
            new Thread(s,"s" + i).start();
        }
    }
}
