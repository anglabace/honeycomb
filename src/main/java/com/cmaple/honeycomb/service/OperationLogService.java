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
     * 函数名：查询函数-根据条件查询日志 - getOperationLogByParams（）
     * 功能描述： 根据账户名 查询数据库中是否存在此用户的数量，返回1则表示存在此账户，否则为不存在此账户
     * 输入参数：<按照参数定义顺序>
     *
     * @param list   String类型的用户名
     * @param params String类型的用户名
     * @param page   String类型的用户名
     * @param num    String类型的用户名
     *               返回值：List
     *               异    常：无
     *               创建人：CMAPLE
     *               日期：2019-01-16
     *               修改人：
     *               级别：普通用户
     *               日期：
     */
    public List<OperationLog> getOperationLogByParams(List<String> list, Map<String, Object> params, int page, int num) {
        return operationLogMapper.getOperationLogByParams(list, params, page, num);
    }

    /**
     * 函数名：复杂查询函数-根据条件查询用户数量 - getOperationLogCountByParams（）
     * 功能描述： 根据条件查询用户数量
     *
     * @param list   条件列表
     * @param params 字段及数值集合
     *               返回值：int
     *               异    常：无
     *               创建人：CMAPLE
     *               日期：2019-01-17
     *               修改人：
     *               级别：普通用户
     *               日期：
     */
    public int getOperationLogCountByParams(List<String> list, Map<String, Object> params) {
        return operationLogMapper.getOperationLogCountByParams(list, params);
    }

    /**
     * 函数名：插入函数-插入日志信息 - insertOperationLog（）
     * 功能描述： 插入日志信息
     *
     * @param operationLog 日志信息实体类
     *                     返回值：int
     *                     异    常：无
     *                     创建人：CMAPLE
     *                     日期：2019-09-25
     *                     修改人：
     *                     级别：普通用户
     *                     日期：
     */
    public int insertOperationLog(OperationLog operationLog) {
        return operationLogMapper.insert(operationLog);
    }
    
}
