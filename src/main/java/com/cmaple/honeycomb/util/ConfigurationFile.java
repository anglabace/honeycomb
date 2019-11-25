package com.cmaple.honeycomb.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data //给实体提供读写属性, 此外还提供了 equals()、hashCode()、toString() 方法
@Accessors(chain = true)  //chain 若为true，则setter方法返回当前对象()
@NoArgsConstructor  //提供无参构造器
@AllArgsConstructor //提供全参构造器
@Component
public class ConfigurationFile {
    /**
     * 缓存文件路径
     * 长度：0
     * 是否为null：N/S
     **/
    @Value("${config.cachefile.path}")
    private String CACHEFILEPATH;
    /**
     * 静态资源文件根目录
     * 长度：0
     * 是否为null：N/S
     **/
    @Value("${config.staticfile.path}")
    private String STATICFILEPATH;
    /**
     * 里程碑文件根目录
     * 长度：0
     * 是否为null：N/S
     **/
    @Value("${config.milestonefile.path}")
    private String MILESTONEFILEPATH;
    /**
     * 调查报告文件根目录
     * 长度：0
     * 是否为null：N/S
     **/
    @Value("${config.reportfile.path}")
    private String REPORTFILEPATH;
    /**
     * 服务资源文件根目录
     * 长度：0
     * 是否为null：N/S
     **/
    @Value("${config.backgroundServicefile.path}")
    private String BACKGROUNDSERVICEFILEPATH;
}
