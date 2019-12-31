package com.cmaple.honeycomb.controller;

import com.cmaple.honeycomb.model.User;
import com.cmaple.honeycomb.model.Work;
import com.cmaple.honeycomb.service.UserService;
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
 * 类名：岗位管理服务控制器 - WorkController
 * 功能描述： 岗位管理服务控制器
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：NULL
 * 创建人：cmaple
 * 创建日期：2019-12-06
 */
@RestController
@RequestMapping("/Work")
public class WorkController {

    /**
     * 引入WorkService
     */
    @Autowired
    private WorkService workService;
    /**
     * 引入UserService
     */
    @Autowired
    private UserService userService;
    /**
     * 引入HttpServletRequest
     */
    @Autowired
    private HttpServletRequest request;

    /**
     * 函数名：select函数 - 根据ID号查询岗位信息 - selectById（）
     * 功能描述： 根据ID号查询岗位信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param id int类型的id号
     *           返回值：map
     *           异    常：无
     *           创建人：NULL
     *           创建日期：2019-12-06
     */
    @RequestMapping(value = "/selectById", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> selectById(
            @RequestParam(value = "id", required = true) int id
    ) {
        Map<String, Object> map = new HashMap<String, Object>();
        Work returnwork = null;
        try {
            //查询公告信息
            returnwork = workService.selectById(id);
            if (null == returnwork) {
                map.put("RTCODE", "error");
                map.put("RTMSG", "不存在此ID号的岗位信息！");
                map.put("RTDATA", null);
                return map;
            }
            //替换创建人员信息
            User user = userService.selectByTelephonenumber(returnwork.getCreateuser());
            returnwork.setCreateuser(user.getPetname());
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
     * 函数名：select函数 - 根据条件查询岗位信息 - selectByCriteria（）
     * 功能描述： 根据条件查询岗位信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param search       String类型的搜索内容
     * @param timeaxisdate list类型的时间范围
     * @param page         int类型的页数
     * @param num          int类型的数量
     *                     返回值：Map
     *                     异    常：NULL
     *                     创建人：CMAPLE
     *                     创建日期：2019-12-06
     */
    @RequestMapping(value = "/selectByCriteria", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> selectByCriteria(
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
            returnWork = workService.selectByCriteria(list, params, ParamsTools.getPageTools().getPageByNum(page, num), num);
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
     * 函数名：select函数 - 根据时间倒叙查询岗位信息 - selectDescOrderByTime（）
     * 功能描述： 根据时间倒叙查询岗位信息
     * 输入参数：<按照参数定义顺序>
     * 返回值：Map
     * 异    常：NULL
     * 创建人：CMAPLE
     * 创建日期：2019-12-06
     */
    @RequestMapping(value = "/selectDescOrderByTime", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> selectDescOrderByTime() {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Work> returnWork = null;
        try {
            //查询公告信息
            returnWork = workService.selectDescOrderByTime();
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
     * 函数名：insert函数 - 创建信息岗位信息 - insert（）
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
     *                    返回值：Map
     *                    异    常：NULL
     *                    创建人：CMAPLE
     *                    创建日期：2019-12-06
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> insert(
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
            int returnWork = workService.insert(insterWork);
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
     * 函数名：update函数 - 更新岗位信息 - update（）
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
     *                    返回值：Map
     *                    异    常：NULL
     *                    创建人：CMAPLE
     *                    创建日期：2019-12-06
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> update(
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
            int returnWork = workService.update(insterWork);
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
     * 函数名：delete函数 - 删除岗位信息 - deleteById（）
     * 功能描述： 删除岗位信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param id int类型的岗位id号
     *           返回值：Map
     *           异    常：NULL
     *           创建人：CMAPLE
     *           创建日期：2019-12-06
     */
    @RequestMapping(value = "/deleteById", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> deleteById(
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
            int isdel = workService.deleteById(id);
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