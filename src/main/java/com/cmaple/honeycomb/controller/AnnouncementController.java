package com.cmaple.honeycomb.controller;

import com.cmaple.honeycomb.model.Announcement;
import com.cmaple.honeycomb.model.User;
import com.cmaple.honeycomb.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

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
     * <p>
     * <p>
     * 返回值：map
     * 异    常：无
     * 创建人：CMAPLE
     * 创建日期：2019-11-11
     * 修改人：
     * 修改日期：
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
            map.put("RTMSG", "请先登录，在注册后台服务！");
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
}
