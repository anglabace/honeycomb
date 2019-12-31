package com.cmaple.honeycomb.controller;

import com.cmaple.honeycomb.model.ServiceVersionLog;
import com.cmaple.honeycomb.model.User;
import com.cmaple.honeycomb.service.ServiceVersionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类名：服务版本日志服务控制器 - ServiceVersionLogController
 * 功能描述： 服务版本日志服务控制器
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：NULL
 * 创建人：CMAPLE
 * 创建日期：2019-11-11
 */
@RestController
@RequestMapping("/ServiceVersionLog")
public class ServiceVersionLogController {

    /**
     * 引入HttpServletRequest
     */
    @Autowired
    private HttpServletRequest request;
    /**
     * 引入ServiceVersionLogService
     */
    @Autowired
    private ServiceVersionLogService serviceVersionLogService;

    /**
     * 函数名：select函数 - 根据服务ID号按照时间倒叙排列获取服务版本日志 - getServiceVersionLogById（）
     * 功能描述：根据服务ID号按照时间倒叙排列获取服务版本日志
     * 输入参数：<按照参数定义顺序>
     *
     * @param serviceid int类型的服务ID
     *                  返回值：Map
     *                  异    常：NULL
     *                  创建人：CMAPLE
     *                  创建日期：2019-11-11
     */
    @RequestMapping(value = "/getServiceVersionLogById", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> getServiceVersionLogById(
            @RequestParam(value = "serviceid", required = true) int serviceid
    ) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        //获取信息
        User sessionuser = (User) session.getAttribute("SSUSER");
        if (null == sessionuser) {
            map.put("RTCODE", "error");
            map.put("RTMSG", "请先登录，再查询服务版本日志！");
            map.put("RTDATA", null);
            return map;
        }
        List<ServiceVersionLog> ServiceVersionLoglist = null;
        try {
            ServiceVersionLoglist = serviceVersionLogService.getBackgroundServices(serviceid);
        } catch (Exception e) {
            e.printStackTrace();
            //报错信息，错误信息插入日志表
            map.put("RTCODE", "error");
            map.put("RTMSG", "查询服务版本日志异常！请联系管理员！");
            map.put("RTDATA", e.getMessage());
            return map;
        }
        //返回成功信息
        map.put("RTCODE", "success");
        map.put("RTMSG", "查询服务版本日志成功！");
        map.put("RTDATA", ServiceVersionLoglist);
        return map;
    }


}
