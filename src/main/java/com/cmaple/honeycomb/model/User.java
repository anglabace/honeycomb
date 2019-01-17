package com.cmaple.honeycomb.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 类名：跨域请求配置类 - User
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

@Data
@TableName("BS_USER")
@Accessors(chain = true)
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
     * 用户登录id
     * 长度：36
     * 是否为null：Y
     **/
    private String username;
    /**
     * 用户密码
     * 长度：200
     * 是否为null：Y
     **/
    private String password;
    /**
     * 账户级别:{101:普通会员/102：付费会员/103:程序三级管理员/999：超级管理员}
     * 长度：3
     * 是否为null：N
     **/
    private String usertype;
    /**
     * 账户状态{0:正常状态/1账户非法操作标记/2:账户删除标记 }
     * 长度：1
     * 是否为null：N
     **/
    private int useraffairs;
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
    private int telephonenumber;
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
     * 账户作废时间
     * 长度：
     * 是否为null：Y
     **/
    private Date closetime;
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
}
