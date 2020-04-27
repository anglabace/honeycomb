package com.cmaple.honeycomb.service;

import com.cmaple.honeycomb.mapper.StatisticsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 类名：统计信息服务功能类 - StatisticsService
 * 功能描述：统计信息服务功能类
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：无
 * 创建人：cmaple
 * 创建日期：2019-12-10
 * 修改人：
 * 级别：NULL
 * 修改日期：
 */
@Service
public class StatisticsService {
    //引入
    @Autowired
    private StatisticsMapper statisticsMapper;

    /**
     * 函数名：查询函数-根据时间查询当日官网访问数量 - selectCountHomePage（）
     * 功能描述： 根据时间查询当日官网访问数量
     * 输入参数：<按照参数定义顺序>
     *
     * @param date String类型的时间
     *             返回值：int
     *             异    常：无
     *             创建人：CMAPLE
     *             日期：2019-12-10
     *             修改人：
     *             日期：
     */
    public int selectCountHomePage(String date) {
        return statisticsMapper.selectCountHomePage(date);
    }

    /**
     * 函数名：查询函数-根据时间查询当日平台访问数量 - getPlatformNumByDate（）
     * 功能描述： 根据时间查询当日平台访问数量
     * 输入参数：<按照参数定义顺序>
     *
     * @param date String类型的时间
     *             返回值：int
     *             异    常：无
     *             创建人：CMAPLE
     *             日期：2019-12-10
     *             修改人：
     *             日期：
     */
    public int selectCountPlatform(String date) {
        return statisticsMapper.selectCountPlatform(date);
    }


    /**
     * 函数名：查询函数-根据时间查询当日异常日志数量 - selectUserCountBefor（）
     * 功能描述： 根据时间查询当日异常日志数量
     * 输入参数：<按照参数定义顺序>
     *
     * @param date String类型的时间
     *             返回值：int
     *             异    常：无
     *             创建人：CMAPLE
     *             日期：2019-12-10
     *             修改人：
     *             日期：
     */
    public int selectUserCountBefor(String date) {
        return statisticsMapper.selectUserCountBefor(date);
    }
}
