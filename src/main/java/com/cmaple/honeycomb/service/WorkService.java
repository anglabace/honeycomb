package com.cmaple.honeycomb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cmaple.honeycomb.mapper.WorkMapper;
import com.cmaple.honeycomb.model.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 类名：岗位功能类业务组件 - WorkService
 * 功能描述：岗位功能类业务组件
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：NULL
 * 创建人：cmaple
 * 创建日期：2019-12-06
 */
@Service
public class WorkService {

    /**
     * 引入WorkMapper
     */
    @Autowired
    private WorkMapper workMapper;

    /**
     * 函数名：select函数-根据ID号查询岗位信息 - selectById（）
     * 功能描述： 根据ID号查询岗位信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param id int类型的公告编号
     *           返回值：Work
     *           异    常：NULL
     *           创建人：CMAPLE
     *           日期：2019-12-06
     */
    public Work selectById(int id) {
        return workMapper.selectById(id);
    }

    /**
     * 函数名：select函数-根据条件查询岗位信息 - selectByCriteria（）
     * 功能描述： 根据条件查询岗位信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param list   List类型的条件列表
     * @param params Map类型的字段及数值集合
     * @param page   int类型的页数
     * @param num    int类型的数量
     *               返回值：List<Work>
     *               异    常：NULL
     *               创建人：CMAPLE
     *               日期：2019-12-06
     */
    public List<Work> selectByCriteria(List<String> list, Map<String, Object> params, int page, int num) {
        return workMapper.selectByCriteria(list, params, page, num);
    }

    /**
     * 函数名：select函数-根据条件查询岗位总数 - selectCountByCriteria（）
     * 功能描述： 根据条件查询岗位总数
     * 输入参数：<按照参数定义顺序>
     *
     * @param list   List类型的条件列表
     * @param params Map类型的字段及数值集合
     *               返回值：int
     *               异    常：NULL
     *               创建人：CMAPLE
     *               日期：2019-12-31
     */
    public int selectCountByCriteria(List<String> list, Map<String, Object> params) {
        return workMapper.selectCountByCriteria(list, params);
    }

    /**
     * 函数名：select函数-根据时间倒叙查询岗位概要信息 - selectDescOrderByTime（）
     * 功能描述： 根据时间倒叙查询岗位概要信息
     * 输入参数：<按照参数定义顺序>
     * 返回值：List<Work>
     * 异    常：NULL
     * 创建人：CMAPLE
     * 日期：2019-12-06
     */
    public List<Work> selectDescOrderByTime() {
        return workMapper.selectDescOrderByTime();
    }

    /**
     * 函数名：insert函数-创建新的岗位信息 - insert（）
     * 功能描述： 创建新的岗位信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param work Work类型的岗位信息实体类
     *             返回值：int
     *             异    常：NULL
     *             创建人：CMAPLE
     *             日期：2019-12-06
     */
    public int insert(Work work) {
        return workMapper.insert(work);
    }

    /**
     * 函数名：update函数-更新岗位信息 - update（）
     * 功能描述： 更新岗位信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param work Work类型的岗位信息实体类
     *             返回值：int
     *             异    常：NULL
     *             创建人：CMAPLE
     *             日期：2019-12-06
     */
    public int update(Work work) {
        return workMapper.update(work, new QueryWrapper<Work>().lambda().eq(Work::getId, work.getId()));
    }

    /**
     * 函数名：delete函数-根据ID删除岗位信息 - deleteById（）
     * 功能描述： 根据ID删除岗位信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param id int类型的岗位id号
     *           <p>
     *           返回值：int
     *           异    常：NULL
     *           创建人：CMAPLE
     *           日期：2019-12-06
     */
    public int deleteById(int id) {
        return workMapper.deleteById(id);
    }

}
