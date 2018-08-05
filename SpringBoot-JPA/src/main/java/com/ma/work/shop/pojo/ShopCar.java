package com.ma.work.shop.pojo;

import javax.persistence.*;

/**
 * @author 华安  mashuai_bj@si-tech.com.cn
 * @Title:
 * @Date: Create in 9:58 2018/4/18
 * @Description:
 */
@Entity
@Table(name = "shop_car_test")
public class ShopCar {

    private String id;
    private String productId;
    private String buyer;

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name="product_id")
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Column(name="buyer")
    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }
}