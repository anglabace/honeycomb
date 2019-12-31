package com.cmaple.honeycomb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cmaple.honeycomb.mapper.MilestoneMapper;
import com.cmaple.honeycomb.model.Milestone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 类名：里程碑服务功能类业务组件 - MilestoneService
 * 功能描述：里程碑服务功能类业务组件
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：NULL
 * 创建人：CMAPLE
 * 创建日期：2019-11-19
 */
@Service
public class MilestoneService {
    /**
     * 引入MilestoneMapper
     */
    @Autowired
    private MilestoneMapper milestoneMapper;

    /**
     * 函数名：select函数-根据条件查询里程碑列表 - selectByCriteria（）
     * 功能描述： 根据条件查询里程碑列表
     * 输入参数：<按照参数定义顺序>
     *
     * @param list   List类型的条件列表
     * @param params Map类型的字段及数值集合
     * @param page   int类型的页数
     * @param num    int类型的数量
     *               返回值：List<Milestone>
     *               异    常：NULL
     *               创建人：CMAPLE
     *               日期：2019-11-19
     */
    public List<Milestone> selectByCriteria(List<String> list, Map<String, Object> params, int page, int num) {
        return milestoneMapper.selectByCriteria(list, params, page, num);
    }

    /**
     * 函数名：select函数-根据条件查询里程碑总数 - selectCountByCriteria（）
     * 功能描述： 根据条件查询里程碑总数
     * 输入参数：<按照参数定义顺序>
     *
     * @param list   List类型的条件列表
     * @param params Map类型的字段及数值集合
     *               返回值：int
     *               异    常：无
     *               创建人：CMAPLE
     *               日期：2019-12-31
     */
    public int selectCountByCriteria(List<String> list, Map<String, Object> params) {
        return milestoneMapper.selectCountByCriteria(list, params);
    }

    /**
     * 函数名：查询函数-主页查询里程碑列表 - selectAtHomePage（）
     * 功能描述： 主页查询里程碑列表
     * 输入参数：<按照参数定义顺序>
     * <p>
     * 返回值：List<Milestone>
     * 异    常：无
     * 创建人：CMAPLE
     * 日期：2019-11-19
     */
    public List<Milestone> selectAtHomePage() {
        return milestoneMapper.selectAtHomePage();
    }

    /**
     * 函数名：查询函数-按照时间倒叙查询里程碑信息 - selectDescOrderByTime（）
     * 功能描述：按照时间倒叙查询里程碑信息
     * 输入参数：<按照参数定义顺序>
     * 返回值：List<Milestone>
     * 异    常：无
     * 创建人：CMAPLE
     * 日期：2019-11-19
     */
    public List<Milestone> selectDescOrderByTime() {
        return milestoneMapper.selectDescOrderByTime();
    }

    /**
     * 函数名：select函数-根据ID号查询里程碑内容 - selectById（）
     * 功能描述： 根据ID号查询里程碑内容
     * 输入参数：<按照参数定义顺序>
     *
     * @param id int类型的公告编号
     *           返回值：int
     *           异    常：NULL
     *           创建人：CMAPLE
     *           日期：2019-11-19
     */
    public Milestone selectById(int id) {
        return milestoneMapper.selectById(id);
    }

    /**
     * 函数名：insert函数-插入里程碑信息 - insert（）
     * 功能描述： 插入里程碑信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param milestone 里程碑信息实体类
     *                  返回值：int
     *                  异    常：无
     *                  创建人：CMAPLE
     *                  日期：2019-11-19
     */
    public int insert(Milestone milestone) {
        return milestoneMapper.insert(milestone);
    }

    /**
     * 函数名：update函数-更新里程碑信息 - update（）
     * 功能描述： 更新里程碑信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param milestone 里程碑信息实体类
     *                  返回值：int
     *                  异    常：无
     *                  创建人：CMAPLE
     *                  日期：2019-11-19
     */
    public int update(Milestone milestone) {
        return milestoneMapper.update(milestone, new QueryWrapper<Milestone>().lambda().eq(Milestone::getId, milestone.getId()));
    }

    /**
     * 函数名：delete函数-根据ID删除里程碑信息 - deleteById（）
     * 功能描述： 根据ID删除里程碑信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param id 里程碑id号
     *           返回值：int
     *           异    常：无
     *           创建人：CMAPLE
     *           日期：2019-11-19
     */
    public int deleteById(int id) {
        return milestoneMapper.deleteById(id);
    }
}
