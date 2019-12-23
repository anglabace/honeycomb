package com.cmaple.honeycomb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cmaple.honeycomb.mapper.AdvancedSQL.TimeAxisSQL;
import com.cmaple.honeycomb.model.TimeAxis;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

/**
 * 类名：mybatis plus 映射公告接口 - TimeAxisMapper
 * 功能描述：
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：无
 * 创建人：CMAPLE
 * 创建日期：2019-12-09
 * 修改人：
 * 级别：NULL
 * 修改日期：
 */
public interface TimeAxisMapper extends BaseMapper<TimeAxis> {
    /**
     * 函数名：复杂查询函数-根据条件查询时间轴信息- queryTimeAxisByParams（）
     * 功能描述： 根据条件查询时间轴信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param list   条件列表
     * @param params 字段及数值集合
     * @param page   分页查询PAGE条件
     * @param num    分页查询NUM条件
     *               返回值：list<TimeAxis>
     *               异    常：无
     *               创建人：CMAPLE
     *               日期：2019-12-09
     *               修改人：
     *               级别：普通用户
     *               日期：
     */
    @SelectProvider(type = TimeAxisSQL.class, method = "queryTimeAxisByParams")
    List<TimeAxis> queryTimeAxisByParams(@Param("list") List<String> list, @Param("params") Map<String, Object> params, @Param("page") int page, @Param("num") int num);

    /**
     * 函数名：复杂查询函数-主页查询,根据时间倒叙查询时间轴 - queryTimeAxisAtHome（）
     * 功能描述： 主页查询，根据时间倒叙查询时间轴
     * 输入参数：<按照参数定义顺序>
     * <p>
     * 返回值：list<TimeAxis>
     * 异    常：无
     * 创建人：CMAPLE
     * 日期：2019-12-09
     * 修改人：
     * 级别：普通用户
     * 日期：
     */
    @SelectProvider(type = TimeAxisSQL.class, method = "queryTimeAxisAtHome")
    List<TimeAxis> queryTimeAxisAtHome();
}
