package com.cmaple.honeycomb.controller;

import com.cmaple.honeycomb.service.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    /**
     * 函数名：查询函数-按照条件查询日志- getUsersByParams（）
     * 功能描述： 根据条件进行条件进行日志的查询
     * 输入参数：<按照参数定义顺序>
     *
     * @param timeaxisdate String类型的用户名
     * @param operatetype  String类型的用户密码
     *                     返回值：map
     *                     异    常：无
     *                     创建人：CMAPLE
     *                     创建日期：2019-01-16
     *                     修改人：CMAPLE
     *                     级别：普通用户及以上
     *                     修改日期：2019-01-18
     */
    @RequestMapping(value = "/getUsersByParams", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> getUsersByParams(
            @RequestParam(value = "timeaxisdate", required = false) String timeaxisdate
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

        return map;
    }
}
