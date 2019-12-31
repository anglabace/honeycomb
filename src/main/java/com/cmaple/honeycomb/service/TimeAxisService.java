package com.cmaple.honeycomb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cmaple.honeycomb.mapper.TimeAxisMapper;
import com.cmaple.honeycomb.model.TimeAxis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 类名：时间轴功能类业务组件 - TimeAxisService
 * 功能描述：时间轴功能类业务组件
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：NULL
 * 创建人：cmaple
 * 创建日期：2019-12-09
 */
@Service
public class TimeAxisService {
    /**
     * 引入TimeAxisMapper
     */
    @Autowired
    private TimeAxisMapper timeAxisMapper;

    /**
     * 函数名：select函数-根据条件查询时间轴信息 - selectByCriteria（）
     * 功能描述： 根据条件查询时间轴信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param list   List类型的条件列表
     * @param params Map类型的字段及数值集合
     * @param page   int类型的页数
     * @param num    int类型的数量
     *               返回值：List<TimeAxis>
     *               异    常：NULL
     *               创建人：CMAPLE
     *               日期：2019-12-09
     */
    public List<TimeAxis> selectByCriteria(List<String> list, Map<String, Object> params, int page, int num) {
        return timeAxisMapper.selectByCriteria(list, params, page, num);
    }

    /**
     * 函数名：select函数-根据条件查询时间轴总数 - selectCountByCriteria（）
     * 功能描述： 根据条件查询时间轴总数
     * 输入参数：<按照参数定义顺序>
     *
     * @param list   List类型的条件列表
     * @param params Map类型的字段及数值集合
     *               返回值：int
     *               异    常：NULL
     *               创建人：CMAPLE
     *               日期：2019-12-31
     */
    public int selectCountByCriteria(List<String> list, Map<String, Object> params) {
        return timeAxisMapper.selectCountByCriteria(list, params);
    }

    /**
     * 函数名：select函数-主页查询时间轴信息 - selectAtHomePage（）
     * 功能描述： 主页查询时间轴信息
     * 输入参数：<按照参数定义顺序>
     * 返回值：List<TimeAxis>
     * 异    常：NULL
     * 创建人：CMAPLE
     * 日期：2019-12-09
     */
    public List<TimeAxis> selectAtHomePage() {
        return timeAxisMapper.selectAtHomePage();
    }

    /**
     * 函数名：insert函数-插入时间轴信息 - insert（）
     * 功能描述： 插入时间轴信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param timeAxis TimeAxis时间轴信息实体类
     *                 返回值：int
     *                 异    常：NULL
     *                 创建人：CMAPLE
     *                 日期：2019-12-09
     */
    public int insert(TimeAxis timeAxis) {
        return timeAxisMapper.insert(timeAxis);
    }

    /**
     * 函数名：update函数-更新公告信息 - update（）
     * 功能描述： 更新公告信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param timeAxis TimeAxis时间轴信息实体类
     *                 <p>
     *                 返回值：int
     *                 异    常：NULL
     *                 创建人：CMAPLE
     *                 日期：2019-12-09
     */
    public int update(TimeAxis timeAxis) {
        return timeAxisMapper.update(timeAxis, new QueryWrapper<TimeAxis>().lambda().eq(TimeAxis::getId, timeAxis.getId()));
    }

    /**
     * 函数名：delete函数-根据ID删除时间轴信息 - deleteById（）
     * 功能描述： 根据ID删除时间轴信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param id int类型的时间轴id号
     *           返回值：int
     *           异    常：无
     *           创建人：CMAPLE
     *           日期：2019-12-09
     */
    public int deleteById(int id) {
        return timeAxisMapper.deleteById(id);
    }
}