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
 * 类名：工作实体类 - Work
 * 功能描述： 工作实体类
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
@TableName("CS_Work")  //对应数据库表名称
@Accessors(chain = true)  //chain 若为true，则setter方法返回当前对象()
@NoArgsConstructor  //提供无参构造器
@AllArgsConstructor //提供全参构造器
public class Work {

    /**
     * 工作id号
     * 长度：24
     * 是否为null：N
     * 自动递增
     * 主键
     **/
    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    /**
     * 工作标题名称
     * 长度：24
     * 是否为null：Y
     **/
    private String title;
    /**
     * 工作入职地区
     * 长度：12
     * 是否为null：Y
     **/
    private String place;
    /**
     * 工作职位类型
     * 长度：12
     * 是否为null：Y
     **/
    private String type;
    /**
     * 工作性质
     * 长度：12
     * 是否为null：Y
     **/
    private String nature;
    /**
     * 工作需求内容
     * 长度：254
     * 是否为null：Y
     **/
    private String content;
    /**
     * 工作创建人
     * 长度：24
     * 是否为null：Y
     **/
    private String createuser;
    /**
     * 工作创建日期
     * 长度：0
     * 是否为null：Y
     **/
    private Date creatdate;
    /**
     * 工作需求人数
     * 长度：6
     * 是否为null：Y
     **/
    private int need;
    /**
     * 工作申请人数
     * 长度：6
     * 是否为null：Y
     **/
    private int application;
}
