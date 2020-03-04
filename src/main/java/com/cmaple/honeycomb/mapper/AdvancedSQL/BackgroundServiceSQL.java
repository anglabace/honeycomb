package com.cmaple.honeycomb.mapper.AdvancedSQL;

import com.cmaple.honeycomb.tools.SqlTool;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

/**
 * 类名：后台服务模块sql拼接类 - BackgroundServiceSQL
 * 功能描述：NULL
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：NULL
 * 创建人：CMAPLE
 * 创建日期：2019-12-17
 */
public class BackgroundServiceSQL {
    /**
     * 函数名：select函数-根据条件查询后台服务列表- selectByCriteria（）
     * 功能描述： 根据条件查询后台服务列表
     * 输入参数：<按照参数定义顺序>
     *
     * @param list   List类型的条件列表
     * @param params Map类型的字段及数值集合
     * @param page   int类型的页数
     * @param num    int类型的数量
     *               返回值：String
     *               异    常：NULL
     *               创建人：CMAPLE
     *               日期：2019-09-30
     */
    public String selectByCriteria(@Param("list") List<String> list, @Param("params") Map<String, Object> params, @Param("page") int page, @Param("num") int num) {
        String result = new SQL() {
            {
                SELECT("id ,serviceid ,name ,synopsis ,version ,path ,route ,size ,proglanguage ,receivetype ,author ,upusername ,createtime ,serverid ,servicestate ,annexepath ");
                FROM("CS_BackgroundService ");
            }
        }.toString();
        //判断添加请求条件
        if (0 != list.size()) {
            result = sqlPutAnd(result, list, params);
        }
        //添加排序
        result = SqlTool.getSqlTool().sqlPutOrderBy(result, "id");
        //添加分页
        result = SqlTool.getSqlTool().sqlPutLimit(result, page, num);
        return result;
    }

    /**
     * 函数名：select函数-根据条件查询后台服务总数- selectCountByCriteria（）
     * 功能描述： 根据条件查询后台服务总数
     * 输入参数：<按照参数定义顺序>
     *
     * @param list   List类型的条件列表
     * @param params Map类型的字段及数值集合
     *               返回值：String
     *               异    常：NULL
     *               创建人：CMAPLE
     *               日期：2019-12-31
     */
    public String selectCountByCriteria(@Param("list") List<String> list, @Param("params") Map<String, Object> params) {
        String result = new SQL() {
            {
                SELECT("COUNT(*) ");
                FROM("CS_BackgroundService ");
            }
        }.toString();
        //判断添加请求条件
        if (0 != list.size()) {
            result = sqlPutAnd(result, list, params);
        }
        return result;
    }

    /**
     * 函数名：select函数-按照时间倒叙分页查询后台服务详情- selectDescOrderByTime（）
     * 功能描述： 按照时间倒叙分页查询后台服务详情
     * 输入参数：<按照参数定义顺序>
     * 返回值：String
     * 异    常：NULL
     * 创建人：CMAPLE
     * 日期：2019-12-24
     */
    public String selectDescOrderByTime() {
        String result = new SQL() {
            {
                SELECT("id ,name ,synopsis ,createtime ");
                FROM("CS_BackgroundService ");
            }
        }.toString();
        //添加排序
        result = SqlTool.getSqlTool().sqlPutOrderBy(result, "createtime");
        return result;
    }

    /**
     * 函数名：私有函数-查询添加其他条件- sqlPutAnd（）
     * 功能描述： 查询添加其他条件
     * 输入参数：<按照参数定义顺序>
     *
     * @param result String类型的sql语句
     * @param list   List类型的条件列表
     * @param params Map类型的字段及数值集合
     *               返回值：String
     *               异    常：NULL
     *               创建人：CMAPLE
     *               日期：2019-09-30
     */
    private String sqlPutAnd(String result, List<String> list, Map<String, Object> params) {
        for (int i = 0; i < list.size(); i++) {
            if (params.containsKey(list.get(i))) {
                //添加AND
                if (i > 0) {
                    result += " AND ";
                } else {
                    result += " WHERE ";
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
