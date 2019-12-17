package com.cmaple.honeycomb.mapper.AdvancedSQL;

import com.cmaple.honeycomb.tools.SqlTool;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

/**
 * 类名：操作日志服务信息复杂sql拼接类 - OperationLogSQL
 * 功能描述：
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：无
 * 创建人：CMAPLE
 * 创建日期：2019-12-17
 * 修改人：
 * 级别：NULL
 * 修改日期：
 */
public class OperationLogSQL {
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
                FROM("CS_OperationLog WHERE ");
            }
        }.toString();
        //判断添加请求条件
        if (0 != list.size()) {
            result = sqlOperationLogPutAnd(result, list, params);
        }
        //添加排序
        result = SqlTool.getSqlTool().sqlPutDescOrderBy(result, "date");
        //添加分页
        result = SqlTool.getSqlTool().sqlPutLimit(result, page, num);
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
                FROM("CS_OperationLog WHERE ");
            }
        }.toString();
        //判断添加请求条件
        if (0 != list.size()) {
            result = sqlOperationLogPutAnd(result, list, params);
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
                //添加AND
                if (i > 0) {
                    result += " AND ";
                }
                //添加条件
                if ("operator".equals(list.get(i))) {
                    result += " operator = '" + params.get(list.get(i)) + "'";
                }
                if ("logstype".equals(list.get(i))) {
                    result += " logstype = '" + params.get(list.get(i)) + "'";
                }
                if ("operatetype".equals(list.get(i))) {
                    result += " operatetype = '" + params.get(list.get(i)) + "'";
                }
                if ("timeaxisdate".equals(list.get(i))) {
                    result += " DATE_FORMAT( date, '%Y-%m-%d') >= '" + ((List) params.get(list.get(i))).get(0) + "' and DATE_FORMAT( date , '%Y-%m-%d') <= '" + ((List) params.get(list.get(i))).get(1) + "'";
                }
                if ("search".equals(list.get(i))) {
                    result += " content LIKE '%" + params.get(list.get(i)) + "%'";
                }
            }
        }
        return result;
    }

}
