package com.cmaple.honeycomb.controller;

import com.alibaba.excel.EasyExcel;
import com.cmaple.honeycomb.Interface.UserLoginToken;
import com.cmaple.honeycomb.model.OperationLog;
import com.cmaple.honeycomb.service.OperationLogService;
import com.cmaple.honeycomb.service.UserService;
import com.cmaple.honeycomb.tools.FormatTime;
import com.cmaple.honeycomb.tools.HttpServletRequestTool;
import com.cmaple.honeycomb.tools.ParamsTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 类名：日志管理服务控制器 - OperationLogController
 * 功能描述： 日志管理服务控制器
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：NULL
 * 创建人：CMAPLE
 * 创建日期：2019-09-11
 */
@RestController
@RequestMapping("/operationlog")
public class OperationLogController {

    /**
     * 引入OperationLogService
     */
    @Autowired
    private OperationLogService operationLogService;
    /**
     * 引入UserService
     */
    @Autowired
    private UserService userService;

    /**
     * 函数名：select函数-按照条件查询日志- selectByCriteria（）
     * 功能描述： 按照条件查询日志
     * 输入参数：<按照参数定义顺序>
     *
     * @param timeaxisdate List类型的时间范围
     * @param search       String类型的全文搜索内容
     * @param logstype     String类型的日志类型
     * @param page         int类型的页数
     * @param num          int类型的展示数量
     *                     返回值：map
     *                     异    常：NULL
     *                     创建人：CMAPLE
     *                     创建日期：2019-09-25
     */
    @UserLoginToken
    @RequestMapping(value = "/selectByCriteria", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> selectByCriteria(
            @RequestParam(value = "timeaxisdate", required = true) List timeaxisdate
            , @RequestParam(value = "search", required = true) String search
            , @RequestParam(value = "logstype", required = true) String logstype
            , @RequestParam(value = "page", required = true) int page
            , @RequestParam(value = "num", required = true) int num
    ) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> data = new HashMap<String, Object>();
        //拼装查询条件
        List list = new ArrayList();
        Map<String, Object> params = new HashMap<String, Object>();
        //拼装条件
        if (0 != timeaxisdate.size()) {
            list.add("timeaxisdate");
            params.put("timeaxisdate", timeaxisdate);
        }
        if (!"".equals(search)) {
            list.add("search");
            params.put("search", search);
        }
        if (!"all".equals(logstype)) {
            list.add("logstype");
            params.put("logstype", logstype);
        }
        List<OperationLog> returnpologs = null;
        int total = 0;
        try {
            //根据条件查询
            returnpologs = operationLogService.selectByCriteria(list, params, ParamsTools.getPageTools().getPageByNum(page, num), num);
            total = operationLogService.selectCountByCriteria(list, params);
        } catch (Exception e) {
            //获取信息
            //报错信息，错误信息插入日志表
            map.put("RTCODE", "error");
            map.put("RTMSG", "日志信息查询失败，查询交易异常！");
            map.put("RTDATA", e.getMessage());
            return map;
        }
        //返回成功信息
        data.put("data", returnpologs);
        data.put("total", total);
        //返回成功信息
        map.put("RTCODE", "success");
        map.put("RTMSG", "日志信息查询成功！");
        map.put("RTDATA", data);
        return map;
    }

    /**
     * 函数名：select函数-按照条件查询登录用户的操作日志- selectUserOperationLogByCriteria（）
     * 功能描述： 根据条件进行条件进行日志的查询
     * 输入参数：<按照参数定义顺序>
     *
     * @param timeaxisdate List类型的时间范围
     * @param logstype     String类型的日志类型
     * @param search       String类型的全文搜索内容
     * @param page         int类型的页数
     * @param num          int类型的展示数量
     *                     返回值：map
     *                     异    常：NULL
     *                     创建人：CMAPLE
     *                     创建日期：2019-09-29
     */
    @UserLoginToken
    @RequestMapping(value = "/selectUserOperationLogByCriteria", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> selectUserOperationLogByCriteria(
            @RequestParam(value = "timeaxisdate", required = true) List timeaxisdate
            , @RequestParam(value = "search", required = true) String search
            , @RequestParam(value = "logstype", required = true) String logstype
            , @RequestParam(value = "page", required = true) int page
            , @RequestParam(value = "num", required = true) int num
            , HttpServletRequest httpServletRequest
    ) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> data = new HashMap<String, Object>();
        //获取登录用户信息
        String telephonenumber = HttpServletRequestTool.getHttpServletRequestToolExample().getIpAddgetRequestUser(httpServletRequest);
        //拼装查询条件
        List list = new ArrayList();
        Map<String, Object> params = new HashMap<String, Object>();
        //配置登录用户信息
        list.add("operator");
        params.put("operator", telephonenumber);
        if (0 != timeaxisdate.size()) {
            list.add("timeaxisdate");
            params.put("timeaxisdate", timeaxisdate);
        }
        if (!"all".equals(logstype)) {
            list.add("logstype");
            params.put("logstype", logstype);
        }
        if (!"".equals(search)) {
            list.add("search");
            params.put("search", search);
        }
        List<OperationLog> returnpologs = null;
        int total = 0;
        //获取日志信息
        try {
            //根据条件查询
            returnpologs = operationLogService.selectByCriteria(list, params, ParamsTools.getPageTools().getPageByNum(page, num), num);
            total = operationLogService.selectCountByCriteria(list, params);
        } catch (Exception e) {
            //报错信息，错误信息插入日志表
            map.put("RTCODE", "error");
            map.put("RTMSG", "日志信息查询失败，查询交易异常！");
            map.put("RTDATA", e.getMessage());
            return map;
        }
        data.put("data", returnpologs);
        data.put("total", total);
        //返回成功信息
        map.put("RTCODE", "success");
        map.put("RTMSG", "日志信息查询成功！");
        map.put("RTDATA", data);
        return map;
    }

