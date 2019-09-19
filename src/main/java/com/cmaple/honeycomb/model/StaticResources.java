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
 * 类名：静态资源实体类 - StaticResources
 * 功能描述： 静态资源实体类
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
@TableName("CS_StaticResources")  //对应数据库表名称
@Accessors(chain = true)  //chain 若为true，则setter方法返回当前对象()
@NoArgsConstructor  //提供无参构造器
@AllArgsConstructor //提供全参构造器
public class StaticResources {

    /**
     * 静态资源id编号（唯一）
     * 长度：24
     * 是否为null：N
     * 自动递增
     * 主键
     **/
    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    /***
     静态资源所在位置
     * 长度：12
     * 是否为null：N
     **/
    private String pagepath;
    /***
     图片绝对路径
     * 长度：64
     * 是否为null：N
     **/
    private String imgrealpath;
    /***
     图片相对路径
     * 长度：64
     * 是否为null：N
     **/
    private String imgrelativepath;
    /***
     图片名称
     * 长度：32
     * 是否为null：N
     **/
    private String imgname;
    /***
     图片实际像素大小
     * 长度：32
     * 是否为null：N
     **/
    private String imgrealsize;
    /***
     页面展示图片大小
     * 长度：32
     * 是否为null：N
     **/
    private String imgrelativesize;
    /***
     静态资源图片大小
     * 长度：12
     * 是否为null：N
     **/
    private String size;
    /***
     静态资源创建日期
     * 长度：0
     * 是否为null：N
     **/
    private Date creattime;
    /***
     静态资源最后修改日期
     * 长度：0
     * 是否为null：Y
     **/
    private Date updatetime;


}
