package com.cmaple.honeycomb.util;

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
public class AliYunPreset {

    @Value("${aliyun.appcode}")
    private String APPCODE;
    @Value("${aliyun.__userId}")
    private String USERID;
    @Value("${aliyun.verifyKey}")
    private String VERIFYKEY;
    @Value("${aliyun.host}")
    private String HOST;
    @Value("${aliyun.path}")
    private String PATH;
    @Value("${aliyun.method}")
    private String METHOD;


    public String getAPPCODE() {
        return APPCODE;
    }

    public String getUSERID() {
        return USERID;
    }

    public String getVERIFYKEY() {
        return VERIFYKEY;
    }

    public String getHOST() {
        return HOST;
    }

    public String getPATH() {
        return PATH;
    }

    public String getMETHOD() {
        return METHOD;
    }
}
