package com.cmaple.honeycomb.controller;

import com.auth0.jwt.JWT;
import com.cmaple.honeycomb.Interface.PassToken;
import com.cmaple.honeycomb.Interface.UserLoginToken;
import com.cmaple.honeycomb.model.Milestone;
import com.cmaple.honeycomb.service.MilestoneService;
import com.cmaple.honeycomb.service.UserService;
import com.cmaple.honeycomb.tools.FileSelect;
import com.cmaple.honeycomb.tools.FormatTime;
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
 * 类名：里程碑管理服务控制器 - MilestoneController
 * 功能描述： 里程碑管理服务控制器
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：NULL
 * 创建人：cmaple
 * 创建日期：2019-11-11
 */
@RestController
@RequestMapping("/Milestone")
public class MilestoneController {
    /**
     * 引入MilestoneService
     */
    @Autowired
    private MilestoneService milestoneService;
    /**
     * 引入UserService
     */
    @Autowired
    private UserService userService;


    /**
     * 函数名：select函数 - 根据ID号查询里程碑内容 - selectById（）
     * 功能描述： 根据ID号查询里程碑内容
     * 输入参数：<按照参数定义顺序>
     *
     * @param id int类型的id号
     *           返回值：map
     *           异    常：NULL
     *           创建人：CMAPLE
     *           创建日期：2019-11-19
     */
    @PassToken
    @RequestMapping(value = "/selectById", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> selectById(
            @RequestParam(value = "id", required = true) int id
    ) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> datamap = new HashMap<String, Object>();
        Milestone returnmilestone = null;
        try {
            //查询里程碑信息信息
            returnmilestone = milestoneService.selectById(id);
            if (null == returnmilestone) {
                map.put("RTCODE", "error");
                map.put("RTMSG", "不存在此ID号的里程碑信息！");
                map.put("RTDATA", null);
                return map;
            }
            //处理里程碑信息
            Map<String, Object> upmap = FileSelect.getFileSelect().readFile(returnmilestone.getFilepath(), returnmilestone.getFilename());
            if (upmap.get("RTCODE").equals("success")) {
                datamap.put("content", upmap.get("RTDATA"));
                datamap.put("data", returnmilestone);
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
            map.put("RTMSG", "查询里程碑信息异常！请联系管理员！");
            map.put("RTDATA", e.getMessage());
            return map;
        }
        map.put("RTCODE", "success");
        map.put("RTMSG", "获取里程碑信息成功！");
        map.put("RTDATA", datamap);
        return map;
    }

    /**
     * 函数名：select函数 - 主页查询里程碑信息 - selectAtHomePage（）
     * 功能描述：主页查询里程碑信息
     * 输入参数：<按照参数定义顺序>
     * 返回值：map
     * 异    常：NULL
     * 创建人：CMAPLE
     * 创建日期：2019-11-19
     */
    @PassToken
    @RequestMapping(value = "/selectAtHomePage", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> selectAtHomePage() {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Milestone> returnmilestone = null;
        try {
            //查询公告信息
            returnmilestone = milestoneService.selectAtHomePage();
        } catch (Exception e) {
            e.printStackTrace();
            //报错信息，错误信息插入日志表
            map.put("RTCODE", "error");
            map.put("RTMSG", "主页查询里程碑信息失败！请联系管理员！");
            map.put("RTDATA", e.getMessage());
            return map;
        }
        map.put("RTCODE", "success");
        map.put("RTMSG", "获取里程碑信息成功！");
        map.put("RTDATA", returnmilestone);
        return map;
    }

    /**
     * 函数名：select函数 - 根据条件查询里程碑 - selectByCriteria（）
     * 功能描述：根据条件查询里程碑
     * 输入参数：<按照参数定义顺序>
     *
     * @param search       String类型的搜索内容
     * @param timeaxisdate list类型的时间范围
     * @param page         int类型的页数
     * @param num          int类型的数量
     *                     返回值：map
     *                     异    常：NULL
     *                     创建人：CMAPLE
     *                     创建日期：2019-11-20
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
        List<Milestone> returnmilestone = null;
        try {
            //查询公告信息
            returnmilestone = milestoneService.selectByCriteria(list, params, ParamsTools.getPageTools().getPageByNum(page, num), num);
        } catch (Exception e) {
            e.printStackTrace();
            //报错信息，错误信息插入日志表
            map.put("RTCODE", "error");
            map.put("RTMSG", "按照条件查询里程碑交易异常！请联系管理员！");
            map.put("RTDATA", e.getMessage());
            return map;
        }
        map.put("RTCODE", "success");
        map.put("RTMSG", "获取里程碑信息成功！");
        map.put("RTDATA", returnmilestone);
        return map;
    }

    /**
     * 函数名：select函数 - 按照时间倒叙查询里程碑信息 - selectDescOrderByTime（）
     * 功能描述：按照时间倒叙查询里程碑信息
     * 输入参数：<按照参数定义顺序>
     * 返回值：map
     * 异    常：NULL
     * 创建人：CMAPLE
     * 创建日期：2019-11-20
     */
    @PassToken
    @RequestMapping(value = "/selectDescOrderByTime", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> selectDescOrderByTime() {
        //初始化参数
        Map<String, Object> map = new HashMap<String, Object>();
        List<Milestone> returnmilestone = null;
        //查询里程碑信息
        try {
            //查询里程碑信息
            returnmilestone = milestoneService.selectDescOrderByTime();
        } catch (Exception e) {
            e.printStackTrace();
            //报错信息，错误信息插入日志表
            map.put("RTCODE", "error");
            map.put("RTMSG", "搜索里程碑交易异常！请联系管理员！");
            map.put("RTDATA", e.getMessage());
            return map;
        }
        map.put("RTCODE", "success");
        map.put("RTMSG", "获取里程碑信息成功！");
        map.put("RTDATA", returnmilestone);
        return map;
    }


    /**
     * 函数名：insert函数 - 增加新的里程碑 - insert（）
     * 功能描述：增加新的公告
     * 输入参数：<按照参数定义顺序>
     *
     * @param title       String类型的标题
     * @param synopsis    String类型的简介
     * @param readtime    String类型的阅读时间
     * @param filename    String类型的文件名称
     * @param filepath    String类型的文件路径
     * @param imgurl      String类型的图片路径
     * @param jpgfile     MultipartFile类型的文件
     * @param buttongroup String类型的按钮组，JSON格式
     * @param file        MultipartFile类型的文件
     *                    返回值：map
     *                    异    常：NULL
     *                    创建人：CMAPLE
     *                    创建日期：2019-11-20
     */
    @UserLoginToken
    @RequestMapping(value = "/insert", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> insert(
            @RequestParam(value = "title", required = true) String title
            , @RequestParam(value = "synopsis", required = true) String synopsis
            , @RequestParam(value = "readtime", required = true) String readtime
            , @RequestParam(value = "buttongroup", required = true) String buttongroup
            , @RequestParam(value = "filename", required = true) String filename
            , @RequestParam(value = "filepath", required = true) String filepath
            , @RequestParam(value = "file", required = true) MultipartFile file
            , @RequestParam(value = "imgurl", required = true) String imgurl
            , @RequestParam(value = "jpgfile", required = true) MultipartFile jpgfile
            , HttpServletRequest httpServletRequest
    ) {
        //初始化参数
        Map<String, Object> map = new HashMap<String, Object>();
        Date insertdate = new Date();
        String imgname = "IMG_Milestone" + FormatTime.getFormatTime().formatYMDToString(insertdate) + FormatTime.getFormatTime().formatHMSToString(insertdate) + ".jpg";
        //上传里程碑文件文件
        if ("success".equals(FileSelect.getFileSelect().checkFileExists(filepath, filename).get("RTCODE"))) {
            Map<String, Object> upmap = FileSelect.getFileSelect().uploadFile(file, filepath, filename);
            if (!"success".equals(upmap.get("RTCODE"))) {
                map.put("RTCODE", "error");
                map.put("RTMSG", "上传里程碑文件失败！请检查相关参数！");
                map.put("RTDATA", upmap.toString());
                return map;
            }
        } else {
            map.put("RTCODE", "error");
            map.put("RTMSG", "存在同名的里程碑文件！更改文件名或者删除同名文件方可继续！");
            map.put("RTDATA", "文件名：【" + filepath + "/" + filename + "】");
            return map;
        }
        //上传图片文件
        if ("success".equals(FileSelect.getFileSelect().checkFileExists(imgurl, imgname).get("RTCODE"))) {
            Map<String, Object> upmap = FileSelect.getFileSelect().uploadFile(jpgfile, imgurl, imgname);
            if (!"success".equals(upmap.get("RTCODE"))) {
                map.put("RTCODE", "error");
                map.put("RTMSG", "上传里程碑图片失败！请检查相关参数！");
                map.put("RTDATA", upmap.toString());
                return map;
            }
        } else {
            map.put("RTCODE", "error");
            map.put("RTMSG", "存在同名的图片文件！更改文件名或者删除同名文件方可继续！");
            map.put("RTDATA", "文件名：【" + imgurl + "/" + imgname + "】");
            return map;
        }
        String token = httpServletRequest.getHeader("token");
        String telephonenumber = JWT.decode(token).getAudience().get(0);
        //创建新的里程碑
        Milestone milestone = new Milestone(0, title, synopsis, telephonenumber, insertdate, readtime, filename, filepath, imgurl + "/" + imgname, buttongroup);
        try {
            int returnmilestone = milestoneService.insert(milestone);
            if (1 == returnmilestone) {
                map.put("RTCODE", "success");
                map.put("RTMSG", "里程碑信息创建成功！");
                map.put("RTDATA", null);
            } else {
                map.put("RTCODE", "error");
                map.put("RTMSG", "里程碑信息创建失败！");
                map.put("RTDATA", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            //报错信息，错误信息插入日志表
            map.put("RTCODE", "error");
            map.put("RTMSG", "创建里程碑信息失败！请联系管理员！");
            map.put("RTDATA", e.getMessage());
            return map;
        }
        return map;
    }

    /**
     * 函数名：update函数-更新里程碑信息 - update（）
     * 功能描述：更新里程碑信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param id          int类型id
     * @param title       String类型的标题
     * @param synopsis    String类型的简介
     * @param readtime    String类型的阅读时间
     * @param filename    String类型的文件名称
     * @param filepath    String类型的文件路径
     * @param imgurl      String类型的图片路径
     * @param jpgfile     MultipartFile类型的文件
     * @param buttongroup String类型的按钮组，JSON格式
     * @param file        MultipartFile类型的文件
     *                    返回值：map
     *                    异    常：NULL
     *                    创建人：CMAPLE
     *                    创建日期：2019-11-21
     */
    @UserLoginToken
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> update(
            @RequestParam(value = "id", required = true) int id
            , @RequestParam(value = "title", required = true) String title
            , @RequestParam(value = "synopsis", required = true) String synopsis
            , @RequestParam(value = "readtime", required = true) String readtime
            , @RequestParam(value = "buttongroup", required = true) String buttongroup
            , @RequestParam(value = "filename", required = true) String filename
            , @RequestParam(value = "filepath", required = true) String filepath
            , @RequestParam(value = "file", required = true) MultipartFile file
            , @RequestParam(value = "imgurl", required = true) String imgurl
            , @RequestParam(value = "jpgfile", required = true) MultipartFile jpgfile
            , HttpServletRequest httpServletRequest
    ) {
        //初始化参数
        Map<String, Object> map = new HashMap<String, Object>();
        //上传里程碑文件文件
        if (!"success".equals(FileSelect.getFileSelect().checkFileExists(filepath, filename).get("RTCODE"))) {
            Map<String, Object> delmap = FileSelect.getFileSelect().delFile(filepath + "/" + filename);
            Map<String, Object> upmap = FileSelect.getFileSelect().uploadFile(file, filepath, filename);
            if (!"success".equals(delmap.get("RTCODE")) || !"success".equals(upmap.get("RTCODE"))) {
                map.put("RTCODE", "error");
                map.put("RTMSG", "更新里程碑文件失败！请检查相关参数！");
                map.put("RTDATA", delmap.toString() + "|||" + upmap.toString());
                return map;
            }
        } else {
            map.put("RTCODE", "error");
            map.put("RTMSG", "不存在同名的里程碑文件！请填写正确的里程碑文件名！");
            map.put("RTDATA", "文件名：【" + filepath + "/" + filename + "】");
            return map;
        }
        //上传图片文件
        if (!"success".equals(FileSelect.getFileSelect().checkFileExists(imgurl, null).get("RTCODE"))) {
            Map<String, Object> delmap = FileSelect.getFileSelect().delFile(imgurl);
            Map<String, Object> upmap = FileSelect.getFileSelect().uploadFile(jpgfile, imgurl, null);
            if (!"success".equals(delmap.get("RTCODE")) || !"success".equals(upmap.get("RTCODE"))) {
                map.put("RTCODE", "error");
                map.put("RTMSG", "更新里程碑图片失败！请检查相关参数！");
                map.put("RTDATA", delmap.toString() + "|||" + upmap.toString());
                return map;
            }
        } else {
            map.put("RTCODE", "error");
            map.put("RTMSG", "不存在同名的图片文件！请填写正确的里程碑图片名！");
            map.put("RTDATA", "文件名：【" + imgurl + "】");
            return map;
        }
        String token = httpServletRequest.getHeader("token");
        String telephonenumber = JWT.decode(token).getAudience().get(0);
        //更新里程碑
        Milestone milestone = new Milestone(id, title, synopsis, telephonenumber, new Date(), readtime, filename, filepath, imgurl, buttongroup);
        try {
            int returnmilestone = milestoneService.update(milestone);
            if (1 == returnmilestone) {
                map.put("RTCODE", "success");
                map.put("RTMSG", "里程碑信息修改成功！");
                map.put("RTDATA", null);
            } else {
                map.put("RTCODE", "error");
                map.put("RTMSG", "里程碑信息修改失败！");
                map.put("RTDATA", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            //报错信息，错误信息插入日志表
            map.put("RTCODE", "error");
            map.put("RTMSG", "修改里程碑信息失败！请联系管理员！");
            map.put("RTDATA", e.getMessage());
            return map;
        }
        return map;
    }


    /**
     * 函数名：delete函数-删除里程碑信息 - deleteById（）
     * 功能描述：删除里程碑信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param id int类型id
     *           返回值：map
     *           异    常：NULL
     *           创建人：CMAPLE
     *           创建日期：2019-11-22
     */
    @UserLoginToken
    @RequestMapping(value = "/deleteById", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> deleteById(
            @RequestParam(value = "id", required = true) int id
    ) {
        //初始化参数
        Map<String, Object> map = new HashMap<String, Object>();
        //删除里程碑
        try {
            //删除相应的文件
            Milestone delmilestone = milestoneService.selectById(id);
            //删除里程碑文件
            Map<String, Object> delfile = FileSelect.getFileSelect().delFile(delmilestone.getFilepath() + "/" + delmilestone.getFilename());
            if (!"success".equals(delfile.get("RTCODE"))) {
                map.put("RTCODE", "error");
                map.put("RTMSG", "上传里程碑图片失败！请检查相关参数！");
                map.put("RTDATA", delfile.toString());
                return map;
            }
            //删除里程碑图片
            Map<String, Object> delimg = FileSelect.getFileSelect().delFile(delmilestone.getImgurl());
            if (!"success".equals(delimg.get("RTCODE"))) {
                map.put("RTCODE", "error");
                map.put("RTMSG", "上传里程碑图片失败！请检查相关参数！");
                map.put("RTDATA", delimg.toString());
                return map;
            }
            //删除数据库记录
            int returnmilestone = milestoneService.deleteById(id);
            if (1 == returnmilestone) {
                map.put("RTCODE", "success");
                map.put("RTMSG", "里程碑信息删除成功！");
                map.put("RTDATA", null);
            } else {
                map.put("RTCODE", "error");
                map.put("RTMSG", "里程碑信息删除失败！");
                map.put("RTDATA", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            //报错信息，错误信息插入日志表
            map.put("RTCODE", "error");
            map.put("RTMSG", "删除里程碑信息失败！请联系管理员！");
            map.put("RTDATA", e.getMessage());
            return map;
        }
        return map;
    }
}
