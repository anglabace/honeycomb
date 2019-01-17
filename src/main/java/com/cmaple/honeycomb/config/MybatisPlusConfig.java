package com.cmaple.honeycomb.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 类名：mybatis plus分页插件类 - MybatisPlusConfig
 * 功能描述： 配置相关分页插件
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：无
 * 创建人：CMAPLE
 * 创建日期：2019-01-17
 * 修改人：
 * 级别：NULL
 * 修改日期：
 */
@EnableTransactionManagement(proxyTargetClass = true)
@Configuration
@MapperScan("com.cmaple.honeycomb.mapper")
public class MybatisPlusConfig {
    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
