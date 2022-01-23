/**
 * From geek45.com
 * Email to : rubixgeek795@gmail.com
 */
package com.geek45.threaddemo.thread.strategy.impl;

import com.geek45.threaddemo.thread.enums.GeneratorNameStrategy;
import com.geek45.threaddemo.thread.strategy.GeneratorThreadNameStrategy;
import com.geek45.threaddemo.thread.util.UUIDUtil;
import org.springframework.stereotype.Component;

/**
 * @ClassName: GeneratorShortUidName
 * @Decription: 生成16位uid类型的线程名
 * @Author: rubik
 *  rubik create GeneratorShortUidName.java of 2022/1/23 11:26 上午
 */
@Component
public class GeneratorShortUidName implements GeneratorThreadNameStrategy {

    @Override
    public Boolean matchType(String type) {
        return GeneratorNameStrategy.SHORT_UID.name().equalsIgnoreCase(type);
    }

    @Override
    public String generatorName() {
        return UUIDUtil.uuidStrShort();
    }

}
