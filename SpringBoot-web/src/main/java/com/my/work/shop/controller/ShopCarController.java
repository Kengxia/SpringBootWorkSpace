package com.my.work.shop.controller;


import com.my.framework.utils.IDUtils;
import com.my.work.shop.pojo.ShopCar;
import com.my.work.shop.service.ShopCarService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 华安  mashuai_bj@si-tech.com.cn
 * @Title:
 * @Date: Create in 11:09 2018/4/18
 * @Description:
 */
@Controller
@RequestMapping("/shop")
public class ShopCarController {


    @Resource
    ShopCarService shopCarService;

    @RequestMapping("/list/{projectId}")
    @ResponseBody
    public Object getShopList(@PathVariable String projectId) {
        return shopCarService.findByProductId(projectId);
    }


    @RequestMapping("/save")
    @ResponseBody
    public Object saveShop(){
        ShopCar shop = new ShopCar();
        shop.setId(IDUtils.createID());
        shop.setBuyer("Buyer");
        shop.setProductId("productId");
        return shopCarService.insertReord(shop);
    }

    @RequestMapping("/findPage")
    @ResponseBody
    public Object findPage(HttpServletRequest request){
        String productId =  request.getParameter("productId");
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("productId",productId);
        return shopCarService.findPageList(paramMap);
    }

} 