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
 * 功能描述：NULL
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：NULL
 * 创建人：cmaple
 * 创建日期：2019-09-09
 */
public interface OperationLogMapper extends BaseMapper<OperationLog> {

    /**
     * 函数名：select函数-根据条件查询操作日志- selectByCriteria（）
     * 功能描述： 根据条件查询操作日志
     * 输入参数：<按照参数定义顺序>
     *
     * @param list   List类型的条件列表
     * @param params Map类型的字段及数值集合
     * @param page   int类型的页数
     * @param num    int类型的数量
     *               返回值：list<OperationLog>
     *               异    常：NULL
     *               创建人：CMAPLE
     *               日期：2019-01-17
     */
    @SelectProvider(type = OperationLogSQL.class, method = "selectByCriteria")
    List<OperationLog> selectByCriteria(@Param("list") List<String> list, @Param("params") Map<String, Object> params, @Param("page") int page, @Param("num") int num);

    /**
     * 函数名：select函数-根据条件查询操作日志数量- selectCountByCriteria（）
     * 功能描述： 根据相应条件查询符合条件的数据并返回
     * 输入参数：<按照参数定义顺序>
     *
     * @param list   List类型的条件列表
     * @param params Map类型的字段及数值集合
     *               返回值：int
     *               异    常：NULL
     *               创建人：CMAPLE
     *               日期：2019-01-17
     */
    @SelectProvider(type = OperationLogSQL.class, method = "selectCountByCriteria")
    int selectCountByCriteria(@Param("list") List<String> list, @Param("params") Map<String, Object> params);

}