package com.cmaple.honeycomb.controller;


import com.cmaple.honeycomb.model.User;
import com.cmaple.honeycomb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    private UserService userService;


    /**
     * 函数名：登录函数- - login（）
     * 功能描述： 根据账户名 密码查询数据库中是否存在此用户,并返回登录用户的非敏感信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param username String类型的用户名
     *                 <p>
     *                 返回值：User(json)
     *                 异    常：无
     *                 创建人：CMAPLE
     *                 创建日期：2019-01-16
     *                 修改人：CMAPLE
     *                 级别：普通用户及以上
     *                 修改日期：null
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

            } else {
                if (1 != user.getUseraffairs()) {
                    user.setUseraffairs(1);
                    userService.updateUser(user);
                }
                map.put("RTCODE", "error");
                map.put("RTMSG", "账户以锁定，请进行账号申诉解锁！");
            }
            return map;
        } else {
            if (1 == user.getUseraffairs()) {
                map.put("RTCODE", "error");
                map.put("RTMSG", "账户以锁定，请进行账号申诉解锁！");
            } else {
                map.put("RTCODE", "success");
                map.put("RTMSG", "登录成功！");
            }
        }
        return map;
    }

}
