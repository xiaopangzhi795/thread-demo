/**
 * From geek45.com
 * Email to : rubixgeek795@gmail.com
 */
package com.geek45.threaddemo.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: ThreadCallback
 * @Decription:
 * @Author: rubik
 *  rubik create ThreadCallback.java of 2022/1/20 11:28 下午
 */
public class ThreadCallback {
    private static final Logger logger = LoggerFactory.getLogger(ThreadCallback.class);

    public static void callSuccess(Thread t) {
        logger.info("[{}]执行异常，异常信息是...执行成功");
        ThreadMonitor.removeThread(t.getName());
    }

    public static void callException(Thread t, Throwable e) {
        logger.error("[{}]执行异常，异常信息是...", t.getName(), e);
        ThreadMonitor.removeThread(t.getName());
    }
}
