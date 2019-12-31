package com.cmaple.honeycomb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cmaple.honeycomb.mapper.ServersInformationMapper;
import com.cmaple.honeycomb.model.ServersInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 类名：服务器相关功能类业务组件 - ServersInformationService
 * 功能描述：服务器相关功能类业务组件
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：NULL
 * 创建人：CMAPLE
 * 创建日期：2019-09-29
 */
@Service
public class ServersInformationService {
    /**
     * 引入ServersInformationMapper
     */
    @Autowired
    private ServersInformationMapper serversInformationMapper;

    /**
     * 函数名：insert函数-增加信息的服务器资源 - insert（）
     * 功能描述： 增加信息的服务器资源
     * 输入参数：<按照参数定义顺序>
     *
     * @param serversInformation ServersInformation类型的条件列表
     *                           返回值：int
     *                           异    常：NULL
     *                           创建人：CMAPLE
     *                           日期：2019-09-29
     */
    public int insert(ServersInformation serversInformation) {
        return serversInformationMapper.insert(serversInformation);
    }

    /**
     * 函数名：修改函数-修改指定id的服务器信息 - update（）
     * 功能描述： 修改指定id的服务器信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param serversInformation ServersInformation类型的条件列表
     *                           返回值：int
     *                           异    常：NULL
     *                           创建人：CMAPLE
     *                           日期：2019-09-29
     */
    public int update(ServersInformation serversInformation) {
        return serversInformationMapper.update(serversInformation, new QueryWrapper<ServersInformation>().lambda().eq(ServersInformation::getId, serversInformation.getId()));
    }
}