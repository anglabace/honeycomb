package com.cmaple.honeycomb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cmaple.honeycomb.mapper.AdvancedSQL.OperationLogSQL;
import com.cmaple.honeycomb.model.OperationLog;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

/**
 * 类名：mybatis plus 映射日志接口 - OperationLogMapper
 * 功能描述：
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：无
 * 创建人：cmaple
 * 创建日期：2019-09-09
 * 修改人：
 * 级别：NULL
 * 修改日期：
 */
public interface OperationLogMapper extends BaseMapper<OperationLog> {

    /**
     * 函数名：复杂查询函数-根据条件查询操作日志- getOperationLogByParams（）
     * 功能描述： 根据相应条件查询符合条件的数据并返回
     * 输入参数：<按照参数定义顺序>
     *
     * @param list   条件列表
     * @param params 字段及数值集合
     * @param page   分页查询PAGE条件
     * @param num    分页查询NUM条件
     *               返回值：list<User>
     *               异    常：无
     *               创建人：CMAPLE
     *               日期：2019-01-17
     *               修改人：
     *               级别：普通用户
     *               日期：
     */
    @SelectProvider(type = OperationLogSQL.class, method = "getOperationLogByParams")
    List<OperationLog> getOperationLogByParams(@Param("list") List<String> list, @Param("params") Map<String, Object> params, @Param("page") int page, @Param("num") int num);

    /**
     * 函数名：复杂查询函数-根据条件查询操作日志数量- getOperationLogCountByParams（）
     * 功能描述： 根据相应条件查询符合条件的数据并返回
     * 输入参数：<按照参数定义顺序>
     *
     * @param list   条件列表
     * @param params 字段及数值集合
     *               返回值：INT
     *               异    常：无
     *               创建人：CMAPLE
     *               日期：2019-01-17
     *               修改人：
     *               级别：普通用户
     *               日期：
     */
    @SelectProvider(type = OperationLogSQL.class, method = "getOperationLogCountByParams")
    int getOperationLogCountByParams(@Param("list") List<String> list, @Param("params") Map<String, Object> params);

}
