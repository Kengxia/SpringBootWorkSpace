package com.my.work.shop.service.impl;


import ch.qos.logback.core.db.dialect.DBUtil;
import com.my.framework.base.service.impl.BaseServiceImpl;
import com.my.framework.utils.IDUtils;
import com.my.framework.utils.PageUtil;
import com.my.work.home.dao.OrderRepository;
import com.my.work.home.pojo.Order;
import com.my.work.shop.dao.ShopCarRepository;
import com.my.work.shop.pojo.ShopCar;
import com.my.work.shop.service.ShopCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;

/**
 * @author 华安  mashuai_bj@si-tech.com.cn
 * @Title:
 * @Date: Create in 10:53 2018/4/18
 * @Description:
 */
@Service("shopCarService")
@Transactional(rollbackFor = Exception.class)
public class ShopCarServiceImpl extends BaseServiceImpl implements ShopCarService {



    @Autowired
    ShopCarRepository shopCarRepository;
    @Autowired
    OrderRepository orderRepository;

    @Override
    public String insertReord(ShopCar shopCar) {
        ShopCar car = shopCarRepository.save(shopCar);
        orderRepository.save(new Order(IDUtils.createID(),"1","admin"));
        return car.getId();
    }

    @Override
    public PageUtil<Map> findPageList(Map<String,Object> paramMap) {
        StringBuilder sbd = new StringBuilder();
        sbd.append("select id from shop_car_test where product_id=:productId");
        return baseDao.findPageList(sbd.toString(),1,10,paramMap);
    }

    @Override
    public List<ShopCar> findByProductId(String productId) {
        return shopCarRepository.findByProductId(productId);
    }
}