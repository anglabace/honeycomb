package com.cmaple.honeycomb.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 类名：用户实体类 - User
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

@Data //给实体提供读写属性, 此外还提供了 equals()、hashCode()、toString() 方法
@Accessors(chain = true)  //chain 若为true，则setter方法返回当前对象()
@NoArgsConstructor  //提供无参构造器
@AllArgsConstructor //提供全参构造器
public class ToolsFile {
    /**
     * 文件名
     * 长度：0
     * 是否为null：N/S
     **/
    private String filename;
    /**
     * 文件类型
     * 长度：0
     * 是否为null：N/S
     **/
    private String filetype;
    /**
     * 文件绝对路径
     * 长度：0
     * 是否为null：N/S
     **/
    private String filepath;
}
