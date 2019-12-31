package com.cmaple.honeycomb.controller;

import com.cmaple.honeycomb.model.Report;
import com.cmaple.honeycomb.model.User;
import com.cmaple.honeycomb.service.ReportService;
import com.cmaple.honeycomb.tools.FileSelect;
import com.cmaple.honeycomb.tools.FormatTime;
import com.cmaple.honeycomb.tools.ParamsTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;

/**
 * 类名：调查报告管理服务控制器 - ReportController
 * 功能描述： 调查报告管理服务控制器
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：NULL
 * 创建人：cmaple
 * 创建日期：2019-11-27
 */
@RestController
@RequestMapping("/Report")
public class ReportController {
    /**
     * 引入ReportService
     */
    @Autowired
    private ReportService reportService;
    /**
     * 引入HttpServletRequest
     */
    @Autowired
    private HttpServletRequest request;
    /**
     * 引入HttpServletResponse
     */
    @Autowired
    private HttpServletResponse response;

    /**
     * 函数名：select函数 - 根据ID号查询调查报告 - selectById（）
     * 功能描述： 根据ID号查询调查报告
     * 输入参数：<按照参数定义顺序>
     *
     * @param id int类型的id号
     *           <p>
     *           返回值：void
     *           异    常：NULL
     *           创建人：CMAPLE
     *           创建日期：2019-11-28
     */
    @RequestMapping(value = "/selectById", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public void selectById(
            @RequestParam(value = "id", required = true) int id
    ) {
        HttpSession session = request.getSession();
        //获取信息
        User sessionuser = (User) session.getAttribute("SSUSER");
        Report returnreport = null;
        try {
            //查询调查报告信息
            returnreport = reportService.selectById(id);
            // 设置信息给客户端
            String type = new MimetypesFileTypeMap().getContentType(returnreport.getFilename());
            // 设置contenttype，即告诉客户端所发送的数据属于什么类型
            response.setHeader("Content-type", type);
            String filename = new String(returnreport.getFilename().getBytes("utf-8"), "iso-8859-1");
            // 设置扩展头，当Content-Type 的类型为要下载的类型时 , 这个信息头会告诉浏览器这个文件的名字和类型。
            response.setHeader("Content-Disposition", "attachment;filename=" + filename);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //数据流
        try (
                //创建输入流
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
                //创建输出流
                BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(new File(returnreport.getFilepath() + "/" + returnreport.getFilename())));
        ) {
            //使用工具函数返回流信息给客户端
            FileSelect.getFileSelect().downloadFile(bufferedOutputStream, bufferedInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 函数名：select函数 - 根据时间倒叙查询调查报告 - selectDescOrderByTime（）
     * 功能描述： 根据时间倒叙查询调查报告
     * 输入参数：<按照参数定义顺序>
     * 返回值：Map
     * 异    常：NULL
     * 创建人：CMAPLE
     * 创建日期：2019-12-02
     */
    @RequestMapping(value = "/selectDescOrderByTime", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> selectDescOrderByTime() {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Report> returnReport = null;
        try {
            //查询公告信息
            returnReport = reportService.selectDescOrderByTime();
        } catch (Exception e) {
            e.printStackTrace();
            //报错信息，错误信息插入日志表
            map.put("RTCODE", "error");
            map.put("RTMSG", "按照时间倒叙查询调查报告信息异常！请联系管理员！");
            map.put("RTDATA", e.getMessage());
            return map;
        }
        map.put("RTCODE", "success");
        map.put("RTMSG", "获取调查报告信息成功！");
        map.put("RTDATA", returnReport);
        return map;
    }

    /**
     * 函数名：select函数 - 根据条件查询调查报告信息 - selectByCriteria（）
     * 功能描述： 根据条件查询调查报告信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param search       String类型的搜索内容
     * @param timeaxisdate list类型的时间范围
     * @param page         int类型的页数
     * @param num          int类型的数量
     *                     返回值：Map
     *                     异    常：NULL
     *                     创建人：CMAPLE
     *                     创建日期：2019-11-28
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
            map.put("RTMSG", "请先登录，在查询调查报告信息！");
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
        List<Report> returnReport = null;
        try {
            //查询公告信息
            returnReport = reportService.selectByCriteria(list, params, ParamsTools.getPageTools().getPageByNum(page, num), num);
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
        map.put("RTDATA", returnReport);
        return map;
    }

    /**
     * 函数名：insert函数 - 注册调查报告 - insert（）
     * 功能描述： 注册调查报告
     * 输入参数：<按照参数定义顺序>
     *
     * @param title      String类型的标题
     * @param reporttype String类型的调查报告类型
     * @param filepath   String类型的文件路径
     * @param file       MultipartFile类型的文件
     *                   返回值：Map
     *                   异    常：NULL
     *                   创建人：CMAPLE
     *                   创建日期：2019-12-03
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> insert(
            @RequestParam(value = "title", required = true) String title
            , @RequestParam(value = "reporttype", required = true) String reporttype
            , @RequestParam(value = "filepath", required = true) String filepath
            , @RequestParam(value = "file", required = true) MultipartFile file
    ) {
        //初始化参数
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        //创建时间
        Date insertdate = new Date();
        //创建调查报告ID
        String insertreportid = "HCR-" + FormatTime.getFormatTime().formatYMDToString(insertdate) + "-" + FormatTime.getFormatTime().formatHMSToString(insertdate);
        //获取信息
        User sessionuser = (User) session.getAttribute("SSUSER");
        if (null == sessionuser) {
            map.put("RTCODE", "error");
            map.put("RTMSG", "请先登录，在创建调查报告信息！");
            map.put("RTDATA", null);
            return map;
        }
        //判断是否存在同名文件
        if (!"success".equals(FileSelect.getFileSelect().checkFileExists(filepath, null).get("RTCODE"))) {
            map.put("RTCODE", "error");
            map.put("RTMSG", "存在同名文件！或者文件路径错误");
            map.put("RTDATA", FileSelect.getFileSelect().checkFileExists(filepath, null));
            return map;
        }
        //上传文件
        if ("success".equals(FileSelect.getFileSelect().checkFileExists(filepath, insertreportid + ".pdf").get("RTCODE"))) {
            FileSelect.getFileSelect().uploadFile(file, filepath, insertreportid + ".pdf");
        } else {
            map.put("RTCODE", "error");
            map.put("RTMSG", "存在同名文件或路径错误！");
            map.put("RTDATA", FileSelect.getFileSelect().checkFileExists(filepath, insertreportid + ".pdf"));
            return map;
        }
        //插入调查报告信息
        try {
            //创建调查报告信息
            Report report = new Report(0, title, insertreportid, reporttype, sessionuser.getTelephonenumber(), new Date(), insertreportid + ".pdf", filepath);
            int returnreport = reportService.insert(report);
            if (1 == returnreport) {
                map.put("RTCODE", "success");
                map.put("RTMSG", "调查报告上传成功！");
                map.put("RTDATA", null);
            } else {
                map.put("RTCODE", "error");
                map.put("RTMSG", "调查报告上传失败！");
                map.put("RTDATA", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            //报错信息，错误信息插入日志表
            map.put("RTCODE", "error");
            map.put("RTMSG", "调查报告创建交易异常！请联系管理员！");
            map.put("RTDATA", e.getMessage());
            return map;
        }
        map.put("RTCODE", "success");
        map.put("RTMSG", "创建调查报告信息成功！");
        map.put("RTDATA", null);
        return map;
    }

    /**
     * 函数名：update函数 - 更新调查报告 - update（）
     * 功能描述： 更新调查报告
     * 输入参数：<按照参数定义顺序>
     *
     * @param title      String类型的标题
     * @param reporttype String类型的调查报告类型
     * @param filepath   String类型的文件路径
     * @param file       MultipartFile类型的文件
     *                   返回值：Map
     *                   异    常：NULL
     *                   创建人：CMAPLE
     *                   创建日期：2019-12-04
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> update(
            @RequestParam(value = "id", required = true) int id
            , @RequestParam(value = "title", required = true) String title
            , @RequestParam(value = "reportid", required = true) String reportid
            , @RequestParam(value = "reporttype", required = true) String reporttype
            , @RequestParam(value = "filename", required = true) String filename
            , @RequestParam(value = "filepath", required = true) String filepath
            , @RequestParam(value = "file", required = true) MultipartFile file
    ) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        //获取信息
        User sessionuser = (User) session.getAttribute("SSUSER");
        if (null == sessionuser) {
            map.put("RTCODE", "error");
            map.put("RTMSG", "请先登录，在更新调查报告信息！");
            map.put("RTDATA", null);
            return map;
        }
        //删除原来的文件
        if (!"success".equals(FileSelect.getFileSelect().checkFileExists(filepath, filename).get("RTCODE"))) {
            FileSelect.getFileSelect().delFile(filepath + "/" + filename);
        } else {
            map.put("RTCODE", "error");
            map.put("RTMSG", "该路径下不存在文件！");
            map.put("RTDATA", FileSelect.getFileSelect().checkFileExists(filepath, filename).get("RTDATA"));
            return map;
        }
        //上传文件
        if ("success".equals(FileSelect.getFileSelect().checkFileExists(filepath, filename).get("RTCODE"))) {
            FileSelect.getFileSelect().uploadFile(file, filepath, filename);
        } else {
            map.put("RTCODE", "error");
            map.put("RTMSG", "存在同名文件或路径错误！");
            map.put("RTDATA", FileSelect.getFileSelect().checkFileExists(filepath, filename).get("RTDATA"));
            return map;
        }
        //插入调查报告信息
        try {
            //创建调查报告信息
            Report report = new Report(id, title, reportid, reporttype, sessionuser.getTelephonenumber(), new Date(), filename, filepath);
            int returnreport = reportService.update(report);
            if (1 == returnreport) {
                map.put("RTCODE", "success");
                map.put("RTMSG", "调查更新成功！");
                map.put("RTDATA", null);
            } else {
                map.put("RTCODE", "error");
                map.put("RTMSG", "调查更新失败！");
                map.put("RTDATA", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            //报错信息，错误信息插入日志表
            map.put("RTCODE", "error");
            map.put("RTMSG", "调查报告更新交易异常！请联系管理员！");
            map.put("RTDATA", e.getMessage());
            return map;
        }
        map.put("RTCODE", "success");
        map.put("RTMSG", "更新调查报告信息成功！");
        map.put("RTDATA", null);
        return map;
    }

    /**
     * 函数名：delete函数 - 删除调查报告 - deleteById（）
     * 功能描述： 删除调查报告
     * 输入参数：<按照参数定义顺序>
     *
     * @param id String类型的标题
     *           返回值：Map
     *           异    常：无
     *           创建人：CMAPLE
     *           创建日期：2019-12-04
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
            map.put("RTMSG", "请先登录，在查询调查报告信息！");
            map.put("RTDATA", null);
            return map;
        }
        Report returnreport = null;
        //查询调查报告信息
        try {
            returnreport = reportService.selectById(id);
        } catch (Exception e) {
            e.printStackTrace();
            //报错信息，错误信息插入日志表
            map.put("RTCODE", "error");
            map.put("RTMSG", "删除调查报告交易过程中查询调查报告信息异常！请联系管理员！");
            map.put("RTDATA", e.getMessage());
            return map;
        }
        //删除文件
        if (!"success".equals(FileSelect.getFileSelect().checkFileExists(returnreport.getFilepath(), returnreport.getFilename()).get("RTCODE"))) {
            FileSelect.getFileSelect().delFile(returnreport.getFilepath() + "/" + returnreport.getFilename());
        } else {
            map.put("RTCODE", "error");
            map.put("RTMSG", "该路径下不存在文件！");
            map.put("RTDATA", FileSelect.getFileSelect().checkFileExists(returnreport.getFilepath(), returnreport.getFilename()).get("RTDATA"));
            return map;
        }
        //删除数据库信息记录
        try {
            int isdel = reportService.deleteById(id);
            if (1 != isdel) {
                map.put("RTCODE", "error");
                map.put("RTMSG", "删除调查报告失败！");
                map.put("RTDATA", null);
            } else {
                map.put("RTCODE", "success");
                map.put("RTMSG", "删除调查报告成功！");
                map.put("RTDATA", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            //报错信息，错误信息插入日志表
            map.put("RTCODE", "error");
            map.put("RTMSG", "删除调查报告信息异常！请联系管理员！");
            map.put("RTDATA", e.getMessage());
            return map;
        }
        return map;
    }
    
}