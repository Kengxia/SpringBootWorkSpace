package com.ma.work.module.shop.service;

import com.ma.work.pojo.ShopCarTest;

import java.util.List;

/**
 * @author 华安  mashuai_bj@si-tech.com.cn
 * @Title:
 * @Date: Create in 10:27 2018/4/17
 * @Description:
 */
public interface ShopService {

    List<ShopCarTest> list();

    int insertShop(ShopCarTest test);
} 