package com.cmaple.honeycomb.controller;

import com.cmaple.honeycomb.model.TimeAxis;
import com.cmaple.honeycomb.model.User;
import com.cmaple.honeycomb.service.TimeAxisService;
import com.cmaple.honeycomb.tools.ParamsTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * 类名：时间轴管理服务接口 - TimeAxisController
 * 功能描述： 时间轴管理服务接口
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：无
 * 创建人：cmaple
 * 创建日期：2019-12-09
 * 修改人：
 * 级别：NULL
 * 修改日期：
 */
@RestController
@RequestMapping("/TimeAxis")
public class TimeAxisController {
    @Autowired
    private TimeAxisService timeAxisService;
    @Autowired
    private HttpServletRequest request;

    /**
     * 函数名：查询函数 - 在主页显示时间轴 - getTimeAxisAtHome（）
     * 功能描述： 在主页显示时间轴
     * 输入参数：<按照参数定义顺序>
     * <p>
     * 返回值：map
     * 异    常：无
     * 创建人：CMAPLE
     * 创建日期：2019-12-09
     * 修改人：
     * 修改日期：
     */
    @RequestMapping(value = "/getTimeAxisAtHome", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> getTimeAxisAtHome() {
        Map<String, Object> map = new HashMap<String, Object>();
        List<TimeAxis> returntimeaxis = null;
        try {
            //查询公告信息
            returntimeaxis = timeAxisService.getTimeAxisAtHome();
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
     * 函数名：查询函数 - 分页，根据条件查询时间轴信息 - getTimeAxisByParams（）
     * 功能描述： 分页，根据条件查询时间轴信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param search       String类型的搜索内容
     * @param timeaxisdate list类型的时间范围
     * @param page         int类型的页数
     * @param num          int类型的数量
     *                     返回值：map
     *                     异    常：无
     *                     创建人：CMAPLE
     *                     创建日期：2019-12-09
     *                     修改人：
     *                     修改日期：
     */
    @RequestMapping(value = "/getTimeAxisByParams", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> getTimeAxisByParams(
            @RequestParam(value = "search", required = true) String search
            , @RequestParam(value = "timeaxisdate", required = true) List timeaxisdate
            , @RequestParam(value = "page", required = true) int page
            , @RequestParam(value = "num", required = true) int num
    ) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        List list = new ArrayList();
        Map<String, Object> params = new HashMap<String, Object>();
        //获取信息
        User sessionuser = (User) session.getAttribute("SSUSER");
        if (null == sessionuser) {
            map.put("RTCODE", "error");
            map.put("RTMSG", "请先登录，在查询公告信息！");
            map.put("RTDATA", null);
            return map;
        }
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
        try {
            //查询公告信息
            returntimeaxis = timeAxisService.getTimeAxisByParams(list, params, ParamsTools.getPageTools().getPageByNum(page, num), num);
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
        map.put("RTDATA", returntimeaxis);
        return map;
    }

    /**
     * 函数名：插入函数 - 增加新的时间轴信息 - insertTimeAxis（）
     * 功能描述：增加新的时间轴信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param title     String类型的标题
     * @param content   String类型的内容
     * @param creattime String类型的事件事件
     *                  返回值：map
     *                  异    常：无
     *                  创建人：CMAPLE
     *                  创建日期：2019-12-09
     *                  修改人：
     *                  修改日期：
     */
    @RequestMapping(value = "/insertTimeAxis", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> insertTimeAxis(
            @RequestParam(value = "title", required = true) String title
            , @RequestParam(value = "synopsis", required = true) String content
            , @RequestParam(value = "content", required = true) Date creattime
    ) {
        //初始化参数
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        User sessionuser = (User) session.getAttribute("SSUSER");
        if (null == sessionuser) {
            map.put("RTCODE", "error");
            map.put("RTMSG", "请先登录，在创建时间轴信息！");
            map.put("RTDATA", null);
            return map;
        }
        Date insertdate = new Date();
        TimeAxis timeAxis = new TimeAxis(0, title, content, creattime, sessionuser.getTelephonenumber(), insertdate);
        try {
            int returntimeAxis = timeAxisService.insertTimeAxis(timeAxis);
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
     * 函数名：插入函数 - 修改时间轴信息 - updateTimeAxis（）
     * 功能描述：修改时间轴信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param id        int类型的id号
     * @param title     String类型的标题
     * @param content   String类型的内容
     * @param creattime String类型的事件事件
     *                  返回值：map
     *                  异    常：无
     *                  创建人：CMAPLE
     *                  创建日期：2019-12-09
     *                  修改人：
     *                  修改日期：
     */
    @RequestMapping(value = "/updateTimeAxis", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> updateTimeAxis(
            @RequestParam(value = "id", required = true) int id
            , @RequestParam(value = "title", required = true) String title
            , @RequestParam(value = "synopsis", required = true) String content
            , @RequestParam(value = "content", required = true) Date creattime
    ) {
        //初始化参数
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        User sessionuser = (User) session.getAttribute("SSUSER");
        if (null == sessionuser) {
            map.put("RTCODE", "error");
            map.put("RTMSG", "请先登录，在修改时间轴信息！");
            map.put("RTDATA", null);
            return map;
        }
        Date insertdate = new Date();
        TimeAxis timeAxis = new TimeAxis(id, title, content, creattime, sessionuser.getTelephonenumber(), insertdate);
        try {
            int returntimeAxis = timeAxisService.updateTimeAxis(timeAxis);
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
     * 函数名：删除函数 - 根据id号删除时间轴信息 - delTimeAxis（）
     * 功能描述：根据id号删除时间轴信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param id int类型的id号
     *           返回值：map
     *           异    常：无
     *           创建人：CMAPLE
     *           创建日期：2019-12-09
     *           修改人：
     *           修改日期：
     */
    @RequestMapping(value = "/delTimeAxis", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> delTimeAxis(
            @RequestParam(value = "id", required = true) int id
    ) {
        //初始化参数
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        User sessionuser = (User) session.getAttribute("SSUSER");
        if (null == sessionuser) {
            map.put("RTCODE", "error");
            map.put("RTMSG", "请先登录，在删除时间轴信息！");
            map.put("RTDATA", null);
            return map;
        }
        try {
            int returntimeAxis = timeAxisService.delTimeAxis(id);
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
