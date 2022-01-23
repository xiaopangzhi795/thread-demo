package com.geek45.threaddemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.geek45")
public class ThreadDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThreadDemoApplication.class, args);
    }

}
