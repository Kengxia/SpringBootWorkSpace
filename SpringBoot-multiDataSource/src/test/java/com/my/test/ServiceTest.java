package com.my.test;

import com.my.MultiBootApplication;
import com.my.work.service.ITestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author 华安  mashuai_bj@si-tech.com.cn
 * @Title:
 * @Date: Create in 10:31 2018/7/25
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MultiBootApplication.class)
@EnableAutoConfiguration
public class ServiceTest {

    @Resource
    ITestService service;
    @Test
    public void test(){
        service.test("1");
    }



} 