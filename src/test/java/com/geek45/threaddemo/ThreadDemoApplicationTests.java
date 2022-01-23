package com.geek45.threaddemo;

import com.geek45.threaddemo.thread.ThreadExecutor;
import com.geek45.threaddemo.thread.ThreadMonitor;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@SpringBootTest(classes = ThreadDemoApplication.class)
class ThreadDemoApplicationTests {
    private static final Logger logger = LoggerFactory.getLogger(ThreadDemoApplicationTests.class);

    @Autowired
    private ThreadExecutor executor;
    private static ReentrantLock LOCK = new ReentrantLock();
    private static Condition STOP = LOCK.newCondition();

    @Test
    void contextLoads() throws InterruptedException {
        ThreadMonitor.sleep(5000L);
        executor.executor(() -> {
            ThreadMonitor.sleep(3000L);
            System.err.println("你好你好你好你好");
        });
        executor.executor(() -> {
            ThreadMonitor.sleep(5000L);
            throw new RuntimeException("异常异常异常");
        });
        addHook();

        try {
            LOCK.lock();
            STOP.await();
        } catch (InterruptedException e) {
            logger.error("exception.. ", e);
        }finally {
            LOCK.unlock();
        }
    }

    public void addHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                ThreadMonitor.stopMonitor();
                LOCK.lock();
                STOP.signal();
            } finally {
                LOCK.unlock();
            }
        }));
    }

}
