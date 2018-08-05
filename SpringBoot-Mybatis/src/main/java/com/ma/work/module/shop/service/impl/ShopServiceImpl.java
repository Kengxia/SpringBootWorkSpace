package com.ma.work.module.shop.service.impl;

import com.ma.work.mapper.ShopCarTestMapper;
import com.ma.work.mapper.TestMapper;
import com.ma.work.module.shop.service.ShopService;
import com.ma.work.pojo.ShopCarTest;
import com.ma.work.pojo.Test;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 华安  mashuai_bj@si-tech.com.cn
 * @Title:
 * @Date: Create in 10:29 2018/4/17
 * @Description:
 */
@Service
@Transactional
public class ShopServiceImpl implements ShopService {
    @Resource
    private ShopCarTestMapper shopCarTestMapper;
    @Resource
    TestMapper testMapper;

    @Override
    public List<ShopCarTest> list() {
        return shopCarTestMapper.selecList();
    }

    @Override
    public int insertShop(ShopCarTest test) {
        shopCarTestMapper.insert(test);
        Test t = new Test();
        t.setName("11111111111111");

        return testMapper.insert(t);
    }
}