/**
 * From geek45.com
 * Email to : rubixgeek795@gmail.com
 */
package com.geek45.threaddemo.thread.strategy;

/**
 * @ClassName: GeneratorThreadNameStrategy
 * @Decription: 生成线程名策略
 * @Author: rubik
 * rubik create GeneratorThreadNameStrategy.java of 2022/1/23 11:23 上午
 */
public interface GeneratorThreadNameStrategy {

    /**
     * 匹配策略类型
     * @param type 忽略大小写
     * @return
     */
    Boolean matchType(String type);

    /**
     * 生成名字
     * @return
     */
    String generatorName();

    /**
     * 是否准备就绪
     * @return
     */
    default Boolean isReady(){
        return Boolean.TRUE;
    }

}
