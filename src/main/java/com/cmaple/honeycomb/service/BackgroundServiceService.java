package com.cmaple.honeycomb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cmaple.honeycomb.mapper.BackgroundServiceMapper;
import com.cmaple.honeycomb.model.BackgroundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 类名：后台服务相关服务功能类业务组件 - BackgroundServiceService
 * 功能描述：后台服务相关服务功能类业务组件
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：NULL
 * 创建人：CMAPLE
 * 创建日期：2019-09-30
 */
@Service
public class BackgroundServiceService {

    /**
     * 引入BackgroundServiceMapper
     */
    @Autowired
    private BackgroundServiceMapper backgroundServiceMapper;

    /**
     * 函数名：select函数-根据条件查询后台服务列表 - selectByCriteria（）
     * 功能描述： 根据条件查询后台服务列表
     *
     * @param list   List类型的条件列表
     * @param params Map类型的字段及数值集合
     * @param page   int类型的页数
     * @param num    int类型的数量
     *               返回值：List<BackgroundService>
     *               异    常：NULL
     *               创建人：CMAPLE
     *               日期：2019-09-30
     */
    public List<BackgroundService> selectByCriteria(List<String> list, Map<String, Object> params, int page, int num) {
        return backgroundServiceMapper.selectByCriteria(list, params, page, num);
    }

    /**
     * 函数名：select函数-根据条件查询后台服务总数 - selectCountByCriteria（）
     * 功能描述： 根据条件查询后台服务总数
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
        return backgroundServiceMapper.selectCountByCriteria(list, params);
    }

    /**
     * 函数名：select函数-根据ID号查询后台服务详情 - selectById（）
     * 功能描述： 根据ID号查询后台服务详情
     *
     * @param id int类型的公告编号
     *           返回值：BackgroundService
     *           异    常：NULL
     *           创建人：CMAPLE
     *           日期：2019-10-17
     */
    public BackgroundService selectById(int id) {
        return backgroundServiceMapper.selectById(id);
    }

    /**
     * 函数名：select函数-按照时间倒叙获取后台服务列表 - selectDescOrderByTime（）
     * 功能描述： 按照时间倒叙获取后台服务列表
     * 返回值：List<BackgroundService>
     * 异    常：NULL
     * 创建人：CMAPLE
     * 日期：2019-12-24
     */
    public List<BackgroundService> selectDescOrderByTime() {
        return backgroundServiceMapper.selectDescOrderByTime();
    }

    /**
     * 函数名：insert函数-插入后台服务信息 - insert（）
     * 功能描述： 插入后台服务信息
     *
     * @param backgroundService 后台服务实体类
     *                          返回值：int
     *                          异    常：NULL
     *                          创建人：CMAPLE
     *                          日期：2019-01-17
     */
    public int insert(BackgroundService backgroundService) {
        return backgroundServiceMapper.insert(backgroundService);
    }

    /**
     * 函数名：update函数-修改后台服务信息 - update（）
     * 功能描述： 修改后台服务信息
     *
     * @param backgroundService 后台服务实体类
     *                          返回值：int
     *                          异    常：NULL
     *                          创建人：CMAPLE
     *                          日期：2019-01-17
     */
    public int update(BackgroundService backgroundService) {
        return backgroundServiceMapper.update(backgroundService, new QueryWrapper<BackgroundService>().lambda().eq(BackgroundService::getId, backgroundService.getId()));
    }
}