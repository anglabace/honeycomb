package com.cmaple.honeycomb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cmaple.honeycomb.mapper.AdvancedSQL.MilestoneSQL;
import com.cmaple.honeycomb.model.Milestone;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

/**
 * 类名：mybatis plus 映射里程碑接口 - MilestoneMapper
 * 功能描述：NULL
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：NULL
 * 创建人：CMAPLE
 * 创建日期：2019-11-19
 */
public interface MilestoneMapper extends BaseMapper<Milestone> {

    /**
     * 函数名：select函数-根据条件查询里程碑信息- selectByCriteria（）
     * 功能描述： 根据条件查询里程碑信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param list   List类型的条件列表
     * @param params Map类型的字段及数值集合
     * @param page   int类型的页数
     * @param num    int类型的数量
     *               返回值：list<Milestone>
     *               异    常：NULL
     *               创建人：CMAPLE
     *               日期：2019-11-19
     */
    @SelectProvider(type = MilestoneSQL.class, method = "selectByCriteria")
    List<Milestone> selectByCriteria(@Param("list") List<String> list, @Param("params") Map<String, Object> params, @Param("page") int page, @Param("num") int num);

    /**
     * 函数名：select函数-根据条件查询里程碑总数- selectCountByCriteria（）
     * 功能描述： 根据条件查询里程碑总数
     * 输入参数：<按照参数定义顺序>
     *
     * @param list   List类型的条件列表
     * @param params Map类型的字段及数值集合
     *               返回值：int
     *               异    常：NULL
     *               创建人：CMAPLE
     *               日期：2019-11-19
     */
    @SelectProvider(type = MilestoneSQL.class, method = "selectCountByCriteria")
    int selectCountByCriteria(@Param("list") List<String> list, @Param("params") Map<String, Object> params);


    /**
     * 函数名：select函数-主页查询里程碑列表 - selectAtHomePage（）
     * 功能描述： 主页查询里程碑列表
     * 输入参数：<按照参数定义顺序>
     * 返回值：list<Milestone>
     * 异    常：NULL
     * 创建人：CMAPLE
     * 日期：2019-11-19
     */
    @SelectProvider(type = MilestoneSQL.class, method = "selectAtHomePage")
    List<Milestone> selectAtHomePage();

    /**
     * 函数名：select函数-按照时间倒叙查询里程碑信息 - selectDescOrderByTime（）
     * 功能描述： 按照时间倒叙查询里程碑信息
     * 输入参数：<按照参数定义顺序>
     * <p>
     * 返回值：list<Milestone>
     * 异    常：NULL
     * 创建人：CMAPLE
     * 日期：2019-11-19
     */
    @SelectProvider(type = MilestoneSQL.class, method = "selectDescOrderByTime")
    List<Milestone> selectDescOrderByTime();

}