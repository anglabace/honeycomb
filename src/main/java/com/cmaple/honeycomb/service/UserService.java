package com.cmaple.honeycomb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cmaple.honeycomb.mapper.UserMapper;
import com.cmaple.honeycomb.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 类名：用户服务功能类 - UserService
 * 功能描述：维护用户管理相应的功能
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：无
 * 创建人：cmaple
 * 创建日期：2019-01-16
 * 修改人：
 * 级别：NULL
 * 修改日期：
 */
@Service
public class UserService extends ServiceImpl<UserMapper,User> {

    //引入用户映射接口
    @Autowired
    private UserMapper userMapper;


    /**
     * 函数名：查询函数-查询此用户名是否存在 - hasUsername（）
     * 功能描述： 根据账户名 查询数据库中是否存在此用户的数量，返回1则表示存在此账户，否则为不存在此账户
     * 输入参数：<按照参数定义顺序>
     *
     * @param username String类型的用户名
     *                 返回值：int
     *                 异    常：无
     *                 创建人：CMAPLE
     *                 日期：2019-01-16
     *                 修改人：
     *                 级别：普通用户
     *                 日期：
     */
    public Integer hasUsername(String username){
        return userMapper.selectCount(new QueryWrapper<User>().lambda().eq(User::getUsername,username));
    }

}
