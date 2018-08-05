package com.ma.framework.base.dao.impl;

import com.ma.framework.base.dao.BaseDao;
import com.ma.framework.util.PageUtil;
import org.hibernate.SQLQuery;
import org.hibernate.query.internal.QueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

/**
 * @author 华安  mashuai_bj@si-tech.com.cn
 * @Title:
 * @Date: Create in 14:37 2018/4/18
 * @Description:
 */
@Repository
public class BaseDaoImpl<T> implements BaseDao<T> {

    @PersistenceContext
    protected EntityManager em;

    @Override
    public PageUtil<Map> findPageList(String querySql, int pageNum, int pageSize, Map<String, Object> params) {
        Query query = em.createNativeQuery(querySql);
        if(params!=null){
            for(Map.Entry<String, Object> entry : params.entrySet()){
                query.setParameter(entry.getKey(),entry.getValue());
            }
        }
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        int total = query.getResultList() != null ? query.getResultList().size() : 0;
        List<Map> list = query.setMaxResults(pageSize)
                .setFirstResult((pageNum - 1) * pageSize).getResultList();
        PageUtil<Map> pageUtil = new PageUtil<Map>();
        pageUtil.paging(pageNum, pageSize, total);
        pageUtil.setList(list);
        return pageUtil;
    }

    @Override
    public List<Map> multiTableByNativeSql(String querySql, Map<String, Object> params) {
        Query query = em.createNativeQuery(querySql);
        if(params!=null){
            for(Map.Entry<String, Object> entry : params.entrySet()){
                query.setParameter(entry.getKey(),entry.getValue());
            }
        }
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Map> list =query.getResultList();
        return list;
    }
}