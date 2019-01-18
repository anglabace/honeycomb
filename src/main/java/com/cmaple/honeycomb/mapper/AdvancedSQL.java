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
                SELECT("username,usertype,useraffairs,userbalance,useraddress,telephonenumber,useremail,createtime,closetime,petname");
                FROM("BS_USER");
            }
        }.toString();
        //添加WHERE条件
        result = userBDPutWhere(result, lousertype);
        //判断添加请求条件
        if (0 != list.size()) {
            result = sqlPutAnd(result, list, params);
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
                FROM("BS_USER");
            }
        }.toString();
        //添加WHERE条件
        result = userBDPutWhere(result, lousertype);
        //判断添加请求条件
        if (0 != list.size()) {
            result = sqlPutAnd(result, list, params);
        }
        return result;
    }


    private String userBDPutWhere(String result, String lousertype) {
        //判断用户类别
        if ("999".equals(lousertype)) {
            result += "\nWHERE usertype <> '999'"; // 超级管理员查看
        } else if ("103".equals(lousertype)) {
            result += "\nWHERE usertype <> '999' AND usertype <> '103'"; // 管理员查看
        } else {
            result += "\nWHERE usertype = '000'"; // 其他级别屏蔽查询结果
        }
        return result;
    }

    private String sqlPutAnd(String result, List<String> list, Map<String, Object> params) {
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
