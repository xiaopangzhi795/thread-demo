/**
 * From geek45.com
 * Email to : rubixgeek795@gmail.com
 */
package com.geek45.threaddemo.thread.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName: GeneratorNameFacory
 * @Decription: 生成线程名字工厂
 * @Author: rubik
 *  rubik create GeneratorNameFacory.java of 2022/1/23 11:29 上午
 */
@Component
public class GeneratorNameFactory {
    private List<GeneratorThreadNameStrategy> generatorThreadNameStrategies;

    public static GeneratorNameFactory INSTANCE = new GeneratorNameFactory();

    /**
     * 获取
     * @param type
     * @return
     */
    public GeneratorThreadNameStrategy getStrategyByType(String type) {
        if (null == generatorThreadNameStrategies) {
            throw new RuntimeException("没有该类型的实现");
        }
        GeneratorThreadNameStrategy generatorThreadNameStrategy = generatorThreadNameStrategies.stream().filter(x -> x.matchType(type)).findAny().orElseGet(() -> {
            throw new RuntimeException("没有该类型的实现");
        });
        return generatorThreadNameStrategy;
    }

    private GeneratorNameFactory() {

    }

    public static GeneratorNameFactory getInstance() {
        return INSTANCE;
    }

    @Autowired
    public void init(List<GeneratorThreadNameStrategy> generatorThreadNameStrategies) {
        INSTANCE.generatorThreadNameStrategies = generatorThreadNameStrategies;
    }

}
