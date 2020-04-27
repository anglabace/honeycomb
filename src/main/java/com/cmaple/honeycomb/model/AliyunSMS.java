package com.cmaple.honeycomb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


/**
 * 类名：阿里云身份认证实体类 - AliyunSMS
 * 功能描述： 用户实体类
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：无
 * 创建人：CMAPLE
 * 创建日期：2020-03-05
 * 修改人：
 * 级别：NULL
 * 修改日期：
 */
@Data //给实体提供读写属性, 此外还提供了 equals()、hashCode()、toString() 方法
@Accessors(chain = true)  //chain 若为true，则setter方法返回当前对象()
@NoArgsConstructor  //提供无参构造器
@AllArgsConstructor //提供全参构造器
public class AliyunSMS {

    /**
     * 状态码的描述。
     * 长度：3
     * 是否为null：N/S
     * 404：非法参数、403：无权限操作、200：成功、500：内服服务器错误
     **/
    private String Message;
    /**
     * 请求ID。
     * 长度：3
     * 是否为null：N/S
     * 404：非法参数、403：无权限操作、200：成功、500：内服服务器错误
     **/
    private String RequestId;
    /**
     * 发送回执ID，可根据该ID在接口QuerySendDetails中查询具体的发送状态。。
     * 长度：3
     * 是否为null：N/S
     * 404：非法参数、403：无权限操作、200：成功、500：内服服务器错误
     **/
    private String BizId;
    /**
     * 请求状态码
     * 长度：3
     * 是否为null：N/S
     * 404：非法参数、403：无权限操作、200：成功、500：内服服务器错误
     **/
    private String Code;
}
