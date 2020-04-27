package com.cmaple.honeycomb.auth.handle;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.cmaple.honeycomb.Interface.PassToken;
import com.cmaple.honeycomb.Interface.UserLoginToken;
import com.cmaple.honeycomb.model.OperationLog;
import com.cmaple.honeycomb.model.User;
import com.cmaple.honeycomb.service.EmailService;
import com.cmaple.honeycomb.service.OperationLogService;
import com.cmaple.honeycomb.service.UserService;
import com.cmaple.honeycomb.tools.*;
import com.cmaple.honeycomb.util.ConfigurationFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 类名：登录验证拦截器 - AuthenticationInterceptor
 * 功能描述： 用于验证用户是否登录
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：NULL
 * 创建人：cmaple
 * 创建日期：2020-03-01
 */
public class PermissionsInterceptor implements HandlerInterceptor {
    /**
     * 引入UserService
     */
    @Autowired
    UserService userService;

    /**
     * 引入ConfigurationFile
     */
    @Autowired
    private ConfigurationFile configurationFile;
    /**
     * 引入OperationLogService
     */
    @Autowired
    private OperationLogService operationLogService;
    /**
     * 引入EmailTool
     */
    @Autowired
    private EmailService emailService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        //记录日志
        operationLogService.insert(new OperationLog(0
                , "HC" + FormatTime.getFormatTime().formatYMDToString(new Date()) + "-" + RandomData.getRandomData().getRandomNHData(6)
                , new Date(), HttpServletRequestTool.getHttpServletRequestToolExample().getIpAddgetRequestUser(httpServletRequest)
                , "normal"
                , "用户：[" + HttpServletRequestTool.getHttpServletRequestToolExample().getIpAddgetRequestUser(httpServletRequest) + "] " +
                "通过 ip[" + HttpServletRequestTool.getHttpServletRequestToolExample().getIpAddress(httpServletRequest) + "] " +
                "访问 [" + HttpServletRequestTool.getHttpServletRequestToolExample().getRequestURI(httpServletRequest) + "]服务，" +
                "请求类型[" + HttpServletRequestTool.getHttpServletRequestToolExample().getMethod(httpServletRequest) + "]，" +
                "请求参数[" + HttpServletRequestTool.getHttpServletRequestToolExample().getQueryString(httpServletRequest) + "]"));

        if ("/user/login".equals(HttpServletRequestTool.getHttpServletRequestToolExample().getRequestURI(httpServletRequest))) {
            String telephonenumber = httpServletRequest.getParameter("telephonenumber");
            User user = userService.selectByTelephonenumber(telephonenumber);
            //登录检查用户状态
            if ("lock".equals(user.getUseraffairs())) {
                //发送邮件提醒
                emailService.sendMessage(
                        configurationFile.getSMTP()
                        , configurationFile.getLOGPRINT()
                        , configurationFile.getPERSONAL()
                        , configurationFile.getADDRESS()
                        , configurationFile.getPASSWORD()
                        , user.getPetname()
                        , user.getUseremail()
                        , "账户风险·账户锁定通知"
                        , null
                        , configurationFile.getRETURNURL() + "?token=" + Token.getTokenExample().getToken(configurationFile.getCONFIGTOKENKEY(), user)
                        , 3
                );
                throw new Exception("账户已锁定，解锁邮件已发送到您的绑定邮箱！");
            } else if ("del".equals(user.getUseraffairs())) {
                throw new Exception("账户已删除，请发送邮件到 cmaple@aliyun.com 申诉可恢复！");
            } else if ("normal".equals(user.getUseraffairs())) {
                //状态正确无特殊处理
            } else {
                throw new Exception("账户状态异常，请联系管理员处理！");
            }
            //检查用户本次登录与上次登录是否一致
            if (!user.getCommonip().equals(httpServletRequest.getParameter("uip"))) {
                //发送邮件提醒
                emailService.sendMessage(
                        configurationFile.getSMTP()
                        , configurationFile.getLOGPRINT()
                        , configurationFile.getPERSONAL()
                        , configurationFile.getADDRESS()
                        , configurationFile.getPASSWORD()
                        , user.getPetname()
                        , user.getUseremail()
                        , "账户风险·账户锁定通知"
                        , null
                        , configurationFile.getRETURNURL() + "?token=" + Token.getTokenExample().getToken(configurationFile.getCONFIGTOKENKEY(), user)
                        , 3
                );
                //更新常用登录地点
                user.setUseraffairs("lock");
                user.setCommonip(httpServletRequest.getParameter("uip"));
                user.setLastplace(httpServletRequest.getParameter("lastplace"));
                userService.update(user);
                throw new Exception("账户当前登录地点非上一次登录地点，为了您的账户安全您的账户已锁定！");
            }
        }
        // 从 http 请求头中取出 token
        String token = httpServletRequest.getHeader("token");
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()) {
                // 执行认证
                if (token == null) {
                    throw new Exception("无token，请重新登录！");
                }
                // 获取 token 中的 user id
                String telephonenumber;
                try {
                    telephonenumber = JWT.decode(token).getAudience().get(0);
                } catch (Exception e) {
                    throw new Exception("获取用户信息异常，请重新登录！" + e.getMessage());
                }
                User user = userService.selectByTelephonenumber(telephonenumber);
                if (user == null) {
                    throw new Exception("用户名不存在，请重新登录！");
                }
                // 验证 token
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(configurationFile.getCONFIGTOKENKEY() + FormatTime.getFormatTime().formatYMDToString(new Date()))).build();
                try {
                    jwtVerifier.verify(token);
                } catch (Exception e) {
                    throw new Exception("客户端token异常，请重新登录！" + e.getMessage());
                }
                return true;
            }

        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {
        //账户锁定状态的用户发送邮件进行提醒
//        if ("/user/login".equals(HttpServletRequestTool.getHttpServletRequestToolExample().getRequestURI(httpServletRequest))) {
//            String telephonenumber = httpServletRequest.getParameter("telephonenumber");
//            User user = userService.selectByTelephonenumber(telephonenumber);
//            if ("lock".equals(user.getUseraffairs())) {
//                emailService.sendMessage(
//                        configurationFile.getSMTP()
//                        , configurationFile.getLOGPRINT()
//                        , configurationFile.getPERSONAL()
//                        , configurationFile.getADDRESS()
//                        , configurationFile.getPASSWORD()
//                        , user.getPetname()
//                        , user.getUseremail()
//                        , "账户风险·账户锁定通知"
//                        , null
//                        , configurationFile.getRETURNURL() + "?token=" + Token.getTokenExample().getToken(configurationFile.getCONFIGTOKENKEY(), user)
//                        , 3
//                );
//            }
//        }
        //日志结束记录
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }
}