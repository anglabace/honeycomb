package com.cmaple.honeycomb.tools;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.cmaple.honeycomb.model.User;

/**
 * 类名：TOKEN管理类 - Token
 * 功能描述：服务管理Token类，主要对Token进行产生和销毁
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：无
 * 创建人：CMAPLE
 * 创建日期：2020-03-01
 * 修改人：
 * 级别：NULL
 * 修改日期：
 */
public class Token {

    //私有化构造函数
    private Token() {

    }

    //内部类进行实例维护
    private static class TokenInternal {
        private static Token token = new Token();
    }

    //重写readResolve()方法，防止序列化及反序列化破坏单利模式
    private Object readResolve() {
        return Token.TokenInternal.token;
    }

    //返回实例的方法
    public static Token getTokenExample() {
        return Token.TokenInternal.token;
    }

    /**
     * 函数名：密钥管理函数-根据登录的用户生成Token - getToken（）
     * 功能描述：生成Token
     * 输入参数：<按照参数定义顺序>
     *
     * @param tokenkey String类型的token密钥
     * @param user     User类型的用户信息
     *                 返回值：String
     *                 异    常：无
     *                 创建人：CMAPLE
     *                 创建日期：2020-03-01
     *                 修改人：
     *                 级别：NULL
     *                 修改日期：
     */
    public String getToken(String tokenkey, User user) {
        String token = "";
        token = JWT.create().withAudience(user.getTelephonenumber())
                .sign(Algorithm.HMAC256(tokenkey));
        return token;
    }

}
