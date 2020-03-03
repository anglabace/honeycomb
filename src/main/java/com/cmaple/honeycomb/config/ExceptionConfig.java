package com.cmaple.honeycomb.config;

import com.cmaple.honeycomb.model.OperationLog;
import com.cmaple.honeycomb.service.OperationLogService;
import com.cmaple.honeycomb.tools.FormatTime;
import com.cmaple.honeycomb.tools.HttpServletRequestTool;
import com.cmaple.honeycomb.tools.RandomData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 类名：全局异常处理 - ExceptionConfig
 * 功能描述： 用于返回固定格式的全局异常信息，取代原有的异常返回信息
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：NULL
 * 创建人：cmaple
 * 创建日期：2020-03-03
 */
@ControllerAdvice
public class ExceptionConfig {
    /**
     * 引入OperationLogService
     */
    @Autowired
    private OperationLogService operationLogService;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> globaException(HttpServletRequest request, Exception exception) {
        //记录异常日志
        operationLogService.insert(new OperationLog(0
                , "HC" + FormatTime.getFormatTime().formatYMDToString(new Date()) + "-" + RandomData.getRandomData().getRandomNHData(6)
                , new Date(), HttpServletRequestTool.getHttpServletRequestToolExample().getIpAddgetRequestUser(request)
                , "exception"
                , ""
                , "用户：[" + HttpServletRequestTool.getHttpServletRequestToolExample().getIpAddgetRequestUser(request) + "] " +
                "通过 ip[" + HttpServletRequestTool.getHttpServletRequestToolExample().getIpAddress(request) + "] " +
                "访问 [" + HttpServletRequestTool.getHttpServletRequestToolExample().getRequestURI(request) + "]服务出现异常，" +
                "请求类型[" + HttpServletRequestTool.getHttpServletRequestToolExample().getMethod(request) + "]，" +
                "请求参数[" + HttpServletRequestTool.getHttpServletRequestToolExample().getQueryString(request) + "]，" +
                "错误信息[" + exception.getMessage() + "]"));
        //返回异常信息
        Map<String, String> map = new HashMap<>();
        map.put("RTCODE", "error");
        map.put("RTMSG", "请求异常，请联系管理员！");
        map.put("RTDATA", exception.getMessage());
        return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
    }


}
