/**
 * From geek45.com
 * Email to : rubixgeek795@gmail.com
 */
package com.geek45.threaddemo.thread.strategy.impl;

import com.geek45.threaddemo.thread.config.ThreadPoolConfiguration;
import com.geek45.threaddemo.thread.enums.GeneratorNameStrategy;
import com.geek45.threaddemo.thread.strategy.GeneratorThreadNameStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @ClassName: GeneratorKeyAndIdName
 * @Decription: 生成key+自增id的线程名
 * @Author: rubik
 *  rubik create GeneratorKeyAndIdName.java of 2022/1/23 11:26 上午
 */
@Component
public class GeneratorKeyAndIdName implements GeneratorThreadNameStrategy {

    private ThreadPoolConfiguration threadPoolConfiguration;
    private AtomicLong num = new AtomicLong(0L);

    @Override
    public Boolean matchType(String type) {
        return GeneratorNameStrategy.KEY_AND_ID.name().equalsIgnoreCase(type);
    }

    @Override
    public String generatorName() {
        return getKey() + "-" + num.getAndIncrement();
    }

    private String getKey() {
        return threadPoolConfiguration.getKey();
    }

    @Override
    public Boolean isReady() {
        return null != getKey();
    }

    @Autowired
    public void init(ThreadPoolConfiguration threadPoolConfiguration) {
        this.threadPoolConfiguration = threadPoolConfiguration;
    }

}
