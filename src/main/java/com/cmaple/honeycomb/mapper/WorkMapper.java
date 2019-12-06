package com.cmaple.honeycomb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cmaple.honeycomb.model.Work;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

/**
 * 类名：mybatis plus 映射岗位接口 - WorkMapper
 * 功能描述：
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：无
 * 创建人：CMAPLE
 * 创建日期：2019-12-05
 * 修改人：
 * 级别：NULL
 * 修改日期：
 */
public interface WorkMapper extends BaseMapper<Work> {
    /**
     * 函数名：复杂查询函数-根据条件查询岗位信息- queryWorkByParams（）
     * 功能描述： 根据条件查询岗位信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param list   条件列表
     * @param params 字段及数值集合
     * @param page   分页查询PAGE条件
     * @param num    分页查询NUM条件
     *               返回值：list<Work>
     *               异    常：无
     *               创建人：CMAPLE
     *               日期：2019-11-18
     *               修改人：
     *               级别：普通用户
     *               日期：
     */
    @SelectProvider(type = AdvancedSQL.class, method = "queryWorkByParams")
    List<Work> queryWorkByParams(@Param("list") List<String> list, @Param("params") Map<String, Object> params, @Param("page") int page, @Param("num") int num);

    /**
     * 函数名：复杂查询函数-根据时间倒叙查询岗位信息，工作页现实- queryWorkDescOrderBy（）
     * 功能描述： 根据时间倒叙查询岗位信息，工作页现实
     * 输入参数：<按照参数定义顺序>
     * <p>
     * 返回值：list<Work>
     * 异    常：无
     * 创建人：CMAPLE
     * 日期：2019-11-18
     * 修改人：
     * 级别：普通用户
     * 日期：
     */
    @SelectProvider(type = AdvancedSQL.class, method = "queryWorkDescOrderBy")
    List<Work> queryWorkDescOrderBy();
}
