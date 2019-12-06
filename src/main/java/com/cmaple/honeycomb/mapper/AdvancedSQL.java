package com.cmaple.honeycomb.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

public class AdvancedSQL {

    /**
     * 函数名：复杂查询函数-根据条件查询用户列表的sql- usersBaseDatas（）
     * 功能描述： 根据相应条件拼接查询sql
     * 输入参数：<按照参数定义顺序>
     *
     * @param list   条件列表
     * @param params 字段及数值集合
     * @param page   分页查询PAGE条件
     * @param num    分页查询NUM条件
     *               返回值：String
     *               异    常：无
     *               创建人：CMAPLE
     *               日期：2019-01-17
     *               修改人：
     *               级别：普通用户
     *               日期：
     */
    public String usersBaseDatas(@Param("list") List<String> list, @Param("params") Map<String, Object> params, @Param("page") int page, @Param("num") int num) {
        String result = new SQL() {
            {
                SELECT("id ,usertype ,useraffairs ,userbalance ,idcard ,name ,useraddress ,telephonenumber " +
                        ",useremail ,createtime ,usersign ,petname ,errortry ,commonip ,lastplace ,permissions");
                FROM("CS_User");
            }
        }.toString();
        //添加WHERE条件
        result = userBDPutWhere(result, (String) params.get("usertype"));
        //判断添加请求条件
        if (0 != list.size()) {
            result = sqlUserPutAnd(result, list, params);
        }
        //添加排序
        result = sqlPutOrderBy(result, "id");
        //添加分页
        result = sqlPutLimit(result, page, num);
        return result;
    }

    /**
     * 函数名：复杂查询函数-根据条件查询条件下的用户总数量- getUserCountByParams（）
     * 功能描述： 根据相应条件拼接查询符合条件的用户数量
     * 输入参数：<按照参数定义顺序>
     *
     * @param list   条件列表
     * @param params 字段及数值集合
     *               返回值：String
     *               异    常：无
     *               创建人：CMAPLE
     *               日期：2019-01-17
     *               修改人：
     *               级别：普通用户
     *               日期：
     */
    public String getUserCountByParams(@Param("list") List<String> list, @Param("params") Map<String, Object> params) {
        String result = new SQL() {
            {
                SELECT("count(*)");
                FROM("CS_User");
            }
        }.toString();
        //添加WHERE条件
        result = userBDPutWhere(result, (String) params.get("usertype"));
        //判断添加请求条件
        if (0 != list.size()) {
            result = sqlUserPutAnd(result, list, params);
        }
        return result;
    }


    /**
     * 函数名：复杂查询函数-根据条件查询日志信息- getOperationLogCountByParams（）
     * 功能描述： 根据条件查询日志信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param list   条件列表
     * @param params 字段及数值集合
     * @param page   分页查询PAGE条件
     * @param num    分页查询NUM条件
     *               返回值：String
     *               异    常：无
     *               创建人：CMAPLE
     *               日期：2019-01-17
     *               修改人：
     *               级别：普通用户
     *               日期：
     */
    public String getOperationLogByParams(@Param("list") List<String> list, @Param("params") Map<String, Object> params, @Param("page") int page, @Param("num") int num) {
        String result = new SQL() {
            {
                SELECT("id ,serialnumber ,date ,operator ,logstype ,operatetype ,content");
                FROM("CS_OperationLog WHERE 1 = 1 ");
            }
        }.toString();
        //判断添加请求条件
        if (0 != list.size()) {
            result = sqlOperationLogPutAnd(result, list, params);
        }
        //添加排序
        result = sqlPutDescOrderBy(result, "date");
        //添加分页
        result = sqlPutLimit(result, page, num);
        return result;
    }

