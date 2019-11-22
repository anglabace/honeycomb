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
 * 类名：里程碑信息实体类 - Milestone
 * 功能描述： 里程碑信息实体类
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：无
 * 创建人：CMAPLE
 * 创建日期：2019-09-17
 * 修改人：
 * 级别：NULL
 * 修改日期：
 */
@Data //给实体提供读写属性, 此外还提供了 equals()、hashCode()、toString() 方法
@TableName("CS_Milestone")  //对应数据库表名称
@Accessors(chain = true)  //chain 若为true，则setter方法返回当前对象()
@NoArgsConstructor  //提供无参构造器
@AllArgsConstructor //提供全参构造器
public class Milestone {

    /**
     * 里程碑id号
     * 长度：24
     * 是否为null：N
     * 自动递增
     * 主键
     **/
    @TableId(value = "id", type = IdType.AUTO)
    private int id;

    /**
     * 里程碑标题
     * 长度：24
     * 是否为null：N
     **/
    private String title;

    /**
     * 里程碑简介
     * 长度：254
     * 是否为null：N
     **/
    private String synopsis;

    /**
     * 里程碑作者
     * 长度：24
     * 是否为null：N
     **/
    private String author;

    /**
     * 里程碑创建时间
     * 长度：0
     * 是否为null：N
     **/
    private Date creattime;

    /**
     * 里程碑阅读时间
     * 长度：12
     * 是否为null：N
     **/
    private String readtime;

    /**
     * 里程碑文件名
     * 长度：24
     * 是否为null：N
     **/
    private String filename;

    /**
     * 里程碑文件路径
     * 长度：46
     * 是否为null：N
     **/
    private String filepath;

    /**
     * 里程碑图片信息（在静态资源信息表）
     * 长度：46
     * 是否为null：N
     **/
    private String imgurl;

    /**
     * 里程碑按钮组
     * 长度：124
     * 是否为null：N
     **/
    private String buttongroup;
}
