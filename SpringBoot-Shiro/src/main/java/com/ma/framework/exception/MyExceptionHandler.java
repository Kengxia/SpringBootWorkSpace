package com.ma.framework.exception;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 华安  mashuai_bj@si-tech.com.cn
 * @Title:
 * @Date: Create in 20:26 2018/4/18
 * @Description:
 */
public class MyExceptionHandler implements HandlerExceptionResolver {

    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception ex) {
        ModelAndView mv = new ModelAndView();
        FastJsonJsonView view = new FastJsonJsonView();
        Map<String, Object> attributes = new HashMap<String, Object>();
        if (ex instanceof UnauthenticatedException) {
            attributes.put("code", "1000001");
            attributes.put("msg", "token错误");
        } else if (ex instanceof UnauthorizedException) {
            attributes.put("code", "1000002");
            attributes.put("msg", "用户无权限");
        } else {
            attributes.put("code", "1000003");
            attributes.put("msg", ex.getMessage());
        }
        view.setAttributesMap(attributes);
        mv.setView(view);
        return mv;
    }
} 