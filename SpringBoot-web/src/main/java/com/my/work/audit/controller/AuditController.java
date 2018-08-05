package com.my.work.audit.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.alibaba.fastjson.JSONObject;
import com.my.framework.base.controller.BaseController;
import com.my.work.audit.pojo.HomeParam;
import com.sitech.prm.settle.compute.api.client.IComputeAuditClient;
import com.sitech.prm.settle.rule.api.client.RuleClientDubboProvider;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.WebAsyncTask;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Callable;

/**
 * @author 华安  mashuai_bj@si-tech.com.cn
 * @Title:
 * @Date: Create in 14:43 2018/7/21
 * @Description:
 */
@RequestMapping("/audit")
@Controller
public class AuditController extends BaseController {

    //进入首页
    @RequestMapping(value = "/index", method = {RequestMethod.GET, RequestMethod.POST}, produces="application/json;charset=UTF-8" )
    @ApiOperation(value = "首页", notes = "跳转到首页")
    public String home(Model model) {
        System.out.println("sddd");
        return "/audit/home";
    }

    /**
     * 模糊查询场景
     *
     * @param sceneName
     * @return
     */
    @RequestMapping(value = "/getScene", method = {RequestMethod.GET} )
    @ApiOperation(value = "查询场景", notes = "查询场景")
    @ResponseBody
    public String getSceneList(@ApiParam(name = "场景名称", required = true) @RequestParam(value = "sceneName", defaultValue = "") String sceneName) {
        return JSONObject.toJSONString(settleRuleDesignDubboClient.getSceneListRecent(sceneName, "", "", 1, 2000));
    }

    /**
     * 获取可查询的列
     *
     * @param logId
     * @return
     */
    @RequestMapping(value = "/getColumns", method = {RequestMethod.GET})
    @ResponseBody
    public String getColumn(@ApiParam(name = "日志id", required = true) @RequestParam(value = "logId", defaultValue = "") String logId) {
        return JSONObject.toJSONString(settleRuleDesignDubboClient.getQueryColumn(logId));
    }

    /**
     * 查询历史
     *
     * @return
     */
    @RequestMapping(value = "/getHisQuery", method = {RequestMethod.GET})
    @ResponseBody
    public Object getHisQuery(@RequestParam(value = "id", defaultValue = "") String id) {
        return computeAuditClient.getComputeAuditResults(currentLoginName, id);
    }

    /**
     * 保存基本信息
     *
     * @param homeParam
     * @return
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    @RequestMapping(value = "/startQuery", method = RequestMethod.POST)
    @ResponseBody
    public WebAsyncTask<String> startQuery(@RequestBody HomeParam homeParam) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        homeParam.setQueryPerson(currentLoginName);
        log.debug(homeParam.toString());
        String id = computeAuditClient.saveInitComputeAuditResult(BeanUtils.describe(homeParam));
        // 1000 为超时设置
        WebAsyncTask<String> webAsyncTask = new WebAsyncTask<String>(1000, new Callable<String>() {
            @Override
            public String call() throws Exception {
                //业务逻辑处理
                return id;
            }
        });
        webAsyncTask.onCompletion(new Runnable() {
            @Override
            public void run() {
                System.out.println("调用完成");
            }
        });
        webAsyncTask.onTimeout(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return id;
            }
        });
        return webAsyncTask;
    }

    //进入详情
    @RequestMapping(value = "/toDetail", method = RequestMethod.POST)
    @ApiOperation(value = "详情", notes = "跳转到详情")
    public String home(@RequestParam(value = "params", defaultValue = "") String params, Model model) {
        model.addAttribute("id", params);
        return "/audit/detail";
    }

    /**
     * 文件下载
     * @param request
     * @param response
     * @param id
     * @throws IOException
     */
    @RequestMapping(value = "/download",method = RequestMethod.POST)
    public void downstudents(HttpServletRequest request, HttpServletResponse response, @RequestParam String id)throws IOException
    {  //我这是根据前端传来的起始时间来查询数据库里的数据，如果没有输入变量要求，保留前两个就行

        String[] headers = { "ID", "主题", "姓名", "手机","创建时间","开始时间","结束时间"};//导出的Excel头部，这个要根据自己项目改一下
        java .util.List dataset = new ArrayList();
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet();
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 18);
        HSSFRow row = sheet.createRow(0);
        for (short i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
            //遍历集合数据，产生数据行
        }

        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=createList.xls");//默认Excel名称
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }
    //文件上传
    @RequestMapping(value="/upload",method=RequestMethod.POST)
    public String upload(HttpServletRequest req) throws Exception{
        MultipartHttpServletRequest mreq = (MultipartHttpServletRequest)req;
        MultipartFile file = mreq.getFile("file");
        String fileName = file.getOriginalFilename();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        FileOutputStream fos = new FileOutputStream(req.getSession().getServletContext().getRealPath("/")+
                "upload/"+sdf.format(new Date())+fileName.substring(fileName.lastIndexOf('.')));
        fos.write(file.getBytes());
        fos.flush();
        fos.close();

        return "hello";
    }
} 