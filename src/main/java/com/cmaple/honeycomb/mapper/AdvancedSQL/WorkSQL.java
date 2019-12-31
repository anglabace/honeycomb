package com.cmaple.honeycomb.mapper.AdvancedSQL;

import com.cmaple.honeycomb.tools.SqlTool;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

/**
 * 类名：岗位模块sql拼接类 - WorkSQL
 * 功能描述：NULL
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：NULL
 * 创建人：CMAPLE
 * 创建日期：2019-12-17
 */
public class WorkSQL {
    /**
     * 函数名：select函数-根据条件查询岗位信息- selectByCriteria（）
     * 功能描述：根据条件查询岗位信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param list   List类型的条件列表
     * @param params Map类型的字段及数值集合
     * @param page   int类型的页数
     * @param num    int类型的数量
     *               返回值：String
     *               异    常：NULL
     *               创建人：CMAPLE
     *               日期：2019-12-06
     */
    public String selectByCriteria(@Param("list") List<String> list, @Param("params") Map<String, Object> params, @Param("page") int page, @Param("num") int num) {
        String result = new SQL() {
            {
                SELECT("id ,title ,place ,type ,nature ,content ,createuser ,createdate ,need ,application ");
                FROM("CS_Work ");
            }
        }.toString();
        if (0 != list.size()) {
            result = sqlPutAnd(result, list, params);
        }
        //添加排序
        result = SqlTool.getSqlTool().sqlPutDescOrderBy(result, "createdate");
        //添加分页
        result = SqlTool.getSqlTool().sqlPutLimit(result, page, num);
        return result;
    }

    /**
     * 函数名：select函数-根据条件查询岗位总数- selectCountByCriteria（）
     * 功能描述： 根据条件查询岗位总数
     * 输入参数：<按照参数定义顺序>
     *
     * @param list   List类型的条件列表
     * @param params Map类型的字段及数值集合
     *               返回值：String
     *               异    常：NULL
     *               创建人：CMAPLE
     *               日期：2019-12-30
     */
    public String selectCountByCriteria(@Param("list") List<String> list, @Param("params") Map<String, Object> params) {
        String result = new SQL() {
            {
                SELECT("COUNT(*) ");
                FROM("CS_Work ");
            }
        }.toString();
        //判断添加请求条件
        if (0 != list.size()) {
            result = sqlPutAnd(result, list, params);
        }
        return result;
    }

    /**
     * 函数名：select函数-按照时间倒叙查询岗位信息- selectDescOrderByTime（）
     * 功能描述：按照时间倒叙查询岗位信息
     * 输入参数：<按照参数定义顺序>
     * 返回值：String
     * 异    常：NULL
     * 创建人：CMAPLE
     * 日期：2019-12-06
     */
    public String selectDescOrderByTime() {
        String result = new SQL() {
            {
                SELECT("id ,title ,place ,type ,nature ,createdate ,need ");
                FROM("CS_Work ");
            }
        }.toString();
        //添加排序
        result = SqlTool.getSqlTool().sqlPutDescOrderBy(result, "createdate");
        return result;
    }

    /**
     * 函数名：私有函数-岗位查询添加其他条件- sqlPutAnd（）
     * 功能描述： 岗位查询添加其他条件
     * 输入参数：<按照参数定义顺序>
     *
     * @param result String类型的sql语句
     * @param list   List类型的条件列表
     * @param params Map类型的字段及数值集合
     *               返回值：String
     *               异    常：NULL
     *               创建人：CMAPLE
     *               日期：2019-12-06
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
                if ("timeaxisdate".equals(list.get(i))) {
                    result += " DATE_FORMAT( createdate, '%Y-%m-%d') >= '" + ((List) params.get(list.get(i))).get(0) + "' and DATE_FORMAT( createdate , '%Y-%m-%d') <= '" + ((List) params.get(list.get(i))).get(1) + "'";
                }
                //全文搜索
                if ("search".equals(list.get(i))) {
                    result += " ( title LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or place LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or type LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or nature LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or content LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or createuser LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or createdate LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or need LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or application LIKE '%" + params.get(list.get(i)) + "%' ) ";
                }
            }
        }
        return result;
    }

}