    /**
     * 函数名：复杂查询函数-根据条件查询条件下的用户总数量- getOperationLogCountByParams（）
     * 功能描述： 根据相应条件拼接查询符合条件的用户数量
     * 输入参数：<按照参数定义顺序>
     *
     * @param list   条件列表
     * @param params 字段及数值集合
     *               返回值：String
     *               异    常：无
     *               创建人：CMAPLE
     *               日期：2019-01-17
     *               修改人：
     *               级别：普通用户
     *               日期：
     */
    public String getOperationLogCountByParams(@Param("list") List<String> list, @Param("params") Map<String, Object> params) {
        String result = new SQL() {
            {
                SELECT("count(*)");
                FROM("CS_OperationLog WHERE 1 = 1 ");
            }
        }.toString();
        //判断添加请求条件
        if (0 != list.size()) {
            result = sqlOperationLogPutAnd(result, list, params);
        }
        return result;
    }

    /**
     * 函数名：复杂查询函数-根据条件查询后台服务列表- queryBackgroundServicesByParams（）
     * 功能描述： 根据条件查询后台服务列表
     * 输入参数：<按照参数定义顺序>
     *
     * @param list   条件列表
     * @param params 字段及数值集合
     * @param page   分页查询PAGE条件
     * @param num    分页查询NUM条件
     *               返回值：String
     *               异    常：无
     *               创建人：CMAPLE
     *               日期：2019-09-30
     *               修改人：
     *               级别：普通用户
     *               日期：
     */
    public String queryBackgroundServicesByParams(@Param("list") List<String> list, @Param("params") Map<String, Object> params, @Param("page") int page, @Param("num") int num) {
        String result = new SQL() {
            {
                SELECT("id ,serviceid ,name ,synopsis ,version ,path ,route ,size ,proglanguage ,receivetype ,author ,upusername ,createdate ,serverid ,servicestate ,annexepath ");
                FROM("CS_BackgroundService WHERE 1 = 1 ");
            }
        }.toString();
        //判断添加请求条件
        if (0 != list.size()) {
            result = sqlBackgroundServicePutAnd(result, list, params);
        }
        //添加排序
        result = sqlPutOrderBy(result, "id");
        //添加分页
        result = sqlPutLimit(result, page, num);
        return result;
    }

    /**
     * 函数名：复杂查询函数-根据条件查询后台服务列表- queryServiceVersionLogById（）
     * 功能描述： 根据条件查询后台服务列表
     * 输入参数：<按照参数定义顺序>
     *
     * @param serviceid 条件列表
     *                  返回值：String
     *                  异    常：无
     *                  创建人：CMAPLE
     *                  日期：2019-11-11
     *                  修改人：
     *                  日期：
     */
    public String queryServiceVersionLogById(@Param("serviceid") int serviceid) {
        String result = new SQL() {
            {
                SELECT("version ,date ,operator ,content ");
                FROM("CS_ServiceVersionLog WHERE 1 = 1 ");
            }
        }.toString();
        //添加排序
        result = sqlPutDescOrderBy(result, "date");
        return result;
    }

    /**
     * 函数名：复杂查询函数-根据条件查询后台服务列表- queryAnnouncementByParams（）
     * 功能描述： 根据条件查询后台服务列表
     * 输入参数：<按照参数定义顺序>
     *
     * @param list   条件列表
     * @param params 字段及数值集合
     * @param page   分页查询PAGE条件
     * @param num    分页查询NUM条件
     *               返回值：String
     *               异    常：无
     *               创建人：CMAPLE
     *               日期：2019-11-18
     *               修改人：
     *               级别：普通用户
     *               日期：
     */
    public String queryAnnouncementByParams(@Param("list") List<String> list, @Param("params") Map<String, Object> params, @Param("page") int page, @Param("num") int num) {
        String result = new SQL() {
            {
                SELECT("id ,announcementid ,title ,synopsis ,content ,author ,creattime ,readtime ,footer ");
                FROM("CS_Announcement WHERE 1 = 1 ");
            }
        }.toString();
        //判断添加请求条件
        if (0 != list.size()) {
            result = sqlAnnouncementPutAnd(result, list, params);
        }
        //添加排序
        result = sqlPutDescOrderBy(result, "creattime");
        //添加分页
        result = sqlPutLimit(result, page, num);
        return result;
    }

