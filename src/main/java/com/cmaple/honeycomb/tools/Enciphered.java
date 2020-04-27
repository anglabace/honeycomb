package com.cmaple.honeycomb.tools;

/**
 * 类名：加密处理类 - Enciphered
 * 功能描述： 可以调用相应的函数进行数据加密操作；本类中使用单例模式极大的节约了系统的资源开支；
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：无
 * 创建人：CMAPLE
 * 创建日期：2018-09-30
 * 修改人：
 * 级别：NULL
 * 修改日期：
 */
public class Enciphered {


    //私有化的构造函数
    private Enciphered() {
    }

    //内部类实现实例的创建
    private static class EncipheredInternal {
        private static Enciphered enciphered = new Enciphered();
    }

    //重写readResolve()方法，防止序列化及反序列化破坏单利模式
    private Object readResolve() {
        return EncipheredInternal.enciphered;
    }

    //返回实例的方法
    public static Enciphered getEnciphered() {
        return EncipheredInternal.enciphered;
    }

    /**
     * 函数名：加密函数-将身份证号码进行加密 - idCardEncoder（）
     * 功能描述： 将身份证号码进行加密，加密方式：将固定位数转化为*
     * 输入参数：<按照参数定义顺序>
     *
     * @param idcard String类型字符串
     *               返回值：String
     *               异    常：无
     *               创建人：CMAPLE
     *               创建日期：2018-09-30
     *               修改人：
     *               级别：NULL
     *               修改日期：
     */
    public String idCardEncoder(String idcard) {
        //判断传入字符是否为空或者为null
        if (null == idcard || "".equals(idcard)) {
            return "";
        }
        String s = null;
        try {
            s = idcard.replaceAll("(?<=[\\d]{6})\\d(?=[\\d]{4})", "*");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    /**
     * 函数名：加密函数-电话号码进行加密 - telephonenumberEncoder（）
     * 功能描述： 电话号码进行加密，加密方式：将固定位数转化为*
     * 输入参数：<按照参数定义顺序>
     *
     * @param telephonenumber String类型字符串
     *                        返回值：String
     *                        异    常：无
     *                        创建人：CMAPLE
     *                        创建日期：2018-09-30
     *                        修改人：
     *                        级别：NULL
     *                        修改日期：
     */
    public String telephonenumberEncoder(String telephonenumber) {
        //判断传入字符是否为空或者为null
        if (null == telephonenumber || "".equals(telephonenumber)) {
            return "";
        }
        String s = null;
        try {
            s = telephonenumber.replaceAll("(?<=[\\d]{3})\\d(?=[\\d]{4})", "*");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }


}
