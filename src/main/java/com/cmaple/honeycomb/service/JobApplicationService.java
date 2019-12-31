package com.cmaple.honeycomb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cmaple.honeycomb.mapper.JobApplicationMapper;
import com.cmaple.honeycomb.model.JobApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 类名：岗位申请服务功能类业务组件 - JobApplicationService
 * 功能描述：岗位申请服务功能类业务组件
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：NULL
 * 创建人：CMAPLE
 * 创建日期：2019-12-30
 */
@Service
public class JobApplicationService {

    /**
     * 引入JobApplicationMapper
     */
    @Autowired
    private JobApplicationMapper jobApplicationMapper;

    /**
     * 函数名：select函数-根据条件查询岗位申请列表 - selectByCriteria（）
     * 功能描述： 根据条件查询岗位申请列表
     * 输入参数：<按照参数定义顺序>
     *
     * @param list   List类型的条件列表
     * @param params Map类型的字段及数值集合
     * @param page   int类型的页数
     * @param num    int类型的数量
     *               返回值：List<JobApplication>
     *               异    常：NULL
     *               创建人：CMAPLE
     *               日期：2019-12-30
     */
    public List<JobApplication> selectByCriteria(List<String> list, Map<String, Object> params, int page, int num) {
        return jobApplicationMapper.selectByCriteria(list, params, page, num);
    }

    /**
     * 函数名：select函数-根据条件查询岗位申请的数量 - selectCountByCriteria（）
     * 功能描述： 根据条件查询岗位申请的数量
     * 输入参数：<按照参数定义顺序>
     *
     * @param list   List类型的条件列表
     * @param params Map类型的字段及数值集合
     *               返回值：int
     *               异    常：无
     *               创建人：CMAPLE
     *               日期：2019-12-30
     */
    public int selectCountByCriteria(List<String> list, Map<String, Object> params) {
        return jobApplicationMapper.selectCountByCriteria(list, params);
    }

    /**
     * 函数名：select函数-根据ID号查询岗位申请内容 - selectById（）
     * 功能描述： 根据ID号查询岗位申请内容
     * 输入参数：<按照参数定义顺序>
     *
     * @param id int类型的公告编号
     *           返回值：int
     *           异    常：无
     *           创建人：CMAPLE
     *           日期：2019-12-30
     */
    public JobApplication selectById(int id) {
        return jobApplicationMapper.selectById(id);
    }

    /**
     * 函数名：insert函数-插入岗位申请信息 - insert（）
     * 功能描述： 插入岗位申请信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param jobApplication 岗位申请实体类
     *                       <p>
     *                       返回值：int
     *                       异    常：无
     *                       创建人：CMAPLE
     *                       日期：2019-12-30
     */
    public int insert(JobApplication jobApplication) {
        return jobApplicationMapper.insert(jobApplication);
    }

    /**
     * 函数名：update函数-更新岗位申请信息 - update（）
     * 功能描述： 更新岗位申请信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param jobApplication 岗位申请实体类
     *                       <p>
     *                       返回值：int
     *                       异    常：无
     *                       创建人：CMAPLE
     *                       日期：2019-12-30
     */
    public int update(JobApplication jobApplication) {
        return jobApplicationMapper.update(jobApplication, new QueryWrapper<JobApplication>().lambda().eq(JobApplication::getId, jobApplication.getId()));
    }

    /**
     * 函数名：delete函数-根据ID删除岗位申请信息 - deleteById（）
     * 功能描述： 根据ID删除岗位申请信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param id 公告id号
     *           <p>
     *           返回值：int
     *           异    常：无
     *           创建人：CMAPLE
     *           日期：2019-12-30
     */
    public int deleteById(int id) {
        return jobApplicationMapper.deleteById(id);
    }

}
