package com.ma.work.login.controller;

import com.alibaba.fastjson.JSONObject;
import com.ma.framework.base.controller.BaseController;
import com.ma.work.login.service.LoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 华安  mashuai_bj@si-tech.com.cn
 * @Title:
 * @Date: Create in 10:22 2018/4/19
 * @Description:
 */
@RestController
@RequestMapping("/login")
public class LoginController extends BaseController{

    @Resource
    LoginService loginService;

    @RequestMapping(value = "/doLogin", method = { RequestMethod.POST, RequestMethod.GET })
    public Object login(HttpServletRequest request, HttpServletResponse response){
        JSONObject jsonObject = new JSONObject();
        String passWord = request.getParameter("passWord");
        String loginNo = request.getParameter("loginNo");
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginNo, passWord);
        try {
            subject.login(token);
            jsonObject.put("token", subject.getSession().getId());
            jsonObject.put("msg", "登录成功");
        } catch (IncorrectCredentialsException e) {
            jsonObject.put("msg", "密码错误");
        } catch (LockedAccountException e) {
            jsonObject.put("msg", "登录失败，该用户已被冻结");
        } catch (AuthenticationException e) {
            jsonObject.put("msg", "该用户不存在");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
} 