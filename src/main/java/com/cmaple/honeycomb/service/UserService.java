package com.cmaple.honeycomb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cmaple.honeycomb.mapper.UserMapper;
import com.cmaple.honeycomb.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
public class UserService extends ServiceImpl<UserMapper, User> {

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
    public Integer hasUsername(String username) {
        return userMapper.selectCount(new QueryWrapper<User>().lambda().eq(User::getUsername, username));
    }

    /**
     * 函数名：查询函数-根据用户名、密码查询用户是否存在 - hasByUsernameAndPassword（）
     * 功能描述： 根据账户名、密码获取当前用户是否存在
     * 输入参数：<按照参数定义顺序>
     *
     * @param username String类型的用户名
     * @param password String类型的用户密码
     *                 返回值：int
     *                 异    常：无
     *                 创建人：CMAPLE
     *                 日期：2019-01-17
     *                 修改人：
     *                 级别：普通用户
     *                 日期：
     */
    public int hasByUsernameAndPassword(String username, String password) {
        return userMapper.selectCount(new QueryWrapper<User>().lambda().eq(User::getUsername, username).eq(User::getPassword, password));
    }

    /**
     * 函数名：查询函数-根据用户名查询用户信息 - getUserByUsername（）
     * 功能描述： 根据用户名查询用户信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param username String类型的用户名
     *                 返回值：User
     *                 异    常：无
     *                 创建人：CMAPLE
     *                 日期：2019-01-17
     *                 修改人：
     *                 级别：普通用户
     *                 日期：
     */
    public User getUserByUsername(String username) {
        return userMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getUsername, username));
    }

    /**
     * 函数名：复杂查询函数-根据条件查询用户列表 - getUsersByParams（）
     * 功能描述： 根据条件查询用户列表
     *
     * @param list       条件列表
     * @param params     字段及数值集合
     * @param lousertype 操作用户级别
     * @param page       分页查询PAGE条件
     * @param num        分页查询NUM条件
     *                   返回值：List<User>
     *                   异    常：无
     *                   创建人：CMAPLE
     *                   日期：2019-01-17
     *                   修改人：
     *                   级别：普通用户
     *                   日期：
     */
    public List<User> getUsersByParams(List<String> list, Map<String, Object> params, String lousertype, int page, int num) {
        return userMapper.queryUserByParams(list, params, lousertype, page, num);
    }

    /**
     * 函数名：复杂查询函数-根据条件查询用户数量 - queryUserCountByParams（）
     * 功能描述： 根据条件查询用户数量
     *
     * @param list       条件列表
     * @param params     字段及数值集合
     * @param lousertype 操作用户级别
     *                   返回值：int
     *                   异    常：无
     *                   创建人：CMAPLE
     *                   日期：2019-01-17
     *                   修改人：
     *                   级别：普通用户
     *                   日期：
     */
    public int queryUserCountByParams(List<String> list, Map<String, Object> params, String lousertype) {
        return userMapper.queryUserCountByParams(list, params, lousertype);
    }

}
