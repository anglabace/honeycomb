package com.cmaple.honeycomb.service;

import com.cmaple.honeycomb.mapper.AnnouncementMapper;
import com.cmaple.honeycomb.model.Announcement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
