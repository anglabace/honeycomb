package com.cmaple.honeycomb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cmaple.honeycomb.mapper.AdvancedSQL.ServiceVersionLogSQL;
import com.cmaple.honeycomb.model.ServiceVersionLog;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * 类名：mybatis plus 映射后台服务接口 - BackgroundServiceMapper
 * 功能描述：NULL
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：NULL
 * 创建人：CMAPLE
 * 创建日期：2019-11-11
 */
public interface ServiceVersionLogMapper extends BaseMapper<ServiceVersionLog> {

    /**
     * 函数名：select函数-根据id号进行查询 - queryServiceVersionLogById（）
     * 功能描述： 根据id号进行查询
     * 输入参数：<按照参数定义顺序>
     *
     * @param serviceid 条件列表
     *                  返回值：list<ServiceVersionLog>
     *                  异    常：NULL
     *                  创建人：CMAPLE
     *                  日期：2019-11-11
     */
    @SelectProvider(type = ServiceVersionLogSQL.class, method = "queryServiceVersionLogById")
    List<ServiceVersionLog> queryServiceVersionLogById(@Param("serviceid") int serviceid);

}
