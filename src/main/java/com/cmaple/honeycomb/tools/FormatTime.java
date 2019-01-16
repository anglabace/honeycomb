package com.cmaple.honeycomb.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 类名：格式化时间类 - FormatTime
 * 功能描述： 可以获取指定时间的指定格式的字符串；本类中使用单例模式极大的节约了系统的资源开支；
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：无
 * 创建人：CMAPLE
 * 创建日期：2018-10-02
 * 修改人：
 * 级别：NULL
 * 修改日期：
 */
public class FormatTime {


    //私有化构造函数
    private FormatTime() {
    }

    //内部类实现实例的创建(用于延迟加载)
    private static class FormatTimeInternal {
        private static FormatTime formatTime = new FormatTime();
        //yyyy-MM-dd格式化类型
        private static SimpleDateFormat FAM_YMD = new SimpleDateFormat("yyyyMMdd");
        //HH-mm:ss格式化类型
        private static SimpleDateFormat FA_HMS = new SimpleDateFormat("HHmmss");
        ////HH-mm:ss:ms格式化类型
        private static SimpleDateFormat FA_HMSSSS = new SimpleDateFormat("HHmmssSSS");
    }

    //重写readResolve()方法，防止序列化及反序列化破坏单利模式
    private Object readResolve() {
        return FormatTimeInternal.formatTime;
    }

    //返回实例的方法
    public static FormatTime getFormatTime() {
        return FormatTimeInternal.formatTime;
    }

    //返回FAM_YMD实例
    private SimpleDateFormat getFAM_YMD() {
        return FormatTimeInternal.FAM_YMD;
    }

    //返回FA_HMS实例
    private SimpleDateFormat getFA_HMS() {
        return FormatTimeInternal.FA_HMS;
    }

    //返回FA_HMSMS实例
    private SimpleDateFormat getFA_HMSSSS() {
        return FormatTimeInternal.FA_HMSSSS;
    }

    /**
     * 函数名：格式化日期函数-格式化传入时间为yyyymmdd当前格式 - formatYMDToString（）
     * 功能描述：格式化传入时间为yyyymmdd当前格式
     * 输入参数：<按照参数定义顺序>
     * <p>
     * 返回值：String
     * 异    常：无
     * 创建人：CMAPLE
     * 创建日期：2018-10-02
     * 修改人：
     * 级别：NULL
     * 修改日期：
     */
    public String formatYMDToString() {
        return getFAM_YMD().format(new Date());
    }

    /**
     * 函数名：格式化日期函数-格式化传入时间为HHmmss当前格式 - formatHMSToString（）
     * 功能描述：格式化传入时间为HHmmss当前格式
     * 输入参数：<按照参数定义顺序>
     * <p>
     * 返回值：String
     * 异    常：无
     * 创建人：CMAPLE
     * 创建日期：2018-10-02
     * 修改人：
     * 级别：NULL
     * 修改日期：
     */
    public String formatHMSToString() {
        return getFA_HMS().format(new Date());
    }

    /**
     * 函数名：格式化日期函数-格式化传入时间为HHmmssms当前格式 - formatHMSMSToString（）
     * 功能描述：格式化传入时间为HHmmssms当前格式
     * 输入参数：<按照参数定义顺序>
     * <p>
     * 返回值：String
     * 异    常：无
     * 创建人：CMAPLE
     * 创建日期：2018-10-08
     * 修改人：
     * 级别：NULL
     * 修改日期：
     */
    public String formatHMSMSToString() {
        return getFA_HMSSSS().format(new Date());
    }
}
