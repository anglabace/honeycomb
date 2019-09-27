package com.cmaple.honeycomb.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 类名：文件信息实体类 - ToolsFile
 * 功能描述： 文件信息实体类
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：无
 * 创建人：CMAPLE
 * 创建日期：2019-09-27
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
    /**
     * 文件大小
     * 长度：0
     * 是否为null：N/S
     **/
    private String filesize;
    /**
     * 文件后缀
     * 长度：0
     * 是否为null：N/S
     **/
    private String suffix;
    /**
     * 文件创建时间
     * 长度：0
     * 是否为null：N/S
     **/
    private String createtime;
    /**
     * 文件修改时间
     * 长度：0
     * 是否为null：N/S
     **/
    private String modifytime;

}
