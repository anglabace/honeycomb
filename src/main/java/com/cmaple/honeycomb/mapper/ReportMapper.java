package com.cmaple.honeycomb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cmaple.honeycomb.mapper.AdvancedSQL.ReportSQL;
import com.cmaple.honeycomb.model.Report;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

/**
 * 类名：mybatis plus 映射调查报告接口 - ReportMapper
 * 功能描述：
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：无
 * 创建人：CMAPLE
 * 创建日期：2019-11-27
 * 修改人：
 * 级别：NULL
 * 修改日期：
 */
public interface ReportMapper extends BaseMapper<Report> {
    /**
     * 函数名：复杂查询函数-根据条件查询调查报告信息信息- queryReportByParams（）
     * 功能描述： 根据条件查询公告信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param list   条件列表
     * @param params 字段及数值集合
     * @param page   分页查询PAGE条件
     * @param num    分页查询NUM条件
     *               返回值：list<Report>
     *               异    常：无
     *               创建人：CMAPLE
     *               日期：2019-11-27
     *               修改人：
     *               级别：普通用户
     *               日期：
     */
    @SelectProvider(type = ReportSQL.class, method = "queryReportByParams")
    List<Report> queryReportByParams(@Param("list") List<String> list, @Param("params") Map<String, Object> params, @Param("page") int page, @Param("num") int num);

    /**
     * 函数名：复杂查询函数-按照时间倒叙查询调查报告- queryReportDescOrderBy（）
     * 功能描述： 按照时间倒叙查询调查报告
     * 输入参数：<按照参数定义顺序>
     * 返回值：list<Report>
     * 异    常：无
     * 创建人：CMAPLE
     * 日期：2019-11-27
     * 修改人：
     * 级别：普通用户
     * 日期：
     */
    @SelectProvider(type = ReportSQL.class, method = "queryReportDescOrderBy")
    List<Report> queryReportDescOrderBy();
}
