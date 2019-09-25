package com.cmaple.honeycomb.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 类名：用户实体类 - User
 * 功能描述： 用户实体类
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：无
 * 创建人：CMAPLE
 * 创建日期：2019-01-16
 * 修改人：
 * 级别：NULL
 * 修改日期：
 */

@Data //给实体提供读写属性, 此外还提供了 equals()、hashCode()、toString() 方法
@TableName("CS_User")  //对应数据库表名称
@Accessors(chain = true)  //chain 若为true，则setter方法返回当前对象()
@NoArgsConstructor  //提供无参构造器
@AllArgsConstructor //提供全参构造器
public class User {

    /**
     * 用户顺序号
     * 长度：24
     * 是否为null：N
     * 自动递增
     * 主键
     **/
    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    /**
     * 用户密码
     * 长度：200
     * 是否为null：Y
     **/
    private String password;
    /**
     * 用户的账户类型{超级管理员：superadmin、管理员：admin、普通会员：member}
     * 长度：12
     * 是否为null：N
     **/
    private String usertype;
    /**
     * 账户状态{正常：normal、锁定：lock 、异常：abnormal、删除：del}
     * 长度：1
     * 是否为null：N
     **/
    private String useraffairs;
    /**
     * 账户余额
     * 长度：10
     * 是否为null：N
     **/
    private double userbalance;
    /**
     * 账户身份证号码
     * 长度：18
     * 是否为null：N
     **/
    private String idcard;
    /**
     * 账户真实姓名
     * 长度：8
     * 是否为null：N
     **/
    private String name;
    /**
     * 用户地址
     * 长度：52
     * 是否为null：N
     **/
    private String useraddress;
    /**
     * 用户电话号码
     * 长度：11
     * 是否为null：N
     **/
    private String telephonenumber;
    /**
     * 用户电子邮箱
     * 长度：48
     * 是否为null：N
     **/
    private String useremail;
    /**
     * 账户创建时间
     * 长度：
     * 是否为null：N
     **/
    private Date createtime;
    /**
     * 账户个性签名
     * 长度：32
     * 是否为null：Y
     **/
    private String usersign;
    /**
     * 账户昵称
     * 长度：12
     * 是否为null：N
     **/
    private String petname;
    /**
     * 错误登录次数
     * 长度：1
     * 是否为null：N
     **/
    private int errortry;
    /**
     * 经常使用的ip地址
     * 长度：24
     * 是否为null：N
     **/
    private String commonip;
    /**
     * 最后登录地点
     * 长度：124
     * 是否为null：N
     **/
    private String lastplace;
    /**
     * 权限列表
     * 长度：124
     * 是否为null：N
     **/
    private String permissions;

}
