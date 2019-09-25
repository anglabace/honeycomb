package com.cmaple.honeycomb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 类名：阿里云身份认证,value实体类 - AliyunIDName_Value
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
public class AliyunIDName_Value {

    /**
     * 回调地址
     * 长度：
     * 是否为null：N/S
     **/
    private String verifyUrl;
    /**
     * bizCode
     * 长度：
     * 是否为null：N/S
     **/
    private int bizCode;
    /**
     * 返回信息
     * 长度：
     * 是否为null：N/S
     **/
    private String message;
}
