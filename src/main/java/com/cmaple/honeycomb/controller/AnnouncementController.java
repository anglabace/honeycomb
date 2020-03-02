package com.cmaple.honeycomb.controller;

import com.auth0.jwt.JWT;
import com.cmaple.honeycomb.Interface.PassToken;
import com.cmaple.honeycomb.Interface.UserLoginToken;
import com.cmaple.honeycomb.model.Announcement;
import com.cmaple.honeycomb.model.User;
import com.cmaple.honeycomb.service.AnnouncementService;
import com.cmaple.honeycomb.service.UserService;
import com.cmaple.honeycomb.tools.FileSelect;
import com.cmaple.honeycomb.tools.ParamsTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 类名：公告管理服务控制器 - AnnouncementController
 * 功能描述： 公告管理服务接口
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：NULL
 * 创建人：CMAPLE
 * 创建日期：2019-11-11
 */
@RestController
@RequestMapping("/Announcement")
public class AnnouncementController {
    /**
     * 引入AnnouncementService
     */
    @Autowired
    private AnnouncementService announcementService;
    /**
     * 引入UserService
     */
    @Autowired
    private UserService userService;

    /**
     * 函数名：select函数 - 根据条件查询公告信息 - selectByCriteria（）
     * 功能描述： 根据条件查询公告信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param search       String类型的搜索内容
     * @param timeaxisdate list类型的时间范围
     * @param page         int类型的页数
     * @param num          int类型的数量
     *                     返回值：Map
     *                     异    常：NULL
     *                     创建人：CMAPLE
     *                     创建日期：2019-11-18
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
        List<Announcement> returnannouncement = null;
        try {
            //查询公告信息
            returnannouncement = announcementService.selectByCriteria(list, params, ParamsTools.getPageTools().getPageByNum(page, num), num);
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
     * 函数名：select函数 - 按照时间倒叙分页查询公告列表 - selectDescOrderByTime（）
     * 功能描述： 按照时间倒叙分页查询公告列表
     * 输入参数：<按照参数定义顺序>
     *
     * @param page int类型的页数
     *             返回值：Map
     *             异    常：NULL
     *             创建人：CMAPLE
     *             创建日期：2019-12-24
     */
    @PassToken
    @RequestMapping(value = "/selectDescOrderByTime", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> selectDescOrderByTime(
            @RequestParam(value = "page", required = true) int page
    ) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> datamap = new HashMap<String, Object>();
        List<Announcement> returnannouncement = null;
        int total = 0;
        try {
            //查询公告信息
            returnannouncement = announcementService.selectDescOrderByTime(ParamsTools.getPageTools().getPageByNum(page, 20), 20);
            total = announcementService.selectCount();
            datamap.put("total", total);
            datamap.put("data", returnannouncement);
        } catch (Exception e) {
            e.printStackTrace();
            //报错信息，错误信息插入日志表
            map.put("RTCODE", "error");
            map.put("RTMSG", "按照时间倒叙分页查询公告信息交易异常！请联系管理员！");
            map.put("RTDATA", e.getMessage());
            return map;
        }
        map.put("RTCODE", "success");
        map.put("RTMSG", "获取公告信息成功！");
        map.put("RTDATA", datamap);
        return map;
    }

