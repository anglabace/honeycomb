package com.cmaple.honeycomb.mapper;

import com.cmaple.honeycomb.mapper.AdvancedSQL.StatisticsSQL;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

/**
 * 类名：mybatis plus 映射公告接口 - StatisticsMapper
 * 功能描述：
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：无
 * 创建人：CMAPLE
 * 创建日期：2019-12-10
 * 修改人：
 * 级别：NULL
 * 修改日期：
 */
public interface StatisticsMapper {
    /**
     * 函数名：复杂查询函数-查询当日官网访问数量- selectCountHomePage（）
     * 功能描述： 查询当日官网访问数量
     * 输入参数：<按照参数定义顺序>
     *
     * @param date String类型的时间条件
     *             返回值：int
     *             异    常：无
     *             创建人：CMAPLE
     *             日期：2020-04-22
     */
    @SelectProvider(type = StatisticsSQL.class, method = "selectCountHomePage")
    int selectCountHomePage(@Param("date") String date);

    /**
     * 函数名：复杂查询函数-查询当日平台访问数量- selectCountPlatform（）
     * 功能描述： 查询当日平台访问数量
     * 输入参数：<按照参数定义顺序>
     *
     * @param date String类型的时间条件
     *             返回值：int
     *             异    常：无
     *             创建人：CMAPLE
     *             日期：2020-04-24
     */
    @SelectProvider(type = StatisticsSQL.class, method = "selectCountPlatform")
    int selectCountPlatform(@Param("date") String date);

    /**
     * 函数名：select函数-根据条件查询用户总数- selectCountByCriteria（）
     * 功能描述： 根据条件查询用户总数
     * 输入参数：<按照参数定义顺序>
     *
     * @param date List类型的条件列表
     *             返回值：int
     *             异    常：NULL
     *             创建人：CMAPLE
     *             日期：2020-04-24
     */
    @SelectProvider(type = StatisticsSQL.class, method = "selectUserCountBefor")
    int selectUserCountBefor(@Param("date") String date);
}
