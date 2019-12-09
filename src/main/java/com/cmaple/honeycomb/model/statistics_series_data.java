package com.cmaple.honeycomb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 类名：统计信息数据实体类 - statistics_series_data
 * 功能描述： 统计信息数据实体类
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
public class statistics_series_data {
    /**
     * 标题
     * 长度：
     * 是否为null：Y
     **/
    private String name;
    /**
     * 图表类型
     * 长度：
     * 是否为null：Y
     **/
    private String type;
    /**
     * 图标类型数据
     * 长度：
     * 是否为null：Y
     **/
    private String stack;
    /**
     * 是否有填充【null为不填充，''为填充颜色】
     * 长度：
     * 是否为null：Y
     **/
    private String areaStyle;
    /**
     * 数据数组
     * 长度：
     * 是否为null：Y
     **/
    private int[] data;
}
