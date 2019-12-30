package com.cmaple.honeycomb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cmaple.honeycomb.mapper.AdvancedSQL.JobApplicationSQL;
import com.cmaple.honeycomb.model.JobApplication;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

/**
 * 类名：mybatis plus 映射公告接口 - JobApplicationMapper
 * 功能描述：
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：无
 * 创建人：CMAPLE
 * 创建日期：2019-12-30
 * 修改人：
 * 级别：NULL
 * 修改日期：
 */
public interface JobApplicationMapper extends BaseMapper<JobApplication> {

    /**
     * 函数名：复杂查询函数-根据条件查询岗位申请列表- queryJobApplicationByParams（）
     * 功能描述： 根据条件查询岗位申请列表
     * 输入参数：<按照参数定义顺序>
     *
     * @param list   条件列表
     * @param params 字段及数值集合
     * @param page   分页查询PAGE条件
     * @param num    分页查询NUM条件
     *               返回值：list<JobApplication>
     *               异    常：无
     *               创建人：CMAPLE
     *               日期：2019-12-30
     *               修改人：
     *               级别：普通用户
     *               日期：
     */
    @SelectProvider(type = JobApplicationSQL.class, method = "queryJobApplicationByParams")
    List<JobApplication> queryJobApplicationByParams(@Param("list") List<String> list, @Param("params") Map<String, Object> params, @Param("page") int page, @Param("num") int num);

    /**
     * 函数名：复杂查询函数-根据条件查询岗位申请的数量- getJobApplicationCountByParams（）
     * 功能描述： 根据条件查询岗位申请的数量
     * 输入参数：<按照参数定义顺序>
     *
     * @param list   条件列表
     * @param params 字段及数值集合
     *               返回值：int
     *               异    常：无
     *               创建人：CMAPLE
     *               日期：2019-12-30
     *               修改人：
     *               级别：普通用户
     *               日期：
     */
    @SelectProvider(type = JobApplicationSQL.class, method = "queryJobApplicationCountByParams")
    int queryJobApplicationCountByParams(@Param("list") List<String> list, @Param("params") Map<String, Object> params);

}
