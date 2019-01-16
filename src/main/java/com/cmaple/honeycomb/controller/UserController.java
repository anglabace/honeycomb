package com.cmaple.honeycomb.controller;


import com.cmaple.honeycomb.mapper.UserMapper;
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
    private UserMapper userMapper;

    /**
     * 函数名：登录函数- - login（）
     * 功能描述： 根据账户名 密码查询数据库中是否存在此用户,并返回登录用户的非敏感信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param username String类型的用户名
     * @param password String类型的密码
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
        System.out.println(("----- selectAll method test ------"));
        //查询
//        List<User> userList = userMapper.selectList(null);
//        for (int i =0;i<userList.size();i++){
//            System.out.println(userList.get(i));
//        }
        //注册
//        User user = new User();
//        user.setUsername("congfeng");
//        user.setPassword("123456");
//        user.setUsertype("999");
//        user.setUseraddress("0");
//        user.setUserbalance(0.0);
//        user.setCreatetime(new Date());
//        int a = userMapper.insert(user);
//        map.put("msg",user);
//        map.put("int",a);
        return map;
    }

}
