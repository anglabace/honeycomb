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

//    /**
//     * 函数名：查询函数-根据时间查询当日官网访问数量 - getWebsiteNumByDate（）
//     * 功能描述： 根据时间查询当日官网访问数量
//     * 输入参数：<按照参数定义顺序>
//     *
//     * @param date String类型的时间
//     *             返回值：int
//     *             异    常：无
//     *             创建人：CMAPLE
//     *             日期：2019-12-10
//     *             修改人：
//     *             日期：
//     */
//    public int getWebsiteNumByDate(String date) {
//        return statisticsMapper.queryWebsiteNumByDate(date);
//    }
//
//    /**
//     * 函数名：查询函数-根据时间查询当日平台访问数量 - getPlatformNumByDate（）
//     * 功能描述： 根据时间查询当日平台访问数量
//     * 输入参数：<按照参数定义顺序>
//     *
//     * @param date String类型的时间
//     *             返回值：int
//     *             异    常：无
//     *             创建人：CMAPLE
//     *             日期：2019-12-10
//     *             修改人：
//     *             日期：
//     */
//    public int getPlatformNumByDate(String date) {
//        return statisticsMapper.queryPlatformNumByDate(date);
//    }
//
//    /**
//     * 函数名：查询函数-查询用户总数量 - getInsertNumByDate（）
//     * 功能描述： 查询用户总数量
//     * 输入参数：<按照参数定义顺序>
//     * <p>
//     * 返回值：int
//     * 异    常：无
//     * 创建人：CMAPLE
//     * 日期：2019-12-10
//     * 修改人：
//     * 日期：
//     */
//    public int getInsertNum() {
//        return statisticsMapper.queryInsertNum();
//    }
//
//    /**
//     * 函数名：查询函数-根据时间查询当日异常日志数量 - getExceptionLogNumByDate（）
//     * 功能描述： 根据时间查询当日异常日志数量
//     * 输入参数：<按照参数定义顺序>
//     *
//     * @param date String类型的时间
//     *             返回值：int
//     *             异    常：无
//     *             创建人：CMAPLE
//     *             日期：2019-12-10
//     *             修改人：
//     *             日期：
//     */
//    public int getExceptionLogNumByDate(String date) {
//        return statisticsMapper.queryExceptionLogNumByDate(date);
//    }
//
//    /**
//     * 函数名：查询函数-根据日期、服务id查询官方服务使用数量 - getServiceUsageByCondition（）
//     * 功能描述： 根据日期、服务id查询官方服务使用数量
//     * 输入参数：<按照参数定义顺序>
//     *
//     * @param servicename String类型的服务名
//     * @param date        String类型的时间
//     *                    返回值：int
//     *                    异    常：无
//     *                    创建人：CMAPLE
//     *                    日期：2019-12-16
//     *                    修改人：
//     *                    日期：
//     */
//    public int getServiceUsageByCondition(String servicename, String date) {
//        return statisticsMapper.queryServiceUsageByCondition(servicename, date);
//    }
//
//    /**
//     * 函数名：查询函数-按照日期查询用户登陆数量 - getServiceUsageByCondition（）
//     * 功能描述： 按照日期查询用户登陆数量
//     * 输入参数：<按照参数定义顺序>
//     *
//     * @param date String类型的时间
//     *             返回值：int
//     *             异    常：无
//     *             创建人：CMAPLE
//     *             日期：2019-12-16
//     *             修改人：
//     *             日期：
//     */
//    public int getLoginByDate(String date) {
//        return statisticsMapper.queryLoginByDate(date);
//    }
//
//    /**
//     * 函数名：复杂查询函数-根据日期、服务id查询服务异常数量- queryServiceExceptionByCondition（）
//     * 功能描述： 根据日期、服务id查询服务异常数量
//     * 输入参数：<按照参数定义顺序>
//     *
//     * @param servicename servicename类型的服务名
//     * @param date        String类型的时间条件
//     *                    <p>
//     *                    返回值：int
//     *                    异    常：无
//     *                    创建人：CMAPLE
//     *                    日期：2019-12-16
//     *                    修改人：
//     *                    级别：普通用户
//     *                    日期：
//     */
//    public int getServiceExceptionByCondition(String servicename, String date) {
//        return statisticsMapper.queryServiceExceptionByCondition(servicename, date);
//    }
//
//    /**
//     * 函数名：复杂查询函数-查询挂载服务器数量- getServersNumber（）
//     * 功能描述： 查询挂载服务器数量
//     * 输入参数：<按照参数定义顺序>
//     *
//     * <p>
//     * 返回值：int
//     * 异    常：无
//     * 创建人：CMAPLE
//     * 日期：2019-12-16
//     * 修改人：
//     * 级别：普通用户
//     * 日期：
//     */
//    public int getServersNumber() {
//        return statisticsMapper.queryServersNumber();
//    }
//
//    /**
//     * 函数名：复杂查询函数-查询上线服务数量（包含锁定的服务）- queryServicesNumber（）
//     * 功能描述： 查询上线服务数量（包含锁定的服务）
//     * 输入参数：<按照参数定义顺序>
//     *
//     * <p>
//     * 返回值：int
//     * 异    常：无
//     * 创建人：CMAPLE
//     * 日期：2019-12-16
//     * 修改人：
//     * 级别：普通用户
//     * 日期：
//     */
//    public int getServicesNumber() {
//        return statisticsMapper.queryServicesNumber();
//    }
//
//    /**
//     * 函数名：复杂查询函数-查询所有服务使用的总和数量- queryAllServiceUsageByCondition（）
//     * 功能描述： 查询所有服务使用的总和数量
//     * 输入参数：<按照参数定义顺序>
//     *
//     * @param servicesname String[]类型的服务名
//     * @param date         String类型的时间
//     *                     <p>
//     *                     返回值：int
//     *                     异    常：无
//     *                     创建人：CMAPLE
//     *                     日期：2019-12-16
//     *                     修改人：
//     *                     级别：普通用户
//     *                     日期：
//     */
//    public int getAllServiceUsageByCondition(String[] servicesname, String date) {
//        return statisticsMapper.queryAllServiceUsageByCondition(servicesname, date);
//    }
//
//    /**
//     * 函数名：复杂查询函数-查询该服务在开启时间之后至今的使用数量- queryServicesNumberByName（）
//     * 功能描述： 查询该服务在开启时间之后至今的使用数量
//     * 输入参数：<按照参数定义顺序>
//     *
//     * @param servicename String类型的服务名字
//     *                    <p>
//     *                    返回值：int
//     *                    异    常：无
//     *                    创建人：CMAPLE
//     *                    日期：2019-12-16
//     *                    修改人：
//     *                    级别：普通用户
//     *                    日期：
//     */
//    public int getServicesNumberByName(String servicename) {
//        return statisticsMapper.queryServicesNumberByName(servicename);
//    }
}
