package com.cmaple.honeycomb.mapper.AdvancedSQL;

import com.cmaple.honeycomb.tools.SqlTool;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

/**
 * 类名：岗位申请模块复杂sql拼接类 - AnnouncementSQL
 * 功能描述：
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：无
 * 创建人：CMAPLE
 * 创建日期：2019-12-30
 * 修改人：
 * 级别：NULL
 * 修改日期：
 */
public class JobApplicationSQL {

    /**
     * 函数名：复杂查询函数-根据条件查询后公告列表- queryJobApplicationByParams（）
     * 功能描述： 根据条件查询后公告列表
     * 输入参数：<按照参数定义顺序>
     *
     * @param list   条件列表
     * @param params 字段及数值集合
     * @param page   分页查询PAGE条件
     * @param num    分页查询NUM条件
     *               返回值：String
     *               异    常：无
     *               创建人：CMAPLE
     *               日期：2019-12-30
     *               修改人：
     *               级别：普通用户
     *               日期：
     */
    public String queryJobApplicationByParams(@Param("list") List<String> list, @Param("params") Map<String, Object> params, @Param("page") int page, @Param("num") int num) {
        String result = new SQL() {
            {
                SELECT("id ,workId ,name ,phonenNmber ,emaile ,address ,company ,githubUrl ,personelUrl ,information ,createDate ,idpassed ,applicationUser ,applicationDate ");
                FROM("CS_JobApplication ");
            }
        }.toString();
        //判断添加请求条件
        if (0 != list.size()) {
            result = sqlJobApplicationPutAnd(result, list, params);
        }
        //添加排序
        result = SqlTool.getSqlTool().sqlPutDescOrderBy(result, "createDate");
        //添加分页
        result = SqlTool.getSqlTool().sqlPutLimit(result, page, num);
        return result;
    }

    /**
     * 函数名：复杂查询函数-根据条件查询后公告列表- queryJobApplicationCountByParams（）
     * 功能描述： 根据条件查询后公告列表
     * 输入参数：<按照参数定义顺序>
     *
     * @param list   条件列表
     * @param params 字段及数值集合
     *               返回值：String
     *               异    常：无
     *               创建人：CMAPLE
     *               日期：2019-12-30
     *               修改人：
     *               级别：普通用户
     *               日期：
     */
    public String queryJobApplicationCountByParams(@Param("list") List<String> list, @Param("params") Map<String, Object> params) {
        String result = new SQL() {
            {
                SELECT(" COUNT(*) ");
                FROM("CS_JobApplication ");
            }
        }.toString();
        //判断添加请求条件
        if (0 != list.size()) {
            result = sqlJobApplicationPutAnd(result, list, params);
        }
        return result;
    }

    /**
     * 函数名：私有函数-公告查询添加其他条件- sqlJobApplicationPutAnd（）
     * 功能描述： 后台服务查询添加其他条件
     * 输入参数：<按照参数定义顺序>
     *
     * @param result 条件列表
     * @param list   字段及数值集合
     * @param params 字段及数值集合
     *               返回值：string
     *               异    常：无
     *               创建人：CMAPLE
     *               日期：2019-12-30
     *               修改人：
     *               级别：普通用户
     *               日期：
     */
    private String sqlJobApplicationPutAnd(String result, List<String> list, Map<String, Object> params) {
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
