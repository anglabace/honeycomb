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
 * 功能描述：
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：无
 * 创建人：CMAPLE
 * 创建日期：2019-11-19
 * 修改人：
 * 级别：NULL
 * 修改日期：
 */
public interface MilestoneMapper extends BaseMapper<Milestone> {

    /**
     * 函数名：复杂查询函数-根据条件查询里程碑信息- queryMilestoneByParams（）
     * 功能描述： 根据条件查询里程碑信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param list   条件列表
     * @param params 字段及数值集合
     * @param page   分页查询PAGE条件
     * @param num    分页查询NUM条件
     *               返回值：list<Milestone>
     *               异    常：无
     *               创建人：CMAPLE
     *               日期：2019-11-19
     *               修改人：
     *               级别：普通用户
     *               日期：
     */
    @SelectProvider(type = MilestoneSQL.class, method = "queryMilestoneByParams")
    List<Milestone> queryMilestoneByParams(@Param("list") List<String> list, @Param("params") Map<String, Object> params, @Param("page") int page, @Param("num") int num);

    /**
     * 函数名：复杂查询函数-主页查询里程碑，只查看日期倒叙的前四条数据 - queryMilestoneAtHome（）
     * 功能描述： 主页查询里程碑，只查看日期倒叙的前四条数据
     * 输入参数：<按照参数定义顺序>
     * <p>
     * 返回值：list<Milestone>
     * 异    常：无
     * 创建人：CMAPLE
     * 日期：2019-11-19
     * 修改人：
     * 级别：普通用户
     * 日期：
     */
    @SelectProvider(type = MilestoneSQL.class, method = "queryMilestoneAtHome")
    List<Milestone> queryMilestoneAtHome();

    /**
     * 函数名：复杂查询函数-查询所有里程碑，按照倒叙排列 - queryMilestoneDescOrderBy（）
     * 功能描述： 查询所有里程碑，按照倒叙排列
     * 输入参数：<按照参数定义顺序>
     * <p>
     * 返回值：list<Milestone>
     * 异    常：无
     * 创建人：CMAPLE
     * 日期：2019-11-19
     * 修改人：
     * 级别：普通用户
     * 日期：
     */
    @SelectProvider(type = MilestoneSQL.class, method = "queryMilestoneDescOrderBy")
    List<Milestone> queryMilestoneDescOrderBy();
    
}
