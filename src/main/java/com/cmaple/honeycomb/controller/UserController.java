package com.cmaple.honeycomb.controller;


import com.cmaple.honeycomb.model.User;
import com.cmaple.honeycomb.service.UserService;
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
 * 类名：跨域请求配置类 - UserController
 * 功能描述： 用户管理相关请求接口
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：无
 * 创建人：cmaple
 * 创建日期：2019-01-16
 * 修改人：
 * 级别：NULL
 * 修改日期：
 */
@RestController
@RequestMapping("/user")
public class UserController {

    //引入userservice
    @Autowired
    private UserService userService;
    @Autowired
    private HttpServletRequest request;


    /**
     * 函数名：登录函数- - login（）
     * 功能描述： 根据账户名 密码查询数据库中是否存在此用户,并返回登录用户的非敏感信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param username String类型的用户名
     *                 <p>
     *                 返回值：map
     *                 异    常：无
     *                 创建人：CMAPLE
     *                 创建日期：2019-01-16
     *                 修改人：CMAPLE
     *                 级别：普通用户及以上
     *                 修改日期：2019-01-18
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> login(@RequestParam(value = "username", required = true) String username, @RequestParam(value = "password", required = true) String password) {
        Map<String, Object> map = new HashMap<String, Object>();
        //判断此用户名是否存在
        if (1 != userService.hasUsername(username)) {
            map.put("RTCODE", "error");
            map.put("RTMSG", "用户名不存在，请注册后登录！");
            return map;
        }
        //获取用户信息
        User user = userService.getUserByUsername(username);
        //判断用户名密码是否匹配
        if (!user.getPassword().equals(password)) {
            if (5 > user.getErrortry()) {
                user.setErrortry(user.getErrortry() + 1);
                userService.updateUser(user);
                map.put("RTCODE", "error");
                map.put("RTMSG", "用户名或密码错误！");
                map.put("RTDATA", null);
            } else {
                //5次锁定账号
                if (1 != user.getUseraffairs()) {
                    user.setUseraffairs(1);
                    userService.updateUser(user);
                }
                map.put("RTCODE", "error");
                map.put("RTMSG", "账户已锁定，请进行账号申诉解锁！");
                map.put("RTDATA", null);
            }
            return map;
        } else {
            if (1 == user.getUseraffairs()) {
                map.put("RTCODE", "error");
                map.put("RTMSG", "账户已锁定，请进行账号申诉解锁！");
                map.put("RTDATA", null);
            } else if (2 == user.getUseraffairs()) {
                map.put("RTCODE", "error");
                map.put("RTMSG", "账户已删除，请进行账号申诉恢复！");
                map.put("RTDATA", null);
            } else {
                User returnuser = new User(0, user.getUsername(), null, user.getUsertype(), 0, user.getUserbalance(), null, null, null, user.getTelephonenumber(), null, null, null, user.getUsersign(), user.getPetname(), 0);
                //设置返回信息
                map.put("RTCODE", "success");
                map.put("RTMSG", "登录成功！");
                map.put("RTDATA", returnuser);
                //将登录信息存储在session中
                HttpSession session = request.getSession(true);
                session.setAttribute("SSUSER", returnuser);
            }
        }
        user = null; //删除强引用，释放相应内存空间，减少内存溢出风险
        return map;
    }

    /**
     * 函数名：退出登录函数 - logOut（）
     * 功能描述： 将session注销，并给予浏览器退出成功提示！
     * 输入参数：<按照参数定义顺序>
     * <p>
     * <p>
     * 返回值：map
     * 异    常：无
     * 创建人：CMAPLE
     * 创建日期：2019-01-18
     * 修改人：
     * 级别：普通用户及以上
     * 修改日期：
     */
    @RequestMapping(value = "/logOut", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> logOut() {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        session.setAttribute("SSUSER", null);
        map.put("RTCODE", "success");
        map.put("RTMSG", "注销登录成功！");
        map.put("RTDATA", session.getAttribute("SSUSER"));
        return map;
    }

    /**
     * 函数名：获取登录用户信息 - getUserSession（）
     * 功能描述： 获取登录用户信息，用于初始页面展示及级别控制
     * 输入参数：<按照参数定义顺序>
     * <p>
     * <p>
     * 返回值：map
     * 异    常：无
     * 创建人：CMAPLE
     * 创建日期：2019-01-18
     * 修改人：
     * 级别：普通用户及以上
     * 修改日期：
     */
    @RequestMapping(value = "/getUserSession", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> getUserSession() {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        User returnuser = (User) session.getAttribute("SSUSER");
        if (null == returnuser) {
            map.put("RTCODE", "error");
            map.put("RTMSG", "获取用户信息失败！");
            map.put("RTDATA", null);
            return map;
        }
        map.put("RTCODE", "success");
        map.put("RTMSG", "获取用户信息成功！");
        map.put("RTDATA", returnuser);
        return map;
    }


}
