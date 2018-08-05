package com.my.work.repositories.second;

import com.my.work.repositories.second.pojo.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 华安  mashuai_bj@si-tech.com.cn
 * @Title:
 * @Date: Create in 10:13 2018/4/18
 * @Description:
 */
public interface OrderRepository extends JpaRepository<Order,Integer>{

    List<Order> findByOrderId(String orderId);
} 