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
 * 类名：时间轴实体类 - TimeAxis
 * 功能描述： 时间轴实体类
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：无
 * 创建人：CMAPLE
 * 创建日期：2019-09-19
 * 修改人：
 * 级别：NULL
 * 修改日期：
 */

@Data //给实体提供读写属性, 此外还提供了 equals()、hashCode()、toString() 方法
@TableName("CS_TimeAxis")  //对应数据库表名称
@Accessors(chain = true)  //chain 若为true，则setter方法返回当前对象()
@NoArgsConstructor  //提供无参构造器
@AllArgsConstructor //提供全参构造器
public class TimeAxis {

    /**
     * 时间轴id号
     * 长度：24
     * 是否为null：N
     * 自动递增
     * 主键
     **/
    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    /**
     * 时间轴标题
     * 长度：24
     * 是否为null：Y
     **/
    private String title;
    /**
     * 时间轴内容
     * 长度：124
     * 是否为null：Y
     **/
    private String content;
    /**
     * 时间轴事件日期
     * 长度：0
     * 是否为null：Y
     **/
    private Date eventdate;
    /**
     * 时间轴创建人
     * 长度：24
     * 是否为null：Y
     **/
    private String createuser;
    /**
     * 时间轴创建日期
     * 长度：0
     * 是否为null：Y
     **/
    private Date createdate;
}
