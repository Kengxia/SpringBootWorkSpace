package com.my.work.shop.service;


import com.my.framework.utils.PageUtil;
import com.my.work.shop.pojo.ShopCar;

import java.util.List;
import java.util.Map;

/**
 * @author 华安  mashuai_bj@si-tech.com.cn
 * @Title:
 * @Date: Create in 10:53 2018/4/18
 * @Description:
 */
public interface ShopCarService {

    String insertReord(ShopCar shopCar);

    List<ShopCar> findByProductId(String productId);

    PageUtil<Map> findPageList(Map<String, Object> paramMap);
} 