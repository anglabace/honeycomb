package com.cmaple.honeycomb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cmaple.honeycomb.mapper.StaticResourcesMapper;
import com.cmaple.honeycomb.model.StaticResources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 类名：日志功能服务类 - OperationLogService
 * 功能描述：维护日志的相应的功能
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：无
 * 创建人：cmaple
 * 创建日期：2019-09-26
 * 修改人：
 * 级别：NULL
 * 修改日期：
 */
@Service
public class StaticResourcesService {

    //引入用户映射接口
    @Autowired
    private StaticResourcesMapper staticResourcesMapper;

    /**
     * 函数名：查询函数-根据条件查询静态资源 - queryStaticResourcesByParams（）
     * 功能描述： 根据条件查询静态资源
     * 输入参数：<按照参数定义顺序>
     *
     * @param list   list类型的条件列表
     * @param params Map类型的条件
     * @param page   int类型的页数
     * @param num    int类型每页需要展示的条数
     *               返回值：List
     *               异    常：无
     *               创建人：CMAPLE
     *               日期：2019-09-26
     *               修改人：
     *               级别：普通用户
     *               日期：
     */
    public List<StaticResources> queryStaticResourcesByParams(List<String> list, Map<String, Object> params, int page, int num) {
        return staticResourcesMapper.queryStaticResourcesByParams(list, params, page, num);
    }

    /**
     * 函数名：插入函数-插入静态资源 - insertStaticResources（）
     * 功能描述： 插入静态资源
     *
     * @param staticResources 静态资源实体类
     *                        返回值：int
     *                        异    常：无
     *                        创建人：CMAPLE
     *                        日期：2019-09-26
     *                        修改人：
     *                        级别：普通用户
     *                        日期：
     */
    public int insertStaticResources(StaticResources staticResources) {
        return staticResourcesMapper.insert(staticResources);
    }

    /**
     * 函数名：删除函数-删除静态资源 - deleteStaticResources（）
     * 功能描述： 根据静态资源实体类主键删除静态资源
     *
     * @param id string类型的id号
     *           返回值：int
     *           异    常：无
     *           创建人：CMAPLE
     *           日期：2019-09-26
     *           修改人：
     *           级别：普通用户
     *           日期：
     */
    public int deleteStaticResources(String id) {
        return staticResourcesMapper.deleteById(id);
    }

    /**
     * 函数名：更新函数-更新静态资源信息 - updateStaticResources（）
     * 功能描述： 更新静态资源信息
     *
     * @param staticResources 静态资源实体类
     *                        返回值：int
     *                        异    常：无
     *                        创建人：CMAPLE
     *                        日期：2019-09-26
     *                        修改人：
     *                        级别：普通用户
     *                        日期：
     */
    public int updateStaticResources(StaticResources staticResources) {
        return staticResourcesMapper.update(staticResources, new QueryWrapper<StaticResources>().lambda().eq(StaticResources::getId, staticResources.getId()));
    }

}
