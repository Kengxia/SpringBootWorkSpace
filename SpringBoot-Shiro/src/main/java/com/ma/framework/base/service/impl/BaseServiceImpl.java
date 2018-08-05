package com.ma.framework.base.service.impl;

import com.ma.framework.base.dao.BaseDao;
import com.ma.framework.base.service.BaseService;

import javax.annotation.Resource;

/**
 * @author 华安  mashuai_bj@si-tech.com.cn
 * @Title:
 * @Date: Create in 15:06 2018/4/18
 * @Description:
 */
public class BaseServiceImpl implements BaseService{

    @Resource
    public BaseDao baseDao;
} 