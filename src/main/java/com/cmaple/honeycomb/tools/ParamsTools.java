package com.cmaple.honeycomb.tools;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类名：分页处理类 - PageTools
 * 功能描述：使用页数及数量进行分页计算
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：无
 * 创建人：CMAPLE
 * 创建日期：2019-01-18
 * 修改人：
 * 级别：NULL
 * 修改日期：
 */
public class ParamsTools {

    //私有化构造函数
    private ParamsTools() {
    }

    //内部类实现实例的创建(用于延迟加载)
    private static class ParamsToolsInternal {
        private static ParamsTools paramsTools = new ParamsTools();
    }

    //重写readResolve()方法，防止序列化及反序列化破坏单利模式
    private Object readResolve() {
        return ParamsToolsInternal.paramsTools;
    }

    //返回实例的方法
    public static ParamsTools getPageTools() {
        return ParamsToolsInternal.paramsTools;
    }

    /**
     * 函数名：根据传入的页数及分页数量计算分页sql需要的数据 - getPageByNum（）
     * 功能描述：根据传入的页数及分页数量计算分页sql需要的数据
     * 输入参数：<按照参数定义顺序>
     *
     * @param page 页数
     * @param num  数量
     *             返回值：map
     *             异    常：无
     *             创建人：CMAPLE
     *             创建日期：2019-01-18
     *             修改人：
     *             级别：普通用户及以上
     *             修改日期：
     */
    public Integer getPageByNum(int page, int num) {
        return (page - 1) * num;
    }


    /**
     * 函数名：条件包装函数 - getParamsToMap（）
     * 功能描述：根据传入条件将条件进行整理
     * 输入参数：<按照参数定义顺序>
     *
     * @param list 条件列表
     * @param map  条件
     *             返回值：map
     *             异    常：无
     *             创建人：CMAPLE
     *             创建日期：2019-01-18
     *             修改人：CMAPLE
     *             级别：普通用户及以上
     *             修改日期：2019-09-11
     */
    public Map<String, Object> getParamsToMap(List<String> list, Map<String, Object> map) {
        Map<String, Object> returnmap = new HashMap<String, Object>();
        Map<String, Object> params = new HashMap<String, Object>();
        //更新条件列表
        for (int i = 0; i < list.size(); i++) {
            if (!map.containsKey(list.get(i))) {
                params.put(list.get(i), map.get(list.get(i)));
                list.remove(list.get(i));
            }
        }
        returnmap.put("map", params);
        returnmap.put("list", list);
        return returnmap;
    }


}
