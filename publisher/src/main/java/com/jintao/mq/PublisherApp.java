package com.jintao.mq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PublisherApp {
    public static void main(String[] args) {
        SpringApplication.run(PublisherApp.class,args);

        System.out.println("运行成功");
    }
}
