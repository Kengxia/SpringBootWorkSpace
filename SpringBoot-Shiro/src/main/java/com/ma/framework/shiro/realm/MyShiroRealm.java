package com.ma.framework.shiro.realm;

import com.ma.work.role.pojo.Role;
import com.ma.work.role.service.RoleService;
import com.ma.work.user.pojo.User;
import com.ma.work.user.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 华安  mashuai_bj@si-tech.com.cn
 * @Title:
 * @Date: Create in 20:10 2018/4/18
 * @Description:
 */
public class MyShiroRealm extends AuthorizingRealm {
    private static final Logger logger = LoggerFactory.getLogger(MyShiroRealm.class);
    @Resource
    private UserService userService;
    private RoleService roleService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        String loginNo = (String) super.getAvailablePrincipal(principals);
        List<String> roleCodes = new ArrayList<String>();
        if (loginNo != null) {
            logger.info("The login username: " + loginNo);
            List<Role> roles = roleService.getRoleByUserId(loginNo);
            if (roles != null && roles.size() > 0) {
                for (Role role : roles) {
                    roleCodes.add(role.getRoleId());
                }
            }
        } else {
            logger.info("The login username is null");
            throw new AuthorizationException();
        }
        info.addRoles(roleCodes);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        logger.info("The login username is null");
        User userInfo = userService.getUserInfo(username);
        if (userInfo == null) {
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                username, //用户名
                userInfo.getPassWord(), //密码
                username  //realm name
        );
        return authenticationInfo;
    }
}