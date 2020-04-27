package com.cmaple.honeycomb.controller;

import com.cmaple.honeycomb.Interface.UserLoginToken;
import com.cmaple.honeycomb.service.BackgroundServiceService;
import com.cmaple.honeycomb.service.OperationLogService;
import com.cmaple.honeycomb.service.StatisticsService;
import com.cmaple.honeycomb.service.UserService;
import com.cmaple.honeycomb.tools.FormatTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * 类名：统计信息相关控制类 - StatisticsController
 * 功能描述： 统计信息相关控制类
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：NULL
 * 创建人：cmaple
 * 创建日期：2020-04-22
 */
@RestController
@RequestMapping("/Statistics")
public class StatisticsController {
    /**
     * 引入
     */
    @Autowired
    private StatisticsService statisticsService;
    @Autowired
    private UserService userService;
    @Autowired
    private OperationLogService operationLogService;
    @Autowired
    private BackgroundServiceService backgroundService;

    /**
     * 函数名：select函数 -获取统计信息 - selectCountHomePage（）
     * 功能描述： 获取统计信息
     * 输入参数：<按照参数定义顺序>
     * 返回值：map
     * 异    常：NULL
     * 创建人：CMAPLE
     * 创建日期：2020-04-26
     */
    @UserLoginToken
    @RequestMapping(value = "/selectCountHomePage", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> selectCountHomePage() {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> data = new HashMap<String, Object>();
        List list = new ArrayList();
        Map<String, Object> params = new HashMap<String, Object>();
        List times = new ArrayList();
        try {
            //查询当日访问官网数量统计
            int homenum = statisticsService.selectCountHomePage(FormatTime.getFormatTime().formatY_M_DToString(new Date()));
            data.put("home_count", homenum);
            //查询当日访问平台数量统计
            int platformnum = statisticsService.selectCountPlatform(FormatTime.getFormatTime().formatY_M_DToString(new Date()));
            data.put("plat_count", platformnum);
            //用户注册量
            int usercount = userService.selectUserCount();
            data.put("user_count", usercount);
            //当前异常日志数量
            list.add("logstype");
            params.put("logstype", "exception");
            times.add(FormatTime.getFormatTime().formatY_M_DToString(new Date()));
            times.add(FormatTime.getFormatTime().formatY_M_DToString(new Date()));
            list.add("timeaxisdate");
            params.put("timeaxisdate", times);
            int logcount = operationLogService.selectCountByCriteria(list, params);
            data.put("log_count", logcount);
            //最近一周用户数量变化
            int[] users = {0, 0, 0, 0, 0, 0, 0};
            String[] dates = {"", "", "", "", "", "", ""};
            for (int i = 6; i >= 0; i--) {

                dates[(6 - i)] = FormatTime.getFormatTime().formatY_M_DToBefor(i);
                users[(6 - i)] = statisticsService.selectUserCountBefor(FormatTime.getFormatTime().formatY_M_DToBefor(i));
            }
            data.put("user_b_date", dates);
            data.put("user_b_count", users);
            //官方在线服务数量
            int bacount = backgroundService.selectBSCount();
            data.put("bs_count", bacount);
            //统计书剑
            data.put("time", FormatTime.getFormatTime().formatYMDHMSToString(new Date()));
        } catch (Exception e) {
            e.printStackTrace();
            //报错信息，错误信息插入日志表
            map.put("RTCODE", "error");
            map.put("RTMSG", "统计信息交易异常！请联系管理员！");
            map.put("RTDATA", e.getMessage());
            return map;
        }
        map.put("RTCODE", "success");
        map.put("RTMSG", "获取统计信息成功！");
        map.put("RTDATA", data);
        return map;
    }
}
