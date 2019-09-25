package com.cmaple.honeycomb.tools;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.security.MessageDigest;

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
        private static BASE64Encoder ENCODER = new BASE64Encoder();
        private static BASE64Decoder DECODER = new BASE64Decoder();
    }

    //重写readResolve()方法，防止序列化及反序列化破坏单利模式
    private Object readResolve() {
        return EncipheredInternal.enciphered;
    }

    //返回实例的方法
    public static Enciphered getEnciphered() {
        return EncipheredInternal.enciphered;
    }

    //返回ENCODER实例
    private BASE64Encoder getENCODER() {
        return EncipheredInternal.ENCODER;
    }

    //返回DECODER实例
    private BASE64Decoder getDECODER() {
        return EncipheredInternal.DECODER;
    }

    /**
     * 函数名：加密函数-将指定的字符串进行Base64编码 - stringBase64Enciphered（）
     * 功能描述： 将指定的字符串进行Base64编码，加密方式：Base64（非加密方式，只是一种编码格式）
     * 输入参数：<按照参数定义顺序>
     *
     * @param string String类型字符串
     *               返回值：String
     *               异    常：无
     *               创建人：CMAPLE
     *               创建日期：2018-09-30
     *               修改人：
     *               级别：NULL
     *               修改日期：
     */
    public String stringBase64Encoder(String string) {
        //判断传入字符是否为空或者为null
        if (null == string || "".equals(string)) {
            return "";
        }
        String s = null;
        try {
            s = getENCODER().encode(string.getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
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

    /**
     * 函数名：解密函数-将指定的字符串进行Base64解码 - stringBase64Decoder（）
     * 功能描述： 将指定的字符串进行Base64解码，加密方式：Base64（非加密方式，只是一种编码格式）
     * 输入参数：<按照参数定义顺序>
     *
     * @param string String类型字符串
     *               返回值：String
     *               异    常：无
     *               创建人：CMAPLE
     *               创建日期：2018-10-01
     *               修改人：
     *               级别：NULL
     *               修改日期：
     */
    public String stringBase64Decoder(String string) {
        //判断传入字符是否为空或者为null
        if (null == string || "".equals(string)) {
            return "";
        }
        String s = null;
        try {
            s = new String(getDECODER().decodeBuffer(string), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    /**
     * 函数名：加密函数-将指定的字符串进行SHA编码 - stringSHAEncoder（）
     * 功能描述： 将指定的字符串进行SHA编码，主要用于数据计算散列值。加密方式：SHA - 安全散列算法（非加密方式，只是一种编码格式）
     * 输入参数：<按照参数定义顺序>
     *
     * @param string String类型字符串
     *               返回值：String
     *               异    常：无
     *               创建人：CMAPLE
     *               创建日期：2018-10-02
     *               修改人：
     *               级别：NULL
     *               修改日期：
     */
    public String stringSHAEncoder(String string) {
        //判断传入字符是否为空或者为null
        if (null == string || "".equals(string)) {
            return "";
        }
        //计算散列值
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA");
            sha.update(string.getBytes());
            byte[] messageDigest = sha.digest();
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 函数名：加密函数-将管理密钥与用户名混合的字符串进行SHA编码 - stringSHAEncoder（）
     * 功能描述： 将管理密钥与用户名混合的字符串进行SHA编码，主要用于数据计算散列值。加密方式：SHA - 安全散列算法（非加密方式，只是一种编码格式）
     * 输入参数：<按照参数定义顺序>
     *
     * @param mangerkey String类型字符串
     * @param username  String类型字符串
     *                  返回值：String
     *                  异    常：无
     *                  创建人：CMAPLE
     *                  创建日期：2018-10-11
     *                  修改人：
     *                  级别：NULL
     *                  修改日期：
     */
    public String stringMankeyUserEncoder(String mangerkey, String username, String usertype) {
        return stringBase64Encoder(mangerkey + username + usertype);
    }

}
