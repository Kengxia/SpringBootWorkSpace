package com.my;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;


/**
 * @author 华安  mashuai_bj@si-tech.com.cn
 * @Title:
 * @Date: Create in 10:17 2018/4/17
 * @Description:
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.my")
public class MultiBootApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(MultiBootApplication.class, args);
    }
}