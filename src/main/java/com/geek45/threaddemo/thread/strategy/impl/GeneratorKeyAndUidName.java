/**
 * From geek45.com
 * Email to : rubixgeek795@gmail.com
 */
package com.geek45.threaddemo.thread.strategy.impl;

import com.geek45.threaddemo.thread.config.ThreadPoolConfiguration;
import com.geek45.threaddemo.thread.enums.GeneratorNameStrategy;
import com.geek45.threaddemo.thread.strategy.GeneratorThreadNameStrategy;
import com.geek45.threaddemo.thread.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName: GeneratorKeyAndUidName
 * @Decription: 生成key+16位uid类型的线程名
 * @Author: rubik
 *  rubik create GeneratorKeyAndUidName.java of 2022/1/23 11:26 上午
 */
@Component
public class GeneratorKeyAndUidName implements GeneratorThreadNameStrategy {

    private ThreadPoolConfiguration threadPoolConfiguration;

    @Override
    public Boolean matchType(String type) {
        return GeneratorNameStrategy.KEY_AND_UID.name().equalsIgnoreCase(type);
    }

    @Override
    public String generatorName() {
        return getKey() + "-" + UUIDUtil.uuidStrShort();
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
