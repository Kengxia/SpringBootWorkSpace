package com.my.framework.base.controller;

import com.sitech.prm.settle.compute.api.client.IComputeAuditClient;
import com.sitech.prm.settle.rule.api.client.RuleClientDubboProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * @author 华安  mashuai_bj@si-tech.com.cn
 * @Title:
 * @Date: Create in 9:24 2018/7/11
 * @Description:
 */
@Controller
public class BaseController {

    protected static String RESPONSE_FAIL="000000";
    protected static String RESPONSE_SUCCESS="00001";
    protected static Logger log= LoggerFactory.getLogger(BaseController.class);
    protected String currentLoginName;

    public BaseController(){
        this.currentLoginName = "admin";
    }

    @Resource
    protected RuleClientDubboProvider settleRuleDesignDubboClient;

    @Resource
    protected IComputeAuditClient computeAuditClient;
} 