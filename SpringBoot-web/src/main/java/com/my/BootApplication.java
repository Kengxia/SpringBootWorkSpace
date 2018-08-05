package com.my;

import com.my.framework.config.WebMVCConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 华安  mashuai_bj@si-tech.com.cn
 * @Title:
 * @Date: Create in 10:17 2018/4/17
 * @Description:
 */
@SpringBootApplication
@EnableWebMvc
@ImportResource({"classpath:conf/spring-dubbo.xml"})
@EnableTransactionManagement
@EnableSwagger2
public class BootApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
    }
} 