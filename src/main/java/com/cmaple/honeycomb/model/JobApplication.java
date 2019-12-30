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
 * 类名：岗位申请实体类 - Work
 * 功能描述： 工作实体类
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：无
 * 创建人：CMAPLE
 * 创建日期：2019-12-27
 * 修改人：
 * 级别：NULL
 * 修改日期：
 */

@Data //给实体提供读写属性, 此外还提供了 equals()、hashCode()、toString() 方法
@TableName("CS_JobApplication")  //对应数据库表名称
@Accessors(chain = true)  //chain 若为true，则setter方法返回当前对象()
@NoArgsConstructor  //提供无参构造器
@AllArgsConstructor //提供全参构造器
public class JobApplication {
    /**
     * 岗位申请id
     * 长度：24
     * 是否为null：N
     * 自动递增
     * 主键
     **/
    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    /**
     * 岗位id
     * 长度：24
     * 是否为null：Y
     **/
    private String workId;
    /**
     * 申请人名字
     * 长度：24
     * 是否为null：Y
     **/
    private String name;
    /**
     * 电话号码
     * 长度：24
     * 是否为null：Y
     **/
    private String phonenNmber;
    /**
     * 电子邮件
     * 长度：24
     * 是否为null：Y
     **/
    private String emaile;
    /**
     * 地址
     * 长度：24
     * 是否为null：Y
     **/
    private String address;
    /**
     * 目前就职公司
     * 长度：24
     * 是否为null：Y
     **/
    private String company;
    /**
     * githuburl
     * 长度：24
     * 是否为null：Y
     **/
    private String githubUrl;
    /**
     * personelurl
     * 长度：24
     * 是否为null：Y
     **/
    private String personelUrl;
    /**
     * 附加信息
     * 长度：24
     * 是否为null：Y
     **/
    private String information;
    /**
     * 申请日期
     * 长度：24
     * 是否为null：Y
     **/
    private Date createDate;
    /**
     * 是否通过申请
     * 长度：24
     * 是否为null：Y
     **/
    private boolean idpassed;
    /**
     * 审核申请人
     * 长度：24
     * 是否为null：Y
     **/
    private String applicationUser;
    /**
     * 审核日期
     * 长度：24
     * 是否为null：Y
     **/
    private Date applicationDate;
}
