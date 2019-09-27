package com.cmaple.honeycomb.controller;

import com.cmaple.honeycomb.model.OperationLog;
import com.cmaple.honeycomb.model.User;
import com.cmaple.honeycomb.service.OperationLogService;
import com.cmaple.honeycomb.tools.FormatTime;
import com.cmaple.honeycomb.tools.ParamsTools;
import com.cmaple.honeycomb.tools.RandomData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * 类名：日志请求接口类 - OperationLogController
 * 功能描述： 日志请求接口
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：无
 * 创建人：CMAPLE
 * 创建日期：2019-09-11
 * 修改人：
 * 级别：NULL
 * 修改日期：
 */
@RestController
@RequestMapping("/operationlog")
public class OperationLogController {

    //引入OperationLogService
    @Autowired
    private OperationLogService operationLogService;
    @Autowired
    private HttpServletRequest request;

    /**
     * 函数名：查询函数-按照条件查询日志- getOperationLogByParams（）
     * 功能描述： 根据条件进行条件进行日志的查询
     * 输入参数：<按照参数定义顺序>
     *
     * @param timeaxisdate String类型的用户名
     * @param operatetype  String类型的用户密码
     *                     返回值：map
     *                     异    常：无
     *                     创建人：CMAPLE
     *                     创建日期：2019-09-25
     *                     修改人：CMAPLE
     *                     修改日期：2019-09-26
     */
    @RequestMapping(value = "/getOperationLogByParams", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> getOperationLogByParams(
            @RequestParam(value = "timeaxisdate", required = true) List timeaxisdate
            , @RequestParam(value = "operatetype", required = true) String operatetype
            , @RequestParam(value = "search", required = true) String search
            , @RequestParam(value = "logstype", required = true) String logstype
            , @RequestParam(value = "page", required = true) int page
            , @RequestParam(value = "num", required = true) int num
    ) {
        Map<String, Object> map = new HashMap<String, Object>();
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
        if (!"all".equals(operatetype)) {
            list.add("operatetype");
            params.put("operatetype", operatetype);
        }
        List<OperationLog> returnpologs = null;
        try {
            //根据条件查询
            returnpologs = operationLogService.getOperationLogByParams(list, params, ParamsTools.getPageTools().getPageByNum(page, num), num);
        } catch (Exception e) {
            //记录错误日志
            HttpSession session = request.getSession();
            //获取信息
            User sessionuser = (User) session.getAttribute("SSUSER");
            operationLogService.insertOperationLog(new OperationLog(0, "HC" + FormatTime.getFormatTime().formatYMDToString(new Date()) + "-" + RandomData.getRandomData().getRandomNHData(6), new Date(), sessionuser.getTelephonenumber(), "exception", "account", "用户：[ " + sessionuser.getTelephonenumber() + " ] 查看日志交易异常，异常信息如下：" + e.getMessage()));
            //报错信息，错误信息插入日志表
            map.put("RTCODE", "error");
            map.put("RTMSG", "日志信息查询失败，查询交易异常！");
            map.put("RTDATA", e.getMessage());
            return map;
        }
        //返回成功信息
        map.put("RTCODE", "success");
        map.put("RTMSG", "日志信息查询成功！");
        map.put("RTDATA", returnpologs);
        return map;
    }
}
