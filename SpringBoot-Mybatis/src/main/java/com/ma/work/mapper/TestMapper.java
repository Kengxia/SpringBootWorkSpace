package com.ma.work.mapper;

import com.ma.work.pojo.Test;

public interface TestMapper {
    int insert(Test record);

    int insertSelective(Test record);
}