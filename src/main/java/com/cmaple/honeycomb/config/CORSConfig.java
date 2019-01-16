package com.cmaple.honeycomb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 类名：跨域请求配置类 - CORSConfig
 * 功能描述： 配置可以跨域访问的URL、请求信息头、发起的任意HTTP Method、及可以发起请求的外域
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：无
 * 创建人：cmaple
 * 创建日期：2019-01-16
 * 修改人：
 * 级别：NULL
 * 修改日期：
 */
@Configuration
public class CORSConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")       //匹配了所有的url
                        .allowedHeaders("*")                //允许跨域请求包含任意头信息
                        .allowedMethods("*")                //允许外域请求发起任意HTTP METHOD
                        .allowedOrigins("*");               //允许所有的外域发起跨域请求

            }
        };
    }

}
