package com.cmaple.honeycomb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cmaple.honeycomb.mapper.AdvancedSQL.BackgroundServiceSQL;
import com.cmaple.honeycomb.model.BackgroundService;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

/**
 * 类名：mybatis plus 映射后台服务接口 - BackgroundServiceMapper
 * 功能描述：
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：无
 * 创建人：cmaple
 * 创建日期：2019-09-30
 * 修改人：
 * 级别：NULL
 * 修改日期：
 */
public interface BackgroundServiceMapper extends BaseMapper<BackgroundService> {

    /**
     * 函数名：复杂查询函数-根据条件查询后台服务列表- queryBackgroundServicesByParams（）
     * 功能描述： 根据条件查询后台服务列表
     * 输入参数：<按照参数定义顺序>
     *
     * @param list   条件列表
     * @param params 字段及数值集合
     * @param page   分页查询PAGE条件
     * @param num    分页查询NUM条件
     *               返回值：list<BackgroundService>
     *               异    常：无
     *               创建人：CMAPLE
     *               日期：2019-09-30
     *               修改人：
     *               级别：普通用户
     *               日期：
     */
    @SelectProvider(type = BackgroundServiceSQL.class, method = "queryBackgroundServicesByParams")
    List<BackgroundService> queryBackgroundServicesByParams(@Param("list") List<String> list, @Param("params") Map<String, Object> params, @Param("page") int page, @Param("num") int num);

    /**
     * 函数名：复杂查询函数-按照时间倒叙获取后台服务详情- queryBackgroundServicesByParams（）
     * 功能描述： 按照时间倒叙获取后台服务详情
     * 输入参数：<按照参数定义顺序>
     * <p>
     * 返回值：List<BackgroundService>
     * 异    常：无
     * 创建人：CMAPLE
     * 日期：2019-12-24
     * 修改人：
     * 级别：普通用户
     * 日期：
     */
    @SelectProvider(type = BackgroundServiceSQL.class, method = "queryBackgroundServicesDescOrderBy")
    List<BackgroundService> queryBackgroundServicesDescOrderBy();

}
