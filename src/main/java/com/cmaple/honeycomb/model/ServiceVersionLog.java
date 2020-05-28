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
 * 类名：服务版本日志实体类 - ServiceVersionLog
 * 功能描述： 服务版本日志实体类
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
@TableName("CS_ServiceVersionLog")  //对应数据库表名称
@Accessors(chain = true)  //chain 若为true，则setter方法返回当前对象()
@NoArgsConstructor  //提供无参构造器
@AllArgsConstructor //提供全参构造器
public class ServiceVersionLog {

    /**
     * 主键
     * 长度：24
     * 是否为null：N
     * 自动递增
     * 主键
     **/
    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    /**
     * 服务编号
     * 长度：24
     * 是否为null：N
     * 主键
     **/
    private String serviceid;
    /**
     * 服务版本号
     * 长度：16
     * 是否为null：Y
     **/
    private String version;
    /**
     * 服务版本修改日期
     * 长度：0
     * 是否为null：Y
     **/
    private Date updatetime;
    /**
     * 服务修改人
     * 长度：24
     * 是否为null：Y
     **/
    private String operator;
    /**
     * 服务修改内容
     * 长度：124
     * 是否为null：Y
     **/
    private String content;
}
