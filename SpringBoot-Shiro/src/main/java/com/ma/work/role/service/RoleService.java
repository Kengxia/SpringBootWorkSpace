package com.ma.work.role.service;

import com.ma.work.role.pojo.Role;
import java.util.List;

/**
 * @author 华安  mashuai_bj@si-tech.com.cn
 * @Title:
 * @Date: Create in 10:06 2018/4/19
 * @Description:
 */
public interface RoleService {

    List<Role> getRoleByUserId(String userId);
} 