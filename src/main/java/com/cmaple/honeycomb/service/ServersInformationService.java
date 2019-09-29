package com.cmaple.honeycomb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cmaple.honeycomb.mapper.ServersInformationMapper;
import com.cmaple.honeycomb.model.ServersInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 类名：服务器详细信息功能类 - ServersInformationService
 * 功能描述：维护服务器详细信息功能类相应的功能
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：无
 * 创建人：cmaple
 * 创建日期：2019-09-29
 * 修改人：
 * 级别：NULL
 * 修改日期：
 */
@Service
public class ServersInformationService {
    //引入服务器详细信息接口
    @Autowired
    private ServersInformationMapper serversInformationMapper;

    /**
     * 函数名：查询函数-查询服务器信息并返回所有的服务器信息数据 - getServersInformations（）
     * 功能描述： 查询服务器信息并返回所有的服务器信息数据
     * <p>
     * 返回值：int
     * 异    常：无
     * 创建人：CMAPLE
     * 日期：2019-09-29
     * 修改人：
     * 级别：普通用户
     * 日期：
     */
    public List<ServersInformation> getServersInformations() {
        return serversInformationMapper.selectList(null);
    }

    /**
     * 函数名：插入函数-增加信息的服务器资源 - insertServersInformation（）
     * 功能描述： 查询服务器信息并返回所有的服务器信息数据
     * <p>
     * 返回值：int
     * 异    常：无
     * 创建人：CMAPLE
     * 日期：2019-09-29
     * 修改人：
     * 级别：普通用户
     * 日期：
     */
    public int insertServersInformation(ServersInformation serversInformation) {
        return serversInformationMapper.insert(serversInformation);
    }

    /**
     * 函数名：修改函数-修改指定id的服务器信息 - updateServersInformation（）
     * 功能描述： 修改指定id的服务器信息
     * <p>
     * 返回值：int
     * 异    常：无
     * 创建人：CMAPLE
     * 日期：2019-09-29
     * 修改人：
     * 级别：普通用户
     * 日期：
     */
    public int updateServersInformation(ServersInformation serversInformation) {
        return serversInformationMapper.update(serversInformation, new QueryWrapper<ServersInformation>().lambda().eq(ServersInformation::getId, serversInformation.getId()));
    }


}
