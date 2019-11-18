package com.cmaple.honeycomb.controller;

import com.cmaple.honeycomb.model.Announcement;
import com.cmaple.honeycomb.model.User;
import com.cmaple.honeycomb.service.AnnouncementService;
import com.cmaple.honeycomb.tools.FormatTime;
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
 * 类名：公告管理服务接口 - AnnouncementController
 * 功能描述： 公告管理服务接口
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：无
 * 创建人：cmaple
 * 创建日期：2019-11-11
 * 修改人：
 * 级别：NULL
 * 修改日期：
 */
@RestController
@RequestMapping("/Announcement")
public class AnnouncementController {

    //引入userservice
    @Autowired
    private AnnouncementService announcementService;
    @Autowired
    private HttpServletRequest request;

    /**
     * 函数名：查询函数 - 根据ID号查询公告内容 - getAnnouncementById（）
     * 功能描述： 根据ID号查询公告内容
     * 输入参数：<按照参数定义顺序>
     *
     * @param id int类型的id号
     *           <p>
     *           返回值：map
     *           异    常：无
     *           创建人：CMAPLE
     *           创建日期：2019-11-11
     *           修改人：
     *           修改日期：
     */
    @RequestMapping(value = "/getAnnouncementById", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> getAnnouncementById(
            @RequestParam(value = "id", required = true) int id
    ) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        //获取信息
        User sessionuser = (User) session.getAttribute("SSUSER");
        if (null == sessionuser) {
            map.put("RTCODE", "error");
            map.put("RTMSG", "请先登录，在查询公告信息！");
            map.put("RTDATA", null);
            return map;
        }
        Announcement returnannouncement = null;
        try {
            //查询公告信息
            returnannouncement = announcementService.getAnnouncementById(id);
            if (null == returnannouncement) {
                map.put("RTCODE", "error");
                map.put("RTMSG", "不存在此ID号的公告！");
                map.put("RTDATA", null);
                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();
            //报错信息，错误信息插入日志表
            map.put("RTCODE", "error");
            map.put("RTMSG", "删除后台服务信息异常！请联系管理员！");
            map.put("RTDATA", e.getMessage());
            return map;
        }
        map.put("RTCODE", "success");
        map.put("RTMSG", "获取公告信息成功！");
        map.put("RTDATA", returnannouncement);
        return map;
    }

    /**
     * 函数名：查询函数 - 分页获取公告列表，公告以时间倒叙排列展示 - getAnnouncementByPage（）
     * 功能描述： 分页获取公告列表，公告以时间倒叙排列展示
     * 输入参数：<按照参数定义顺序>
     *
     * @param search       String类型的搜索内容
     * @param timeaxisdate list类型的时间范围
     * @param page         int类型的页数
     * @param num          int类型的数量
     *                     返回值：map
     *                     异    常：无
     *                     创建人：CMAPLE
     *                     创建日期：2019-11-18
     *                     修改人：
     *                     修改日期：
     */
    @RequestMapping(value = "/getAnnouncementByPage", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> getAnnouncementByPage(
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
        List<Announcement> returnannouncement = null;
        try {
            //查询公告信息
            returnannouncement = announcementService.getAnnouncementByParams(list, params, ParamsTools.getPageTools().getPageByNum(page, num), num);
        } catch (Exception e) {
            e.printStackTrace();
            //报错信息，错误信息插入日志表
            map.put("RTCODE", "error");
            map.put("RTMSG", "按照条件查询公告交易异常！请联系管理员！");
            map.put("RTDATA", e.getMessage());
            return map;
        }
        map.put("RTCODE", "success");
        map.put("RTMSG", "获取公告信息成功！");
        map.put("RTDATA", returnannouncement);
        return map;
    }

    /**
     * 函数名：查询函数 - 只查询5个最后日期的公告 - getAnnouncementAtHome（）
     * 功能描述：只查询5个最后日期的公告
     * 输入参数：<按照参数定义顺序>
     * <p>
     * <p>
     * 返回值：map
     * 异    常：无
     * 创建人：CMAPLE
     * 创建日期：2019-11-18
     * 修改人：
     * 修改日期：
     */
    @RequestMapping(value = "/getAnnouncementAtHome", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> getAnnouncementAtHome() {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Announcement> returnannouncement = null;
        try {
            //查询公告信息
            returnannouncement = announcementService.getAnnouncementAtHome();
        } catch (Exception e) {
            e.printStackTrace();
            //报错信息，错误信息插入日志表
            map.put("RTCODE", "error");
            map.put("RTMSG", "主页查询公告信息失败！请联系管理员！");
            map.put("RTDATA", e.getMessage());
            return map;
        }
        map.put("RTCODE", "success");
        map.put("RTMSG", "获取公告信息成功！");
        map.put("RTDATA", returnannouncement);
        return map;
    }

    /**
     * 函数名：插入函数 - 增加新的公告 - insertAnnouncement（）
     * 功能描述：增加新的公告
     * 输入参数：<按照参数定义顺序>
     *
     * @param title    String类型的标题
     * @param synopsis String类型的简介
     * @param content  String类型的内容，JSON格式
     * @param readtime String类型的阅读时间
     * @param footer   String类型的页尾内容，JSON格式
     *                 返回值：map
     *                 异    常：无
     *                 创建人：CMAPLE
     *                 创建日期：2019-11-18
     *                 修改人：
     *                 修改日期：
     */
    @RequestMapping(value = "/insertAnnouncement", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> insertAnnouncement(
            @RequestParam(value = "title", required = true) String title
            , @RequestParam(value = "synopsis", required = true) String synopsis
            , @RequestParam(value = "content", required = true) String content
            , @RequestParam(value = "readtime", required = true) String readtime
            , @RequestParam(value = "footer", required = true) String footer
    ) {
        //初始化参数
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        User sessionuser = (User) session.getAttribute("SSUSER");
        if (null == sessionuser) {
            map.put("RTCODE", "error");
            map.put("RTMSG", "请先登录，在创建公告信息！");
            map.put("RTDATA", null);
            return map;
        }
        Date insertdate = new Date();
        Announcement announcement = new Announcement(0, "N001-" + FormatTime.getFormatTime().formatYMDToString(insertdate) + FormatTime.getFormatTime().formatHMSToString(insertdate), title, synopsis, content, sessionuser.getTelephonenumber(), insertdate, readtime, footer);
        try {
            int returnannouncement = announcementService.insertAnnouncement(announcement);
            if (1 == returnannouncement) {
                map.put("RTCODE", "success");
                map.put("RTMSG", "公告信息创建成功！");
                map.put("RTDATA", null);
            } else {
                map.put("RTCODE", "error");
                map.put("RTMSG", "公告信息创建失败！");
                map.put("RTDATA", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            //报错信息，错误信息插入日志表
            map.put("RTCODE", "error");
            map.put("RTMSG", "创建公告信息失败！请联系管理员！");
            map.put("RTDATA", e.getMessage());
            return map;
        }
        return map;
    }

    /**
     * 函数名：更新函数 - 修改公告内容 - updateAnnouncement（）
     * 功能描述：修改公告内容
     * 输入参数：<按照参数定义顺序>
     *
     * @param id             int类型的id号
     * @param announcementid String类型的编号
     * @param title          String类型的标题
     * @param synopsis       String类型的简介
     * @param content        String类型的内容，JSON格式
     * @param readtime       String类型的阅读时间
     * @param footer         String类型的页尾内容，JSON格式
     *                       返回值：map
     *                       异    常：无
     *                       创建人：CMAPLE
     *                       创建日期：2019-11-18
     *                       修改人：
     *                       修改日期：
     */
    @RequestMapping(value = "/updateAnnouncement", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> updateAnnouncement(
            @RequestParam(value = "id", required = true) int id
            , @RequestParam(value = "announcementid", required = true) String announcementid
            , @RequestParam(value = "title", required = true) String title
            , @RequestParam(value = "synopsis", required = true) String synopsis
            , @RequestParam(value = "content", required = true) String content
            , @RequestParam(value = "readtime", required = true) String readtime
            , @RequestParam(value = "footer", required = true) String footer
    ) {
        //初始化参数
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        User sessionuser = (User) session.getAttribute("SSUSER");
        if (null == sessionuser) {
            map.put("RTCODE", "error");
            map.put("RTMSG", "请先登录，在更新公告信息！");
            map.put("RTDATA", null);
            return map;
        }
        Announcement announcement = new Announcement(id, announcementid, title, synopsis, content, sessionuser.getTelephonenumber(), null, readtime, footer);
        try {
            int returnannouncement = announcementService.updateAnnouncement(announcement);
            if (1 == returnannouncement) {
                map.put("RTCODE", "success");
                map.put("RTMSG", "公告信息更新成功！");
                map.put("RTDATA", null);
            } else {
                map.put("RTCODE", "error");
                map.put("RTMSG", "公告信息更新失败！");
                map.put("RTDATA", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            //报错信息，错误信息插入日志表
            map.put("RTCODE", "error");
            map.put("RTMSG", "更新公告信息失败！请联系管理员！");
            map.put("RTDATA", e.getMessage());
            return map;
        }
        return map;
    }

    /**
     * 函数名：删除函数 - 删除已经存在的公告 - delAnnouncement（）
     * 功能描述：删除已经存在的公告
     * 输入参数：<按照参数定义顺序>
     *
     * @param id int类型的id号
     *           返回值：map
     *           异    常：无
     *           创建人：CMAPLE
     *           创建日期：2019-11-18
     *           修改人：
     *           修改日期：
     */
    @RequestMapping(value = "/delAnnouncement", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> delAnnouncement(
            @RequestParam(value = "id", required = true) int id
    ) {
        //初始化参数
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        User sessionuser = (User) session.getAttribute("SSUSER");
        if (null == sessionuser) {
            map.put("RTCODE", "error");
            map.put("RTMSG", "请先登录，在删除公告信息！");
            map.put("RTDATA", null);
            return map;
        }
        try {
            int returnannouncement = announcementService.delAnnouncement(id);
            if (1 == returnannouncement) {
                map.put("RTCODE", "success");
                map.put("RTMSG", "公告信息删除成功！");
                map.put("RTDATA", null);
            } else {
                map.put("RTCODE", "error");
                map.put("RTMSG", "公告信息删除失败！");
                map.put("RTDATA", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            //报错信息，错误信息插入日志表
            map.put("RTCODE", "error");
            map.put("RTMSG", "删除公告信息失败！请联系管理员！");
            map.put("RTDATA", e.getMessage());
            return map;
        }
        return map;
    }

}
