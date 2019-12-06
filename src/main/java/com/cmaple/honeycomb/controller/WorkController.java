package com.cmaple.honeycomb.controller;

import com.cmaple.honeycomb.model.User;
import com.cmaple.honeycomb.model.Work;
import com.cmaple.honeycomb.service.WorkService;
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
 * 类名：岗位管理服务接口 - AnnouncementController
 * 功能描述： 岗位管理服务接口
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：无
 * 创建人：cmaple
 * 创建日期：2019-12-06
 * 修改人：
 * 级别：NULL
 * 修改日期：
 */
@RestController
@RequestMapping("/Work")
public class WorkController {

    //引入userservice
    @Autowired
    private WorkService workService;
    @Autowired
    private HttpServletRequest request;

    /**
     * 函数名：查询函数 - 根据ID号查询岗位信息 - getWorkById（）
     * 功能描述： 根据ID号查询岗位信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param id int类型的id号
     *           <p>
     *           返回值：map
     *           异    常：无
     *           创建人：CMAPLE
     *           创建日期：2019-12-06
     *           修改人：
     *           修改日期：
     */
    @RequestMapping(value = "/getWorkById", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> getWorkById(
            @RequestParam(value = "id", required = true) int id
    ) {
        Map<String, Object> map = new HashMap<String, Object>();
        Work returnwork = null;
        try {
            //查询公告信息
            returnwork = workService.getWorkById(id);
            if (null == returnwork) {
                map.put("RTCODE", "error");
                map.put("RTMSG", "不存在此ID号的岗位信息！");
                map.put("RTDATA", null);
                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();
            //报错信息，错误信息插入日志表
            map.put("RTCODE", "error");
            map.put("RTMSG", "查询岗位信息异常！请联系管理员！");
            map.put("RTDATA", e.getMessage());
            return map;
        }
        map.put("RTCODE", "success");
        map.put("RTMSG", "获取岗位信息成功！");
        map.put("RTDATA", returnwork);
        return map;
    }

    /**
     * 函数名：查询函数 - 根据条件查询岗位信息 - getWorkByParams（）
     * 功能描述： 根据条件查询岗位信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param search       String类型的搜索内容
     * @param timeaxisdate list类型的时间范围
     * @param page         int类型的页数
     * @param num          int类型的数量
     *                     <p>
     *                     返回值：void
     *                     异    常：无
     *                     创建人：CMAPLE
     *                     创建日期：2019-12-06
     *                     修改人：
     *                     修改日期：
     */
    @RequestMapping(value = "/getWorkByParams", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> getWorkByParams(
            @RequestParam(value = "search", required = true) String search
            , @RequestParam(value = "timeaxisdate", required = true) List timeaxisdate
            , @RequestParam(value = "page", required = true) int page
            , @RequestParam(value = "num", required = true) int num
    ) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> params = new HashMap<String, Object>();
        List list = new ArrayList();
        HttpSession session = request.getSession();
        //获取信息
        User sessionuser = (User) session.getAttribute("SSUSER");
        if (null == sessionuser) {
            map.put("RTCODE", "error");
            map.put("RTMSG", "请先登录，在查询岗位信息！");
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
        List<Work> returnWork = null;
        try {
            //查询公告信息
            returnWork = workService.getWorkByParams(list, params, ParamsTools.getPageTools().getPageByNum(page, num), num);
        } catch (Exception e) {
            e.printStackTrace();
            //报错信息，错误信息插入日志表
            map.put("RTCODE", "error");
            map.put("RTMSG", "按照条件查询调查报告交易异常！请联系管理员！");
            map.put("RTDATA", e.getMessage());
            return map;
        }
        map.put("RTCODE", "success");
        map.put("RTMSG", "获取调查报告信息成功！");
        map.put("RTDATA", returnWork);
        return map;
    }

    /**
     * 函数名：查询函数 - 根据时间倒叙查询岗位信息 - getWorkDescOrderBy（）
     * 功能描述： 根据时间倒叙查询岗位信息
     * 输入参数：<按照参数定义顺序>
     * <p>
     * 返回值：void
     * 异    常：无
     * 创建人：CMAPLE
     * 创建日期：2019-12-06
     * 修改人：
     * 修改日期：
     */
    @RequestMapping(value = "/getWorkDescOrderBy", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> getWorkDescOrderBy() {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Work> returnWork = null;
        try {
            //查询公告信息
            returnWork = workService.getWorkDescOrderBy();
        } catch (Exception e) {
            e.printStackTrace();
            //报错信息，错误信息插入日志表
            map.put("RTCODE", "error");
            map.put("RTMSG", "按照时间倒叙查询岗位信息异常！请联系管理员！");
            map.put("RTDATA", e.getMessage());
            return map;
        }
        map.put("RTCODE", "success");
        map.put("RTMSG", "获取岗位信息成功！");
        map.put("RTDATA", returnWork);
        return map;
    }

    /**
     * 函数名：插入函数 - 创建信息岗位信息 - insertWork（）
     * 功能描述： 创建信息岗位信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param title       String类型的岗位标题
     * @param place       String类型的岗位位置
     * @param type        String类型的岗位类型
     * @param nature      String类型的工作类型
     * @param content     String类型的岗位内容
     * @param need        int类型的需求人数
     * @param application int类型的工作申请人数
     *                    <p>
     *                    返回值：void
     *                    异    常：无
     *                    创建人：CMAPLE
     *                    创建日期：2019-12-06
     *                    修改人：
     *                    修改日期：
     */
    @RequestMapping(value = "/insertWork", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> insertWork(
            @RequestParam(value = "title", required = true) String title
            , @RequestParam(value = "place", required = true) String place
            , @RequestParam(value = "type", required = true) String type
            , @RequestParam(value = "nature", required = true) String nature
            , @RequestParam(value = "content", required = true) String content
            , @RequestParam(value = "need", required = true) int need
            , @RequestParam(value = "application", required = true) int application
    ) {
        //初始化参数
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        //创建时间
        Date insertdate = new Date();
        //获取信息
        User sessionuser = (User) session.getAttribute("SSUSER");
        if (null == sessionuser) {
            map.put("RTCODE", "error");
            map.put("RTMSG", "请先登录，在创建岗位信息！");
            map.put("RTDATA", null);
            return map;
        }
        //插入岗位信息
        try {
            //创建岗位信息
            Work insterWork = new Work(0, title, place, type, nature, content, sessionuser.getTelephonenumber(), insertdate, need, application);
            int returnWork = workService.insertWork(insterWork);
            if (1 == returnWork) {
                map.put("RTCODE", "success");
                map.put("RTMSG", "岗位信息创建成功！");
                map.put("RTDATA", null);
            } else {
                map.put("RTCODE", "error");
                map.put("RTMSG", "岗位信息创建失败！");
                map.put("RTDATA", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            //报错信息，错误信息插入日志表
            map.put("RTCODE", "error");
            map.put("RTMSG", "创建新的岗位交易异常！请联系管理员！");
            map.put("RTDATA", e.getMessage());
            return map;
        }
        map.put("RTCODE", "success");
        map.put("RTMSG", "创建新的岗位成功！");
        map.put("RTDATA", null);
        return map;
    }

    /**
     * 函数名：修改函数 - 更新岗位信息 - updateWork（）
     * 功能描述： 更新岗位信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param id          int类型的id号
     * @param title       String类型的岗位标题
     * @param place       String类型的岗位位置
     * @param type        String类型的岗位类型
     * @param nature      String类型的工作类型
     * @param content     String类型的岗位内容
     * @param need        int类型的需求人数
     * @param application int类型的工作申请人数
     *                    <p>
     *                    返回值：void
     *                    异    常：无
     *                    创建人：CMAPLE
     *                    创建日期：2019-12-06
     *                    修改人：
     *                    修改日期：
     */
    @RequestMapping(value = "/updateWork", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> updateWork(
            @RequestParam(value = "id", required = true) int id
            , @RequestParam(value = "title", required = true) String title
            , @RequestParam(value = "place", required = true) String place
            , @RequestParam(value = "type", required = true) String type
            , @RequestParam(value = "nature", required = true) String nature
            , @RequestParam(value = "content", required = true) String content
            , @RequestParam(value = "need", required = true) int need
            , @RequestParam(value = "application", required = true) int application
    ) {
        //初始化参数
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        //创建时间
        Date insertdate = new Date();
        //获取信息
        User sessionuser = (User) session.getAttribute("SSUSER");
        if (null == sessionuser) {
            map.put("RTCODE", "error");
            map.put("RTMSG", "请先登录，在更新岗位信息！");
            map.put("RTDATA", null);
            return map;
        }
        //插入岗位信息
        try {
            //创建岗位信息
            Work insterWork = new Work(id, title, place, type, nature, content, sessionuser.getTelephonenumber(), insertdate, need, application);
            int returnWork = workService.updateWork(insterWork);
            if (1 == returnWork) {
                map.put("RTCODE", "success");
                map.put("RTMSG", "岗位信息更新成功！");
                map.put("RTDATA", null);
            } else {
                map.put("RTCODE", "error");
                map.put("RTMSG", "岗位信息更新失败！");
                map.put("RTDATA", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            //报错信息，错误信息插入日志表
            map.put("RTCODE", "error");
            map.put("RTMSG", "更新岗位交易异常！请联系管理员！");
            map.put("RTDATA", e.getMessage());
            return map;
        }
        map.put("RTCODE", "success");
        map.put("RTMSG", "更新岗位成功！");
        map.put("RTDATA", null);
        return map;
    }

    /**
     * 函数名：删除函数 - 删除岗位信息 - delWork（）
     * 功能描述： 删除岗位信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param id int类型的岗位id号
     *           <p>
     *           返回值：void
     *           异    常：无
     *           创建人：CMAPLE
     *           创建日期：2019-12-06
     *           修改人：
     *           修改日期：
     */
    @RequestMapping(value = "/delWork", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> delWork(
            @RequestParam(value = "id", required = true) int id
    ) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        //获取信息
        User sessionuser = (User) session.getAttribute("SSUSER");
        if (null == sessionuser) {
            map.put("RTCODE", "error");
            map.put("RTMSG", "请先登录，在删除岗位信息！");
            map.put("RTDATA", null);
            return map;
        }
        //删除数据库信息记录
        try {
            int isdel = workService.delWork(id);
            if (1 != isdel) {
                map.put("RTCODE", "error");
                map.put("RTMSG", "删除岗位信息失败！");
                map.put("RTDATA", null);
            } else {
                map.put("RTCODE", "success");
                map.put("RTMSG", "删除岗位信息成功！");
                map.put("RTDATA", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            //报错信息，错误信息插入日志表
            map.put("RTCODE", "error");
            map.put("RTMSG", "删除岗位信息异常！请联系管理员！");
            map.put("RTDATA", e.getMessage());
            return map;
        }
        return map;
    }

}
