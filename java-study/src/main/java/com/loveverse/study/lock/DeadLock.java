package com.loveverse.study.lock;

/**
 * @author love
 * @since 2025/5/24 10:09
 */
public class DeadLock {
    private static final Object resource1 = new Object();
    private static final Object resource2 = new Object();

    public static void main(String[] args) {

        new Thread(() -> {
            synchronized (resource2) {
                System.out.println(Thread.currentThread() + "获取资源2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "等待获取资源1");
                synchronized (resource1) {
                    System.out.println(Thread.currentThread() + "获取资源1");
                }
            }
        }, "线程1").start();
        new Thread(() -> {
            synchronized (resource1) {
                System.out.println(Thread.currentThread() + "获取资源1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "等待获取资源2");
                synchronized (resource2) {
                    System.out.println(Thread.currentThread() + "获取资源2");
                }
            }
        }, "线程2").start();


    }
}
