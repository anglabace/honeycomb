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
     *               返回值：string
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
     *               返回值：string
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
     *               返回值：string
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
     *               返回值：string
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
     * 函数名：复杂查询函数-根据条件查询静态资源列表- queryStaticResourcesByParams（）
     * 功能描述： 根据条件查询静态资源列表
     * 输入参数：<按照参数定义顺序>
     *
     * @param list   条件列表
     * @param params 字段及数值集合
     * @param page   分页查询PAGE条件
     * @param num    分页查询NUM条件
     *               返回值：list<User>
     *               异    常：无
     *               创建人：CMAPLE
     *               日期：2019-09-26
     *               修改人：
     *               级别：普通用户
     *               日期：
     */
    public String queryStaticResourcesByParams(@Param("list") List<String> list, @Param("params") Map<String, Object> params, @Param("page") int page, @Param("num") int num) {
        String result = new SQL() {
            {
                SELECT("id ,pagepath ,imgrealpath ,imgrelativepath ,imgname ,imgrealsize ,imgrelativesize ,size ,creattime ,updatetime ");
                FROM("CS_StaticResources WHERE 1 = 1 ");
            }
        }.toString();
        //判断添加请求条件
        if (0 != list.size()) {
            result = sqlStaticResourcesPutAnd(result, list, params);
        }
        //添加排序
        result = sqlPutOrderBy(result, "id");
        //添加分页
        result = sqlPutLimit(result, page, num);
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
     *               返回值：list<User>
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
                SELECT("id ,serviceid ,name ,synopsis ,version ,path ,route ,size ,size ,proglanguage ,receivetype ,author ,upusername ,createdate ,serverid ,servicestate ,annexepath ");
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
     * 函数名：私有函数-静态资源查询添加其他条件- sqlStaticResourcesPutAnd（）
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
    private String sqlStaticResourcesPutAnd(String result, List<String> list, Map<String, Object> params) {
        for (int i = 0; i < list.size(); i++) {
            if (params.containsKey(list.get(i))) {
                if ("pagepath".equals(list.get(i))) {
                    result += " and pagepath = '" + params.get(list.get(i)) + "'";
                }
                if ("search".equals(list.get(i))) {
                    result += " and imgrealpath LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or imgrelativepath LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or imgname LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or imgrealsize LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or imgrelativesize LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or size LIKE '%" + params.get(list.get(i)) + "%' ";
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
