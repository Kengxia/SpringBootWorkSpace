package com.ma.framework.base.dao;

import com.ma.framework.util.PageUtil;

import java.util.Map;
import java.util.List;

/**
 * @author 华安  mashuai_bj@si-tech.com.cn
 * @Title:
 * @Date: Create in 14:34 2018/4/18
 * @Description:
 */
public interface  BaseDao<T> {
    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @param params
     * @return
     */
    PageUtil<Map> findPageList(String sql, int pageNum, int pageSize, Map<String, Object> params);

    List<Map> multiTableByNativeSql(String sql,Map<String, Object> params);
}