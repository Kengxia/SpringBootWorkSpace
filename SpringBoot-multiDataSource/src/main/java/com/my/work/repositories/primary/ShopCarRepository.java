package com.my.work.repositories.primary;

import com.my.work.repositories.primary.pojo.ShopCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 华安  mashuai_bj@si-tech.com.cn
 * @Title:
 * @Date: Create in 10:13 2018/4/18
 * @Description:
 */
public interface ShopCarRepository extends JpaRepository<ShopCar,Integer>{

    List<ShopCar> findByProductId(String productId);
} 