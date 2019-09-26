package com.cmaple.honeycomb.tools;

import java.util.Random;

/**
 * 类名：管理密钥管理类 - RandomData
 * 功能描述： 生成指定长度及相关格式的随机数的相关类；本类中使用单例模式极大的节约了系统的资源开支；
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：无
 * 创建人：CMAPLE
 * 创建日期：2018-10-10
 * 修改人：
 * 级别：NULL
 * 修改日期：
 */
public class RandomData {
    //随机数参数
    private static char ch[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G',
            'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b',
            'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
            'x', 'y', 'z', '0', '1'};

    private static char nh[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8',
            '9', '0', '1', '2', '3'};

    //私有化构造函数
    private RandomData() {
    }

    //内部类进行实例维护
    private static class RandomDataInternal {
        private static RandomData randomData = new RandomData();
    }

    //重写readResolve()方法，防止序列化及反序列化破坏单利模式
    private Object readResolve() {
        return RandomDataInternal.randomData;
    }

    //返回实例的方法
    public static RandomData getRandomData() {
        return RandomDataInternal.randomData;
    }

    /**
     * 函数名：随机数函数-获取随机数 - getRandomCHData（）
     * 功能描述：
     * 输入参数：<按照参数定义顺序>
     *
     * @param datalen int类型的数据长度
     *                返回值：String
     *                异    常：无
     *                创建人：CMAPLE
     *                创建日期：2018-10-10
     *                修改人：
     *                级别：NULL
     *                修改日期：
     */
    public String getRandomCHData(int datalen) {
        String res = "";
        if (0 < datalen) {
            Random random = new Random();
            int index = 0;
            char[] temp = new char[datalen];
            int num = random.nextInt();
            for (int i = 0; i < datalen % 5; i++) {
                temp[index++] = ch[num & 63];//取后面六位，记得对应的二进制是以补码形式存在的。
                // 为什么要右移6位？因为数组里面一共有64个有效字符。为什么要除5取余？因为一个int型要用4个字节表示，也就是32位。
                num >>= 6;//63的二进制为:111111
            }
            for (int i = 0; i < datalen / 5; i++) {
                num = random.nextInt();
                for (int j = 0; j < 5; j++) {
                    temp[index++] = ch[num & 63];
                    num >>= 6;
                }
            }
            res = new String(temp, 0, datalen);
        } else {
            res = "";
        }
        return res;
    }

    /**
     * 函数名：随机数函数-获取随机数 - getRandomCHData（）
     * 功能描述：
     * 输入参数：<按照参数定义顺序>
     *
     * @param datalen int类型的数据长度
     *                返回值：String
     *                异    常：无
     *                创建人：CMAPLE
     *                创建日期：2018-10-10
     *                修改人：
     *                级别：NULL
     *                修改日期：
     */
    public String getRandomNHData(int datalen) {
        String res = "";
        if (0 < datalen) {
            Random random = new Random();
            int index = 0;
            char[] temp = new char[datalen];
            int num = random.nextInt();
            for (int i = 0; i < datalen % 5; i++) {
                temp[index++] = nh[num & 63];//取后面六位，记得对应的二进制是以补码形式存在的。
                // 为什么要右移6位？因为数组里面一共有64个有效字符。为什么要除5取余？因为一个int型要用4个字节表示，也就是32位。
                num >>= 6;//63的二进制为:111111
            }
            for (int i = 0; i < datalen / 5; i++) {
                num = random.nextInt();
                for (int j = 0; j < 5; j++) {
                    temp[index++] = nh[num & 63];
                    num >>= 6;
                }
            }
            res = new String(temp, 0, datalen);
        } else {
            res = "";
        }
        return res;
    }


}
