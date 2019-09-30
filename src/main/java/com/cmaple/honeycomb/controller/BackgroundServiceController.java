package com.cmaple.honeycomb.controller;

import com.cmaple.honeycomb.model.BackgroundService;
import com.cmaple.honeycomb.model.ToolsFile;
import com.cmaple.honeycomb.model.User;
import com.cmaple.honeycomb.service.BackgroundServiceService;
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
 * 类名：后台服务管理请求接口 - BackgroundServiceController
 * 功能描述： 后台服务管理请求接口
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：无
 * 创建人：cmaple
 * 创建日期：2019-09-30
 * 修改人：
 * 级别：NULL
 * 修改日期：
 */
@RestController
@RequestMapping("/BackgroundService")
public class BackgroundServiceController {


    //引入BackgroundService
    @Autowired
    private BackgroundServiceService backgroundService;
    @Autowired
    private HttpServletRequest request;


    /**
     * 函数名：根据条件获取后台服务列表 - getBackgroundServices（）
     * 功能描述：根据条件获取后台服务列表
     * 输入参数：<按照参数定义顺序>
     *
     * @param servicestate String类型的服务状态
     * @param proglanguage String类型的服务语言
     * @param search       String类型的搜索内容
     * @param page         int类型的页数
     * @param num          int类型的展示数量
     *                     返回值：map
     *                     异    常：无
     *                     创建人：CMAPLE
     *                     创建日期：2019-09-30
     *                     修改人：
     *                     级别：null
     *                     修改日期：
     */
    @RequestMapping(value = "/getBackgroundServices", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> getBackgroundServices(
            @RequestParam(value = "servicestate", required = true) String servicestate
            , @RequestParam(value = "proglanguage", required = true) String proglanguage
            , @RequestParam(value = "search", required = true) String search
            , @RequestParam(value = "page", required = true) int page
            , @RequestParam(value = "num", required = true) int num
    ) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<String> list = new ArrayList<String>();
        Map<String, Object> params = new HashMap<String, Object>();
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
            returnBackgroundService = backgroundService.getBackgroundServices(list, params, ParamsTools.getPageTools().getPageByNum(page, num), num);
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
     * 函数名：注册后台服务 - insertBackgroundService（）
     * 功能描述：注册后台服务
     * 输入参数：<按照参数定义顺序>
     * <p>
     *
     * @param serviceid String类型的服务状态
     *                  返回值：map
     *                  异    常：无
     *                  创建人：CMAPLE
     *                  创建日期：2019-09-30
     *                  修改人：
     *                  级别：null
     *                  修改日期：
     */
    @RequestMapping(value = "/insertBackgroundService", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> insertBackgroundService(
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
        Map<String, Object> remap = new HashMap<String, Object>();
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
            HttpSession session = request.getSession();
            //获取信息
            User sessionuser = (User) session.getAttribute("SSUSER");
            if (null == sessionuser) {
                map.put("RTCODE", "error");
                map.put("RTMSG", "请先登录，在注册后台服务！");
                map.put("RTDATA", null);
            }
            //注册后台服务
            int returnBackgroundService = backgroundService.insertBackgroundService(new BackgroundService(0, serviceid, name, synopsis, version, path, route + "/" + name, filesize, proglanguage, receivetype, author, sessionuser.getTelephonenumber(), new Date(), serverid, "lock", path + "/" + name));
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

    @RequestMapping(value = "/test", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public int test() {
        return 0;
    }


}
