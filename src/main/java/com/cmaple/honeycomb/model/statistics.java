package com.cmaple.honeycomb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 类名：统计信息实体类 - statistics
 * 功能描述： 统计信息实体类
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：无
 * 创建人：CMAPLE
 * 创建日期：2019-12-09
 * 修改人：
 * 级别：NULL
 * 修改日期：
 */

@Data //给实体提供读写属性, 此外还提供了 equals()、hashCode()、toString() 方法
@Accessors(chain = true)  //chain 若为true，则setter方法返回当前对象()
@NoArgsConstructor  //提供无参构造器
@AllArgsConstructor //提供全参构造器
public class statistics {
    /**
     * 统计信息标题
     * 长度：
     * 是否为null：Y
     **/
    private String[] title;
    /**
     * 统计x轴信息
     * 长度：
     * 是否为null：Y
     **/
    private String[] x_axis;
    /**
     * 表单数据信息
     * 长度：
     * 是否为null：Y
     **/
    private List<statistics_series_data> series_data;
}
