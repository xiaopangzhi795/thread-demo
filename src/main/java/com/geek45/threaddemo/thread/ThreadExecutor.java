/**
 * From geek45.com
 * Email to : rubixgeek795@gmail.com
 */
package com.geek45.threaddemo.thread;

import com.geek45.threaddemo.thread.config.ThreadPoolConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ClassName: ThreadExecutor
 * @Decription: 线程执行器
 * @Author: rubik
 *  rubik create ThreadExecutor.java of 2022/1/20 11:03 下午
 */
@Component
public class ThreadExecutor implements CommandLineRunner {

    private ThreadPoolExecutor pool = null;

    private ThreadPoolConfiguration poolConfiguration;

    @Override
    public void run(String... args) throws Exception {
        pool = ThreadMonitor.initPool(poolConfiguration);
    }

    @Autowired
    public void init(ThreadPoolConfiguration threadPoolConfiguration) {
        poolConfiguration = threadPoolConfiguration;
    }

    public void executor(GeekTaskRunnable task) {
        pool.execute(task);
    }

}
