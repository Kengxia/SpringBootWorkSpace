package com.ma.work.shop.service;

import com.ma.framework.util.PageUtil;
import com.ma.work.shop.pojo.ShopCar;
import org.springframework.data.domain.Page;

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

    PageUtil<Map> findPageList(Map<String,Object> paramMap);
} 