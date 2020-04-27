package com.cmaple.honeycomb.controller;


import com.auth0.jwt.JWT;
import com.cmaple.honeycomb.Interface.PassToken;
import com.cmaple.honeycomb.Interface.UserLoginToken;
import com.cmaple.honeycomb.model.User;
import com.cmaple.honeycomb.service.EmailService;
import com.cmaple.honeycomb.service.UserService;
import com.cmaple.honeycomb.tools.*;
import com.cmaple.honeycomb.util.AliYunPreset;
import com.cmaple.honeycomb.util.ConfigurationFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 类名：用户管理服务控制器 - UserController
 * 功能描述： 用户管理服务控制器
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：NULL
 * 创建人：cmaple
 * 创建日期：2019-01-16
 */
@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * 引入UserService
     */
    @Autowired
    private UserService userService;

    /**
     * 引入ConfigurationFile
     */
    @Autowired
    private ConfigurationFile configurationFile;

    /**
     * 引入AliYunPreset
     */
    @Autowired
    private AliYunPreset aliYunPreset;

    /**
     * 引入EmailTool
     */
    @Autowired
    private EmailService emailService;

    /**
     * 短信验证码存储集合
     */
    public static final Map<String, Object> VER_CODE = new HashMap<String, Object>();

    /**
     * 函数名：登录函数- - login（）
     * 功能描述： 根据账户名 密码查询数据库中是否存在此用户,并返回登录用户的非敏感信息
     * 输入参数：<按照参数定义顺序>
     * ！需要增加短信验证码的接口
     *
     * @param telephonenumber String类型的用户名
     * @param password        String类型的用户密码
     *                        返回值：map
     *                        异    常：NULL
     *                        创建人：CMAPLE
     *                        创建日期：2019-01-16
     */
    @PassToken
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> login(
            @RequestParam(value = "telephonenumber", required = true) String telephonenumber
            , @RequestParam(value = "password", required = true) String password
            , @RequestParam(value = "uip", required = false) String uip
            , @RequestParam(value = "lastplace", required = false) String lastplace
    ) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        //获取用户信息
        User user = userService.selectByTelephonenumber(telephonenumber);
        //判断用户名密码是否匹配
        if (!user.getPassword().equals(password)) {
            //密码错误执行
            if (5 > user.getErrortry()) {
                user.setErrortry(user.getErrortry() + 1);
                userService.update(user);
                map.put("RTCODE", "error");
                map.put("RTMSG", "用户名或密码错误！");
                map.put("RTDATA", null);
            } else {
                //5次锁定账号
                if (!"lock".equals(user.getUseraffairs())) {
                    user.setUseraffairs("lock");
                    userService.update(user);
                }
                map.put("RTCODE", "error");
                map.put("RTMSG", "账户已锁定，解锁邮件已发送到您的绑定邮箱！");
                map.put("RTDATA", null);
            }
            return map;
        } else {
            //密码正确执行
            if (0 != user.getErrortry()) {
                //登录成功重写错误记录数
                user.setErrortry(0);
                userService.update(user);
            }
            User returnuser = new User(user.getId(), null, user.getUsertype(), "normal", user.getUserbalance(), Enciphered.getEnciphered().idCardEncoder(user.getIdcard()), user.getName(), user.getUseraddress(), user.getTelephonenumber(), user.getUseremail(), user.getCreatetime(), user.getUsersign(), user.getPetname(), user.getErrortry(), user.getCommonip(), user.getLastplace(), user.getPermissions());
            //生成token
            String token = Token.getTokenExample().getToken(configurationFile.getCONFIGTOKENKEY(), returnuser);
            //设置返回信息
            map.put("RTCODE", "success");
            map.put("RTMSG", "登录成功！");
            map.put("RTDATA", token);
            //删除强引用，释放相应内存空间，减少内存溢出风险
            user = null;
            //返回消息
            return map;
        }
    }

    /**
     * 函数名：获取登录用户信息 - getUserSession（）
     * 功能描述： 获取登录用户信息，用于初始页面展示及级别控制
     * 输入参数：<按照参数定义顺序>
     * 返回值：map
     * 异    常：NULL
     * 创建人：CMAPLE
     * 创建日期：2019-01-18
     */
    @UserLoginToken
    @RequestMapping(value = "/getUserSession", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> getUserSession(HttpServletRequest httpServletRequest) {
        //设置参数
        Map<String, Object> map = new HashMap<String, Object>();
        String token = httpServletRequest.getHeader("token");
        String telephonenumber = JWT.decode(token).getAudience().get(0);
        //获取用户信息
        User user = userService.selectByTelephonenumber(telephonenumber);
        user.setIdcard(Enciphered.getEnciphered().idCardEncoder(user.getIdcard()));
        user.setTelephonenumber(Enciphered.getEnciphered().telephonenumberEncoder(user.getTelephonenumber()));
        if (null == user) {
            map.put("RTCODE", "error");
            map.put("RTMSG", "获取用户信息失败！账号不存在！");
            map.put("RTDATA", null);
        } else {
            map.put("RTCODE", "success");
            map.put("RTMSG", "获取用户信息成功！");
            map.put("RTDATA", user);
        }
        return map;
    }

    /**
     * 函数名：select函数 - 根据条件获取用户列表 - selectByCriteria（）
     * 功能描述：根据用户名获取相应的用户信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param usertype     String类型的用户类型
     * @param useraffairs  String类型 用户状态
     * @param content      String类型 搜索内容
     * @param timeaxisdate List类型 搜索内容
     *                     返回值：map
     *                     异    常：NULL
     *                     创建人：CMAPLE
     *                     创建日期：2019-01-18
     */
    @UserLoginToken
    @RequestMapping(value = "/selectByCriteria", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> selectByCriteria(
            @RequestParam(value = "usertype", required = true) String usertype
            , @RequestParam(value = "useraffairs", required = true) String useraffairs
            , @RequestParam(value = "content", required = true) String content
            , @RequestParam(value = "timeaxisdate", required = true) List timeaxisdate
            , @RequestParam(value = "page", required = true) int page
            , @RequestParam(value = "num", required = true) int num
    ) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<String> list = new ArrayList<String>();
        Map<String, Object> params = new HashMap<String, Object>();
        Map<String, Object> data = new HashMap<String, Object>();
        //条件整理
        list.add("usertype");
        params.put("usertype", usertype);
        if (!"all".equals(useraffairs)) {
            list.add("useraffairs");
            params.put("useraffairs", useraffairs);
        }
        if (!"".equals(content)) {
            list.add("content");
            params.put("content", content);
        }
        if (0 != timeaxisdate.size()) {
            list.add("timeaxisdate");
            params.put("timeaxisdate", timeaxisdate);
        }
        List<User> returnusers = null;
        int total;
        try {
            //根据条件查询
            returnusers = userService.selectByCriteria(list, params, ParamsTools.getPageTools().getPageByNum(page, num), num);
            total = userService.selectCountByCriteria(list, params);
            //加密电话及身份证，防止信息泄漏
            for (int i = 0; i < returnusers.size(); i++) {
                returnusers.get(i).setTelephonenumber(Enciphered.getEnciphered().telephonenumberEncoder(returnusers.get(i).getTelephonenumber()));
                returnusers.get(i).setIdcard(Enciphered.getEnciphered().idCardEncoder(returnusers.get(i).getIdcard()));
            }
        } catch (Exception e) {
            //报错信息，错误信息插入日志表
            map.put("RTCODE", "error");
            map.put("RTMSG", "获取用户信息失败！");
            map.put("RTDATA", e.getMessage());
            return map;
        }
        data.put("data", returnusers);
        data.put("total", total);
        //返回成功信息
        map.put("RTCODE", "success");
        map.put("RTMSG", "获取用户信息成功！");
        map.put("RTDATA", data);
        return map;
    }

    /**
     * 函数名：insert函数 - 注册用户 - insert（）
     * 功能描述：根据必要条件注册用户
     * 输入参数：<按照参数定义顺序>
     *
     * @param password        String类型的密码
     * @param name            String类型的真实姓名
     * @param idcard          String类型的身份证好吗
     * @param telephonenumber String类型的电话好吗
     * @param petname         String类型的昵称
     * @param commonip        String类型的常用ip
     * @param securitycode    String类型的手机验证码
     * @param lastplace       String类型的最后登录地址
     *                        <p>
     *                        返回值：map
     *                        异    常：NULL
     *                        创建人：CMAPLE
     *                        创建日期：2019-01-18
     */
    @PassToken
    @RequestMapping(value = "/insert", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> insert(
            @RequestParam(value = "password", required = true) String password
            , @RequestParam(value = "name", required = true) String name
            , @RequestParam(value = "idcard", required = true) String idcard
            , @RequestParam(value = "telephonenumber", required = true) String telephonenumber
            , @RequestParam(value = "securitycode", required = true) String securitycode
            , @RequestParam(value = "petname", required = true) String petname
            , @RequestParam(value = "commonip", required = true) String commonip
            , @RequestParam(value = "lastplace", required = true) String lastplace
            , HttpServletRequest request
    ) {
        Map<String, Object> map = new HashMap<String, Object>();
        //检查电话号码是否被注册
        if (null != telephonenumber) {
            if (0 < userService.equalsTelephonenumber(telephonenumber)) {
                map.put("RTCODE", "error");
                map.put("RTMSG", "电话号码【" + telephonenumber + "】已被注册！");
                map.put("RTDATA", null);
                return map;
            }
        }
        //检查身份证号码是否被注册
        if (null != idcard) {
            if (0 < userService.equalsIdcard(idcard)) {
                map.put("RTCODE", "error");
                map.put("RTMSG", "身份证【" + idcard + "】已被注册！");
                map.put("RTDATA", null);
                return map;
            }
        }
        //验证电话短信验证码
        if (!VER_CODE.get(telephonenumber).equals(securitycode)) {
            map.put("RTCODE", "error");
            map.put("RTMSG", "验证码错误,请重新获取验证码！");
            map.put("RTDATA", null);
            VER_CODE.remove(telephonenumber);
            return map;
        }
        //验证实名
        if ("error".equals(Aliyun.getAliyun().aliyun_Idcard_Name(name, idcard, aliYunPreset.getAPPCODE(), aliYunPreset.getUSERID(), aliYunPreset.getVERIFYKEY(), aliYunPreset.getHOST(), aliYunPreset.getPATH(), aliYunPreset.getMETHOD()).get("RTCODE"))) {
            map.put("RTCODE", "error");
            map.put("RTMSG", "实名验证失败，请输入正确的实名信息！");
            map.put("RTDATA", null);
            return map;
        }
        //检查昵称是否重复
        int insertreturn = userService.insert(new User(0, password, "member", "normal", 0.00, idcard, name, null, telephonenumber, null, new Date(), null, petname, 0, commonip, lastplace, "0,0,0,1,1"));
        if (1 == insertreturn) {
            map.put("RTCODE", "success");
            map.put("RTMSG", "用户注册成功！");
            map.put("RTDATA", null);
        } else {
            map.put("RTCODE", "error");
            map.put("RTMSG", "注册用户失败！");
            map.put("RTDATA", null);
        }
        return map;
    }

    /**
     * 函数名：update函数 - 修改用户信息 - updateUser（）
     * 功能描述：根据传入的用户信息条件修改用户信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param id              int类型的用户id
     * @param password        String类型的密码
     * @param usertype        String类型的用户类型
     * @param useraffairs     String类型的用户状态
     * @param userbalance     String类型的用户余额
     * @param name            String类型的真实姓名
     * @param idcard          String类型的身份证好吗
     * @param useraddress     String类型的用户地址
     * @param telephonenumber String类型的电话好吗
     * @param useremail       String类型的电子邮件
     * @param usersign        String类型的签名
     * @param petname         String类型的昵称
     * @param commonip        String类型的常用ip
     * @param lastplace       String类型的最后登录地址
     * @param permissions     String类型的权限列表
     *                        返回值：map
     *                        异    常：NULL
     *                        创建人：CMAPLE
     *                        创建日期：2019-01-18
     */
    @UserLoginToken
    @RequestMapping(value = "/updatetUser", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> updatetUser(
            @RequestParam(value = "id", required = true) int id
            , @RequestParam(value = "password", required = true) String password
            , @RequestParam(value = "usertype", required = true) String usertype
            , @RequestParam(value = "useraffairs", required = false) String useraffairs
            , @RequestParam(value = "userbalance", required = false) double userbalance
            , @RequestParam(value = "name", required = false) String name
            , @RequestParam(value = "idcard", required = false) String idcard
            , @RequestParam(value = "useraddress", required = false) String useraddress
            , @RequestParam(value = "telephonenumber", required = false) String telephonenumber
            , @RequestParam(value = "useremail", required = false) String useremail
            , @RequestParam(value = "usersign", required = false) String usersign
            , @RequestParam(value = "petname", required = false) String petname
            , @RequestParam(value = "commonip", required = false) String commonip
            , @RequestParam(value = "lastplace", required = false) String lastplace
            , @RequestParam(value = "permissions", required = false) String permissions
    ) {
        Map<String, Object> map = new HashMap<String, Object>();
        User user = userService.selectByTelephonenumber(telephonenumber);
        if ("del".equals(useraffairs)) {
            if (1 != userService.update(new User(
                    id                         //用户编号
                    , password                 //用户登录密码
                    , usertype                 //用户类型
                    , useraffairs              //用户状态
                    , userbalance              //用户余额
                    , idcard                   //用户身份证号码
                    , name                     //用户真实姓名
                    , useraddress              //用户地址
                    , telephonenumber          //用户电话
                    , useremail                //用户电子邮箱
                    , user.getCreatetime()     //用户创建时间
                    , usersign                 //用户签名
                    , petname                  //用户昵称
                    , user.getErrortry()    //用户密码连续输入错误次数
                    , commonip                 //常用ip
                    , lastplace                //最后登录地点
                    , permissions              //权限列表
            ))) {
                map.put("RTCODE", "error");
                map.put("RTMSG", "用户删除失败！");
                map.put("RTDATA", null);
                return map;
            }
        } else {
            if (1 != userService.update(new User(
                    id                         //用户编号
                    , password                 //用户登录密码
                    , usertype                 //用户类型
                    , useraffairs              //用户状态
                    , userbalance              //用户余额
                    , idcard                   //用户身份证号码
                    , name                     //用户真实姓名
                    , useraddress              //用户地址
                    , telephonenumber          //用户电话
                    , useremail                //用户电子邮箱
                    , user.getCreatetime()     //用户创建时间
                    , usersign                 //用户签名
                    , petname                  //用户昵称
                    , user.getErrortry()     //用户密码连续输入错误次数
                    , commonip                 //常用ip
                    , lastplace                //最后登录地点
                    , permissions              //权限列表
            ))) {
                map.put("RTCODE", "error");
                map.put("RTMSG", "用户信息更新失败！");
                map.put("RTDATA", null);
                return map;
            }
        }
        map.put("RTCODE", "success");
        map.put("RTMSG", "用户信息更新成功！");
        map.put("RTDATA", null);
        return map;
    }

    /**
     * 函数名：update函数 - 更新用户登录地址信息 - updateUserLoginInfo（）
     * 功能描述：根据传入的用户信息条件修改用户信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param httpServletRequest HttpServletRequest类型的请求
     * @param commonip           String类型的常用ip
     * @param lastplace          String类型的最后登录地址
     *                           返回值：map
     *                           异    常：NULL
     *                           创建人：CMAPLE
     *                           创建日期：2020-02-18
     */
    @UserLoginToken
    @RequestMapping(value = "/updateUserLoginInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> updateUserLoginInfo(
            @RequestParam(value = "commonip", required = false) String commonip
            , @RequestParam(value = "lastplace", required = false) String lastplace
            , HttpServletRequest httpServletRequest
    ) {
        Map<String, Object> map = new HashMap<String, Object>();
        String token = httpServletRequest.getHeader("token");
        String telephonenumber = JWT.decode(token).getAudience().get(0);
        User user = userService.selectByTelephonenumber(telephonenumber);
        //更新登录信息
        if (1 != userService.update(new User(
                user.getId()                         //用户编号
                , user.getPassword()                 //用户登录密码
                , user.getUsertype()                 //用户类型
                , user.getUseraffairs()              //用户状态
                , user.getUserbalance()             //用户余额
                , user.getIdcard()                   //用户身份证号码
                , user.getName()                     //用户真实姓名
                , user.getUseraddress()              //用户地址
                , telephonenumber          //用户电话
                , user.getUseremail()                //用户电子邮箱
                , user.getCreatetime()     //用户创建时间
                , user.getUsersign()                 //用户签名
                , user.getPetname()                  //用户昵称
                , user.getErrortry()    //用户密码连续输入错误次数
                , commonip                 //常用ip
                , lastplace                //最后登录地点
                , user.getPermissions()              //权限列表
        ))) {
            map.put("RTCODE", "error");
            map.put("RTMSG", "用户登录信息更新失败！");
            map.put("RTDATA", null);
            return map;
        }

        map.put("RTCODE", "success");
        map.put("RTMSG", "用户登录信息更新成功！");
        map.put("RTDATA", null);
        return map;
    }

    /**
     * 函数名：update函数 - 更改用户密码 - updateUserPassword（）
     * 功能描述：根据传入的用户信息条件修改用户信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param httpServletRequest HttpServletRequest类型的请求
     * @param password           String类型的密码
     *                           返回值：map
     *                           异    常：NULL
     *                           创建人：CMAPLE
     *                           创建日期：2020-03-05
     */
    @UserLoginToken
    @RequestMapping(value = "/updateUserPassword", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> updateUserPassword(HttpServletRequest
                                                          httpServletRequest, @RequestParam(value = "password", required = true) String password) {
        Map<String, Object> map = new HashMap<String, Object>();
        //获取用户名
        String telephonenumber = HttpServletRequestTool.getHttpServletRequestToolExample().getIpAddgetRequestUser(httpServletRequest);
        User user = userService.selectByTelephonenumber(telephonenumber);
        //更新登录信息
        if (1 != userService.update(new User(
                user.getId()                         //用户编号
                , password                 //用户登录密码
                , user.getUsertype()                 //用户类型
                , user.getUseraffairs()              //用户状态
                , user.getUserbalance()             //用户余额
                , user.getIdcard()                   //用户身份证号码
                , user.getName()                     //用户真实姓名
                , user.getUseraddress()              //用户地址
                , telephonenumber          //用户电话
                , user.getUseremail()                //用户电子邮箱
                , user.getCreatetime()     //用户创建时间
                , user.getUsersign()                 //用户签名
                , user.getPetname()                  //用户昵称
                , user.getErrortry()    //用户密码连续输入错误次数
                , user.getCommonip()                 //常用ip
                , user.getLastplace()                //最后登录地点
                , user.getPermissions()              //权限列表
        ))) {
            map.put("RTCODE", "error");
            map.put("RTMSG", "用户密码修改失败！");
            map.put("RTDATA", null);
            return map;
        }
        map.put("RTCODE", "success");
        map.put("RTMSG", "用户密码修改成功！");
        map.put("RTDATA", null);
        return map;
    }

    /**
     * 函数名：验证函数 - 发送手机验证码 - sendSMS（）
     * 功能描述：发送手机验证码到手机号
     * 输入参数：<按照参数定义顺序>
     *
     * @param telephonenumber HttpServletRequest类型的请求
     *                        返回值：map
     *                        异    常：NULL
     *                        创建人：CMAPLE
     *                        创建日期：2020-03-06
     */
    @PassToken
    @RequestMapping(value = "/sendSMS", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> sendSMS(
            HttpServletRequest request
            , @RequestParam(value = "telephonenumber", required = true) String telephonenumber
    ) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        //生成验证码
        String code = RandomData.getRandomData().getRandomNHData(6);
        //request.getSession().setAttribute(telephonenumber, code);
        //System.out.println(aliYunPreset.getSIGNNAME() + "/" + aliYunPreset.getTEMPLATECODE() + "/" + aliYunPreset.getACCESSKEYID() + "/" + aliYunPreset.getACCESSSECRET());
        VER_CODE.put(telephonenumber, code);
        map = Aliyun.getAliyun().aliyun_SendSms(telephonenumber, code, aliYunPreset.getSIGNNAME(), aliYunPreset.getTEMPLATECODE(), aliYunPreset.getACCESSKEYID(), aliYunPreset.getACCESSSECRET());
        return map;
    }

    /**
     * 函数名：验证函数 - 验证码验证函数 - MessageVerification（）
     * 功能描述：根据传入的用户信息条件修改用户信息
     * 输入参数：<按照参数定义顺序>
     * <p>
     * param httpServletRequest HttpServletRequest类型的请求
     *
     * @param request         String类型的密码
     * @param telephonenumber String类型的密码
     * @param ver_code        String类型的密码
     *                        返回值：map
     *                        异    常：NULL
     *                        创建人：CMAPLE
     *                        创建日期：2020-03-05
     */
    @PassToken
    @RequestMapping(value = "/MessageVerification", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> MessageVerification(
            HttpServletRequest request
            , @RequestParam(value = "telephonenumber", required = true) String telephonenumber
            , @RequestParam(value = "ver_code", required = true) String ver_code
    ) {
        Map<String, Object> map = new HashMap<String, Object>();
        //String sessionCode = (String) request.getSession().getAttribute(telephonenumber);
        if (!VER_CODE.get(telephonenumber).equals(ver_code)) {
            map.put("RTCODE", "error");
            map.put("RTMSG", "验证码错误，请稍稍后新验证！");
            map.put("RTDATA", null);
            return map;
        }
        map.put("RTCODE", "success");
        map.put("RTMSG", "验证码验证成功！");
        map.put("RTDATA", null);
        return map;
    }


    /**
     * 函数名：update函数 - 更改绑定电话 - updateUserPassword（）
     * 功能描述：根据传入的用户信息条件修改用户信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param httpServletRequest HttpServletRequest类型的请求
     * @param password           String类型的密码
     *                           返回值：map
     *                           异    常：NULL
     *                           创建人：CMAPLE
     *                           创建日期：2020-03-05
     */

    /**
     * 函数名：验证函数 - 发送电子邮件验证 - sendEmail（）
     * 功能描述：根据传入的用户信息条件修改用户信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param httpServletRequest     HttpServletRequest类型的请求
     * @param receivetelephonenumber String类型的收件人
     * @param emailheader            String类型的邮箱标题头
     * @param emailcontent           String类型的邮件内容
     *                               <p>
     *                               返回值：map
     *                               异    常：NULL
     *                               创建人：CMAPLE
     *                               创建日期：2020-03-06
     */
    @UserLoginToken
    @RequestMapping(value = "/sendEmail", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> sendEmail(
            HttpServletRequest httpServletRequest
            , @RequestParam(value = "receivetelephonenumber", required = true) String receivetelephonenumber
            , @RequestParam(value = "emailheader", required = true) String emailheader
            , @RequestParam(value = "emailcontent", required = true) String emailcontent
            , @RequestParam(value = "emailtype", required = true) int emailtype
    ) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        //获取用户信息
        String telephonenumber = HttpServletRequestTool.getHttpServletRequestToolExample().getIpAddgetRequestUser(httpServletRequest);
        User loginuser = userService.selectByTelephonenumber(telephonenumber);
        //权限验证
        if ("member".equals(loginuser.getUsertype())) {
            map.put("RTCODE", "error");
            map.put("RTMSG", "您没有权限发送邮件！");
            map.put("RTDATA", null);
            return map;
        }
        //获取收件人信息
        User receiveuser = userService.selectByTelephonenumber(receivetelephonenumber);
        //发送邮件
        emailService.sendMessage(
                configurationFile.getSMTP()
                , configurationFile.getLOGPRINT()
                , configurationFile.getPERSONAL()
                , configurationFile.getADDRESS()
                , configurationFile.getPASSWORD()
                , receiveuser.getPetname()
                , receiveuser.getUseremail()
                , emailheader
                , emailcontent
                , configurationFile.getRETURNURL() + "?token=" + httpServletRequest.getHeader("token")
                , emailtype
        );
        map.put("RTCODE", "success");
        map.put("RTMSG", "发送电子邮件完成！");
        map.put("RTDATA", null);
        return map;
    }

    /**
     * 函数名：update函数 - 更新账户电子邮件 - updateUserEmail（）
     * 功能描述：根据传入的用户信息条件修改用户信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param httpServletRequest HttpServletRequest类型的请求
     * @param email              String类型的密码
     *                           返回值：map
     *                           异    常：NULL
     *                           创建人：CMAPLE
     *                           创建日期：2020-03-06
     */
    @PassToken
    @RequestMapping(value = "/updateUserEmail", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> updateUserEmail(
            HttpServletRequest httpServletRequest
            , @RequestParam(value = "email", required = true) String email
            , @RequestParam(value = "token", required = true) String token
    ) {
        Map<String, Object> map = new HashMap<String, Object>();
        //获取用户名
        String telephonenumber = HttpServletRequestTool.getHttpServletRequestToolExample().getIpAddgetRequestUser(httpServletRequest);
        User user = userService.selectByTelephonenumber(telephonenumber);
        //更新登录信息
        if (1 != userService.update(new User(
                user.getId()                         //用户编号
                , user.getPassword()                 //用户登录密码
                , user.getUsertype()                 //用户类型
                , user.getUseraffairs()              //用户状态
                , user.getUserbalance()             //用户余额
                , user.getIdcard()                   //用户身份证号码
                , user.getName()                     //用户真实姓名
                , user.getUseraddress()              //用户地址
                , telephonenumber          //用户电话
                , email                //用户电子邮箱
                , user.getCreatetime()     //用户创建时间
                , user.getUsersign()                 //用户签名
                , user.getPetname()                  //用户昵称
                , user.getErrortry()    //用户密码连续输入错误次数
                , user.getCommonip()                 //常用ip
                , user.getLastplace()                //最后登录地点
                , user.getPermissions()              //权限列表
        ))) {
            map.put("RTCODE", "error");
            map.put("RTMSG", "用户电子邮箱修改失败！");
            map.put("RTDATA", null);
            return map;
        }
        map.put("RTCODE", "success");
        map.put("RTMSG", "用户电子邮箱修改成功！");
        map.put("RTDATA", null);
        return map;
    }

    /**
     * 函数名：update函数 - 账户充值 - updateUserPassword（）- 暂时无法实现
     * 功能描述：根据传入的用户信息条件修改用户信息
     * 输入参数：<按照参数定义顺序>
     *
     * @param httpServletRequest HttpServletRequest类型的请求
     * @param password           String类型的密码
     *                           返回值：map
     *                           异    常：NULL
     *                           创建人：CMAPLE
     *                           创建日期：2020-03-05
     */

}
