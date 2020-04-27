package com.cmaple.honeycomb.mapper.AdvancedSQL;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
 * 类名：统计信息模块sql拼接类 - StatisticsSQL
 * 功能描述：NULL
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：NULL
 * 创建人：CMAPLE
 * 创建日期：2019-12-17
 */
public class StatisticsSQL {

    /**
     * 函数名：select函数-查询当日官网访问数量- selectCountHomePage（）
     * 功能描述： 查询当日官网访问数量
     * 输入参数：<按照参数定义顺序>
     *
     * @param date string类型的日期条件
     *             返回值：String
     *             异    常：NULL
     *             创建人：CMAPLE
     *             日期：2020-04-22
     */
    public String selectCountHomePage(@Param("date") String date) {
        String result = new SQL() {
            {
                SELECT("count(*) ");
                FROM("CS_OperationLog WHERE DATE_FORMAT( createtime, '%Y-%m-%d') = '" + date + "' AND content LIKE '%/Milestone/selectAtHomePage%'");
            }
        }.toString();
        return result;
    }


    /**
     * 函数名：复杂查询函数-查询当日平台访问数量- queryPlatformNumByDate（）
     * 功能描述： 查询当日平台访问数量
     * 输入参数：<按照参数定义顺序>
     *
     * @param date String类型的时间条件
     *             返回值：int
     *             异    常：无
     *             创建人：CMAPLE
     *             日期：2020-04-24
     */
    public String selectCountPlatform(@Param("date") String date) {
        String result = new SQL() {
            {
                SELECT("count(*) ");
                FROM("CS_OperationLog WHERE DATE_FORMAT( createtime, '%Y-%m-%d') = '" + date + "' AND content LIKE '%/user/login%' AND logstype = 'normal'");
            }
        }.toString();
        return result;
    }

    /**
     * 函数名：select函数-根据时间查询当前用户注册数量- selectUserCountBefor（）
     * 功能描述： 根据时间查询当前用户注册数量
     * 输入参数：<按照参数定义顺序>
     *
     * @param date List类型的条件列表
     *             返回值：String
     *             异    常：NULL
     *             创建人：CMAPLE
     *             日期：2020-04-24
     */
    public String selectUserCountBefor(@Param("date") String date) {
        String result = new SQL() {
            {
                SELECT("COUNT(*) ");
                FROM("CS_OperationLog WHERE DATE_FORMAT( createtime, '%Y-%m-%d') = '" + date + "' AND content LIKE '%/user/insert%' AND logstype = 'normal'");
            }
        }.toString();
        return result;
    }

}
