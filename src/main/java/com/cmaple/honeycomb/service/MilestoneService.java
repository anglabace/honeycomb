package com.cmaple.honeycomb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cmaple.honeycomb.mapper.MilestoneMapper;
import com.cmaple.honeycomb.model.Milestone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 类名：里程碑服务功能类 - MilestoneService
 * 功能描述：维护里程碑管理相应的功能
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：无
 * 创建人：cmaple
 * 创建日期：2019-11-19
 * 修改人：
 * 级别：NULL
 * 修改日期：
 */
@Service
public class MilestoneService {
    //引入用户映射接口
    @Autowired
    private MilestoneMapper milestoneMapper;

    /**
     * 函数名：查询函数-根据ID号查询里程碑内容 - queryMilestoneById（）
     * 功能描述： 根据ID号查询里程碑内容
     * 输入参数：<按照参数定义顺序>
     *
     * @param id int类型的公告编号
     *           返回值：int
     *           异    常：无
     *           创建人：CMAPLE
     *           日期：2019-11-19
     *           修改人：
     *           日期：
     */
    public Milestone queryMilestoneById(int id) {
        return milestoneMapper.selectById(id);
    }

    /**
     * 函数名：查询函数-根据条件查询里程碑 - queryMilestoneByParams（）
     * 功能描述： 根据条件查询里程碑
     * 输入参数：<按照参数定义顺序>
     *
     * @param list   条件列表
     * @param params 字段及数值集合
     * @param page   分页查询PAGE条件
     * @param num    分页查询NUM条件
     *               返回值：List<Milestone>
     *               异    常：无
     *               创建人：CMAPLE
     *               日期：2019-11-19
     *               修改人：
     *               日期：
     */
    public List<Milestone> queryMilestoneByParams(List<String> list, Map<String, Object> params, int page, int num) {
        return milestoneMapper.queryMilestoneByParams(list, params, page, num);
    }

    /**
     * 函数名：查询函数-主页查询里程碑，只查看日期倒叙的前四条数据 - queryMilestoneAtHome（）
     * 功能描述： 主页查询里程碑，只查看日期倒叙的前四条数据
     * 输入参数：<按照参数定义顺序>
     * <p>
     * 返回值：List<Milestone>
     * 异    常：无
     * 创建人：CMAPLE
     * 日期：2019-11-19
     * 修改人：
     * 日期：
     */
    public List<Milestone> queryMilestoneAtHome() {
        return milestoneMapper.queryMilestoneAtHome();
    }

    /**
     * 函数名：查询函数-查询所有里程碑，按照倒叙排列 - queryMilestoneDescOrderBy（）
     * 功能描述：查询所有里程碑，按照倒叙排列
     * 输入参数：<按照参数定义顺序>
     * <p>
     * 返回值：List<Milestone>
     * 异    常：无
     * 创建人：CMAPLE
     * 日期：2019-11-19
     * 修改人：
     * 日期：
     */
    public List<Milestone> queryMilestoneDescOrderBy() {
        return milestoneMapper.queryMilestoneDescOrderBy();
    }

    /**
     * 函数名：插入函数-插入里程碑信息 - insertMilestone（）
     * 功能描述： 插入里程碑信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param milestone 里程碑信息实体类
     *                  <p>
     *                  返回值：int
     *                  异    常：无
     *                  创建人：CMAPLE
     *                  日期：2019-11-19
     *                  修改人：
     *                  日期：
     */
    public int insertMilestone(Milestone milestone) {
        return milestoneMapper.insert(milestone);
    }

    /**
     * 函数名：更新函数-更新里程碑信息 - updateMilestone（）
     * 功能描述： 更新里程碑信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param milestone 里程碑信息实体类
     *                  <p>
     *                  返回值：int
     *                  异    常：无
     *                  创建人：CMAPLE
     *                  日期：2019-11-19
     *                  修改人：
     *                  日期：
     */
    public int updateMilestone(Milestone milestone) {
        return milestoneMapper.update(milestone, new QueryWrapper<Milestone>().lambda().eq(Milestone::getId, milestone.getId()));
    }

    /**
     * 函数名：删除函数-根据ID删除里程碑信息 - delMilestone（）
     * 功能描述： 根据ID删除里程碑信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param id 里程碑id号
     *           <p>
     *           返回值：int
     *           异    常：无
     *           创建人：CMAPLE
     *           日期：2019-11-19
     *           修改人：
     *           日期：
     */
    public int delMilestone(int id) {
        return milestoneMapper.deleteById(id);
    }
}
