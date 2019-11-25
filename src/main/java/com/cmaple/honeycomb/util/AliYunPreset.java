package com.cmaple.honeycomb.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 类名：阿里云配置信息预设实体类 - AliYunPreset
 * 功能描述： 阿里云配置信息预设实体类
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：无
 * 创建人：CMAPLE
 * 创建日期：2019-09-26
 * 修改人：
 * 级别：NULL
 * 修改日期：
 */

@Component
@Data //给实体提供读写属性, 此外还提供了 equals()、hashCode()、toString() 方法
@Accessors(chain = true)  //chain 若为true，则setter方法返回当前对象()
@NoArgsConstructor  //提供无参构造器
@AllArgsConstructor //提供全参构造器
public class AliYunPreset {
    /**
     * 阿里云appcode
     * 长度：3
     * 是否为null：N/S
     **/
    @Value("${aliyun.appcode}")
    private String APPCODE;
    /**
     * 阿里云userid
     * 长度：3
     * 是否为null：N/S
     **/
    @Value("${aliyun.__userId}")
    private String USERID;
    /**
     * 阿里云verifyKey
     * 长度：3
     * 是否为null：N/S
     **/
    @Value("${aliyun.verifyKey}")
    private String VERIFYKEY;
    /**
     * 阿里云host
     * 长度：3
     * 是否为null：N/S
     **/
    @Value("${aliyun.host}")
    private String HOST;
    /**
     * 阿里云path
     * 长度：3
     * 是否为null：N/S
     **/
    @Value("${aliyun.path}")
    private String PATH;
    /**
     * 阿里云METHOD
     * 长度：3
     * 是否为null：N/S
     **/
    @Value("${aliyun.method}")
    private String METHOD;
}
