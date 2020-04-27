package com.cmaple.honeycomb.service;

import com.cmaple.honeycomb.mapper.OperationLogMapper;
import com.cmaple.honeycomb.model.OperationLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 类名：日志功能类业务组件 - OperationLogService
 * 功能描述：日志功能类业务组件
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：NULL
 * 创建人：CMAPLE
 * 创建日期：2019-09-09
 */
@Service
public class OperationLogService {

    /**
     * 引入OperationLogMapper
     */
    @Autowired
    private OperationLogMapper operationLogMapper;

    /**
     * 函数名：select函数-根据条件查询日志 - selectByCriteria（）
     * 功能描述： 根据条件查询日志
     * 输入参数：<按照参数定义顺序>
     *
     * @param list   List类型的条件列表
     * @param params Map类型的字段及数值集合
     * @param page   int类型的页数
     * @param num    int类型的数量
     *               返回值：List<OperationLog>
     *               异    常：NULL
     *               创建人：CMAPLE
     *               日期：2019-01-16
     */
    public List<OperationLog> selectByCriteria(List<String> list, Map<String, Object> params, int page, int num) {
        return operationLogMapper.selectByCriteria(list, params, page, num);
    }


    /**
     * 函数名：select函数-根据条件查询日志数量 - selectCountByCriteria（）
     * 功能描述： 根据条件查询用户数量
     *
     * @param list   List类型的条件列表
     * @param params Map类型的字段及数值集合
     *               返回值：int
     *               异    常：NULL
     *               创建人：CMAPLE
     *               日期：2019-01-17
     */
    public int selectCountByCriteria(List<String> list, Map<String, Object> params) {
        return operationLogMapper.selectCountByCriteria(list, params);
    }

    /**
     * 函数名：insert函数-插入日志信息 - insert（）
     * 功能描述： 插入日志信息
     *
     * @param operationLog 日志信息实体类
     *                     返回值：int
     *                     异    常：NULL
     *                     创建人：CMAPLE
     *                     日期：2019-09-25
     */
    public int insert(OperationLog operationLog) {
        return operationLogMapper.insert(operationLog);
    }

}