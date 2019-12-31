package com.cmaple.honeycomb.controller;

import com.cmaple.honeycomb.model.BackgroundService;
import com.cmaple.honeycomb.model.ToolsFile;
import com.cmaple.honeycomb.model.User;
import com.cmaple.honeycomb.service.BackgroundServiceService;
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
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * 类名：后台管理服务控制器 - BackgroundServiceController
 * 功能描述： 后台管理服务控制器
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：NULL
 * 创建人：cmaple
 * 创建日期：2019-09-30
 */
@RestController
@RequestMapping("/BackgroundService")
public class BackgroundServiceController {
    /**
     * 引入BackgroundServiceService
     */
    @Autowired
    private BackgroundServiceService backgroundService;
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
     * 函数名：select函数 - 根据条件获取后台服务列表 - selectByCriteria（）
     * 功能描述：根据条件获取后台服务列表
     * 输入参数：<按照参数定义顺序>
     *
     * @param servicestate String类型的服务状态
     * @param proglanguage String类型的服务语言
     * @param search       String类型的搜索内容
     * @param page         int类型的页数
     * @param num          int类型的展示数量
     *                     返回值：map
     *                     异    常：NULL
     *                     创建人：CMAPLE
     *                     创建日期：2019-09-30
     */
    @RequestMapping(value = "/selectByCriteria", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> selectByCriteria(
            @RequestParam(value = "servicestate", required = true) String servicestate
            , @RequestParam(value = "proglanguage", required = true) String proglanguage
            , @RequestParam(value = "search", required = true) String search
            , @RequestParam(value = "page", required = true) int page
            , @RequestParam(value = "num", required = true) int num
    ) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        List<String> list = new ArrayList<String>();
        Map<String, Object> params = new HashMap<String, Object>();
        //获取信息
        User sessionuser = (User) session.getAttribute("SSUSER");
        if (null == sessionuser) {
            map.put("RTCODE", "error");
            map.put("RTMSG", "请先登录，在查询公告信息！");
            map.put("RTDATA", null);
            return map;
        }
        //条件整理
        if (!"all".equals(servicestate)) {
            list.add("servicestate");
            params.put("servicestate", servicestate);
        }
        if (!"all".equals(proglanguage)) {
            list.add("proglanguage");
            params.put("proglanguage", proglanguage);
        }
        if (!"".equals(search)) {
            list.add("search");
            params.put("search", search);
        }
        List<BackgroundService> returnBackgroundService = null;
        try {
            //根据条件查询
            returnBackgroundService = backgroundService.selectByCriteria(list, params, ParamsTools.getPageTools().getPageByNum(page, num), num);
        } catch (Exception e) {
            //报错信息，错误信息插入日志表
            map.put("RTCODE", "error");
            map.put("RTMSG", "获取后台服务信息异常！请联系管理员！");
            map.put("RTDATA", e.getMessage());
            return map;
        }
        //返回成功信息
        map.put("RTCODE", "success");
        map.put("RTMSG", "获取后台服务信息成功！");
        map.put("RTDATA", returnBackgroundService);
        return map;
    }

