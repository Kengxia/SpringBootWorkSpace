package com.ma.work.module.shop.controller;

import com.ma.work.module.shop.service.ShopService;
import com.ma.work.pojo.ShopCarTest;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author 华安  mashuai_bj@si-tech.com.cn
 * @Title:
 * @Date: Create in 10:19 2018/4/17
 * @Description:
 */
@Controller
@RequestMapping("/shop")
public class ShopController {

    @Resource
    ShopService shopService;

    @RequestMapping("/list")
    @ResponseBody
    public Object getShopList() {
        ShopCarTest test = new ShopCarTest();
        test.setBuyer("buyer");
        test.setProductId("11");
        return shopService.insertShop(test);
    }
} 