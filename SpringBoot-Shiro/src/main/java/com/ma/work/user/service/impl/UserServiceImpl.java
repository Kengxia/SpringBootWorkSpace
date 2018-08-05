package com.ma.work.user.service.impl;

import com.ma.framework.base.service.impl.BaseServiceImpl;
import com.ma.work.user.dao.UserRepository;
import com.ma.work.user.pojo.User;
import com.ma.work.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 华安  mashuai_bj@si-tech.com.cn
 * @Title:
 * @Date: Create in 11:10 2018/4/19
 * @Description:
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    public User getUserInfo(String userName){
        return userRepository.findByUserId(userName);
    }
} 