    /**
     * 函数名：select函数 - 根据ID获取后台服务信息 - selectById（）
     * 功能描述：根据ID获取后台服务信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param id int类型的服务器id号
     *           返回值：map
     *           异    常：NULL
     *           创建人：CMAPLE
     *           创建日期：2019-10-28
     */
    @RequestMapping(value = "/selectById", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> selectById(
            @RequestParam(value = "id", required = true) int id
    ) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> datamap = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        BackgroundService returnBackgroundService = null;
        try {
            //根据条件查询
            returnBackgroundService = backgroundService.selectById(id);
            if (null == returnBackgroundService) {
                map.put("RTCODE", "error");
                map.put("RTMSG", "不存在此ID号的后台服务！");
                map.put("RTDATA", null);
                return map;
            }
            //处理公告信息
            User user = userService.selectByTelephonenumber(returnBackgroundService.getAuthor());
            returnBackgroundService.setAuthor(user.getPetname());
            //读取文件内容
            Map<String, Object> upmap = FileSelect.getFileSelect().readFile(returnBackgroundService.getAnnexepath() + "/" + returnBackgroundService.getName() + "/" + returnBackgroundService.getVersion(), "backgroundservice.txt");
            if (upmap.get("RTCODE").equals("success")) {
                datamap.put("content", upmap.get("RTDATA"));
                datamap.put("data", returnBackgroundService);
            } else {
                map.put("RTCODE", "error");
                map.put("RTMSG", "根据ID号查询里程碑内容 - 读取文件内容异常！");
                map.put("RTDATA", upmap.get("RTDATA"));
                return map;
            }
        } catch (Exception e) {
            //报错信息，错误信息插入日志表
            map.put("RTCODE", "error");
            map.put("RTMSG", "获取后台服务信息异常！请联系管理员！");
            map.put("RTDATA", e.getMessage());
            return map;
        }
        //返回成功信息
        map.put("RTCODE", "success");
        map.put("RTMSG", "获取后台服务信息成功！");
        map.put("RTDATA", datamap);
        return map;
    }

