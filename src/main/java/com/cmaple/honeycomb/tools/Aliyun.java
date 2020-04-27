package com.cmaple.honeycomb.tools;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.cmaple.honeycomb.model.AliyunIDName;
import com.cmaple.honeycomb.model.AliyunSMS;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 类名：阿系工具类 - AliTool
 * 功能描述： 阿里云工具类；进行阿里云相关接口调用；
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：无
 * 创建人：CMAPLE
 * 创建日期：2019-09-24
 * 修改人：
 * 级别：NULL
 * 修改日期：
 */
public class Aliyun {

    //私有化的构造函数
    private Aliyun() {
    }

    //内部类实现实例的创建
    private static class AliyunInternal {
        private static Aliyun aliTool = new Aliyun();
    }

    //重写readResolve()方法，防止序列化及反序列化破坏单利模式
    private Object readResolve() {
        return AliyunInternal.aliTool;
    }

    //返回实例的方法
    public static Aliyun getAliyun() {
        return AliyunInternal.aliTool;
    }

    /**
     * 函数名：阿里云工具函数 - 云盾身份认证（二要素） - aliyun_Idcard_Name（）
     * 功能描述： 云盾身份认证（二要素）,验证姓名，身份证是否一致
     * 输入参数：<按照参数定义顺序>
     *
     * @param name   String类型字符串
     * @param idcard String类型字符串
     *               返回值：String
     *               异    常：无
     *               创建人：CMAPLE
     *               创建日期：2019-09-25
     *               修改人：
     *               级别：NULL
     *               修改日期：
     */
    public Map<String, String> aliyun_Idcard_Name(String name, String idcard, String appcode, String userid, String verifyKey, String host, String path, String method) {
        Map<String, String> map = new HashMap<String, String>();
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("__userId", userid);
        //querys.put("customerID", "customerID");
        querys.put("identifyNum", idcard);
        querys.put("userName", name);
        querys.put("verifyKey", verifyKey);
        try {
            //验证请求
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            //获取response的body
            AliyunIDName aliyunIDName = JSON.parseObject(EntityUtils.toString(response.getEntity()), AliyunIDName.class);
            if (200 == aliyunIDName.getCode()) {
                if (0 == aliyunIDName.getValue().getBizCode()) {
                    map.put("RTCODE", "success");
                    map.put("RTMSG", "实名认证成功！");
                    map.put("RTDATA", aliyunIDName.getCode() + " - " + aliyunIDName.getMessage());
                } else {
                    map.put("RTCODE", "error");
                    map.put("RTMSG", "实名认证失败！");
                    map.put("RTDATA", aliyunIDName.getCode() + " - " + aliyunIDName.getValue().getMessage());
                }
            } else {
                String msg = "";
                if (403 == aliyunIDName.getCode()) {
                    msg = " 无权限操作！请联系管理员";
                }
                if (500 == aliyunIDName.getCode()) {
                    msg = " 内部服务器错误，请重新验证！";
                }
                map.put("RTCODE", "error");
                map.put("RTMSG", "实名认证失败！" + msg);
                map.put("RTDATA", aliyunIDName.getCode() + " - " + aliyunIDName.getMessage());
            }
        } catch (Exception e) {
            //拼接返回信息
            map.put("RTCODE", "error");
            map.put("RTMSG", "实名认证异常，验证过程出现异常，请联系管理员进行处理！");
            map.put("RTDATA", e.getMessage());
            return map;
        }
        return map;
    }

    /**
     * 函数名：阿里云工具函数 - 短信服务 - aliyun_SendSms（）
     * 功能描述： 短信服务，发送短信进行信息通知
     * 输入参数：<按照参数定义顺序>
     *
     * @param PhoneNumbers String类型字符串
     * @param SignName     String类型字符串
     * @param TemplateCode String类型字符串
     * @param accessKeyId  String类型字符串
     * @param accessSecret String类型字符串
     *                     返回值：String
     *                     异    常：无
     *                     创建人：CMAPLE
     *                     创建日期：2020-03-05
     *                     修改人：
     *                     级别：NULL
     *                     修改日期：
     */
    public Map<String, Object> aliyun_SendSms(String PhoneNumbers, String code, String SignName, String TemplateCode, String accessKeyId, String accessSecret) {
        Map<String, Object> map = new HashMap<String, Object>();
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessSecret);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");//FormatTime.getFormatTime().formatYMDToString(new Date())
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", PhoneNumbers);
        request.putQueryParameter("SignName", SignName);
        request.putQueryParameter("TemplateCode", TemplateCode);
        request.putQueryParameter("TemplateParam", "{\"code\":\"" + code + "\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);

            AliyunSMS aliyunSMS = JSON.parseObject(response.getData(), AliyunSMS.class);
            if ("OK".equals(aliyunSMS.getCode())) {
                map.put("RTCODE", "success");
                map.put("RTMSG", "给用户：" + PhoneNumbers + " 发送验证码成功！");
                map.put("RTDATA", "回执信息：" + aliyunSMS.getBizId() + "/" + aliyunSMS.getMessage() + "/" + aliyunSMS.getRequestId());
            } else {
                map.put("RTCODE", "error");
                map.put("RTMSG", "给用户: " + PhoneNumbers + " 发送验证码失败！");
                map.put("RTDATA", "回执信息：" + aliyunSMS.getBizId() + "/" + aliyunSMS.getMessage() + "/" + aliyunSMS.getRequestId());
            }
        } catch (ServerException e) {
            map.put("RTCODE", "error");
            map.put("RTMSG", "给用户: " + PhoneNumbers + " 发送验证码失败！");
            map.put("RTDATA", e.getMessage());
            e.printStackTrace();
        } catch (ClientException e) {
            map.put("RTCODE", "error");
            map.put("RTMSG", "给用户: " + PhoneNumbers + " 发送验证码失败！");
            map.put("RTDATA", e.getMessage());
            e.printStackTrace();
        }
        return map;
    }
}