    /**
     * 函数名：复杂查询函数-主页查询，只查看日期倒叙的前五条数据- queryAnnouncementAtHome（）
     * 功能描述： 主页查询，只查看日期倒叙的前五条数据
     * 输入参数：<按照参数定义顺序>
     * <p>
     * 返回值：String
     * 异    常：无
     * 创建人：CMAPLE
     * 日期：2019-11-18
     * 修改人：
     * 级别：普通用户
     * 日期：
     */
    public String queryAnnouncementAtHome() {
        String result = new SQL() {
            {
                SELECT("id ,title ,creattime ");
                FROM("CS_Announcement WHERE 1 = 1 ");
            }
        }.toString();
        //添加排序
        result = sqlPutDescOrderBy(result, "creattime");
        //添加分页
        result = sqlPutLimit(result, 0, 5);
        return result;
    }

    /**
     * 函数名：复杂查询函数-根据条件查询里程碑- queryMilestoneByParams（）
     * 功能描述：根据条件查询里程碑
     * 输入参数：<按照参数定义顺序>
     * <p>
     * 返回值：String
     * 异    常：无
     * 创建人：CMAPLE
     * 日期：2019-11-19
     * 修改人：
     * 级别：普通用户
     * 日期：
     */
    public String queryMilestoneByParams(@Param("list") List<String> list, @Param("params") Map<String, Object> params, @Param("page") int page, @Param("num") int num) {
        String result = new SQL() {
            {
                SELECT("id ,title ,synopsis ,author ,creattime ,readtime ,filename ,filepath ,imgurl ,buttongroup ");
                FROM("CS_Milestone WHERE 1 = 1 ");
            }
        }.toString();
        //判断添加请求条件
        if (0 != list.size()) {
            result = sqlMilestonePutAnd(result, list, params);
        }
        //添加排序
        result = sqlPutDescOrderBy(result, "creattime");
        //添加分页
        result = sqlPutLimit(result, page, num);
        return result;
    }

    /**
     * 函数名：复杂查询函数-主页查询里程碑，只查看日期倒叙的前四条数据- queryMilestoneAtHome（）
     * 功能描述：主页查询里程碑，只查看日期倒叙的前四条数据
     * 输入参数：<按照参数定义顺序>
     * <p>
     * 返回值：String
     * 异    常：无
     * 创建人：CMAPLE
     * 日期：2019-11-19
     * 修改人：
     * 级别：普通用户
     * 日期：
     */
    public String queryMilestoneAtHome() {
        String result = new SQL() {
            {
                SELECT("id ,title ,synopsis ,author ,creattime ,readtime ,filename ,filepath ,imgurl ,buttongroup ");
                FROM("CS_Milestone WHERE 1 = 1 ");
            }
        }.toString();
        //添加排序
        result = sqlPutDescOrderBy(result, "creattime");
        //添加分页
        result = sqlPutLimit(result, 0, 4);
        return result;
    }

    /**
     * 函数名：复杂查询函数-查询所有里程碑，按照倒叙排列- queryMilestoneDescOrderBy（）
     * 功能描述：查询所有里程碑，按照倒叙排列
     * 输入参数：<按照参数定义顺序>
     * <p>
     * 返回值：String
     * 异    常：无
     * 创建人：CMAPLE
     * 日期：2019-11-19
     * 修改人：
     * 级别：普通用户
     * 日期：
     */
    public String queryMilestoneDescOrderBy() {
        String result = new SQL() {
            {
                SELECT("id ,title ,synopsis ,author ,creattime ,readtime ,filename ,filepath ,imgurl ,buttongroup ");
                FROM("CS_Milestone WHERE 1 = 1 ");
            }
        }.toString();
        //添加排序
        result = sqlPutDescOrderBy(result, "creattime");
        return result;
    }


