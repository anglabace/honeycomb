package com.cmaple.honeycomb.service;

import com.cmaple.honeycomb.mapper.ServiceVersionLogMapper;
import com.cmaple.honeycomb.model.ServiceVersionLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 类名：服务版本日志功能类 - BackgroundServiceService
 * 功能描述：服务版本日志功能类
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：无
 * 创建人：CMAPLE
 * 创建日期：2019-11-11
 * 修改人：
 * 级别：NULL
 * 修改日期：
 */
@Service
public class ServiceVersionLogService {

    //引入映射后台服务接口
    @Autowired
    private ServiceVersionLogMapper serviceVersionLogMapper;

    /**
     * 函数名：根据服务ID号按照时间倒叙排列获取服务版本日志 - getServiceVersionLogById（）
     * 功能描述：根据服务ID号按照时间倒叙排列获取服务版本日志
     * 输入参数：<按照参数定义顺序>
     *
     * @param serviceid int类型的服务ID
     *                  返回值：map
     *                  异    常：无
     *                  创建人：CMAPLE
     *                  创建日期：2019-11-11
     *                  修改人：
     *                  级别：null
     *                  修改日期：
     */
    public List<ServiceVersionLog> getBackgroundServices(int serviceid) {
        return serviceVersionLogMapper.queryServiceVersionLogById(serviceid);
    }

}
