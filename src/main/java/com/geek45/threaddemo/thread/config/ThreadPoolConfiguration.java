/**
 * From geek45.com
 * Email to : rubixgeek795@gmail.com
 */
package com.geek45.threaddemo.thread.config;

import com.geek45.threaddemo.thread.enums.GeneratorNameStrategy;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: ThreadPoolConfiguration
 * @Decription: 线程池配置类
 * @Author: rubik
 * rubik create ThreadPoolConfiguration.java of 2022/1/20 11:04 下午
 */
@ConfigurationProperties(prefix = "thread.config")
public class ThreadPoolConfiguration implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 核心线程数大小
     */
    private Integer coreSize;
    /**
     * 最大线程数
     */
    private Integer maxSize;
    /**
     * 闲置线程持续多久
     */
    private Long keepAliveTime;
    /**
     * 时间单位
     *
     * @see TimeUnit#name()
     */
    private String timeUnit;
    /**
     * 生成线程名时所使用的的key
     * 近当生成name类型使用到该值时才会生效
     *
     * @see GeneratorNameStrategy#name()
     */
    private String key;
    /**
     * 生成线程名的策略
     */
    private String generatorNameType;
    /**
     * 最大支持的任务数量
     */
    private Integer taskMaxCount;

    /**
     * 是否启用守护线程
     */
    private Boolean daemon;
    /**
     * 线程优先级
     */
    private Integer priority;

    /**
     * 是否监控
     */
    private Boolean monitor;

    /**
     * 监控间隔时间
     */
    private Long monitorMills;

    public Integer getCoreSize() {
        return coreSize;
    }

    public void setCoreSize(Integer coreSize) {
        this.coreSize = coreSize;
    }

    public Integer getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(Integer maxSize) {
        this.maxSize = maxSize;
    }

    public Long getKeepAliveTime() {
        return keepAliveTime;
    }

    public void setKeepAliveTime(Long keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
    }

    public String getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(String timeUnit) {
        this.timeUnit = timeUnit;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getGeneratorNameType() {
        return generatorNameType;
    }

    public void setGeneratorNameType(String generatorNameType) {
        this.generatorNameType = generatorNameType;
    }

    public Integer getTaskMaxCount() {
        return taskMaxCount;
    }

    public void setTaskMaxCount(Integer taskMaxCount) {
        this.taskMaxCount = taskMaxCount;
    }

    public Boolean getDaemon() {
        return daemon;
    }

    public void setDaemon(Boolean daemon) {
        this.daemon = daemon;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Boolean getMonitor() {
        return monitor;
    }

    public void setMonitor(Boolean monitor) {
        this.monitor = monitor;
    }

    public Long getMonitorMills() {
        return monitorMills;
    }

    public void setMonitorMills(Long monitorMills) {
        this.monitorMills = monitorMills;
    }

    @Override
    public String toString() {
        return "ThreadPoolConfiguration{" +
                "coreSize=" + coreSize +
                ", maxSize=" + maxSize +
                ", keepAliveTime=" + keepAliveTime +
                ", timeUnit='" + timeUnit + '\'' +
                ", key='" + key + '\'' +
                ", generatorNameType='" + generatorNameType + '\'' +
                ", taskMaxCount=" + taskMaxCount +
                ", daemon=" + daemon +
                ", priority=" + priority +
                ", monitor=" + monitor +
                ", monitorMills=" + monitorMills +
                '}';
    }
}
