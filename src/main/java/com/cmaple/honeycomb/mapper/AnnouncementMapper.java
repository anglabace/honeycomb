package com.cmaple.honeycomb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cmaple.honeycomb.mapper.AdvancedSQL.AnnouncementSQL;
import com.cmaple.honeycomb.model.Announcement;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

/**
 * 类名：mybatis plus 映射公告接口 - AnnouncementMapper
 * 功能描述：
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：无
 * 创建人：CMAPLE
 * 创建日期：2019-11-11
 */
public interface AnnouncementMapper extends BaseMapper<Announcement> {

    /**
     * 函数名：select函数-根据条件查询公告信息- selectByCriteria（）
     * 功能描述： 根据条件查询公告信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param list   List类型的条件列表
     * @param params Map类型的字段及数值集合
     * @param page   int类型的页数
     * @param num    int类型的数量
     *               返回值：list<Announcement>
     *               异    常：无
     *               创建人：CMAPLE
     *               日期：2019-11-18
     */
    @SelectProvider(type = AnnouncementSQL.class, method = "selectByCriteria")
    List<Announcement> selectByCriteria(@Param("list") List<String> list, @Param("params") Map<String, Object> params, @Param("page") int page, @Param("num") int num);

    /**
     * 函数名：select函数-根据条件查询公告总数- selectCountByCriteria（）
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
    @SelectProvider(type = AnnouncementSQL.class, method = "selectCountByCriteria")
    int selectCountByCriteria(@Param("list") List<String> list, @Param("params") Map<String, Object> params);

    /**
     * 函数名：select函数-按照时间倒叙分页查询公告信息- selectDescOrderByTime（）
     * 功能描述： 按照时间倒叙分页查询公告信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param page int类型的页数
     * @param num  int类型的数量
     *             返回值：list<Announcement>
     *             异    常：无
     *             创建人：CMAPLE
     *             日期：2019-12-24
     */
    @SelectProvider(type = AnnouncementSQL.class, method = "selectDescOrderByTime")
    List<Announcement> selectDescOrderByTime(@Param("page") int page, @Param("num") int num);

    /**
     * 函数名：复杂查询函数-主页查询公告列表- selectAtHomePage（）
     * 功能描述： 主页查询公告列表
     * 输入参数：<按照参数定义顺序>
     * <p>
     * 返回值：list<Announcement>
     * 异    常：无
     * 创建人：CMAPLE
     * 日期：2019-11-18
     */
    @SelectProvider(type = AnnouncementSQL.class, method = "selectAtHomePage")
    List<Announcement> selectAtHomePage();
}