    /**
     * 函数名：导出函数-将查询的日志导入为Excel- getOperationLogByParamsToExcel（）
     * 功能描述： 将日志导入为Excel
     * 输入参数：<按照参数定义顺序>
     *
     * @param timeaxisdate List类型的时间范围
     * @param search       String类型的全文搜索内容
     * @param logstype     String类型的日志类型
     * @param num          int类型的展示数量
     *                     返回值：map
     *                     异    常：NULL
     *                     创建人：CMAPLE
     *                     创建日期：2019-09-29
     */
    @UserLoginToken
    @RequestMapping(value = "/getOperationLogByParamsToExcel", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public void getOperationLogByParamsToExcel(
            @RequestParam(value = "timeaxisdate", required = true) List timeaxisdate
            , @RequestParam(value = "search", required = true) String search
            , @RequestParam(value = "logstype", required = true) String logstype
            , @RequestParam(value = "num", required = true) int num
            , HttpServletRequest request
            , HttpServletResponse response
    ) {
        Map<String, Object> map = new HashMap<String, Object>();
        String telephonenumber = HttpServletRequestTool.getHttpServletRequestToolExample().getIpAddgetRequestUser(request);
        List paramslist = new ArrayList();
        Map<String, Object> params = new HashMap<String, Object>();
        //拼装条件
        paramslist.add("operator");
        params.put("operator", telephonenumber);
        if (0 != timeaxisdate.size()) {
            paramslist.add("timeaxisdate");
            params.put("timeaxisdate", timeaxisdate);
        }
        if (!"".equals(search)) {
            paramslist.add("search");
            params.put("search", search);
        }
        if (!"all".equals(logstype)) {
            paramslist.add("logstype");
            params.put("logstype", logstype);
        }
        try {
            List<OperationLog> result = operationLogService.selectByCriteria(paramslist, params, ParamsTools.getPageTools().getPageByNum(1, num), num);
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-disposition", "attachment;filename=OperationLog_" + FormatTime.getFormatTime().formatHMSMSToString(new Date()) + ".xlsx");
            EasyExcel.write(response.getOutputStream(), OperationLog.class).sheet("模板").doWrite(result);
            //记录操作日志
        } catch (Exception e) {
            //
        }
    }

    /**
     * 函数名：导出函数-将查询的日志导入为Excel- getOperationLogByParamsToExcel（）
     * 功能描述： 将日志导入为Excel
     * 输入参数：<按照参数定义顺序>
     *
     * @param timeaxisdate List类型的时间范围
     * @param search       String类型的全文搜索内容
     * @param logstype     String类型的日志类型
     * @param num          int类型的展示数量
     *                     返回值：map
     *                     异    常：NULL
     *                     创建人：CMAPLE
     *                     创建日期：2019-09-29
     */
    @UserLoginToken
    @RequestMapping(value = "/getOperationLogToExcel", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public void getOperationLogToExcel(
            @RequestParam(value = "timeaxisdate", required = true) List timeaxisdate
            , @RequestParam(value = "search", required = true) String search
            , @RequestParam(value = "logstype", required = true) String logstype
            , @RequestParam(value = "num", required = true) int num
            , HttpServletRequest request
            , HttpServletResponse response
    ) {
        Map<String, Object> map = new HashMap<String, Object>();
        List paramslist = new ArrayList();
        Map<String, Object> params = new HashMap<String, Object>();
        if (0 != timeaxisdate.size()) {
            paramslist.add("timeaxisdate");
            params.put("timeaxisdate", timeaxisdate);
        }
        if (!"".equals(search)) {
            paramslist.add("search");
            params.put("search", search);
        }
        if (!"all".equals(logstype)) {
            paramslist.add("logstype");
            params.put("logstype", logstype);
        }
        try {
            List<OperationLog> result = operationLogService.selectByCriteria(paramslist, params, ParamsTools.getPageTools().getPageByNum(1, num), num);
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-disposition", "attachment;filename=OperationLog_" + FormatTime.getFormatTime().formatHMSMSToString(new Date()) + ".xlsx");
            EasyExcel.write(response.getOutputStream(), OperationLog.class).sheet("模板").doWrite(result);
            //记录操作日志
        } catch (Exception e) {
            //
        }
    }

}
