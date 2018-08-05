package com.my.work.controller;

import com.my.work.service.ITestService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author 华安  mashuai_bj@si-tech.com.cn
 * @Title:
 * @Date: Create in 14:17 2018/7/25
 * @Description:
 */
@Controller
@RequestMapping("/t")
public class TestController {
    @Resource
    ITestService testService;

    @RequestMapping("/test")
    public void test(HttpServletRequest request){
        ;
        testService.test(request.getParameter("id")+"");
    }
} 