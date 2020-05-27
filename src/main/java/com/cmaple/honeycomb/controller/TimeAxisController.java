package com.cmaple.honeycomb.controller;

import com.auth0.jwt.JWT;
import com.cmaple.honeycomb.Interface.PassToken;
import com.cmaple.honeycomb.Interface.UserLoginToken;
import com.cmaple.honeycomb.model.TimeAxis;
import com.cmaple.honeycomb.service.TimeAxisService;
import com.cmaple.honeycomb.tools.ParamsTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 类名：时间轴管理服务控制器 - TimeAxisController
 * 功能描述： 时间轴管理服务控制器
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：NULL
 * 创建人：cmaple
 * 创建日期：2019-12-09
 */
@RestController
@RequestMapping("/TimeAxis")
public class TimeAxisController {
    /**
     * 引入TimeAxisService
     */
    @Autowired
    private TimeAxisService timeAxisService;

    /**
     * 函数名：select函数 - 在主页显示时间轴 - selectAtHomePage（）
     * 功能描述： 在主页显示时间轴
     * 输入参数：<按照参数定义顺序>
     * 返回值：map
     * 异    常：NULL
     * 创建人：CMAPLE
     * 创建日期：2019-12-09
     */
    @PassToken
    @RequestMapping(value = "/selectAtHomePage", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> selectAtHomePage() {
        Map<String, Object> map = new HashMap<String, Object>();
        List<TimeAxis> returntimeaxis = null;
        try {
            //查询公告信息
            returntimeaxis = timeAxisService.selectAtHomePage();
        } catch (Exception e) {
            e.printStackTrace();
            //报错信息，错误信息插入日志表
            map.put("RTCODE", "error");
            map.put("RTMSG", "主页查询时间轴交易异常！请联系管理员！");
            map.put("RTDATA", e.getMessage());
            return map;
        }
        map.put("RTCODE", "success");
        map.put("RTMSG", "获取时间轴信息成功！");
        map.put("RTDATA", returntimeaxis);
        return map;
    }

    /**
     * 函数名：select函数 - 根据条件查询时间轴信息 - selectByCriteria（）
     * 功能描述： 根据条件查询时间轴信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param search       String类型的搜索内容
     * @param timeaxisdate list类型的时间范围
     * @param page         int类型的页数
     * @param num          int类型的数量
     *                     返回值：map
     *                     异    常：NULL
     *                     创建人：CMAPLE
     *                     创建日期：2019-12-09
     */
    @UserLoginToken
    @RequestMapping(value = "/selectByCriteria", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> selectByCriteria(
            @RequestParam(value = "search", required = true) String search
            , @RequestParam(value = "timeaxisdate", required = true) List timeaxisdate
            , @RequestParam(value = "page", required = true) int page
            , @RequestParam(value = "num", required = true) int num
    ) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> data = new HashMap<String, Object>();
        List list = new ArrayList();
        Map<String, Object> params = new HashMap<String, Object>();
        //拼接条件
        if (0 != timeaxisdate.size()) {
            list.add("timeaxisdate");
            params.put("timeaxisdate", timeaxisdate);
        }
        if (!"".equals(search)) {
            list.add("search");
            params.put("search", search);
        }
        List<TimeAxis> returntimeaxis = null;
        int total = 0;
        try {
            //查询公告信息
            returntimeaxis = timeAxisService.selectByCriteria(list, params, ParamsTools.getPageTools().getPageByNum(page, num), num);
            total = timeAxisService.selectCountByCriteria(list,params);
            data.put("data", returntimeaxis);
            data.put("total", total);
        } catch (Exception e) {
            e.printStackTrace();
            //报错信息，错误信息插入日志表
            map.put("RTCODE", "error");
            map.put("RTMSG", "按照条件查询时间轴交易异常！请联系管理员！");
            map.put("RTDATA", e.getMessage());
            return map;
        }
        map.put("RTCODE", "success");
        map.put("RTMSG", "查询时间轴信息成功！");
        map.put("RTDATA", data);
        return map;
    }

    /**
     * 函数名：insert函数 - 增加新的时间轴信息 - insert（）
     * 功能描述：增加新的时间轴信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param title     String类型的标题
     * @param content   String类型的内容
     * @param eventtime String类型的事件时间
     *                  返回值：map
     *                  异    常：NULL
     *                  创建人：CMAPLE
     *                  创建日期：2019-12-09
     */
    @UserLoginToken
    @RequestMapping(value = "/insert", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> insert(
            @RequestParam(value = "title", required = true) String title
            , @RequestParam(value = "content", required = true) String content
            , @RequestParam(value = "eventtime", required = true) String eventtime
            , HttpServletRequest httpServletRequest
    ) {
        //初始化参数
        Map<String, Object> map = new HashMap<String, Object>();
        Date insertdate = new Date();
        String token = httpServletRequest.getHeader("token");
        String telephonenumber = JWT.decode(token).getAudience().get(0);
        TimeAxis timeAxis = new TimeAxis(0, title, content, java.sql.Date.valueOf(eventtime), telephonenumber, insertdate);
        try {
            int returntimeAxis = timeAxisService.insert(timeAxis);
            if (1 == returntimeAxis) {
                map.put("RTCODE", "success");
                map.put("RTMSG", "时间轴信息创建成功！");
                map.put("RTDATA", null);
            } else {
                map.put("RTCODE", "error");
                map.put("RTMSG", "时间轴信息创建失败！");
                map.put("RTDATA", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            //报错信息，错误信息插入日志表
            map.put("RTCODE", "error");
            map.put("RTMSG", "创建时间轴信息失败！请联系管理员！");
            map.put("RTDATA", e.getMessage());
            return map;
        }
        return map;
    }

    /**
     * 函数名：update函数 - 修改时间轴信息 - update（）
     * 功能描述：修改时间轴信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param id        int类型的id号
     * @param title     String类型的标题
     * @param content   String类型的内容
     * @param eventtime String类型的事件事件
     *                  返回值：map
     *                  异    常：NULL
     *                  创建人：CMAPLE
     *                  创建日期：2019-12-09
     */
    @UserLoginToken
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> update(
            @RequestParam(value = "id", required = true) int id
            , @RequestParam(value = "title", required = true) String title
            , @RequestParam(value = "content", required = true) String content
            , @RequestParam(value = "eventtime", required = true) String eventtime
            , HttpServletRequest httpServletRequest
    ) {
        //初始化参数
        Map<String, Object> map = new HashMap<String, Object>();
        Date insertdate = new Date();
        String token = httpServletRequest.getHeader("token");
        String telephonenumber = JWT.decode(token).getAudience().get(0);
        TimeAxis timeAxis = new TimeAxis(id, title, content, java.sql.Date.valueOf(eventtime), telephonenumber, insertdate);
        try {
            int returntimeAxis = timeAxisService.update(timeAxis);
            if (1 == returntimeAxis) {
                map.put("RTCODE", "success");
                map.put("RTMSG", "时间轴信息修改成功！");
                map.put("RTDATA", null);
            } else {
                map.put("RTCODE", "error");
                map.put("RTMSG", "时间轴信息修改失败！");
                map.put("RTDATA", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            //报错信息，错误信息插入日志表
            map.put("RTCODE", "error");
            map.put("RTMSG", "修改时间轴信息失败！请联系管理员！");
            map.put("RTDATA", e.getMessage());
            return map;
        }
        return map;
    }


    /**
     * 函数名：delete函数 - 根据id号删除时间轴信息 - deleteById（）
     * 功能描述：根据id号删除时间轴信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param id int类型的id号
     *           返回值：map
     *           异    常：NULL
     *           创建人：CMAPLE
     *           创建日期：2019-12-09
     */
    @UserLoginToken
    @RequestMapping(value = "/deleteById", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> deleteById(
            @RequestParam(value = "id", required = true) int id
    ) {
        //初始化参数
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            int returntimeAxis = timeAxisService.deleteById(id);
            if (1 == returntimeAxis) {
                map.put("RTCODE", "success");
                map.put("RTMSG", "时间轴信息删除成功！");
                map.put("RTDATA", null);
            } else {
                map.put("RTCODE", "error");
                map.put("RTMSG", "时间轴信息删除失败！");
                map.put("RTDATA", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            //报错信息，错误信息插入日志表
            map.put("RTCODE", "error");
            map.put("RTMSG", "删除时间轴信息失败！请联系管理员！");
            map.put("RTDATA", e.getMessage());
            return map;
        }
        return map;
    }

}