    /**
     * 函数名：select函数 - 主页查询公告列表 - selectAtHomePage（）
     * 功能描述：主页查询公告列表
     * 输入参数：<按照参数定义顺序>
     * 返回值：Map
     * 异    常：NULL
     * 创建人：CMAPLE
     * 创建日期：2019-11-18
     */
    @PassToken
    @RequestMapping(value = "/selectAtHomePage", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> selectAtHomePage() {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Announcement> returnannouncement = null;
        try {
            //查询公告信息
            returnannouncement = announcementService.selectAtHomePage();
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
     * 函数名：select函数 - 根据ID号查询公告信息 - selectById（）
     * 功能描述： 根据ID号查询公告信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param id int类型的id号
     *           返回值：Map
     *           异    常：NULL
     *           创建人：CMAPLE
     *           创建日期：2019-11-11
     */
    @PassToken
    @RequestMapping(value = "/selectById", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> selectById(
            @RequestParam(value = "id", required = true) int id
    ) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> datamap = new HashMap<String, Object>();
        Announcement returnannouncement = null;
        try {
            //查询公告信息
            returnannouncement = announcementService.selectById(id);
            if (null == returnannouncement) {
                map.put("RTCODE", "error");
                map.put("RTMSG", "不存在此ID号的公告！");
                map.put("RTDATA", null);
                return map;
            }
            //处理公告信息
            User user = userService.selectByTelephonenumber(returnannouncement.getAuthor());
            returnannouncement.setAuthor(user.getPetname());
            Map<String, Object> upmap = FileSelect.getFileSelect().readFile(returnannouncement.getFilepath(), returnannouncement.getFilename());
            if (upmap.get("RTCODE").equals("success")) {
                datamap.put("content", upmap.get("RTDATA"));
                datamap.put("data", returnannouncement);
            } else {
                map.put("RTCODE", "error");
                map.put("RTMSG", "根据ID号查询里程碑内容 - 读取文件内容异常！");
                map.put("RTDATA", upmap.get("RTDATA"));
                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();
            //报错信息，错误信息插入日志表
            map.put("RTCODE", "error");
            map.put("RTMSG", "查询公告信息异常！请联系管理员！");
            map.put("RTDATA", e.getMessage());
            return map;
        }
        map.put("RTCODE", "success");
        map.put("RTMSG", "获取公告信息成功！");
        map.put("RTDATA", datamap);
        return map;
    }

    /**
     * 函数名：insert函数 - 创建新的公告信息 - insert（）
     * 功能描述：创建新的公告信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param title    String类型的标题
     * @param synopsis String类型的简介
     * @param filename String类型的文件名
     * @param filepath String类型的文件路径
     * @param file     MultipartFile类型的文件
     * @param readtime String类型的阅读时间
     *                 返回值：Map
     *                 异    常：NULL
     *                 创建人：CMAPLE
     *                 创建日期：2019-11-18
     */
    @UserLoginToken
    @RequestMapping(value = "/insert", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> insert(
            @RequestParam(value = "title", required = true) String title
            , @RequestParam(value = "synopsis", required = true) String synopsis
            , @RequestParam(value = "filename", required = true) String filename
            , @RequestParam(value = "filepath", required = true) String filepath
            , @RequestParam(value = "file", required = true) MultipartFile file
            , @RequestParam(value = "readtime", required = true) String readtime
            , HttpServletRequest httpServletRequest
    ) {
        //初始化参数
        Map<String, Object> map = new HashMap<String, Object>();
        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
        String telephonenumber = JWT.decode(token).getAudience().get(0);
        Date insertdate = new Date();
        //上传公告文件
        if ("success".equals(FileSelect.getFileSelect().checkFileExists(filepath, filename).get("RTCODE"))) {
            Map<String, Object> upmap = FileSelect.getFileSelect().uploadFile(file, filepath, filename);
            if (!"success".equals(upmap.get("RTCODE"))) {
                map.put("RTCODE", "error");
                map.put("RTMSG", "上传公告文件失败！请检查相关参数！");
                map.put("RTDATA", upmap.toString());
                return map;
            }
        } else {
            map.put("RTCODE", "error");
            map.put("RTMSG", "存在同名的里程碑文件！更改文件名或者删除同名文件方可继续！");
            map.put("RTDATA", "文件名：【" + filepath + "/" + filename + "】");
            return map;
        }
        Announcement announcement = new Announcement(0, title, synopsis, filename, filepath, telephonenumber, insertdate, readtime);
        try {
            int returnannouncement = announcementService.insert(announcement);
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
     * 函数名：update函数 - 修改公告信息 - update（）
     * 功能描述：修改公告信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param id       int类型的id号
     * @param title    String类型的标题
     * @param synopsis String类型的简介
     * @param filename String类型的文件名
     * @param filepath String类型的文件路径
     * @param file     MultipartFile类型的文件
     * @param readtime String类型的阅读时间
     *                 返回值：Map
     *                 异    常：NULL
     *                 创建人：CMAPLE
     *                 创建日期：2019-11-18
     */
    @UserLoginToken
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> update(
            @RequestParam(value = "id", required = true) int id
            , @RequestParam(value = "title", required = true) String title
            , @RequestParam(value = "synopsis", required = true) String synopsis
            , @RequestParam(value = "filename", required = true) String filename
            , @RequestParam(value = "filepath", required = true) String filepath
            , @RequestParam(value = "file", required = true) MultipartFile file
            , @RequestParam(value = "readtime", required = true) String readtime
            , HttpServletRequest httpServletRequest
    ) {
        //初始化参数
        Map<String, Object> map = new HashMap<String, Object>();
        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
        String telephonenumber = JWT.decode(token).getAudience().get(0);
        Announcement announcement = new Announcement(0, title, synopsis, filename, filepath, telephonenumber, null, readtime);
        try {
            int returnannouncement = announcementService.update(announcement);
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
     * 函数名：delete函数 - 删除已经存在的公告 - deleteById（）
     * 功能描述：删除已经存在的公告
     * 输入参数：<按照参数定义顺序>
     *
     * @param id int类型的id号
     *           返回值：Map
     *           异    常：NULL
     *           创建人：CMAPLE
     *           创建日期：2019-11-18
     */
    @UserLoginToken
    @RequestMapping(value = "/deleteById", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> deleteById(
            @RequestParam(value = "id", required = true) int id
    ) {
        //初始化参数
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            int returnannouncement = announcementService.deleteById(id);
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