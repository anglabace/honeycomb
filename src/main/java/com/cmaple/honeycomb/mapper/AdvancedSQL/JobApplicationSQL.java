package com.cmaple.honeycomb.mapper.AdvancedSQL;

import com.cmaple.honeycomb.tools.SqlTool;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

/**
 * 类名：岗位申请模块sql拼接类 - JobApplicationSQL
 * 功能描述：NULL
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：NULL
 * 创建人：CMAPLE
 * 创建日期：2019-12-30
 */
public class JobApplicationSQL {

    /**
     * 函数名：select函数-根据条件查询岗位申请列表- selectByCriteria（）
     * 功能描述： 根据条件查询岗位申请列表
     * 输入参数：<按照参数定义顺序>
     *
     * @param list   List类型的条件列表
     * @param params Map类型的字段及数值集合
     * @param page   int类型的页数
     * @param num    int类型的数量
     *               返回值：String
     *               异    常：NULL
     *               创建人：CMAPLE
     *               日期：2019-12-30
     */
    public String selectByCriteria(@Param("list") List<String> list, @Param("params") Map<String, Object> params, @Param("page") int page, @Param("num") int num) {
        String result = new SQL() {
            {
                SELECT("id ,workId ,name ,phonenNmber ,emaile ,address ,company ,githubUrl ,personelUrl ,information ,createDate ,idpassed ,applicationUser ,applicationDate ");
                FROM("CS_JobApplication ");
            }
        }.toString();
        //判断添加请求条件
        if (0 != list.size()) {
            result = sqlPutAnd(result, list, params);
        }
        //添加排序
        result = SqlTool.getSqlTool().sqlPutDescOrderBy(result, "createDate");
        //添加分页
        result = SqlTool.getSqlTool().sqlPutLimit(result, page, num);
        return result;
    }

    /**
     * 函数名：select函数-根据条件查询岗位申请总数- selectCountByCriteria（）
     * 功能描述： 根据条件查询岗位申请总数
     * 输入参数：<按照参数定义顺序>
     *
     * @param list   List类型的条件列表
     * @param params Map类型的字段及数值集合
     *               返回值：String
     *               异    常：无
     *               创建人：CMAPLE
     *               日期：2019-12-30
     */
    public String selectCountByCriteria(@Param("list") List<String> list, @Param("params") Map<String, Object> params) {
        String result = new SQL() {
            {
                SELECT(" COUNT(*) ");
                FROM("CS_JobApplication ");
            }
        }.toString();
        //判断添加请求条件
        if (0 != list.size()) {
            result = sqlPutAnd(result, list, params);
        }
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
     *               返回值：string
     *               异    常：无
     *               创建人：CMAPLE
     *               日期：2019-12-30
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
                    result += " DATE_FORMAT( createDate, '%Y-%m-%d') >= '" + ((List) params.get(list.get(i))).get(0) + "' and DATE_FORMAT( createDate , '%Y-%m-%d') <= '" + ((List) params.get(list.get(i))).get(1) + "'";
                }
                //全文搜索
                if ("search".equals(list.get(i))) {
                    result += " ( name LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or phonenNmber LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or emaile LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or address LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or company LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or githubUrl LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or personelUrl LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or information LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or applicationUser LIKE '%" + params.get(list.get(i)) + "%' ) ";
                }
            }
        }
        return result;
    }
}