    /**
     * 函数名：复杂查询函数-查询所有调查报告，按照时间倒叙排列- queryReportDescOrderBy（）
     * 功能描述：查询所有调查报告，按照时间倒叙排列
     * 输入参数：<按照参数定义顺序>
     * <p>
     * 返回值：String
     * 异    常：无
     * 创建人：CMAPLE
     * 日期：2019-11-27
     * 修改人：
     * 级别：普通用户
     * 日期：
     */
    public String queryReportDescOrderBy() {
        String result = new SQL() {
            {
                SELECT("id ,title ,reporttype ,creatdate ");
                FROM("CS_Report WHERE 1 = 1 ");
            }
        }.toString();
        //添加排序
        result = sqlPutDescOrderBy(result, "creatdate");
        return result;
    }

    /**
     * 函数名：复杂查询函数-查询所有调查报告，按照时间倒叙排列- queryReportByParams（）
     * 功能描述：查询所有调查报告，按照时间倒叙排列
     * 输入参数：<按照参数定义顺序>
     * <p>
     * 返回值：String
     * 异    常：无
     * 创建人：CMAPLE
     * 日期：2019-11-27
     * 修改人：
     * 级别：普通用户
     * 日期：
     */
    public String queryReportByParams(@Param("list") List<String> list, @Param("params") Map<String, Object> params, @Param("page") int page, @Param("num") int num) {
        String result = new SQL() {
            {
                SELECT("id ,title ,reportid ,reporttype ,author ,creatdate ,filename ,filepath ");
                FROM("CS_Report WHERE 1 = 1 ");
            }
        }.toString();
        if (0 != list.size()) {
            result = sqlReportPutAnd(result, list, params);
        }
        //添加排序
        result = sqlPutDescOrderBy(result, "creatdate");
        //添加分页
        result = sqlPutLimit(result, page, num);
        return result;
    }

    /**
     * 函数名：复杂查询函数-查询所有调查报告，按照时间倒叙排列- queryWorkByParams（）
     * 功能描述：查询所有调查报告，按照时间倒叙排列
     * 输入参数：<按照参数定义顺序>
     * <p>
     * 返回值：String
     * 异    常：无
     * 创建人：CMAPLE
     * 日期：2019-12-06
     * 修改人：
     * 级别：普通用户
     * 日期：
     */
    public String queryWorkByParams(@Param("list") List<String> list, @Param("params") Map<String, Object> params, @Param("page") int page, @Param("num") int num) {
        String result = new SQL() {
            {
                SELECT("id ,title ,place ,type ,nature ,content ,createuser ,creatdate ,need ,application ");
                FROM("CS_Work WHERE 1 = 1 ");
            }
        }.toString();
        if (0 != list.size()) {
            result = sqlworkPutAnd(result, list, params);
        }
        //添加排序
        result = sqlPutDescOrderBy(result, "creatdate");
        //添加分页
        result = sqlPutLimit(result, page, num);
        return result;
    }

    /**
     * 函数名：复杂查询函数-查询所有岗位信息，按照时间倒叙排列- queryWorkDescOrderBy（）
     * 功能描述：查询所有岗位信息，按照时间倒叙排列
     * 输入参数：<按照参数定义顺序>
     * <p>
     * 返回值：String
     * 异    常：无
     * 创建人：CMAPLE
     * 日期：2019-12-06
     * 修改人：
     * 级别：普通用户
     * 日期：
     */
    public String queryWorkDescOrderBy() {
        String result = new SQL() {
            {
                SELECT("id ,title ,place ,type ,creatdate ");
                FROM("CS_Work WHERE 1 = 1 ");
            }
        }.toString();
        //添加排序
        result = sqlPutDescOrderBy(result, "creatdate");
        return result;
    }

    /**
     * ----------------------------------------------------------------------------------------------------------------------------- 私有函数 -----------------------------------------------------------------------------------------------------------------------------
     */

    /**
     * 函数名：私有函数-用户查询添加where条件- userBDPutWhere（）
     * 功能描述： 用户查询添加where条件
     * 输入参数：<按照参数定义顺序>
     *
     * @param result     条件列表
     * @param lousertype 字段及数值集合
     *                   返回值：string
     *                   异    常：无
     *                   创建人：CMAPLE
     *                   日期：2019-01-17
     *                   修改人：
     *                   级别：普通用户
     *                   日期：
     */
    private String userBDPutWhere(String result, String lousertype) {
        //判断用户类别
        result += " WHERE usertype = '" + lousertype + "'";
        return result;
    }

