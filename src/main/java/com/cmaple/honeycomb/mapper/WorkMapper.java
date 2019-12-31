package com.cmaple.honeycomb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cmaple.honeycomb.mapper.AdvancedSQL.WorkSQL;
import com.cmaple.honeycomb.model.Work;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

/**
 * 类名：mybatis plus 映射岗位接口 - WorkMapper
 * 功能描述：NULL
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：NULL
 * 创建人：CMAPLE
 * 创建日期：2019-11-18
 */
public interface WorkMapper extends BaseMapper<Work> {
    /**
     * 函数名：select函数-根据条件查询岗位信息- selectByCriteria（）
     * 功能描述： 根据条件查询岗位信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param list   List类型的条件列表
     * @param params Map类型的字段及数值集合
     * @param page   int类型的页数
     * @param num    int类型的数量
     *               返回值：list<Work>
     *               异    常：NULL
     *               创建人：CMAPLE
     *               日期：2019-11-18
     */
    @SelectProvider(type = WorkSQL.class, method = "selectByCriteria")
    List<Work> selectByCriteria(@Param("list") List<String> list, @Param("params") Map<String, Object> params, @Param("page") int page, @Param("num") int num);

    /**
     * 函数名：select函数-根据条件查询岗位信息总数- selectCountByCriteria（）
     * 功能描述： 根据条件查询岗位信息总数
     * 输入参数：<按照参数定义顺序>
     *
     * @param list   List类型的条件列表
     * @param params Map类型的字段及数值集合
     *               返回值：int
     *               异    常：NULL
     *               创建人：CMAPLE
     *               日期：2019-11-18
     */
    @SelectProvider(type = WorkSQL.class, method = "selectCountByCriteria")
    int selectCountByCriteria(@Param("list") List<String> list, @Param("params") Map<String, Object> params);

    /**
     * 函数名：复杂查询函数-根据时间倒叙查询岗位信息- selectDescOrderByTime（）
     * 功能描述： 根据时间倒叙查询岗位信息
     * 输入参数：<按照参数定义顺序>
     * 返回值：list<Work>
     * 异    常：无
     * 创建人：CMAPLE
     * 日期：2019-11-18
     */
    @SelectProvider(type = WorkSQL.class, method = "selectDescOrderByTime")
    List<Work> selectDescOrderByTime();
}
