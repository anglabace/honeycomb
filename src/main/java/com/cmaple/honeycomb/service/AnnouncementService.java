package com.cmaple.honeycomb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cmaple.honeycomb.mapper.AnnouncementMapper;
import com.cmaple.honeycomb.model.Announcement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 类名：公告服务功能类业务组件 - AnnouncementService
 * 功能描述：公告服务功能类业务组件
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：NULL
 * 创建人：CMAPLE
 * 创建日期：2019-11-11
 */
@Service
public class AnnouncementService {
    /**
     * 引入AnnouncementMapper
     */
    @Autowired
    private AnnouncementMapper announcementMapper;


    /**
     * 函数名：select函数-根据条件查询公告 - selectByCriteria（）
     * 功能描述： 根据条件查询公告
     * 输入参数：<按照参数定义顺序>
     *
     * @param list   List类型的条件列表
     * @param params Map类型的字段及数值集合
     * @param page   int类型的页数
     * @param num    int类型的数量
     *               返回值：List<Announcement>
     *               异    常：无
     *               创建人：CMAPLE
     *               日期：2019-11-18
     */
    public List<Announcement> selectByCriteria(List<String> list, Map<String, Object> params, int page, int num) {
        return announcementMapper.selectByCriteria(list, params, page, num);
    }

    /**
     * 函数名：select函数-根据条件查询公告总数 - selectCountByCriteria（）
     * 功能描述： 根据条件查询公告总数
     * 输入参数：<按照参数定义顺序>
     *
     * @param list   List类型的条件列表
     * @param params Map类型的字段及数值集合
     *               返回值：int
     *               异    常：无
     *               创建人：CMAPLE
     *               日期：2019-12-30
     */
    public int selectCountByCriteria(List<String> list, Map<String, Object> params) {
        return announcementMapper.selectCountByCriteria(list, params);
    }

    /**
     * 函数名：select函数-按照时间倒叙分页查询公告信息 - selectDescOrderByTime（）
     * 功能描述： 按照时间倒叙分页查询公告信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param page int类型的页数
     * @param num  int类型的数量
     *             返回值：List<Announcement>
     *             异    常：无
     *             创建人：CMAPLE
     *             日期：2019-12-24
     */
    public List<Announcement> selectDescOrderByTime(int page, int num) {
        return announcementMapper.selectDescOrderByTime(page, num);
    }

    /**
     * 函数名：select函数-主页查询，只查看日期倒叙的前五条数据 - selectAtHomePage（）
     * 功能描述： 主页查询，只查看日期倒叙的前五条数据
     * 输入参数：<按照参数定义顺序>
     * <p>
     * 返回值：List<Announcement>
     * 异    常：无
     * 创建人：CMAPLE
     * 日期：2019-11-18
     */
    public List<Announcement> selectAtHomePage() {
        return announcementMapper.selectAtHomePage();
    }

    /**
     * 函数名：查询函数-查询公告总数量 - selectCount（）
     * 功能描述： 查询公告总数量
     * 输入参数：<按照参数定义顺序>
     * <p>
     * 返回值：int
     * 异    常：无
     * 创建人：CMAPLE
     * 日期：2019-12-24
     */
    public int selectCount() {
        return announcementMapper.selectCount(null);
    }

    /**
     * 函数名：select函数-根据ID号查询公告信息 - selectById（）
     * 功能描述： 根据ID号查询公告信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param id int类型的公告编号
     *           返回值：int
     *           异    常：无
     *           创建人：CMAPLE
     *           日期：2019-11-11
     */
    public Announcement selectById(int id) {
        return announcementMapper.selectById(id);
    }

    /**
     * 函数名：insert函数-插入公告信息 - insert（）
     * 功能描述： 插入公告信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param announcement 公告信息实体类
     *                     <p>
     *                     返回值：int
     *                     异    常：无
     *                     创建人：CMAPLE
     *                     日期：2019-11-18
     */
    public int insert(Announcement announcement) {
        return announcementMapper.insert(announcement);
    }

    /**
     * 函数名：update函数-更新公告信息 - update（）
     * 功能描述： 更新公告信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param announcement 公告信息实体类
     *                     <p>
     *                     返回值：int
     *                     异    常：无
     *                     创建人：CMAPLE
     *                     日期：2019-11-18
     */
    public int update(Announcement announcement) {
        return announcementMapper.update(announcement, new QueryWrapper<Announcement>().lambda().eq(Announcement::getId, announcement.getId()));
    }

    /**
     * 函数名：delete函数-根据ID删除公告信息 - deleteById（）
     * 功能描述： 根据ID删除公告信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param id 公告id号
     *           <p>
     *           返回值：int
     *           异    常：无
     *           创建人：CMAPLE
     *           日期：2019-11-18
     */
    public int deleteById(int id) {
        return announcementMapper.deleteById(id);
    }

}
