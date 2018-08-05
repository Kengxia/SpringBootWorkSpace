package com.my.work.repositories.second.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author 华安  mashuai_bj@si-tech.com.cn
 * @Title:
 * @Date: Create in 14:53 2018/7/24
 * @Description:
 */
@Entity
@Table(name = "shop_order_test")
public class Order {
    private String id;
    private String orderId;
    private String buyer;

    public Order(String id , String orderId, String buyer){
        this.id=id;
        this.orderId =orderId;
        this.buyer=buyer;
    }
    public Order(){

    }
    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name="order_id")
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Column(name="buyer")
    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }
}