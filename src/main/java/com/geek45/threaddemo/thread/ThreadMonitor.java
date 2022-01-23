/**
 * From geek45.com
 * Email to : rubixgeek795@gmail.com
 */
package com.geek45.threaddemo.thread;

import com.geek45.threaddemo.thread.config.ThreadPoolConfiguration;
import com.geek45.threaddemo.thread.strategy.GeneratorNameFactory;
import com.geek45.threaddemo.thread.strategy.GeneratorThreadNameStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @ClassName: ThreadMonitor
 * @Decription: 线程监控
 * @Author: rubik
 *  rubik create ThreadMonitor.java of 2022/1/20 11:08 下午
 */
public class  ThreadMonitor {
    private static final Logger logger = LoggerFactory.getLogger(ThreadMonitor.class);

    private static Map<String, Object> map = new ConcurrentHashMap<>(16);

    private static ThreadPoolExecutor poolExecutor = null;

    public static synchronized ThreadPoolExecutor initPool(ThreadPoolConfiguration configuration) {
        if (null != poolExecutor) {
            return poolExecutor;
        }
        GeneratorThreadNameStrategy generatorThreadNameStrategy = GeneratorNameFactory.INSTANCE.getStrategyByType(configuration.getGeneratorNameType());
        poolExecutor = new ThreadPoolExecutor(configuration.getCoreSize(),
                configuration.getMaxSize(),
                configuration.getKeepAliveTime(),
                TimeUnit.valueOf(configuration.getTimeUnit()),
                new LinkedBlockingDeque<>(configuration.getTaskMaxCount()),
                new GeekThreadFactory());
        return poolExecutor;
    }

    public static void addThread(String threadName, Object data) {
        logger.info("【{}】增加线程开始执行任务", threadName);
        logger.info("当前线程是；{}", Thread.currentThread().getName());
        map.put(threadName, data);
    }

    public static void removeThread(String threadName) {
        logger.info("【{}】线程结束执行", threadName);
        logger.info("当前线程是；{}", Thread.currentThread().getName());
        map.remove(threadName);
    }

    public static void printThreadInfo() {
        logger.info("当前线程池状态：线程数量：{}.监控线程数:{}.活跃线程数量：{}.最大线程数量：{}.任务数量：{}.配置核心线程阈值：{}.配置线程数量阈值：{}.",
                poolExecutor.getPoolSize(),
                map.size(),
                poolExecutor.getActiveCount(),
                poolExecutor.getLargestPoolSize(),
                poolExecutor.getTaskCount(),
                poolExecutor.getCorePoolSize(),
                poolExecutor.getMaximumPoolSize());

    }

    private static AtomicBoolean isMonitor = new AtomicBoolean(Boolean.FALSE);

    /**
     * //TODO 开始执行监控，考虑单独打印日志，由配置触发，可手动结束
     * 开始监控
     * @param millis 监控间隔时间
     */
    public static void monitor(long millis) {
        boolean isAction = isMonitor.compareAndSet(Boolean.FALSE, Boolean.TRUE);
        if (!isAction) {
            logger.info("监控已经是启动状态");
            return;
        }

        poolExecutor.execute(() -> {
            String name = Thread.currentThread().getName();
            addThread(name, Thread.currentThread());
            while (isMonitor.get()) {
                printThreadInfo();
                sleep(millis);
            }
            removeThread(name);
        });
    }

    /**
     * 休眠
     * @param millis 指定休眠多久
     */
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            logger.error("休眠异常...", e);
        }
    }

    public static void stopMonitor() {
        isMonitor.compareAndSet(Boolean.TRUE, Boolean.FALSE);
        logger.info("结束监控...");
    }

}
