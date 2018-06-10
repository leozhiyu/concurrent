/**
 * @author:Leo
 * @create 2018/6/9
 * @desc
 */
package com.concurrentart.chapter8;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest3 {
    static CyclicBarrier c = new CyclicBarrier(3);

    public static void main(String[] args) {
        Thread thread = new Thread(()->{
            try {
                c.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        thread.interrupt();
        try {
            c.await();
        } catch (InterruptedException e) {
            System.out.println(c.isBroken());
        } catch (BrokenBarrierException e) {
            System.out.println(c.isBroken());
        }
    }
}
