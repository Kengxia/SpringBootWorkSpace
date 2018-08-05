package com.my.framework.base.service.impl;



import com.my.framework.base.dao.BaseDao;
import com.my.framework.base.service.BaseService;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author 华安  mashuai_bj@si-tech.com.cn
 * @Title:
 * @Date: Create in 15:06 2018/4/18
 * @Description:
 */
public class BaseServiceImpl implements BaseService {

    @Resource
    public BaseDao baseDao;

    @PersistenceContext
    protected EntityManager em;
} 