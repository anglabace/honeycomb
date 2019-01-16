package com.cmaple.honeycomb.tools;

/**
 * 类名：管理密钥管理类 - ManageKey
 * 功能描述： 可以对管理密钥进行想用的维护；本类中使用单例模式极大的节约了系统的资源开支；
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：无
 * 创建人：CMAPLE
 * 创建日期：2018-10-02
 * 修改人：
 * 级别：NULL
 * 修改日期：
 */
public class ManageKey {
    //私有化构造函数
    private ManageKey() {

    }

    //内部类进行实例维护
    private static class ManageKeyInternal {
        private static ManageKey manageKey = new ManageKey();
    }

    //重写readResolve()方法，防止序列化及反序列化破坏单利模式
    private Object readResolve() {
        return ManageKeyInternal.manageKey;
    }

    //返回实例的方法
    public static ManageKey getManageKey() {
        return ManageKeyInternal.manageKey;
    }

    /**
     * 函数名：密钥管理函数-获取管理密钥 - obtainManageKey（）
     * 功能描述：生成管理密钥
     * 输入参数：<按照参数定义顺序>
     * 返回值：String
     * 异    常：无
     * 创建人：CMAPLE
     * 创建日期：2018-10-11
     * 修改人：
     * 级别：NULL
     * 修改日期：
     */
    public String obtainManageKey() {
        return "SUNYARD-" + RandomData.getRandomData().getRandomData(13) + "-" + FormatTime.getFormatTime().formatYMDToString() + "-" + FormatTime.getFormatTime().formatHMSMSToString();
    }

    /**
     * 函数名：密钥管理函数-生成用户用户端加密的管理密钥串 - checkManageKeys（）
     * 功能描述：对比用户提交的密钥与数据库中存储的密钥是否相同。
     * 输入参数：<按照参数定义顺序>
     *
     * @param mangerkey String类型生成的管理密钥
     * @param username  String类型的用户名
     *                  返回值：boolean
     *                  异    常：无
     *                  创建人：CMAPLE
     *                  创建日期：2018-10-02
     *                  修改人：
     *                  级别：NULL
     *                  修改日期：
     */
    public String getUserMangerKey(String mangerkey, String username) {
        return Enciphered.getEnciphered().stringSHAEncoder(Enciphered.getEnciphered().stringMankeyUserEncoder(mangerkey, username));
    }

    /**
     * 函数名：密钥管理函数-密钥密钥比对函数 - checkManageKeys（）
     * 功能描述：对比用户提交的密钥与数据库中存储的密钥是否相同。
     * 输入参数：<按照参数定义顺序>
     *
     * @param db_key   String类型数据库存放的管理密钥
     * @param user_key String类型的前台传递的管理密钥
     *                 返回值：boolean
     *                 异    常：无
     *                 创建人：CMAPLE
     *                 创建日期：2018-10-02
     *                 修改人：
     *                 级别：NULL
     *                 修改日期：
     */
    public boolean checkManageKeys(String db_key, String username, String user_key) {
        boolean istrue = false;
        if (user_key.equals(getUserMangerKey(db_key, username))) {
            istrue = true;
        }
        return istrue;
    }

}
