/**
 * From geek45.com
 * Email to : rubixgeek795@gmail.com
 */
package com.geek45.threaddemo.thread;

/**
 * @ClassName: GeekTaskRunnable
 * @Decription: 任务接口
 * @Author: rubik
 * rubik create GeekTaskRunnable.java of 2022/1/20 11:07 下午
 */
public interface GeekTaskRunnable extends Runnable {

    @Override
    default void run(){
        ThreadMonitor.addThread(Thread.currentThread().getName(), Thread.currentThread());
        task();
        ThreadMonitor.removeThread(Thread.currentThread().getName());
    }

    void task();
}
