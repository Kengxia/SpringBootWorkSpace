package com.my.work.service.impl;

import com.alibaba.druid.pool.xa.DruidXADataSource;
import com.my.framework.base.service.BaseService;
import com.my.work.repositories.primary.ShopCarRepository;
import com.my.work.repositories.primary.pojo.ShopCar;
import com.my.work.repositories.second.OrderRepository;
import com.my.work.repositories.second.pojo.Order;
import com.my.work.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 华安  mashuai_bj@si-tech.com.cn
 * @Title:
 * @Date: Create in 10:33 2018/7/25
 * @Description:
 */
@Service
@Transactional
public class TestServiceImpl extends BaseService implements ITestService {
    @Autowired
    ShopCarRepository shopCarRepository;

    @Autowired
    OrderRepository orderRepository;

    @Override
    public void test(String id) {
        new DruidXADataSource();
        shopCarRepository.save(new ShopCar(id, "1", "1"));
        orderRepository.save(new Order(id, "1", "1"));
    }
} 