    /**
     * 函数名：私有函数-用户查询添加其他条件- sqlUserPutAnd（）
     * 功能描述： 用户查询添加其他条件
     * 输入参数：<按照参数定义顺序>
     *
     * @param result 条件列表
     * @param list   字段及数值集合
     * @param params 字段及数值集合
     *               返回值：string
     *               异    常：无
     *               创建人：CMAPLE
     *               日期：2019-01-17
     *               修改人：
     *               级别：普通用户
     *               日期：
     */
    private String sqlUserPutAnd(String result, List<String> list, Map<String, Object> params) {
        for (int i = 0; i < list.size(); i++) {
            if ("useraffairs".equals(list.get(i))) {
                result += " and useraffairs = '" + params.get(list.get(i)) + "'";
            }
            if ("timeaxisdate".equals(list.get(i))) {
                result += " and DATE_FORMAT( createtime, '%Y-%m-%d') >= '" + ((List) params.get(list.get(i))).get(0) + "' and DATE_FORMAT( createtime , '%Y-%m-%d') <= '" + ((List) params.get(list.get(i))).get(1) + "'";
            }
            if ("content".equals(list.get(i))) {
                result += " and idcard LIKE '%" + params.get(list.get(i)) + "%' " +
                        "or name LIKE '%" + params.get(list.get(i)) + "%' " +
                        "or useraddress LIKE '%" + params.get(list.get(i)) + "%' " +
                        "or telephonenumber LIKE '%" + params.get(list.get(i)) + "%' " +
                        "or useremail LIKE '%" + params.get(list.get(i)) + "%' " +
                        "or petname LIKE '%" + params.get(list.get(i)) + "%' " +
                        "or commonip LIKE '%" + params.get(list.get(i)) + "%' " +
                        "or lastplace LIKE '%" + params.get(list.get(i)) + "%'";
            }
        }
        return result;
    }

    /**
     * 函数名：私有函数-日志查询添加其他条件- sqlOperationLogPutAnd（）
     * 功能描述： 日志查询添加其他条件
     * 输入参数：<按照参数定义顺序>
     *
     * @param result 条件列表
     * @param list   字段及数值集合
     * @param params 字段及数值集合
     *               返回值：string
     *               异    常：无
     *               创建人：CMAPLE
     *               日期：2019-01-17
     *               修改人：
     *               级别：普通用户
     *               日期：
     */
    private String sqlOperationLogPutAnd(String result, List<String> list, Map<String, Object> params) {
        for (int i = 0; i < list.size(); i++) {
            if (params.containsKey(list.get(i))) {
                if ("operator".equals(list.get(i))) {
                    result += " and operator = '" + params.get(list.get(i)) + "'";
                }
                if ("logstype".equals(list.get(i))) {
                    result += " and logstype = '" + params.get(list.get(i)) + "'";
                }
                if ("operatetype".equals(list.get(i))) {
                    result += " and operatetype = '" + params.get(list.get(i)) + "'";
                }
                if ("timeaxisdate".equals(list.get(i))) {
                    result += " and DATE_FORMAT( date, '%Y-%m-%d') >= '" + ((List) params.get(list.get(i))).get(0) + "' and DATE_FORMAT( date , '%Y-%m-%d') <= '" + ((List) params.get(list.get(i))).get(1) + "'";
                }
                if ("search".equals(list.get(i))) {
                    result += " and content LIKE '%" + params.get(list.get(i)) + "%'";
                }
            }
        }
        return result;
    }

