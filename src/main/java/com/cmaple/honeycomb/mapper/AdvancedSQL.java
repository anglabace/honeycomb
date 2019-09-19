package com.cmaple.honeycomb.mapper;

import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

public class AdvancedSQL {

    /**
     * 函数名：复杂查询函数-根据条件查询用户列表的sql- usersBaseDatas（）
     * 功能描述： 根据相应条件拼接查询sql
     * 输入参数：<按照参数定义顺序>
     *
     * @param list       条件列表
     * @param params     字段及数值集合
     * @param lousertype 操作用户级别
     * @param page       分页查询PAGE条件
     * @param num        分页查询NUM条件
     *                   返回值：string
     *                   异    常：无
     *                   创建人：CMAPLE
     *                   日期：2019-01-17
     *                   修改人：
     *                   级别：普通用户
     *                   日期：
     */
    public String usersBaseDatas(List<String> list, Map<String, Object> params, String lousertype, int page, int num) {
        String result = new SQL() {
            {
                SELECT("username,usertype,useraffairs,userbalance,useraddress,telephonenumber,useremail,createtime,petname,commonip,lastplace,permissions");
                FROM("CS_User");
            }
        }.toString();
        //添加WHERE条件
        result = userBDPutWhere(result, lousertype);
        //判断添加请求条件
        if (0 != list.size()) {
            result = sqlUserPutAnd(result, list, params);
        }
        //添加排序
        result = sqlPutOrderBy(result, "username");
        //添加分页
        result = sqlPutLimit(result, page, num);
        return result;
    }

    /**
     * 函数名：复杂查询函数-根据条件查询条件下的用户总数量- getUserCountByParams（）
     * 功能描述： 根据相应条件拼接查询符合条件的用户数量
     * 输入参数：<按照参数定义顺序>
     *
     * @param list       条件列表
     * @param params     字段及数值集合
     * @param lousertype 操作用户级别
     *                   返回值：string
     *                   异    常：无
     *                   创建人：CMAPLE
     *                   日期：2019-01-17
     *                   修改人：
     *                   级别：普通用户
     *                   日期：
     */
    public String getUserCountByParams(List<String> list, Map<String, Object> params, String lousertype) {
        String result = new SQL() {
            {
                SELECT("count(*)");
                FROM("CS_User");
            }
        }.toString();
        //添加WHERE条件
        result = userBDPutWhere(result, lousertype);
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
     *               返回值：string
     *               异    常：无
     *               创建人：CMAPLE
     *               日期：2019-01-17
     *               修改人：
     *               级别：普通用户
     *               日期：
     */
    public String getOperationLogByParams(List<String> list, Map<String, Object> params) {
        String result = new SQL() {
            {
                SELECT("id,serialnumber,date,operator,logstype,operatetype,content");
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
    public String getOperationLogCountByParams(List<String> list, Map<String, Object> params) {
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
     * -----------------------------------------------------------------------------------------------------------------------------
     * 内蒙古西南梦扎赉特旗音德尔镇团结路五四街建设小区6号楼3单元401号
     */

    private String userBDPutWhere(String result, String lousertype) {
        //判断用户类别
        result += "\nWHERE usertype = '" + lousertype + "'";
        return result;
    }

    private String sqlOperationLogPutAnd(String result, List<String> list, Map<String, Object> params) {
        for (int i = 0; i < list.size(); i++) {
            if (params.containsKey(list.get(i))) {
                if ("logstype".equals(list.get(i)) || "operatetype".equals(list.get(i))) {
                    result += " and " + list.get(i) + " = " + params.get(list.get(i));
                } else if ("timeaxisdate".equals(list.get(i))) {
                    result += " and DATE_FORMAT( date, '%Y-%m-%d') >= '" + ((List) params.get(list.get(i))).get(0) + "' and DATE_FORMAT( date , '%Y-%m-%d') <= '" + ((List) params.get(list.get(i))).get(1) + "'";
                } else {
                    result += " and content LIKE '%" + params.get(list.get(i)) + "%'";
                }
            }
        }
        return result;
    }

    private String sqlUserPutAnd(String result, List<String> list, Map<String, Object> params) {
        for (int i = 0; i < list.size(); i++) {
            if ("username".equals(list.get(i)) || "name".equals(list.get(i)) || "petname".equals(list.get(i))) {
                result += " and " + list.get(i) + " LIKE '%" + params.get(list.get(i)) + "%'";
            } else {
                result += " and " + list.get(i) + " = " + params.get(list.get(i));
            }
        }
        return result;
    }

    private String sqlPutOrderBy(String result, String ordderby) {
        result += "\norder by " + ordderby;
        return result;
    }

    private String sqlPutLimit(String result, int page, int num) {
        result += "\nlimit " + page + "," + num;
        return result;
    }

}
