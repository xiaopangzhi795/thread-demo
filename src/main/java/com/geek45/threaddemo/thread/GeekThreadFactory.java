/**
 * From geek45.com
 * Email to : rubixgeek795@gmail.com
 */
package com.geek45.threaddemo.thread;

import com.geek45.threaddemo.thread.util.UUIDUtil;

import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @ClassName: GeekThreadFactory
 * @Decription:
 * @Author: rubik
 *  rubik create GeekThreadFactory.java of 2022/1/20 11:07 下午
 */
public class GeekThreadFactory implements ThreadFactory {

    private String name;

    /**
     * 生成名字的策略,必选
     * UUID，key+uuid，key+数字
     */
    private String generatorNameType;

    /**
     * 是否启用守护线程
     * //TODO 异常处理handler，优先级..
     */
    private Boolean daemon;

    /**
     * 创建线程的工厂
     */
    private ThreadFactory threadFactory;

    public ThreadFactory getThreadFactory() {
        if (this.threadFactory == null) {
            this.threadFactory = Executors.defaultThreadFactory();
        }
        return this.threadFactory;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = getThreadFactory().newThread(r);
        String threadName = UUIDUtil.uuidStr();
        t.setName(threadName);
        t.setUncaughtExceptionHandler(new GeekUncaughtExceptionHandler());
        //TODO 创建线程时，将线程加入监控中
        //TODO 非手动控制的线程，当销毁时，如何控制从监控中拿走？

        return t;
    }
}
