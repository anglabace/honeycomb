package com.cmaple.honeycomb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 类名：阿里云身份认证实体类 - AliyunIDName
 * 功能描述： 用户实体类
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：无
 * 创建人：CMAPLE
 * 创建日期：2019-09-24
 * 修改人：
 * 级别：NULL
 * 修改日期：
 */

@Data //给实体提供读写属性, 此外还提供了 equals()、hashCode()、toString() 方法
@Accessors(chain = true)  //chain 若为true，则setter方法返回当前对象()
@NoArgsConstructor  //提供无参构造器
@AllArgsConstructor //提供全参构造器
public class AliyunIDName {

    /**
     * 错误码
     * 长度：3
     * 是否为null：N/S
     * 404：非法参数、403：无权限操作、200：成功、500：内服服务器错误
     **/
    private int code;
    /**
     * 其他信息
     * 长度：
     * 是否为null：N/S
     **/
    private AliyunIDName_Value value;
    /**
     * 返回信息
     * 长度：3
     * 是否为null：N/S
     **/
    private String message;
}
