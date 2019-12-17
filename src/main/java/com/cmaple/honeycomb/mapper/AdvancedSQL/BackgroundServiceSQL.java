package com.cmaple.honeycomb.mapper.AdvancedSQL;

import com.cmaple.honeycomb.tools.SqlTool;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

/**
 * 类名：后台服务信息复杂sql拼接类 - BackgroundServiceSQL
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
public class BackgroundServiceSQL {
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
                FROM("CS_BackgroundService WHERE ");
            }
        }.toString();
        //判断添加请求条件
        if (0 != list.size()) {
            result = sqlBackgroundServicePutAnd(result, list, params);
        }
        //添加排序
        result = SqlTool.getSqlTool().sqlPutOrderBy(result, "id");
        //添加分页
        result = SqlTool.getSqlTool().sqlPutLimit(result, page, num);
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
                //添加AND
                if (i > 0) {
                    result += " AND ";
                }
                //服务状态
                if ("servicestate".equals(list.get(i))) {
                    result += " servicestate = '" + params.get(list.get(i)) + "'";
                }
                //语言类型
                if ("proglanguage".equals(list.get(i))) {
                    result += " proglanguage = '" + params.get(list.get(i)) + "'";
                }
                //全文搜索
                if ("search".equals(list.get(i))) {
                    result += " ( serviceid LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or name LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or synopsis LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or path LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or route LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or proglanguage LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or author LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or upusername LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or serverid LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or annexepath LIKE '%" + params.get(list.get(i)) + "%' ) ";
                }
            }
        }
        return result;
    }
}
