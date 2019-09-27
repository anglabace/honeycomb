package com.cmaple.honeycomb.controller;

import com.cmaple.honeycomb.service.OperationLogService;
import com.cmaple.honeycomb.service.StaticResourcesService;
import com.cmaple.honeycomb.tools.FileSelect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 类名：静态资源请求接口类 - StaticResourcesController
 * 功能描述： 静态资源请求接口类
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
        Map<String, Object> returnmap = new HashMap<String, Object>();
        returnmap = FileSelect.getFileSelect().getfileMap(filepath);
        return returnmap;
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
        Map<String, Object> returnmap = new HashMap<String, Object>();
        returnmap = FileSelect.getFileSelect().uploadFile(file, uploadpath, filename);
        return returnmap;
    }

}
