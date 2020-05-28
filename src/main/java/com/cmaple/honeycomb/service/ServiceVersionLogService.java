package com.cmaple.honeycomb.service;

import com.cmaple.honeycomb.mapper.ServiceVersionLogMapper;
import com.cmaple.honeycomb.model.ServiceVersionLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 类名：服务版本功能类业务组件 - BackgroundServiceService
 * 功能描述：服务版本功能类业务组件
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：NULL
 * 创建人：CMAPLE
 * 创建日期：2019-11-11
 */
@Service
public class ServiceVersionLogService {
    /**
     * 引入ServiceVersionLogMapper
     */
    @Autowired
    private ServiceVersionLogMapper serviceVersionLogMapper;

    /**
     * 函数名：select函数-根据服务id查询服务版本日志 - getBackgroundServices（）
     * 功能描述：根据服务id查询服务版本日志
     * 输入参数：<按照参数定义顺序>
     *
     * @param serviceid int类型的服务ID
     *                  返回值：List<ServiceVersionLog>
     *                  异    常：NULL
     *                  创建人：CMAPLE
     *                  创建日期：2019-11-11
     */
    public List<ServiceVersionLog> getBackgroundServices(String serviceid) {
        return serviceVersionLogMapper.queryServiceVersionLogById(serviceid);
    }

}
