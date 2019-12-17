package com.cmaple.honeycomb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cmaple.honeycomb.mapper.TimeAxisMapper;
import com.cmaple.honeycomb.model.TimeAxis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 类名：时间轴功能类 - TimeAxisService
 * 功能描述：时间轴功能类
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：无
 * 创建人：cmaple
 * 创建日期：2019-12-09
 * 修改人：
 * 级别：NULL
 * 修改日期：
 */
@Service
public class TimeAxisService {
    //引入用户映射接口
    @Autowired
    private TimeAxisMapper timeAxisMapper;

//    /**
//     * 函数名：查询函数-根据条件查询公告 - getTimeAxisByParams（）
//     * 功能描述： 根据条件查询公告
//     * 输入参数：<按照参数定义顺序>
//     *
//     * @param list   条件列表
//     * @param params 字段及数值集合
//     * @param page   分页查询PAGE条件
//     * @param num    分页查询NUM条件
//     *               返回值：List<TimeAxis>
//     *               异    常：无
//     *               创建人：CMAPLE
//     *               日期：2019-12-09
//     *               修改人：
//     *               日期：
//     */
//    public List<TimeAxis> getTimeAxisByParams(List<String> list, Map<String, Object> params, int page, int num) {
//        return timeAxisMapper.queryTimeAxisByParams(list, params, page, num);
//    }
//
//    /**
//     * 函数名：查询函数-主页查询，根据时间倒叙查询时间轴 - getTimeAxisAtHome（）
//     * 功能描述： 主页查询，根据时间倒叙查询时间轴
//     * 输入参数：<按照参数定义顺序>
//     * <p>
//     * 返回值：List<TimeAxis>
//     * 异    常：无
//     * 创建人：CMAPLE
//     * 日期：2019-12-09
//     * 修改人：
//     * 日期：
//     */
//    public List<TimeAxis> getTimeAxisAtHome() {
//        return timeAxisMapper.queryTimeAxisAtHome();
//    }

    /**
     * 函数名：插入函数-插入时间轴信息 - insertTimeAxis（）
     * 功能描述： 插入时间轴信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param timeAxis 时间轴信息实体类
     *                 <p>
     *                 返回值：int
     *                 异    常：无
     *                 创建人：CMAPLE
     *                 日期：2019-12-09
     *                 修改人：
     *                 日期：
     */
    public int insertTimeAxis(TimeAxis timeAxis) {
        return timeAxisMapper.insert(timeAxis);
    }

    /**
     * 函数名：更新函数-更新公告信息 - updateAnnouncement（）
     * 功能描述： 更新公告信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param timeAxis 时间轴信息实体类
     *                 <p>
     *                 返回值：int
     *                 异    常：无
     *                 创建人：CMAPLE
     *                 日期：2019-12-09
     *                 修改人：
     *                 日期：
     */
    public int updateTimeAxis(TimeAxis timeAxis) {
        return timeAxisMapper.update(timeAxis, new QueryWrapper<TimeAxis>().lambda().eq(TimeAxis::getId, timeAxis.getId()));
    }

    /**
     * 函数名：删除函数-根据ID删除时间轴信息 - delTimeAxis（）
     * 功能描述： 根据ID删除时间轴信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param id 时间轴id号
     *           <p>
     *           返回值：int
     *           异    常：无
     *           创建人：CMAPLE
     *           日期：2019-12-09
     *           修改人：
     *           日期：
     */
    public int delTimeAxis(int id) {
        return timeAxisMapper.deleteById(id);
    }
}
