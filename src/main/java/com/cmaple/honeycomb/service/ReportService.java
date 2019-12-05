package com.cmaple.honeycomb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cmaple.honeycomb.mapper.ReportMapper;
import com.cmaple.honeycomb.model.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 类名：调查报告服务功能类 - ReportService
 * 功能描述：维护调查报告管理相应的功能
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：无
 * 创建人：cmaple
 * 创建日期：2019-11-27
 * 修改人：
 * 级别：NULL
 * 修改日期：
 */
@Service
public class ReportService {
    //引入用户映射接口
    @Autowired
    private ReportMapper reportMapper;

    /**
     * 函数名：查询函数-根据ID号查询调查报告 - getReportById（）
     * 功能描述： 根据ID号查询调查报告
     * 输入参数：<按照参数定义顺序>
     *
     * @param id int类型的公告编号
     *           返回值：int
     *           异    常：无
     *           创建人：CMAPLE
     *           日期：2019-11-11
     *           修改人：
     *           日期：
     */
    public Report getReportById(int id) {
        return reportMapper.selectById(id);
    }

    /**
     * 函数名：查询函数-根据条件查询调查报告信息 - getReportByParams（）
     * 功能描述： 根据条件查询调查报告信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param list   条件列表
     * @param params 字段及数值集合
     * @param page   分页查询PAGE条件
     * @param num    分页查询NUM条件
     *               返回值：List<Report>
     *               异    常：无
     *               创建人：CMAPLE
     *               日期：2019-11-27
     *               修改人：
     *               日期：
     */
    public List<Report> getReportByParams(List<String> list, Map<String, Object> params, int page, int num) {
        return reportMapper.queryReportByParams(list, params, page, num);
    }

    /**
     * 函数名：查询函数-查询所有调查报告，按照时间倒叙排列 - queryReportDescOrderBy（）
     * 功能描述： 查询所有调查报告，按照时间倒叙排列
     * 输入参数：<按照参数定义顺序>
     * 返回值：List<Report>
     * 异    常：无
     * 创建人：CMAPLE
     * 日期：2019-11-27
     * 修改人：
     * 日期：
     */
    public List<Report> getReportDescOrderBy() {
        return reportMapper.queryReportDescOrderBy();
    }

    /**
     * 函数名：插入函数-插入公告信息 - insertReport（）
     * 功能描述： 插入公告信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param report 调查报告实体类
     *               <p>
     *               返回值：int
     *               异    常：无
     *               创建人：CMAPLE
     *               日期：2019-11-27
     *               修改人：
     *               日期：
     */
    public int insertReport(Report report) {
        return reportMapper.insert(report);
    }

    /**
     * 函数名：修改函数-修改调查报告信息 - updateReport（）
     * 功能描述： 修改调查报告信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param report 调查报告实体类
     *               <p>
     *               返回值：int
     *               异    常：无
     *               创建人：CMAPLE
     *               日期：2019-11-27
     *               修改人：
     *               日期：
     */
    public int updateReport(Report report) {
        return reportMapper.update(report, new QueryWrapper<Report>().lambda().eq(Report::getId, report.getId()));
    }

    /**
     * 函数名：修改函数-修改调查报告信息 - delReport（）
     * 功能描述： 修改调查报告信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param id 调查报告实体类
     *           <p>
     *           返回值：int
     *           异    常：无
     *           创建人：CMAPLE
     *           日期：2019-11-27
     *           修改人：
     *           日期：
     */
    public int delReport(int id) {
        return reportMapper.deleteById(id);
    }

}
