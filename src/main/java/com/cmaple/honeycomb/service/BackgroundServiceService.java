package com.cmaple.honeycomb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cmaple.honeycomb.mapper.BackgroundServiceMapper;
import com.cmaple.honeycomb.model.BackgroundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 类名：后台服务信息功能类 - BackgroundServiceService
 * 功能描述：维护服务器详细信息功能类相应的功能
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：无
 * 创建人：cmaple
 * 创建日期：2019-09-30
 * 修改人：
 * 级别：NULL
 * 修改日期：
 */
@Service
public class BackgroundServiceService {

    //引入映射后台服务接口
    @Autowired
    private BackgroundServiceMapper backgroundServiceMapper;

    /**
     * 函数名：查询函数-查询服务器信息并返回所有的服务器信息数据 - getBackgroundServices（）
     * 功能描述： 查询服务器信息并返回所有的服务器信息数据
     * <p>
     * 返回值：int
     * 异    常：无
     * 创建人：CMAPLE
     * 日期：2019-09-30
     * 修改人：
     * 级别：普通用户
     * 日期：
     */
    public List<BackgroundService> getBackgroundServices(List<String> list, Map<String, Object> params, int page, int num) {
        return backgroundServiceMapper.queryBackgroundServicesByParams(list, params, page, num);
    }

    /**
     * 函数名：查询函数-根据ID号查询后台服务详情 - getBackgroundServicesById（）
     * 功能描述： 根据ID号查询后台服务详情
     * <p>
     * 返回值：BackgroundService
     * 异    常：无
     * 创建人：CMAPLE
     * 日期：2019-10-17
     * 修改人：
     * 级别：普通用户
     * 日期：
     */
    public BackgroundService getBackgroundServicesById(int id) {
        return backgroundServiceMapper.selectById(id);
    }

    /**
     * 函数名：查询函数-按照时间倒叙获取后台服务详情 - getBackgroundServicesDescOrderBy（）
     * 功能描述： 按照时间倒叙获取后台服务详情
     * <p>
     * 返回值：List<BackgroundService>
     * 异    常：无
     * 创建人：CMAPLE
     * 日期：2019-12-24
     * 修改人：
     * 级别：普通用户
     * 日期：
     */
    public List<BackgroundService> getBackgroundServicesDescOrderBy() {
        return backgroundServiceMapper.queryBackgroundServicesDescOrderBy();
    }

    /**
     * 函数名：插入函数-插入后台服务信息 - insertBackgroundService（）
     * 功能描述： 插入后台服务信息
     *
     * @param backgroundService 后台服务实体类
     *                          返回值：int
     *                          异    常：无
     *                          创建人：CMAPLE
     *                          日期：2019-01-17
     *                          修改人：
     *                          级别：普通用户
     *                          日期：
     */
    public int insertBackgroundService(BackgroundService backgroundService) {
        return backgroundServiceMapper.insert(backgroundService);
    }

    /**
     * 函数名：修改函数-修改后台服务信息 - updateBackgroundService（）
     * 功能描述： 修改后台服务信息
     *
     * @param backgroundService 后台服务实体类
     *                          返回值：int
     *                          异    常：无
     *                          创建人：CMAPLE
     *                          日期：2019-01-17
     *                          修改人：
     *                          级别：普通用户
     *                          日期：
     */
    public int updateBackgroundService(BackgroundService backgroundService) {
        return backgroundServiceMapper.update(backgroundService, new QueryWrapper<BackgroundService>().lambda().eq(BackgroundService::getId, backgroundService.getId()));
    }
}