    /**
     * 函数名：私有函数-后台服务查询添加其他条件- sqlBackgroundServicePutAnd（）
     * 功能描述： 后台服务查询添加其他条件
     * 输入参数：<按照参数定义顺序>
     *
     * @param result 条件列表
     * @param list   字段及数值集合
     * @param params 字段及数值集合
     *               返回值：string
     *               异    常：无
     *               创建人：CMAPLE
     *               日期：2019-09-30
     *               修改人：
     *               级别：普通用户
     *               日期：
     */
    private String sqlBackgroundServicePutAnd(String result, List<String> list, Map<String, Object> params) {
        for (int i = 0; i < list.size(); i++) {
            if (params.containsKey(list.get(i))) {
                //服务状态
                if ("servicestate".equals(list.get(i))) {
                    result += " and servicestate = '" + params.get(list.get(i)) + "'";
                }
                //语言类型
                if ("proglanguage".equals(list.get(i))) {
                    result += " and proglanguage = '" + params.get(list.get(i)) + "'";
                }
                //全文搜索
                if ("search".equals(list.get(i))) {
                    result += " and serviceid LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or name LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or synopsis LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or path LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or route LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or proglanguage LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or author LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or upusername LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or serverid LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or annexepath LIKE '%" + params.get(list.get(i)) + "%' ";
                }
            }
        }
        return result;
    }


    /**
     * 函数名：私有函数-后台服务查询添加其他条件- sqlAnnouncementPutAnd（）
     * 功能描述： 后台服务查询添加其他条件
     * 输入参数：<按照参数定义顺序>
     *
     * @param result 条件列表
     * @param list   字段及数值集合
     * @param params 字段及数值集合
     *               返回值：string
     *               异    常：无
     *               创建人：CMAPLE
     *               日期：2019-11-18
     *               修改人：
     *               级别：普通用户
     *               日期：
     */
    private String sqlAnnouncementPutAnd(String result, List<String> list, Map<String, Object> params) {
        for (int i = 0; i < list.size(); i++) {
            if (params.containsKey(list.get(i))) {
                //服务状态
                if ("timeaxisdate".equals(list.get(i))) {
                    result += " and DATE_FORMAT( creattime, '%Y-%m-%d') >= '" + ((List) params.get(list.get(i))).get(0) + "' and DATE_FORMAT( creattime , '%Y-%m-%d') <= '" + ((List) params.get(list.get(i))).get(1) + "'";
                }
                //全文搜索
                if ("search".equals(list.get(i))) {
                    result += " and announcementid LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or title LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or synopsis LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or content LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or author LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or footer LIKE '%" + params.get(list.get(i)) + "%' ";
                }
            }
        }
        return result;
    }

    /**
     * 函数名：私有函数-里程碑查询添加其他条件- sqlMilestonePutAnd（）
     * 功能描述： 里程碑查询添加其他条件
     * 输入参数：<按照参数定义顺序>
     *
     * @param result 条件列表
     * @param list   字段及数值集合
     * @param params 字段及数值集合
     *               返回值：string
     *               异    常：无
     *               创建人：CMAPLE
     *               日期：2019-11-19
     *               修改人：
     *               级别：普通用户
     *               日期：
     */
    private String sqlMilestonePutAnd(String result, List<String> list, Map<String, Object> params) {
        for (int i = 0; i < list.size(); i++) {
            if (params.containsKey(list.get(i))) {
                //服务状态
                if ("timeaxisdate".equals(list.get(i))) {
                    result += " and DATE_FORMAT( creattime, '%Y-%m-%d') >= '" + ((List) params.get(list.get(i))).get(0) + "' and DATE_FORMAT( creattime , '%Y-%m-%d') <= '" + ((List) params.get(list.get(i))).get(1) + "'";
                }
                //全文搜索
                if ("search".equals(list.get(i))) {
                    result += " and title LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or synopsis LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or author LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or filename LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or filepath LIKE '%" + params.get(list.get(i)) + "%' ";
                }
            }
        }
        return result;
    }


    //sqlMilestonePutAnd

