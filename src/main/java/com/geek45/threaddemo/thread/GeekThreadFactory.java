/**
 * From geek45.com
 * Email to : rubixgeek795@gmail.com
 */
package com.geek45.threaddemo.thread;

import com.geek45.threaddemo.thread.config.ThreadPoolConfiguration;
import com.geek45.threaddemo.thread.strategy.GeneratorNameFactory;
import com.geek45.threaddemo.thread.strategy.GeneratorThreadNameStrategy;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @ClassName: GeekThreadFactory
 * @Decription:
 * @Author: rubik
 *  rubik create GeekThreadFactory.java of 2022/1/20 11:07 下午
 */
public class GeekThreadFactory implements ThreadFactory {

    /**
     * 是否启用守护线程
     */
    private Boolean daemon;

    /**
     * 线程优先级
     */
    private Integer priority;

    /**
     * 线程异常处理器
     */
    private Thread.UncaughtExceptionHandler uncaughtExceptionHandler;

    /**
     * 创建线程的工厂
     */
    private ThreadFactory threadFactory;

    /**
     * 生成名字的策略
     */
    private GeneratorThreadNameStrategy generatorStrategy;

    public static <R extends DefaultThreadCallback> GeekThreadFactory create(ThreadPoolConfiguration configuration, GeekUncaughtExceptionHandler<R> handler, ThreadFactory factory) {
        GeekThreadFactory threadFactory = baseInit(configuration);

        //TODO 是否能配置化
        threadFactory.threadFactory = factory;
        threadFactory.uncaughtExceptionHandler = handler;
        return threadFactory;
    }

    public static <R extends DefaultThreadCallback> GeekThreadFactory create(ThreadPoolConfiguration configuration, GeekUncaughtExceptionHandler<R> handler) {
        GeekThreadFactory factory = baseInit(configuration);

        //TODO 是否能配置化
        factory.threadFactory = Executors.defaultThreadFactory();
        factory.uncaughtExceptionHandler = handler;
        return factory;
    }

    public static GeekThreadFactory create(ThreadPoolConfiguration configuration) {
        GeekThreadFactory factory = baseInit(configuration);

        //TODO 是否能配置化
        factory.threadFactory = Executors.defaultThreadFactory();
        factory.uncaughtExceptionHandler = new GeekUncaughtExceptionHandler();
        return factory;
    }

    private static GeekThreadFactory baseInit(ThreadPoolConfiguration configuration) {
        GeekThreadFactory factory = new GeekThreadFactory();
        if (null == configuration.getDaemon()) {
            configuration.setDaemon(Boolean.FALSE);
        }
        factory.daemon = configuration.getDaemon();
        if (null == configuration.getPriority()) {
            configuration.setPriority(Thread.NORM_PRIORITY);
        }
        factory.priority = configuration.getPriority();
        factory.generatorStrategy = GeneratorNameFactory.INSTANCE.getStrategyByType(configuration.getGeneratorNameType());
        return factory;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = threadFactory.newThread(r);
        String threadName = generatorStrategy.generatorName();
        t.setName(threadName);
        if (null != daemon) {
            t.setDaemon(daemon);
        }
        if (null != priority) {
            t.setPriority(priority);
        }
        if (null != uncaughtExceptionHandler) {
            t.setUncaughtExceptionHandler(uncaughtExceptionHandler);
        }

        return t;
    }
}
