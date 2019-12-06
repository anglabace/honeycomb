package com.cmaple.honeycomb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cmaple.honeycomb.mapper.WorkMapper;
import com.cmaple.honeycomb.model.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 类名：岗位服务功能类 - WorkService
 * 功能描述：维护岗位管理相应的功能
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：无
 * 创建人：cmaple
 * 创建日期：2019-12-06
 * 修改人：
 * 级别：NULL
 * 修改日期：
 */
@Service
public class WorkService {

    //引入用户映射接口
    @Autowired
    private WorkMapper workMapper;

    /**
     * 函数名：查询函数-根据ID号查询岗位信息 - getWorkById（）
     * 功能描述： 根据ID号查询岗位信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param id int类型的公告编号
     *           返回值：int
     *           异    常：无
     *           创建人：CMAPLE
     *           日期：2019-12-06
     *           修改人：
     *           日期：
     */
    public Work getWorkById(int id) {
        return workMapper.selectById(id);
    }

    /**
     * 函数名：查询函数-根据条件查询岗位信息 - getWorkByParams（）
     * 功能描述： 根据条件查询岗位信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param list   条件列表
     * @param params 字段及数值集合
     * @param page   分页查询PAGE条件
     * @param num    分页查询NUM条件
     *               返回值：List<Work>
     *               异    常：无
     *               创建人：CMAPLE
     *               日期：2019-12-06
     *               修改人：
     *               日期：
     */
    public List<Work> getWorkByParams(List<String> list, Map<String, Object> params, int page, int num) {
        return workMapper.queryWorkByParams(list, params, page, num);
    }

    /**
     * 函数名：查询函数-根据时间倒叙查询岗位概要信息 - getWorkDescOrderBy（）
     * 功能描述： 根据时间倒叙查询岗位概要信息
     * 输入参数：<按照参数定义顺序>
     * <p>
     * 返回值：List<Work>
     * 异    常：无
     * 创建人：CMAPLE
     * 日期：2019-12-06
     * 修改人：
     * 日期：
     */
    public List<Work> getWorkDescOrderBy() {
        return workMapper.queryWorkDescOrderBy();
    }

    /**
     * 函数名：插入函数-创建新的岗位信息 - insertWork（）
     * 功能描述： 创建新的岗位信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param work 岗位信息实体类
     *             <p>
     *             返回值：int
     *             异    常：无
     *             创建人：CMAPLE
     *             日期：2019-12-06
     *             修改人：
     *             日期：
     */
    public int insertWork(Work work) {
        return workMapper.insert(work);
    }

    /**
     * 函数名：更新函数-更新岗位信息 - updateWork（）
     * 功能描述： 更新岗位信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param work 岗位信息实体类
     *             <p>
     *             返回值：int
     *             异    常：无
     *             创建人：CMAPLE
     *             日期：2019-12-06
     *             修改人：
     *             日期：
     */
    public int updateWork(Work work) {
        return workMapper.update(work, new QueryWrapper<Work>().lambda().eq(Work::getId, work.getId()));
    }

    /**
     * 函数名：删除函数-根据ID删除岗位信息 - delWork（）
     * 功能描述： 根据ID删除岗位信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param id 岗位id号
     *           <p>
     *           返回值：int
     *           异    常：无
     *           创建人：CMAPLE
     *           日期：2019-12-06
     *           修改人：
     *           日期：
     */
    public int delWork(int id) {
        return workMapper.deleteById(id);
    }

}
