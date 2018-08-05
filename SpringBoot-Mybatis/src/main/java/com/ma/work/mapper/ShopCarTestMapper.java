package com.ma.work.mapper;

import com.ma.work.pojo.ShopCarTest;

import java.util.List;

public interface ShopCarTestMapper {
    int insert(ShopCarTest record);

    List<ShopCarTest>  selecList();
}