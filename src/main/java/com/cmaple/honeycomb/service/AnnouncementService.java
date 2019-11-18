package com.cmaple.honeycomb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cmaple.honeycomb.mapper.AnnouncementMapper;
import com.cmaple.honeycomb.model.Announcement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 类名：公告服务功能类 - AnnouncementService
 * 功能描述：维护公告管理相应的功能
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：无
 * 创建人：cmaple
 * 创建日期：2019-11-11
 * 修改人：
 * 级别：NULL
 * 修改日期：
 */
@Service
public class AnnouncementService {
    //引入用户映射接口
    @Autowired
    private AnnouncementMapper announcementMapper;

    /**
     * 函数名：查询函数-根据ID号查询公告内容 - getAnnouncementById（）
     * 功能描述： 根据ID号查询公告内容
     * 输入参数：<按照参数定义顺序>
     *
     * @param id int类型的公告编号
     *           返回值：int
     *           异    常：无
     *           创建人：CMAPLE
     *           日期：2019-11-11
     *           修改人：
     *           日期：
     */
    public Announcement getAnnouncementById(int id) {
        return announcementMapper.selectById(id);
    }

    /**
     * 函数名：查询函数-根据条件查询公告 - getAnnouncementByParams（）
     * 功能描述： 根据条件查询公告
     * 输入参数：<按照参数定义顺序>
     *
     * @param list   条件列表
     * @param params 字段及数值集合
     * @param page   分页查询PAGE条件
     * @param num    分页查询NUM条件
     *               返回值：List<Announcement>
     *               异    常：无
     *               创建人：CMAPLE
     *               日期：2019-11-18
     *               修改人：
     *               日期：
     */
    public List<Announcement> getAnnouncementByParams(List<String> list, Map<String, Object> params, int page, int num) {
        return announcementMapper.queryAnnouncementByParams(list, params, page, num);
    }

    /**
     * 函数名：查询函数-根据条件查询公告 - getAnnouncementAtHome（）
     * 功能描述： 根据条件查询公告
     * 输入参数：<按照参数定义顺序>
     * <p>
     * 返回值：List<Announcement>
     * 异    常：无
     * 创建人：CMAPLE
     * 日期：2019-11-18
     * 修改人：
     * 日期：
     */
    public List<Announcement> getAnnouncementAtHome() {
        return announcementMapper.queryAnnouncementAtHome();
    }

    /**
     * 函数名：插入函数-插入公告信息 - insertAnnouncement（）
     * 功能描述： 插入公告信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param announcement 公告信息实体类
     *                     <p>
     *                     返回值：int
     *                     异    常：无
     *                     创建人：CMAPLE
     *                     日期：2019-11-18
     *                     修改人：
     *                     日期：
     */
    public int insertAnnouncement(Announcement announcement) {
        return announcementMapper.insert(announcement);
    }

    /**
     * 函数名：更新函数-更新公告信息 - updateAnnouncement（）
     * 功能描述： 更新公告信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param announcement 公告信息实体类
     *                     <p>
     *                     返回值：int
     *                     异    常：无
     *                     创建人：CMAPLE
     *                     日期：2019-11-18
     *                     修改人：
     *                     日期：
     */
    public int updateAnnouncement(Announcement announcement) {
        return announcementMapper.update(announcement, new QueryWrapper<Announcement>().lambda().eq(Announcement::getId, announcement.getId()));
    }

    /**
     * 函数名：删除函数-根据ID删除公告信息 - delAnnouncement（）
     * 功能描述： 根据ID删除公告信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param id 公告id号
     *           <p>
     *           返回值：int
     *           异    常：无
     *           创建人：CMAPLE
     *           日期：2019-11-18
     *           修改人：
     *           日期：
     */
    public int delAnnouncement(int id) {
        return announcementMapper.deleteById(id);
    }

}
