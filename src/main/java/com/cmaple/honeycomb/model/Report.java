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
 * 类名：调查报告实体类 - Report
 * 功能描述： 调查报告实体类
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
@TableName("CS_Report")  //对应数据库表名称
@Accessors(chain = true)  //chain 若为true，则setter方法返回当前对象()
@NoArgsConstructor  //提供无参构造器
@AllArgsConstructor //提供全参构造器
public class Report {

    /**
     * 调查报告id号
     * 长度：24
     * 是否为null：N
     * 自动递增
     * 主键
     **/
    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    /**
     * 调查报告标题
     * 长度：24
     * 是否为null：Y
     **/
    private String title;
    /**
     * 调查报告编号
     * 长度：24
     * 是否为null：Y
     **/
    private String reportid;
    /**
     * 调查报告类型
     * 长度：12
     * 是否为null：Y
     **/
    private String reporttype;
    /**
     * 调查报告作者
     * 长度：24
     * 是否为null：Y
     **/
    private String author;
    /**
     * 调查报告创建时间
     * 长度：0
     * 是否为null：Y
     **/
    private Date creatdate;
    /**
     * 调查报告文件名
     * 长度：24
     * 是否为null：Y
     **/
    private String filename;
    /**
     * 调查报告文件路径
     * 长度：46
     * 是否为null：Y
     **/
    private String filepath;
}
