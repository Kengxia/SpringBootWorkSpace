package com.ma.work.role.service.impl;

import com.ma.framework.base.service.impl.BaseServiceImpl;
import com.ma.work.role.dao.RoleRepository;
import com.ma.work.role.pojo.Role;
import com.ma.work.role.service.RoleService;
import java.util.List;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 华安  mashuai_bj@si-tech.com.cn
 * @Title:
 * @Date: Create in 10:07 2018/4/19
 * @Description:
 */
@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl implements RoleService {

    @Resource
    RoleRepository roleRepository;

    @Override
    public List<Role> getRoleByUserId(String userId) {
        return roleRepository.findByUserId(userId);
    }
} 