    /**
     * 函数名：select函数 - 按照时间倒叙获取后台服务详情 - selectDescOrderByTime（）
     * 功能描述：按照时间倒叙获取后台服务详情
     * 输入参数：<按照参数定义顺序>
     * 返回值：map
     * 异    常：NULL
     * 创建人：CMAPLE
     * 创建日期：2019-12-24
     */
    @RequestMapping(value = "/selectDescOrderByTime", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> selectDescOrderByTime() {
        Map<String, Object> map = new HashMap<String, Object>();
        List<BackgroundService> returnBackgroundService = null;
        try {
            //根据条件查询
            returnBackgroundService = backgroundService.selectDescOrderByTime();
        } catch (Exception e) {
            //报错信息，错误信息插入日志表
            map.put("RTCODE", "error");
            map.put("RTMSG", "获取后台服务信息异常！请联系管理员！");
            map.put("RTDATA", e.getMessage());
            return map;
        }
        //返回成功信息
        map.put("RTCODE", "success");
        map.put("RTMSG", "获取后台服务信息成功！");
        map.put("RTDATA", returnBackgroundService);
        return map;
    }

    /**
     * 函数名：insert函数 - 注册后台服务 - insert（）
     * 功能描述：注册后台服务
     * 输入参数：<按照参数定义顺序>
     *
     * @param serviceid    String类型的服务ID号
     * @param name         String类型的服务名称
     * @param synopsis     String类型的服务简介
     * @param version      String类型的服务版本号
     * @param path         String类型的服务服务存放路径
     * @param route        String类型的服务接口地址
     * @param proglanguage String类型的服务语言类型
     * @param receivetype  String类型的服务接受数据类型
     * @param author       String类型的服务作者
     * @param serverid     String类型的服务所在服务器编号
     * @param files        String类型的服务文件
     *                     返回值：map
     *                     异    常：NULL
     *                     创建人：CMAPLE
     *                     创建日期：2019-09-30
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> insert(
            @RequestParam(value = "serviceid", required = true) String serviceid
            , @RequestParam(value = "name", required = true) String name
            , @RequestParam(value = "synopsis", required = true) String synopsis
            , @RequestParam(value = "version", required = true) String version
            , @RequestParam(value = "path", required = true) String path
            , @RequestParam(value = "route", required = true) String route //接口地址需要检查
            , @RequestParam(value = "proglanguage", required = true) String proglanguage
            , @RequestParam(value = "receivetype", required = true) String receivetype
            , @RequestParam(value = "author", required = true) String author   //作者,界面上选择
            , @RequestParam(value = "serverid", required = true) String serverid   //页面上选择
            , @RequestParam(value = "files", required = true) List<MultipartFile> files
    ) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        Map<String, Object> remap = new HashMap<String, Object>();
        //获取信息
        User sessionuser = (User) session.getAttribute("SSUSER");
        if (null == sessionuser) {
            map.put("RTCODE", "error");
            map.put("RTMSG", "请先登录，在查询公告信息！");
            map.put("RTDATA", null);
            return map;
        }
        try {
            //检查服务名和服务id(后续添加)
            //创建服务文件夹
            if ("success".equals(FileSelect.getFileSelect().checkFileExists(path, name).get("RTCODE"))) {
                FileSelect.getFileSelect().createDirectory(path, name);
            }
            //创建版本文件夹
            if ("success".equals(FileSelect.getFileSelect().checkFileExists(path + "/" + name, version).get("RTCODE"))) {
                FileSelect.getFileSelect().createDirectory(path + "/" + name, version);
            }
            //上传服务文件
            for (int i = 0; i < files.size(); i++) {
                if ("success".equals(FileSelect.getFileSelect().checkFileExists(path + "/" + name + "/" + version, files.get(i).getOriginalFilename()).get("RTCODE"))) {
                    FileSelect.getFileSelect().uploadFile(files.get(i), path + "/" + name + "/" + version, files.get(i).getOriginalFilename());
                }
            }
            //返回服务文件夹下所有文件信息
            String filesize = null;
            Map<String, Object> filesmap = FileSelect.getFileSelect().getfileMap(path + "/" + name + "/" + version);
            ArrayList<ToolsFile> ToolsFiles = (ArrayList<ToolsFile>) filesmap.get("RTDATA");
            if (0 < ToolsFiles.size()) {
                for (int i = 0; i < ToolsFiles.size(); i++) {
                    if ("war".equals(ToolsFiles.get(i).getSuffix())) {
                        filesize = ToolsFiles.get(i).getFilesize();
                    }
                }
            }
            //注册后台服务
            int returnBackgroundService = backgroundService.insert(new BackgroundService(0, serviceid, name, synopsis, version, path, route + "/" + name, filesize, proglanguage, receivetype, author, sessionuser.getTelephonenumber(), new Date(), serverid, "lock", path + "/" + name));
            if (1 == returnBackgroundService) {
                map.put("RTCODE", "success");
                map.put("RTMSG", "后台服务注册成功！");
                map.put("RTDATA", null);
            } else {
                map.put("RTCODE", "error");
                map.put("RTMSG", "后台服务注册失败！");
                map.put("RTDATA", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            //报错信息，错误信息插入日志表
            map.put("RTCODE", "error");
            map.put("RTMSG", "获取后台服务信息异常！请联系管理员！");
            map.put("RTDATA", e.getMessage());
            return map;
        }
        return map;
    }

    /**
     * 函数名：update函数 - 锁定后台服务 - lock（）
     * 功能描述：锁定后台服务
     * 输入参数：<按照参数定义顺序>
     *
     * @param id int类型的服务器id号
     *           返回值：map
     *           异    常：NULL
     *           创建人：CMAPLE
     *           创建日期：2019-10-17
     */
    @RequestMapping(value = "/lock", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> lock(
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
        BackgroundService bs = new BackgroundService();
        bs.setId(id);
        bs.setServicestate("lock");
        try {
            BackgroundService returnbackgroundService = backgroundService.selectById(id);
            if (!"run".equals(returnbackgroundService.getServicestate())) {
                map.put("RTCODE", "error");
                map.put("RTMSG", "后台服务处于非运行状态，不可以锁定！");
                map.put("RTDATA", returnbackgroundService.getName() + " - 服务状态 - " + returnbackgroundService.getServicestate());
                return map;
            }
            //注册后台服务
            int returnBackgroundService = backgroundService.update(bs);
            if (1 != returnBackgroundService) {
                map.put("RTCODE", "error");
                map.put("RTMSG", "后台服务锁定失败！");
                map.put("RTDATA", null);
            } else {
                map.put("RTCODE", "success");
                map.put("RTMSG", "后台服务锁定成功！");
                map.put("RTDATA", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            //报错信息，错误信息插入日志表
            map.put("RTCODE", "error");
            map.put("RTMSG", "锁定后台服务信息异常！请联系管理员！");
            map.put("RTDATA", e.getMessage());
            return map;
        }
        return map;
    }

    /**
     * 函数名：update函数 - 解锁后台服务 - unLock（）
     * 功能描述：解锁后台服务
     * 输入参数：<按照参数定义顺序>
     * <p>
     *
     * @param id int类型的服务器id号
     *           返回值：map
     *           异    常：NULL
     *           创建人：CMAPLE
     *           创建日期：2019-10-17
     */
    @RequestMapping(value = "/unLock", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> unLock(
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
        BackgroundService bs = new BackgroundService();
        bs.setId(id);
        bs.setServicestate("run");
        try {
            BackgroundService returnbackgroundService = backgroundService.selectById(id);
            if (!"lock".equals(returnbackgroundService.getServicestate())) {
                map.put("RTCODE", "error");
                map.put("RTMSG", "后台服务处于非锁定状态，不可以锁定！");
                map.put("RTDATA", returnbackgroundService.getName() + " - 服务状态 - " + returnbackgroundService.getServicestate());
                return map;
            }
            //注册后台服务
            int returnBackgroundService = backgroundService.update(bs);
            if (1 != returnBackgroundService) {
                map.put("RTCODE", "error");
                map.put("RTMSG", "后台服务解锁失败！");
                map.put("RTDATA", null);
            } else {
                map.put("RTCODE", "success");
                map.put("RTMSG", "后台服务解锁成功！");
                map.put("RTDATA", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            //报错信息，错误信息插入日志表
            map.put("RTCODE", "error");
            map.put("RTMSG", "解锁后台服务信息异常！请联系管理员！");
            map.put("RTDATA", e.getMessage());
            return map;
        }
        return map;
    }

    /**
     * 函数名：update函数 - 更新后台服务信息 - updateBackgroundService（）
     * 功能描述：更新后台服务信息，包括服务相关信息及服务相关文件
     * 输入参数：<按照参数定义顺序>
     *
     * @param id           int类型的服务器id号
     * @param serviceid    String类型的服务ID号
     * @param name         String类型的服务名称
     * @param synopsis     String类型的服务简介
     * @param version      String类型的服务版本号
     * @param path         String类型的服务服务存放路径
     * @param route        String类型的服务接口地址
     * @param proglanguage String类型的服务语言类型
     * @param receivetype  String类型的服务接受数据类型
     * @param author       String类型的服务作者
     * @param serverid     String类型的服务所在服务器编号
     * @param files        String类型的服务文件
     *                     返回值：map
     *                     异    常：NULL
     *                     创建人：CMAPLE
     *                     创建日期：2019-10-28
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> update(
            @RequestParam(value = "id", required = true) int id
            , @RequestParam(value = "serviceid", required = true) String serviceid
            , @RequestParam(value = "name", required = true) String name
            , @RequestParam(value = "synopsis", required = true) String synopsis
            , @RequestParam(value = "version", required = true) String version
            , @RequestParam(value = "path", required = true) String path
            , @RequestParam(value = "route", required = true) String route //接口地址需要检查
            , @RequestParam(value = "proglanguage", required = true) String proglanguage
            , @RequestParam(value = "receivetype", required = true) String receivetype
            , @RequestParam(value = "author", required = true) String author   //作者,界面上选择
            , @RequestParam(value = "serverid", required = true) String serverid   //页面上选择
            , @RequestParam(value = "files", required = true) List<MultipartFile> files
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
        //判断服务根目录文件夹是否存在
        if ("success".equals(FileSelect.getFileSelect().checkFileExists(path, name).get("RTCODE"))) {
            map.put("RTCODE", "error");
            map.put("RTMSG", "后台服务根目录不存在，请检查并输入正确的服务名称重试！");
            map.put("RTDATA", null);
            return map;
        }
        //创建版本文件夹
        if ("success".equals(FileSelect.getFileSelect().checkFileExists(path + "/" + name, version).get("RTCODE"))) {
            FileSelect.getFileSelect().createDirectory(path + "/" + name, version);
        }
        //上传服务文件
        for (int i = 0; i < files.size(); i++) {
            if ("success".equals(FileSelect.getFileSelect().checkFileExists(path + "/" + name + "/" + version, files.get(i).getOriginalFilename()).get("RTCODE"))) {
                FileSelect.getFileSelect().uploadFile(files.get(i), path + "/" + name + "/" + version, files.get(i).getOriginalFilename());
            }
        }
        //返回服务文件夹下所有文件信息
        String filesize = null;
        Map<String, Object> filesmap = FileSelect.getFileSelect().getfileMap(path + "/" + name + "/" + version);
        ArrayList<ToolsFile> ToolsFiles = (ArrayList<ToolsFile>) filesmap.get("RTDATA");
        if (0 < ToolsFiles.size()) {
            for (int i = 0; i < ToolsFiles.size(); i++) {
                if ("war".equals(ToolsFiles.get(i).getSuffix())) {
                    filesize = ToolsFiles.get(i).getFilesize();
                }
            }
        }
        BackgroundService updatebs = new BackgroundService(id, serviceid, name, synopsis, version, path, null, filesize, proglanguage, receivetype, author, sessionuser.getTelephonenumber(), new Date(), serverid, "lock", null);
        try {
            int returnBackgroundService = backgroundService.update(updatebs);
            if (1 == returnBackgroundService) {
                map.put("RTCODE", "success");
                map.put("RTMSG", "后台服务更新成功！");
                map.put("RTDATA", null);
            } else {
                map.put("RTCODE", "error");
                map.put("RTMSG", "后台服务更新失败！");
                map.put("RTDATA", null);
            }
        } catch (Exception e) {
            //删除刚刚创建的版本文件
            Map<String, Object> fallbackfilesmap = FileSelect.getFileSelect().getfileMap(path + "/" + name + "/" + version);
            ArrayList<ToolsFile> fallbackToolsFiles = (ArrayList<ToolsFile>) fallbackfilesmap.get("RTDATA");
            if (0 < fallbackToolsFiles.size()) {
                for (int i = 0; i < fallbackToolsFiles.size(); i++) {
                    FileSelect.getFileSelect().delFile(fallbackToolsFiles.get(i).getFilepath());
                }
            }
            e.printStackTrace();
            //报错信息，错误信息插入日志表
            map.put("RTCODE", "error");
            map.put("RTMSG", "编辑后台服务信息异常！请联系管理员！");
            map.put("RTDATA", e.getMessage());
            return map;
        }
        return map;
    }

    /**
     * 函数名：delect函数 - 删除后台服务 - delete（）
     * 功能描述：删除后台服务
     * 输入参数：<按照参数定义顺序>
     *
     * @param id int类型的服务器id号
     *           返回值：map
     *           异    常：NULL
     *           创建人：CMAPLE
     *           创建日期：2019-10-28
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> delete(
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
        try {
            //判断是否为正常状态
            BackgroundService returnbackgroundService = backgroundService.selectById(id);
            if (!"run".equals(returnbackgroundService.getServicestate())) {
                map.put("RTCODE", "error");
                map.put("RTMSG", "后台服务输入非运行状态，不可以删除！");
                map.put("RTDATA", returnbackgroundService.getName() + " - 服务状态 - " + returnbackgroundService.getServicestate());
                return map;
            }
            //删除后台服务操作
            BackgroundService bs = new BackgroundService();
            bs.setId(id);
            bs.setServicestate("del");
            int returnBackgroundService = backgroundService.update(bs);
            if (1 != returnBackgroundService) {
                map.put("RTCODE", "error");
                map.put("RTMSG", "后台服务删除失败！");
                map.put("RTDATA", null);
            } else {
                map.put("RTCODE", "success");
                map.put("RTMSG", "后台服务删除成功！");
                map.put("RTDATA", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            //报错信息，错误信息插入日志表
            map.put("RTCODE", "error");
            map.put("RTMSG", "删除后台服务信息异常！请联系管理员！");
            map.put("RTDATA", e.getMessage());
            return map;
        }
        return map;
    }
}
