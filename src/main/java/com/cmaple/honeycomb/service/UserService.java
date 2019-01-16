package com.cmaple.honeycomb.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {


    /**
     * 函数名：查询函数-查询此用户名是否存在 - hasUsername（）
     * 功能描述： 根据账户名 查询数据库中是否存在此用户的数量，返回1则表示正确，不等于一则表示不正确
     * 输入参数：<按照参数定义顺序>
     *
     * @param username String类型的用户名
     *                 返回值：int
     *                 异    常：无
     *                 创建人：cf
     *                 日期：2018-08-29
     *                 修改人：
     *                 级别：普通用户
     *                 日期：
     */
    public int hasUsername(String username){
        return 0;
    }

}
