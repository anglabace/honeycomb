package com.cmaple.honeycomb.controller;

import com.cmaple.honeycomb.model.OperationLog;
import com.cmaple.honeycomb.model.User;
import com.cmaple.honeycomb.service.OperationLogService;
import com.cmaple.honeycomb.service.StaticResourcesService;
import com.cmaple.honeycomb.tools.FileSelect;
import com.cmaple.honeycomb.tools.FormatTime;
import com.cmaple.honeycomb.tools.RandomData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 类名：静态资源请求接口类 - StaticResourcesController
 * 功能描述： 静态资源请求接口类（需要更新，增压删除和增加之前对文件进行核验操作，查看在相应的静态资源表是否有同名文件或者是否正在使用！！！）
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：无
 * 创建人：CMAPLE
 * 创建日期：2019-09-26
 * 修改人：
 * 级别：NULL
 * 修改日期：
 */
@RestController
@RequestMapping("/staticresources")
public class StaticResourcesController {

    //StaticResourcesService
    @Autowired
    private StaticResourcesService staticResourcesService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private OperationLogService operationLogService;

    /**
     * 函数名：查询函数-按照条件查询日志- getStaticResourcesByParams（）
     * 功能描述： 根据条件进行条件进行日志的查询
     * 输入参数：<按照参数定义顺序>
     *
     * @param filepath String类型的地址
     *                 返回值：map
     *                 异    常：无
     *                 创建人：CMAPLE
     *                 创建日期：2019-09-27
     *                 修改人：
     *                 级别：null
     *                 修改日期：
     */
    @RequestMapping(value = "/getStaticResources", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> getStaticResources(
            @RequestParam(value = "pagespath", required = true) String filepath
    ) {
        Map<String, Object> map = new HashMap<String, Object>();
        map = FileSelect.getFileSelect().getfileMap(filepath);
        //当请求交易异常，进行日志记录
        if ("Exception".equals(map.get("RTCODE"))) {
            map.put("RTCODE", "error");
            //记录错误日志
            HttpSession session = request.getSession();
            //获取信息
            User sessionuser = (User) session.getAttribute("SSUSER");
            operationLogService.insertOperationLog(new OperationLog(0, "HC" + FormatTime.getFormatTime().formatYMDToString(new Date()) + "-" + RandomData.getRandomData().getRandomNHData(6), new Date(), sessionuser.getTelephonenumber(), "exception", "resources", "用户：[ " + sessionuser.getTelephonenumber() + " ] 查看静态资源列表交易异常，异常信息如下：" + map.get("RTDATA")));
        }
        return map;
    }

    /**
     * 函数名：上传函数-上传文件到指定目录下- uploadFile（）
     * 功能描述： 上传文件到指定目录下
     * 输入参数：<按照参数定义顺序>
     *
     * @param file       MultipartFile类型的上传文件"流"
     * @param uploadpath String类型的上传路径
     * @param filename   String类型的文件名
     *                   返回值：map
     *                   异    常：无
     *                   创建人：CMAPLE
     *                   创建日期：2019-09-27
     *                   修改人：
     *                   级别：null
     *                   修改日期：
     */
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> uploadFile(
            @RequestParam(value = "file", required = true) MultipartFile file
            , @RequestParam(value = "search", required = true) String uploadpath
            , @RequestParam(value = "search", required = true) String filename
    ) {
        Map<String, Object> map = new HashMap<String, Object>();
        map = FileSelect.getFileSelect().uploadFile(file, uploadpath, filename);
        //当请求交易异常，进行日志记录
        if ("Exception".equals(map.get("RTCODE"))) {
            map.put("RTCODE", "error");
            //记录错误日志
            HttpSession session = request.getSession();
            //获取信息
            User sessionuser = (User) session.getAttribute("SSUSER");
            operationLogService.insertOperationLog(new OperationLog(0, "HC" + FormatTime.getFormatTime().formatYMDToString(new Date()) + "-" + RandomData.getRandomData().getRandomNHData(6), new Date(), sessionuser.getTelephonenumber(), "exception", "resources", "用户：[ " + sessionuser.getTelephonenumber() + " ] 上传静态资源交易异常，异常信息如下：" + map.get("RTDATA")));
        }
        return map;
    }

    /**
     * 函数名：删除函数-将指定目录下的文件进行删除- deleteFile（）
     * 功能描述： 将指定目录下的文件进行删除
     * 输入参数：<按照参数定义顺序>
     *
     * @param filepath String类型文件绝对路径
     *                 返回值：map
     *                 异    常：无
     *                 创建人：CMAPLE
     *                 创建日期：2019-09-29
     *                 修改人：
     *                 级别：null
     *                 修改日期：
     */
    @RequestMapping(value = "/deleteFile", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> deleteFile(
            @RequestParam(value = "filepath", required = true) String filepath
    ) {
        Map<String, Object> map = new HashMap<String, Object>();
        map = FileSelect.getFileSelect().delFile(filepath);
        if ("Exception".equals(map.get("RTCODE"))) {
            map.put("RTCODE", "error");
            //记录错误日志
            HttpSession session = request.getSession();
            //获取信息
            User sessionuser = (User) session.getAttribute("SSUSER");
            operationLogService.insertOperationLog(new OperationLog(0, "HC" + FormatTime.getFormatTime().formatYMDToString(new Date()) + "-" + RandomData.getRandomData().getRandomNHData(6), new Date(), sessionuser.getTelephonenumber(), "exception", "resources", "用户：[ " + sessionuser.getTelephonenumber() + " ] 删除静态资源交易异常，异常信息如下：" + map.get("RTDATA")));
        }
        return map;
    }
}
