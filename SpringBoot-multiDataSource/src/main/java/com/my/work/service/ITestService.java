package com.my.work.service;

import com.my.framework.base.service.BaseService;
import com.my.work.repositories.primary.ShopCarRepository;
import com.my.work.repositories.primary.pojo.ShopCar;
import com.my.work.repositories.second.OrderRepository;
import com.my.work.repositories.second.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author 华安  mashuai_bj@si-tech.com.cn
 * @Title:
 * @Date: Create in 10:26 2018/7/25
 * @Description:
 */

public interface ITestService {
    public void test(String id);
}