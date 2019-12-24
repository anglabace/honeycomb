package com.cmaple.honeycomb.mapper.AdvancedSQL;

import com.cmaple.honeycomb.tools.SqlTool;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

/**
 * 类名：里程碑模块复杂sql拼接类 - BackgroundServiceSQL
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
public class MilestoneSQL {
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
                FROM("CS_Milestone ");
            }
        }.toString();
        //判断添加请求条件
        if (0 != list.size()) {
            result = sqlMilestonePutAnd(result, list, params);
        }
        //添加排序
        result = SqlTool.getSqlTool().sqlPutDescOrderBy(result, "creattime");
        //添加分页
        result = SqlTool.getSqlTool().sqlPutLimit(result, page, num);
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
                SELECT("id ,title ,imgurl ");
                FROM("CS_Milestone ");
            }
        }.toString();
        //添加排序
        result = SqlTool.getSqlTool().sqlPutDescOrderBy(result, "creattime");
        //添加分页
        result = SqlTool.getSqlTool().sqlPutLimit(result, 0, 4);
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
                FROM("CS_Milestone ");
            }
        }.toString();
        //添加排序
        result = SqlTool.getSqlTool().sqlPutDescOrderBy(result, "creattime");
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
                //添加AND
                if (i > 0) {
                    result += " AND ";
                } else {
                    result += " WHERE ";
                }
                //服务状态
                if ("timeaxisdate".equals(list.get(i))) {
                    result += " DATE_FORMAT( creattime, '%Y-%m-%d') >= '" + ((List) params.get(list.get(i))).get(0) + "' and DATE_FORMAT( creattime , '%Y-%m-%d') <= '" + ((List) params.get(list.get(i))).get(1) + "'";
                }
                //全文搜索
                if ("search".equals(list.get(i))) {
                    result += " ( title LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or synopsis LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or author LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or filename LIKE '%" + params.get(list.get(i)) + "%' " +
                            "or filepath LIKE '%" + params.get(list.get(i)) + "%' ) ";
                }
            }
        }
        return result;
    }
}
