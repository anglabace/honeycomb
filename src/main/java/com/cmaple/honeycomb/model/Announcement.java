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
 * 类名：公告信息实体类 - Announcement
 * 功能描述： 公告信息实体类
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：无
 * 创建人：CMAPLE
 * 创建日期：2019-09-17
 */

@Data //给实体提供读写属性, 此外还提供了 equals()、hashCode()、toString() 方法
@TableName("CS_Announcement")  //对应数据库表名称
@Accessors(chain = true)  //chain 若为true，则setter方法返回当前对象()
@NoArgsConstructor  //提供无参构造器
@AllArgsConstructor //提供全参构造器
public class Announcement {

    /**
     * 公告id号
     * 长度：24
     * 是否为null：N
     * 自动递增
     * 主键
     **/
    @TableId(value = "id", type = IdType.AUTO)
    private int id;

    /**
     * 公告标题
     * 长度：36
     * 是否为null：N
     **/
    private String title;

    /**
     * 公告简介
     * 长度：100
     * 是否为null：N
     **/
    private String synopsis;

    /**
     * 公告文件名
     * 长度：24
     * 是否为null：N
     **/
    private String filename;

    /**
     * 公告文件路径
     * 长度：46
     * 是否为null：N
     **/
    private String filepath;

    /**
     * 公告作者
     * 长度：24
     * 是否为null：N
     **/
    private String author;

    /**
     * 公告创建时间
     * 长度：0
     * 是否为null：N
     **/
    private Date creattime;

    /**
     * 公告阅读时间
     * 长度：12
     * 是否为null：N
     **/
    private String readtime;
}
