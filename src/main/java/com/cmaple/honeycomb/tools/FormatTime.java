package com.cmaple.honeycomb.tools;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
        //yyyyMMdd格式化类型
        private static SimpleDateFormat FAM_YMD = new SimpleDateFormat("yyyyMMdd");
        //HHmmss格式化类型
        private static SimpleDateFormat FA_HMS = new SimpleDateFormat("HHmmss");
        //HHmmssms格式化类型
        private static SimpleDateFormat FA_HMSSSS = new SimpleDateFormat("HHmmssSSS");
        //yyyy-MM-dd HH:mm:ss格式化类型
        private static SimpleDateFormat FA_YMDHMS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //yyyy-MM-dd格式化类型
        private static SimpleDateFormat FA_Y_M_D = new SimpleDateFormat("yyyy-MM-dd");
        //HH:mm:ss格式化类型
        private static SimpleDateFormat FA_H_M_S = new SimpleDateFormat("HH:mm:ss");
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

    //返回FA_YMDHMS实例
    private SimpleDateFormat getFA_YMDHMS() {
        return FormatTimeInternal.FA_YMDHMS;
    }

    //返回FA_Y_M_D实例
    private SimpleDateFormat getFA_Y_M_D() {
        return FormatTimeInternal.FA_Y_M_D;
    }

    //返回FA_H_M_S实例
    private SimpleDateFormat getFA_H_M_S() {
        return FormatTimeInternal.FA_H_M_S;
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
    public String formatYMDToString(Date date) {
        return getFAM_YMD().format(date);
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
    public String formatHMSToString(Date date) {
        return getFA_HMS().format(date);
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
    public String formatHMSMSToString(Date date) {
        return getFA_HMSSSS().format(date);
    }

    /**
     * 函数名：格式化日期函数-格式化传入时间为yyyy-MM-dd HH:mm:ss当前格式 - formatYMDHMSToString（）
     * 功能描述：格式化传入时间为yyyy-MM-dd HH:mm:ss当前格式
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
    public String formatYMDHMSToString(Date date) {
        return getFA_YMDHMS().format(date);
    }

    /**
     * 函数名：格式化日期函数-格式化传入时间为yyyy-MM-dd当前格式 - formatYMDHMSToString（）
     * 功能描述：格式化传入时间为yyyy-MM-dd当前格式
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
    public String formatY_M_DToString(Date date) {
        return getFA_Y_M_D().format(date);
    }

    /**
     * 函数名：格式化日期函数-格式化传入时间为HH:mm:ss当前格式 - formatH_M_SToString（）
     * 功能描述：格式化传入时间为HH:mm:ss当前格式
     * 输入参数：<按照参数定义顺序>
     * <p>
     * 返回值：String
     * 异    常：无
     * 创建人：CMAPLE
     * 创建日期：2018-10-08
     */
    public String formatH_M_SToString(Date date) {
        return getFA_H_M_S().format(date);
    }

    /**
     * 函数名：格式化日期函数-格式化传入时间为HH:mm:ss当前格式 - formatH_M_SToString（）
     * 功能描述：格式化传入时间为HH:mm:ss当前格式
     * 输入参数：<按照参数定义顺序>
     * <p>
     * 返回值：String
     * 异    常：无
     * 创建人：CMAPLE
     * 创建日期：2018-10-08
     */
    public String formatY_M_DToBefor(int brfor) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -brfor);
        Date time = c.getTime();
        return getFA_Y_M_D().format(time);
    }
}
