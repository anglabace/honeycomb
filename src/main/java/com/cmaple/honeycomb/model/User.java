package com.cmaple.honeycomb.model;

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

public class User {

    /**
     * 用户顺序号
     * 长度：24
     * 是否为null：N
     * 自动递增
     * 主键
     **/
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
     * 管理密钥
     * 长度：100
     * 是否为null：Y
     **/
    private String manage;

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getUsertype() {
        return usertype;
    }

    public int getUseraffairs() {
        return useraffairs;
    }

    public double getUserbalance() {
        return userbalance;
    }

    public String getIdcard() {
        return idcard;
    }

    public String getName() {
        return name;
    }

    public String getUseraddress() {
        return useraddress;
    }

    public int getTelephonenumber() {
        return telephonenumber;
    }

    public String getUseremail() {
        return useremail;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public Date getClosetime() {
        return closetime;
    }

    public String getUsersign() {
        return usersign;
    }

    public String getPetname() {
        return petname;
    }

    public String getManage() {
        return manage;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public void setUseraffairs(int useraffairs) {
        this.useraffairs = useraffairs;
    }

    public void setUserbalance(double userbalance) {
        this.userbalance = userbalance;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUseraddress(String useraddress) {
        this.useraddress = useraddress;
    }

    public void setTelephonenumber(int telephonenumber) {
        this.telephonenumber = telephonenumber;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public void setClosetime(Date closetime) {
        this.closetime = closetime;
    }

    public void setUsersign(String usersign) {
        this.usersign = usersign;
    }

    public void setPetname(String petname) {
        this.petname = petname;
    }

    public void setManage(String manage) {
        this.manage = manage;
    }
}