    /**
     * 函数名：私有函数-调查报告查询添加其他条件- sqlReportPutAnd（）
     * 功能描述： 调查报告查询添加其他条件
     * 输入参数：<按照参数定义顺序>
     *
     * @param result 条件列表
     * @param list   字段及数值集合
     * @param params 字段及数值集合
     *               返回值：string
     *               异    常：无
     *               创建人：CMAPLE
     *               日期：2019-11-19
     *               修改人：
     *               级别：普通用户
     *               日期：
     */
    private String sqlReportPutAnd(String result, List<String> list, Map<String, Object> params) {
        for (int i = 0; i < list.size(); i++) {
            if (params.containsKey(list.get(i))) {
                //服务状态
                if ("timeaxisdate".equals(list.get(i))) {
                    result += " and DATE_FORMAT( creatdate, '%Y-%m-%d') >= '" + ((List) params.get(list.get(i))).get(0) + "' and DATE_FORMAT( creatdate , '%Y-%m-%d') <= '" + ((List) params.get(list.get(i))).get(1) + "'";
                }
                //全文搜索
                if ("search".equals(list.get(i))) {
                    result += " and reportid LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or title LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or reporttype LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or author LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or filename LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or filepath LIKE '%" + params.get(list.get(i)) + "%' ";
                }
            }
        }
        return result;
    }

    /**
     * 函数名：私有函数-岗位查询添加其他条件- sqlworkPutAnd（）
     * 功能描述： 岗位查询添加其他条件
     * 输入参数：<按照参数定义顺序>
     *
     * @param result 条件列表
     * @param list   字段及数值集合
     * @param params 字段及数值集合
     *               返回值：string
     *               异    常：无
     *               创建人：CMAPLE
     *               日期：2019-12-06
     *               修改人：
     *               级别：普通用户
     *               日期：
     */
    private String sqlworkPutAnd(String result, List<String> list, Map<String, Object> params) {
        for (int i = 0; i < list.size(); i++) {
            if (params.containsKey(list.get(i))) {
                //服务状态
                if ("timeaxisdate".equals(list.get(i))) {
                    result += " and DATE_FORMAT( creatdate, '%Y-%m-%d') >= '" + ((List) params.get(list.get(i))).get(0) + "' and DATE_FORMAT( creatdate , '%Y-%m-%d') <= '" + ((List) params.get(list.get(i))).get(1) + "'";
                }
                //全文搜索
                if ("search".equals(list.get(i))) {
                    result += " and title LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or place LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or type LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or nature LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or content LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or createuser LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or creatdate LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or need LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or application LIKE '%" + params.get(list.get(i)) + "%' ";
                }
            }
        }
        return result;
    }


    /**
     * 函数名：私有函数-按照相应字段进行从小到大排序- sqlPutOrderBy（）
     * 功能描述： 按照相应字段进行从小到大排序
     * 输入参数：<按照参数定义顺序>
     *
     * @param result   条件列表
     * @param ordderby 字段及数值集合
     *                 返回值：string
     *                 异    常：无
     *                 创建人：CMAPLE
     *                 日期：2019-01-17
     *                 修改人：
     *                 级别：普通用户
     *                 日期：
     */
    private String sqlPutOrderBy(String result, String ordderby) {
        result += " order by " + ordderby;
        return result;
    }

    /**
     * 函数名：私有函数-按照相应字段进行从大到小排序- sqlPutDescOrderBy（）
     * 功能描述： 按照相应字段进行从大到小排序
     * 输入参数：<按照参数定义顺序>
     *
     * @param result   条件列表
     * @param ordderby 字段及数值集合
     *                 返回值：string
     *                 异    常：无
     *                 创建人：CMAPLE
     *                 日期：2019-01-17
     *                 修改人：
     *                 级别：普通用户
     *                 日期：
     */
    private String sqlPutDescOrderBy(String result, String ordderby) {
        result += " order by " + ordderby + " desc ";
        return result;
    }

    /**
     * 函数名：私有函数-分页函数- sqlPutLimit（）
     * 功能描述： 分页函数
     * 输入参数：<按照参数定义顺序>
     *
     * @param result 条件列表
     * @param page   字段及数值集合
     * @param num    字段及数值集合
     *               返回值：string
     *               异    常：无
     *               创建人：CMAPLE
     *               日期：2019-01-17
     *               修改人：
     *               级别：普通用户
     *               日期：
     */
    private String sqlPutLimit(String result, int page, int num) {
        result += " limit " + page + "," + num;
        return result;
    }

}
