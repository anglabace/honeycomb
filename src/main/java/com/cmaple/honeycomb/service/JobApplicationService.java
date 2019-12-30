package com.cmaple.honeycomb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cmaple.honeycomb.mapper.JobApplicationMapper;
import com.cmaple.honeycomb.model.JobApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 类名：岗位申请相关功能类 - JobApplicationService
 * 功能描述：维护岗位申请管理相应的功能
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：无
 * 创建人：cmaple
 * 创建日期：2019-12-30
 * 修改人：
 * 级别：NULL
 * 修改日期：
 */
@Service
public class JobApplicationService {

    //引入映射接口
    @Autowired
    private JobApplicationMapper jobApplicationMapper;

    /**
     * 函数名：查询函数-根据条件查询岗位申请 - getJobApplicationByParams（）
     * 功能描述： 根据条件查询岗位申请
     * 输入参数：<按照参数定义顺序>
     *
     * @param list   条件列表
     * @param params 字段及数值集合
     * @param page   分页查询PAGE条件
     * @param num    分页查询NUM条件
     *               返回值：List<JobApplication>
     *               异    常：无
     *               创建人：CMAPLE
     *               日期：2019-12-30
     *               修改人：
     *               日期：
     */
    public List<JobApplication> getJobApplicationByParams(List<String> list, Map<String, Object> params, int page, int num) {
        return jobApplicationMapper.queryJobApplicationByParams(list, params, page, num);
    }

    /**
     * 函数名：查询函数-根据条件查询岗位申请的数量 - getJobApplicationCountByParams（）
     * 功能描述： 根据条件查询岗位申请的数量
     * 输入参数：<按照参数定义顺序>
     *
     * @param list   条件列表
     * @param params 字段及数值集合
     *               返回值：Int
     *               异    常：无
     *               创建人：CMAPLE
     *               日期：2019-12-30
     *               修改人：
     *               日期：
     */
    public int getJobApplicationCountByParams(List<String> list, Map<String, Object> params) {
        return jobApplicationMapper.queryJobApplicationCountByParams(list, params);
    }

    /**
     * 函数名：查询函数-根据ID号查询岗位申请内容 - getJobApplicationById（）
     * 功能描述： 根据ID号查询岗位申请内容
     * 输入参数：<按照参数定义顺序>
     *
     * @param id int类型的公告编号
     *           返回值：int
     *           异    常：无
     *           创建人：CMAPLE
     *           日期：2019-12-30
     *           修改人：
     *           日期：
     */
    public JobApplication getJobApplicationById(int id) {
        return jobApplicationMapper.selectById(id);
    }

    /**
     * 函数名：查询函数-查询岗位申请总数量 - getJobApplicationCount（）
     * 功能描述： 查询岗位申请总数量
     * 输入参数：<按照参数定义顺序>
     * <p>
     * 返回值：int
     * 异    常：无
     * 创建人：CMAPLE
     * 日期：2019-12-30
     * 修改人：
     * 日期：
     */
    public int getJobApplicationCount() {
        return jobApplicationMapper.selectCount(null);
    }

    /**
     * 函数名：插入函数-插入岗位申请信息 - insertJobApplication（）
     * 功能描述： 插入岗位申请信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param jobApplication 岗位申请实体类
     *                       <p>
     *                       返回值：int
     *                       异    常：无
     *                       创建人：CMAPLE
     *                       日期：2019-12-30
     *                       修改人：
     *                       日期：
     */
    public int insertJobApplication(JobApplication jobApplication) {
        return jobApplicationMapper.insert(jobApplication);
    }

    /**
     * 函数名：更新函数-更新岗位申请信息 - updateJobApplication（）
     * 功能描述： 更新岗位申请信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param jobApplication 岗位申请实体类
     *                       <p>
     *                       返回值：int
     *                       异    常：无
     *                       创建人：CMAPLE
     *                       日期：2019-12-30
     *                       修改人：
     *                       日期：
     */
    public int updateJobApplication(JobApplication jobApplication) {
        return jobApplicationMapper.update(jobApplication, new QueryWrapper<JobApplication>().lambda().eq(JobApplication::getId, jobApplication.getId()));
    }

    /**
     * 函数名：删除函数-根据ID删除岗位申请信息 - delJobApplication（）
     * 功能描述： 根据ID删除岗位申请信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param id 公告id号
     *           <p>
     *           返回值：int
     *           异    常：无
     *           创建人：CMAPLE
     *           日期：2019-12-30
     *           修改人：
     *           日期：
     */
    public int delJobApplication(int id) {
        return jobApplicationMapper.deleteById(id);
    }

}
