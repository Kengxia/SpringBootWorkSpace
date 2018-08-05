package com.ma.work.shop.service.impl;

import com.ma.framework.base.service.impl.BaseServiceImpl;
import com.ma.framework.util.PageUtil;
import com.ma.work.shop.dao.ShopCarRepository;
import com.ma.work.shop.pojo.ShopCar;
import com.ma.work.shop.service.ShopCarService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author 华安  mashuai_bj@si-tech.com.cn
 * @Title:
 * @Date: Create in 10:53 2018/4/18
 * @Description:
 */
@Service("shopCarService")
@Transactional
public class ShopCarServiceImpl extends BaseServiceImpl implements ShopCarService{

    @PersistenceContext
    protected EntityManager em;

    @Autowired
    ShopCarRepository shopCarRepository;

    @Override
    public String insertReord(ShopCar shopCar) {
        ShopCar car = shopCarRepository.save(shopCar);
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