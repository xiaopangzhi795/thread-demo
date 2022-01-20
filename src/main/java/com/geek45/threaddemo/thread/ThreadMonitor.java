/**
 * From geek45.com
 * Email to : rubixgeek795@gmail.com
 */
package com.geek45.threaddemo.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: ThreadMonitor
 * @Decription: 线程监控
 * @Author: rubik
 *  rubik create ThreadMonitor.java of 2022/1/20 11:08 下午
 */
public class ThreadMonitor {
    private static final Logger logger = LoggerFactory.getLogger(ThreadMonitor.class);

    private static Map<String, Object> map = new ConcurrentHashMap<>(16);

    private static ThreadPoolExecutor poolExecutor = null;

    public static synchronized ThreadPoolExecutor initPool() {
        if (null != poolExecutor) {
            return poolExecutor;
        }
        poolExecutor = new ThreadPoolExecutor(8, 128, 0, TimeUnit.SECONDS, new LinkedBlockingDeque<>(2048), new GeekThreadFactory());
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
        //TODO 打印当前线程池状态
        logger.info("当前线程池状态：线程数量：{}.活跃线程数量：{}.最大线程数量：{}.任务数量：{}.配置核心线程阈值：{}.配置线程数量阈值：{}.",
                poolExecutor.getPoolSize(),
                poolExecutor.getActiveCount(),
                poolExecutor.getLargestPoolSize(),
                poolExecutor.getTaskCount(),
                poolExecutor.getCorePoolSize(),
                poolExecutor.getMaximumPoolSize());

    }

    private static Boolean isMonitor;

    public static void monitor() {
        isMonitor = true;
        //TODO 开始执行监控，考虑单独打印日志，由配置触发，可手动结束
        poolExecutor.execute(()->{
            String name = Thread.currentThread().getName();
            addThread(name, Thread.currentThread());
            while (isMonitor) {
                printThreadInfo();
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    logger.error("sleep exception...", e);
                }
            }
            removeThread(name);
        });
    }

    public static void stopMonitor() {
        //TODO 结束监控
        logger.info("结束监控...");
        isMonitor = false;
    }

}
