package com.cmaple.honeycomb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cmaple.honeycomb.mapper.ReportMapper;
import com.cmaple.honeycomb.model.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 类名：调查报告功能类业务组件 - ReportService
 * 功能描述：调查报告功能类业务组件
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：NULL
 * 创建人：CMAPLE
 * 创建日期：2019-11-27
 */
@Service
public class ReportService {
    /**
     * 引入ReportMapper
     */
    @Autowired
    private ReportMapper reportMapper;


    /**
     * 函数名：select函数-根据条件查询调查报告列表 - selectByCriteria（）
     * 功能描述： 根据条件查询调查报告列表
     * 输入参数：<按照参数定义顺序>
     *
     * @param list   List类型的条件列表
     * @param params Map类型的字段及数值集合
     * @param page   int类型的页数
     * @param num    int类型的数量
     *               返回值：List<Report>
     *               异    常：NULL
     *               创建人：CMAPLE
     *               日期：2019-11-27
     */
    public List<Report> selectByCriteria(List<String> list, Map<String, Object> params, int page, int num) {
        return reportMapper.selectByCriteria(list, params, page, num);
    }

    /**
     * 函数名：select函数-根据条件查询调查报告总数 - selectCountByCriteria（）
     * 功能描述： 根据条件查询调查报告总数
     * 输入参数：<按照参数定义顺序>
     *
     * @param list   List类型的条件列表
     * @param params Map类型的字段及数值集合
     *               返回值：int
     *               异    常：无
     *               创建人：CMAPLE
     *               日期：2019-12-31
     */
    public int selectCountByCriteria(List<String> list, Map<String, Object> params) {
        return reportMapper.selectCountByCriteria(list, params);
    }

    /**
     * 函数名：select函数-查询所有调查报告，按照时间倒叙排列 - selectDescOrderByTime（）
     * 功能描述： 查询所有调查报告，按照时间倒叙排列
     * 输入参数：<按照参数定义顺序>
     * 返回值：List<Report>
     * 异    常：NULL
     * 创建人：CMAPLE
     * 日期：2019-11-27
     */
    public List<Report> selectDescOrderByTime() {
        return reportMapper.selectDescOrderByTime();
    }

    /**
     * 函数名：select函数-根据ID号查询调查报告 - selectById（）
     * 功能描述： 根据ID号查询调查报告
     * 输入参数：<按照参数定义顺序>
     *
     * @param id int类型的公告编号
     *           返回值：Report
     *           异    常：NULL
     *           创建人：CMAPLE
     *           日期：2019-11-11
     */
    public Report selectById(int id) {
        return reportMapper.selectById(id);
    }

    /**
     * 函数名：insert函数-插入公告信息 - insert（）
     * 功能描述： 插入公告信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param report 调查报告实体类
     *               返回值：int
     *               异    常：NULL
     *               创建人：CMAPLE
     *               日期：2019-11-27
     */
    public int insert(Report report) {
        return reportMapper.insert(report);
    }

    /**
     * 函数名：update函数-修改调查报告信息 - update（）
     * 功能描述： 修改调查报告信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param report 调查报告实体类
     *               返回值：int
     *               异    常：NULL
     *               创建人：CMAPLE
     *               日期：2019-11-27
     */
    public int update(Report report) {
        return reportMapper.update(report, new QueryWrapper<Report>().lambda().eq(Report::getId, report.getId()));
    }

    /**
     * 函数名：delete函数-修改调查报告信息 - deleteById（）
     * 功能描述： 修改调查报告信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param id 调查报告实体类
     *           返回值：int
     *           异    常：NULL
     *           创建人：CMAPLE
     *           日期：2019-11-27
     */
    public int deleteById(int id) {
        return reportMapper.deleteById(id);
    }

}