package com.cmaple.honeycomb.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * 类名：c3p0数据库链接配置类 - DataSourceConfig
 * 功能描述： 配置c3p0数据库链接
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：无
 * 创建人：CMAPLE
 * 创建日期：2019-01-17
 * 修改人：
 * 级别：NULL
 * 修改日期：
 */
@SpringBootConfiguration
public class DataSourceConfig {
    //指定当前对象作为bean
    @Bean(name = "dataSource")
    //指定dataSource来DI
    @Qualifier(value = "dataSource")
    //primary将当前数据库连接池作为默认数据库连接池
    @Primary
    //在application.properties文件中增加前缀c3p0
    @ConfigurationProperties(prefix = "c3p0")
    public DataSource dataSource(){
        return DataSourceBuilder.create().type(com.mchange.v2.c3p0.ComboPooledDataSource.class).build();
    }

}
