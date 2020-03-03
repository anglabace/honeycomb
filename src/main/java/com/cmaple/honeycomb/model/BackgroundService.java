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
 * 类名：后台服务实体类 - BackgroundService
 * 功能描述： 后台服务实体类
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
@TableName("CS_BackgroundService")  //对应数据库表名称
@Accessors(chain = true)  //chain 若为true，则setter方法返回当前对象()
@NoArgsConstructor  //提供无参构造器
@AllArgsConstructor //提供全参构造器
public class BackgroundService {

    /**
     * 服务id号
     * 长度：24
     * 是否为null：N
     * 自动递增
     * 主键
     **/
    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    /**
     * 服务编号
     * 长度：12
     * 是否为null：Y
     **/
    private String serviceid;
    /**
     * 服务名
     * 长度：24
     * 是否为null：Y
     **/
    private String name;
    /**
     * 服务简介
     * 长度：254
     * 是否为null：Y
     **/
    private String synopsis;
    /**
     * 版本号
     * 长度：14
     * 是否为null：Y
     **/
    private String version;
    /**
     * 服务存放路径
     * 长度：46
     * 是否为null：Y
     **/
    private String path;
    /**
     * 接口地址
     * 长度：36
     * 是否为null：Y
     **/
    private String route;
    /**
     * 服务大小
     * 长度：6
     * 是否为null：Y
     **/
    private String size;
    /**
     * 语言类型
     * 长度：12
     * 是否为null：Y
     **/
    private String proglanguage;
    /**
     * 接收数据类型
     * 长度：12
     * 是否为null：Y
     **/
    private String receivetype;
    /**
     * 服务作者
     * 长度：24
     * 是否为null：Y
     **/
    private String author;
    /**
     * 服务上传人员
     * 长度：24
     * 是否为null：Y
     **/
    private String upusername;
    /**
     * 服务上传时间
     * 长度：0
     * 是否为null：Y
     **/
    private Date createtime;
    /**
     * 服务所在服务器
     * 长度：12
     * 是否为null：Y
     **/
    private String serverid;
    /**
     * 服务状态
     * 长度：12
     * 是否为null：Y
     **/
    private String servicestate;
    /**
     * 附件地址
     * 长度：36
     * 是否为null：Y
     **/
    private String annexepath;
}
