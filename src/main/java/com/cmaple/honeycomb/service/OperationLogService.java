package com.cmaple.honeycomb.service;

import com.cmaple.honeycomb.mapper.OperationLogMapper;
import com.cmaple.honeycomb.model.OperationLog;
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
 * 创建日期：2019-09-09
 * 修改人：
 * 级别：NULL
 * 修改日期：
 */
@Service
public class OperationLogService {

    //引入用户映射接口
    @Autowired
    private OperationLogMapper operationLogMapper;

    /**
     * 函数名：查询函数-根据条件查询日志 - informationByTermLog（）
     * 功能描述： 根据账户名 查询数据库中是否存在此用户的数量，返回1则表示存在此账户，否则为不存在此账户
     * 输入参数：<按照参数定义顺序>
     *
     * @param list String类型的用户名
     *             返回值：List
     *             异    常：无
     *             创建人：CMAPLE
     *             日期：2019-01-16
     *             修改人：
     *             级别：普通用户
     *             日期：
     */
    public List<OperationLog> getUsersByParams(List<String> list, Map<String, Object> params, int page, int num) {
        return operationLogMapper.queryOperationLogByParams(list, params, page, num);
    }


}
