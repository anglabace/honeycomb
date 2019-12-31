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
 * 类名：用户管理功能类业务组件 - UserService
 * 功能描述：用户管理功能类业务组件
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：NULL
 * 创建人：CMAPLE
 * 创建日期：2019-01-16
 */
@Service
public class UserService extends ServiceImpl<UserMapper, User> {

    /**
     * 引入UserMapper
     */
    @Autowired
    private UserMapper userMapper;

    /**
     * 函数名：select函数-查询此电话号码是否已经存在 - equalsTelephonenumber（）
     * 功能描述： 查询此电话号码是否已经存在
     * 输入参数：<按照参数定义顺序>
     *
     * @param telephonenumber String类型的用户名
     *                        返回值：int
     *                        异    常：NULL
     *                        创建人：CMAPLE
     *                        日期：2019-01-16
     */
    public Integer equalsTelephonenumber(String telephonenumber) {
        return userMapper.selectCount(new QueryWrapper<User>().lambda().eq(User::getTelephonenumber, telephonenumber));
    }

    /**
     * 函数名：select函数-查询此身份证号码是否注册 - equalsIdcard（）
     * 功能描述： 查询此身份证号码是否注册
     * 输入参数：<按照参数定义顺序>
     *
     * @param idcard String类型的用户身份证号码
     *               返回值：int
     *               异    常：NULL
     *               创建人：CMAPLE
     *               日期：2019-01-16
     */
    public Integer equalsIdcard(String idcard) {
        return userMapper.selectCount(new QueryWrapper<User>().lambda().eq(User::getIdcard, idcard));
    }

    /**
     * 函数名：select函数-查询此电子邮箱是否注册 - equalsEmail（）
     * 功能描述： 查询此身份证号码是否注册
     * 输入参数：<按照参数定义顺序>
     *
     * @param email String类型的用户名
     *              返回值：int
     *              异    常：NULL
     *              创建人：CMAPLE
     *              日期：2019-01-16
     */
    public Integer equalsEmail(String email) {
        return userMapper.selectCount(new QueryWrapper<User>().lambda().eq(User::getUseremail, email));
    }

    /**
     * 函数名：select函数-根据电话号码查询用户信息 - selectByTelephonenumber（）
     * 功能描述： 根据电话号码查询用户信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param telephonenumber String类型的用户名
     *                        返回值：User
     *                        异    常：NULL
     *                        创建人：CMAPLE
     *                        日期：2019-01-17
     */
    public User selectByTelephonenumber(String telephonenumber) {
        return userMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getTelephonenumber, telephonenumber));
    }

    /**
     * 函数名：select函数-根据ID号查询用户信息 - selectById（）
     * 功能描述： 根据ID号查询用户信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param id int类型的用户名
     *           返回值：User
     *           异    常：NULL
     *           创建人：CMAPLE
     *           日期：2019-01-17
     */
    public User selectById(int id) {
        return userMapper.selectById(id);
    }

    /**
     * 函数名：select函数-根据条件查询用户列表 - selectByCriteria（）
     * 功能描述： 根据条件查询用户列表
     *
     * @param list   List类型的条件列表
     * @param params Map类型的字段及数值集合
     * @param page   int类型的页数
     * @param num    int类型的数量
     *               返回值：List<User>
     *               异    常：NULL
     *               创建人：CMAPLE
     *               日期：2019-01-17
     */
    public List<User> selectByCriteria(List<String> list, Map<String, Object> params, int page, int num) {
        return userMapper.selectByCriteria(list, params, page, num);
    }

    /**
     * 函数名：select函数-根据条件查询用户数量 - selectCountByCriteria（）
     * 功能描述： 根据条件查询用户数量
     *
     * @param list   List类型的条件列表
     * @param params Map类型的字段及数值集合
     *               返回值：int
     *               异    常：NULL
     *               创建人：CMAPLE
     *               日期：2019-01-17
     */
    public int selectCountByCriteria(List<String> list, Map<String, Object> params) {
        return userMapper.selectCountByCriteria(list, params);
    }

    /**
     * 函数名：insert函数-插入用户信息 - insert（）
     * 功能描述： 插入新的账户信息
     *
     * @param user User类型的用户实体类
     *             返回值：int
     *             异    常：NULL
     *             创建人：CMAPLE
     *             日期：2019-01-17
     */
    public int insert(User user) {
        return userMapper.insert(user);
    }

    /**
     * 函数名：update函数-修改用户信息 - update（）
     * 功能描述： 修改用户信息
     *
     * @param user User类型用户实体类
     *             返回值：int
     *             异    常：NULL
     *             创建人：CMAPLE
     *             日期：2019-01-17
     */
    public int update(User user) {
        return userMapper.update(user, new QueryWrapper<User>().lambda().eq(User::getTelephonenumber, user.getTelephonenumber()));
    }

}
