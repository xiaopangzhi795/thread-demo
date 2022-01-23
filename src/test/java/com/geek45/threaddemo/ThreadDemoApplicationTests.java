package com.geek45.threaddemo;

import com.geek45.threaddemo.thread.ThreadExecutor;
import com.geek45.threaddemo.thread.ThreadMonitor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ThreadDemoApplicationTests {

    @Autowired
    private ThreadExecutor executor;

    @Test
    void contextLoads() throws InterruptedException {
        ThreadMonitor.monitor(3000L);
        executor.executor(()->{
            System.err.println("你好你好你好你好");
        });

        executor.executor(()->{
            throw new RuntimeException("异常异常异常");
        });

        Thread.sleep(20000L);
        ThreadMonitor.stopMonitor();
        Thread.sleep(10000L);
    }

}
