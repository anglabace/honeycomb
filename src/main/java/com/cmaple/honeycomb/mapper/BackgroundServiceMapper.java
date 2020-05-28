package com.cmaple.honeycomb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cmaple.honeycomb.mapper.AdvancedSQL.BackgroundServiceSQL;
import com.cmaple.honeycomb.model.BackgroundService;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * 类名：mybatis plus 映射后台服务接口 - BackgroundServiceMapper
 * 功能描述：NULL
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：NULL
 * 创建人：CMAPLE
 * 创建日期：2019-09-30
 */
public interface BackgroundServiceMapper extends BaseMapper<BackgroundService> {

    /**
     * 函数名：select函数-根据条件查询后台服务列表- selectByCriteria（）
     * 功能描述： 根据条件查询后台服务列表
     * 输入参数：<按照参数定义顺序>
     * <p>
     * 返回值：list<BackgroundService>
     * 异    常：NULL
     * 创建人：CMAPLE
     * 日期：2019-09-30
     */
    @SelectProvider(type = BackgroundServiceSQL.class, method = "selectByCriteria")
    List<BackgroundService> selectByCriteria();

    /**
     * 函数名：select函数-根据条件查询后台服务总数- selectCountByCriteria（）
     * 功能描述： 根据条件查询后台服务总数
     * 输入参数：<按照参数定义顺序>
     * <p>
     * 返回值：int
     * 异    常：NULL
     * 创建人：CMAPLE
     * 日期：2019-12-31
     */
    @SelectProvider(type = BackgroundServiceSQL.class, method = "selectCountByCriteria")
    int selectCountByCriteria();

    /**
     * 函数名：select函数-按照时间倒叙获取后台服务列表 - selectDescOrderByTime（）
     * 功能描述： 按照时间倒叙获取后台服务列表
     * 输入参数：<按照参数定义顺序>
     * 返回值：List<BackgroundService>
     * 异    常：NULL
     * 创建人：CMAPLE
     * 日期：2019-12-24
     */
    @SelectProvider(type = BackgroundServiceSQL.class, method = "selectDescOrderByTime")
    List<BackgroundService> selectDescOrderByTime();

}