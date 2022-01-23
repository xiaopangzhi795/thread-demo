/**
 * From geek45.com
 * Email to : rubixgeek795@gmail.com
 */
package com.geek45.threaddemo.thread.enums;

/**
 * @ClassName: GeneratorNameStrategy
 * @Decription: 生成线程名字使用的策略
 * @Author: rubik
 * rubik create GeneratorNameStrategy.java of 2022/1/23 11:15 上午
 */
public enum GeneratorNameStrategy {

    /**
     * 32位UID组成
     */
    LONG_UID,
    /**
     * 16位UID组成
     */
    SHORT_UID,
    /**
     * KEY + 16位UID组成
     */
    KEY_AND_UID,
    /**
     * key + 自增id组成
     */
    KEY_AND_ID,
    ;
}
