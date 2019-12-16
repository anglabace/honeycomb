package com.cmaple.honeycomb.mapper;

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
     * 函数名：复杂查询函数-查询当日官网访问数量- queryWebsiteNumByDate（）
     * 功能描述： 查询当日官网访问数量
     * 输入参数：<按照参数定义顺序>
     *
     * @param date String类型的时间条件
     *             返回值：int
     *             异    常：无
     *             创建人：CMAPLE
     *             日期：2019-12-10
     *             修改人：
     *             级别：普通用户
     *             日期：
     */
    @SelectProvider(type = AdvancedSQL.class, method = "queryWebsiteNumByDate")
    int queryWebsiteNumByDate(String date);

    /**
     * 函数名：复杂查询函数-查询当日平台访问数量- queryPlatformNumByDate（）
     * 功能描述： 查询当日平台访问数量
     * 输入参数：<按照参数定义顺序>
     *
     * @param date String类型的时间条件
     *             返回值：int
     *             异    常：无
     *             创建人：CMAPLE
     *             日期：2019-12-10
     *             修改人：
     *             级别：普通用户
     *             日期：
     */
    @SelectProvider(type = AdvancedSQL.class, method = "queryPlatformNumByDate")
    int queryPlatformNumByDate(String date);

    /**
     * 函数名：复杂查询函数-查询用户总数量- queryInsertNum（）
     * 功能描述： 查询用户总数量
     * 输入参数：<按照参数定义顺序>
     * <p>
     * 返回值：int
     * 异    常：无
     * 创建人：CMAPLE
     * 日期：2019-12-10
     * 修改人：
     * 级别：普通用户
     * 日期：
     */
    @SelectProvider(type = AdvancedSQL.class, method = "queryInsertNum")
    int queryInsertNum();

    /**
     * 函数名：复杂查询函数-查询今日异常日志数量- queryExceptionLogNumByDate（）
     * 功能描述： 查询今日异常日志数量
     * 输入参数：<按照参数定义顺序>
     *
     * @param date String类型的时间条件
     *             返回值：int
     *             异    常：无
     *             创建人：CMAPLE
     *             日期：2019-12-10
     *             修改人：
     *             级别：普通用户
     *             日期：
     */
    @SelectProvider(type = AdvancedSQL.class, method = "queryExceptionLogNumByDate")
    int queryExceptionLogNumByDate(String date);

    /**
     * 函数名：复杂查询函数-根据服务名及时间查询该服务的当日使用数量- queryServiceUsageByCondition（）
     * 功能描述： 根据服务名及时间查询该服务的当日使用数量
     * 输入参数：<按照参数定义顺序>
     *
     * @param servicename String类型的服务名
     * @param date        String类型的时间条件
     *                    <p>
     *                    返回值：int
     *                    异    常：无
     *                    创建人：CMAPLE
     *                    日期：2019-12-10
     *                    修改人：
     *                    级别：普通用户
     *                    日期：
     */
    @SelectProvider(type = AdvancedSQL.class, method = "queryServiceUsageByCondition")
    int queryServiceUsageByCondition(String servicename, String date);

    /**
     * 函数名：复杂查询函数-按照日期查询用户登陆数量- queryLoginByDate（）
     * 功能描述： 按照日期查询用户登陆数量
     * 输入参数：<按照参数定义顺序>
     *
     * @param date String类型的时间条件
     *             <p>
     *             返回值：int
     *             异    常：无
     *             创建人：CMAPLE
     *             日期：2019-12-10
     *             修改人：
     *             级别：普通用户
     *             日期：
     */
    @SelectProvider(type = AdvancedSQL.class, method = "queryLoginByDate")
    int queryLoginByDate(String date);

    /**
     * 函数名：复杂查询函数-根据日期、服务id查询服务异常数量- queryServiceExceptionByCondition（）
     * 功能描述： 根据日期、服务id查询服务异常数量
     * 输入参数：<按照参数定义顺序>
     *
     * @param servicename servicename类型的服务名
     * @param date        String类型的时间条件
     *                    <p>
     *                    返回值：int
     *                    异    常：无
     *                    创建人：CMAPLE
     *                    日期：2019-12-10
     *                    修改人：
     *                    级别：普通用户
     *                    日期：
     */
    @SelectProvider(type = AdvancedSQL.class, method = "queryServiceExceptionByCondition")
    int queryServiceExceptionByCondition(String servicename, String date);

    /**
     * 函数名：复杂查询函数-查询挂载服务器数量- queryServersNumber（）
     * 功能描述： 查询挂载服务器数量
     * 输入参数：<按照参数定义顺序>
     *
     * <p>
     * 返回值：int
     * 异    常：无
     * 创建人：CMAPLE
     * 日期：2019-12-10
     * 修改人：
     * 级别：普通用户
     * 日期：
     */
    @SelectProvider(type = AdvancedSQL.class, method = "queryServersNumber")
    int queryServersNumber();

    /**
     * 函数名：复杂查询函数-查询上线服务数量（包含锁定的服务）- queryServicesNumber（）
     * 功能描述： 查询上线服务数量（包含锁定的服务）
     * 输入参数：<按照参数定义顺序>
     *
     * <p>
     * 返回值：int
     * 异    常：无
     * 创建人：CMAPLE
     * 日期：2019-12-10
     * 修改人：
     * 级别：普通用户
     * 日期：
     */
    @SelectProvider(type = AdvancedSQL.class, method = "queryServicesNumber")
    int queryServicesNumber();

    /**
     * 函数名：复杂查询函数-查询所有服务使用的总和数量- queryAllServiceUsageByCondition（）
     * 功能描述： 查询所有服务使用的总和数量
     * 输入参数：<按照参数定义顺序>
     *
     * @param servicesname String[]类型的服务名
     * @param date         String类型的时间
     *                     <p>
     *                     返回值：int
     *                     异    常：无
     *                     创建人：CMAPLE
     *                     日期：2019-12-10
     *                     修改人：
     *                     级别：普通用户
     *                     日期：
     */
    @SelectProvider(type = AdvancedSQL.class, method = "queryAllServiceUsageByCondition")
    int queryAllServiceUsageByCondition(String[] servicesname, String date);

    /**
     * 函数名：复杂查询函数-查询该服务在开启时间之后至今的使用数量- queryServicesNumberByName（）
     * 功能描述： 查询该服务在开启时间之后至今的使用数量
     * 输入参数：<按照参数定义顺序>
     *
     * @param servicename String类型的服务名字
     *                    <p>
     *                    返回值：int
     *                    异    常：无
     *                    创建人：CMAPLE
     *                    日期：2019-12-10
     *                    修改人：
     *                    级别：普通用户
     *                    日期：
     */
    @SelectProvider(type = AdvancedSQL.class, method = "queryServicesNumberByName")
    int queryServicesNumberByName(String servicename);

}
