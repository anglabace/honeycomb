package com.cmaple.honeycomb.mapper.AdvancedSQL;

import com.cmaple.honeycomb.tools.SqlTool;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
 * 类名：服务版本日志模块复杂sql拼接类 - ServiceVersionLogSQL
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
public class ServiceVersionLogSQL {

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
                FROM("CS_ServiceVersionLog ");
            }
        }.toString();
        //添加排序
        result = SqlTool.getSqlTool().sqlPutDescOrderBy(result, "date");
        return result;
    }
}
