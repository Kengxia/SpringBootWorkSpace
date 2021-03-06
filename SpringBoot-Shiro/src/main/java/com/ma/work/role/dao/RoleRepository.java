package com.ma.work.role.dao;

import com.ma.work.role.pojo.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author 华安  mashuai_bj@si-tech.com.cn
 * @Title:
 * @Date: Create in 10:13 2018/4/18
 * @Description:
 */
public interface RoleRepository extends JpaRepository<Role,Integer>{

    List<Role> findByUserId(String userId);
} 