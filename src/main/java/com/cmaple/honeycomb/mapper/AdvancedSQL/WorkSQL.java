package com.cmaple.honeycomb.mapper.AdvancedSQL;

import com.cmaple.honeycomb.tools.SqlTool;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

/**
 * 类名：岗位模块复杂sql拼接类 - WorkSQL
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
public class WorkSQL {
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
                FROM("CS_Work WHERE ");
            }
        }.toString();
        if (0 != list.size()) {
            result = sqlworkPutAnd(result, list, params);
        }
        //添加排序
        result = SqlTool.getSqlTool().sqlPutDescOrderBy(result, "creatdate");
        //添加分页
        result = SqlTool.getSqlTool().sqlPutLimit(result, page, num);
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
                FROM("CS_Work ");
            }
        }.toString();
        //添加排序
        result = SqlTool.getSqlTool().sqlPutDescOrderBy(result, "creatdate");
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
                //添加AND
                if (i > 0) {
                    result += " AND ";
                }
                //服务状态
                if ("timeaxisdate".equals(list.get(i))) {
                    result += " DATE_FORMAT( creatdate, '%Y-%m-%d') >= '" + ((List) params.get(list.get(i))).get(0) + "' and DATE_FORMAT( creatdate , '%Y-%m-%d') <= '" + ((List) params.get(list.get(i))).get(1) + "'";
                }
                //全文搜索
                if ("search".equals(list.get(i))) {
                    result += " ( title LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or place LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or type LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or nature LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or content LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or createuser LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or creatdate LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or need LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or application LIKE '%" + params.get(list.get(i)) + "%' ) ";
                }
            }
        }
        return result;
    }

}
