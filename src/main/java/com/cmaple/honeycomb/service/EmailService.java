package com.cmaple.honeycomb.service;

import com.cmaple.honeycomb.tools.FormatTime;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 类名：邮件管理工具 - EmailTool
 * 功能描述： 进行邮件功能管理的工具类
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：无
 * 创建人：CMAPLE
 * 创建日期：2020-03-06
 * 修改人：
 * 级别：NULL
 * 修改日期：
 */
@Service
public class EmailService {

    /**
     * 函数名：私有函数 - 邮件内容创建函数 - createMimeMessage（）
     * 功能描述： 邮件内容创建函数
     * 输入参数：<按照参数定义顺序>
     *
     * @param session     和服务器交互的会话
     * @param sendMail    发件人邮箱
     * @param receiveMail 收件人邮箱
     *                    返回值：String
     *                    异    常：无
     *                    创建人：CMAPLE
     *                    创建日期：2020-03-06
     *                    修改人：
     *                    级别：NULL
     *                    修改日期：
     */
    private static MimeMessage createMimeMessage(
            Session session
            , String sendpersonal
            , String sendMail
            , String receivepersonal
            , String receiveMail
            , String emailheader
            , String emailcontent
    ) throws Exception {
        //创建一封邮件
        MimeMessage message = new MimeMessage(session);
        //发件人
        message.setFrom(new InternetAddress(sendMail, sendpersonal, "UTF-8"));
        //收件人（可以增加多个收件人、抄送、密送）
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, receivepersonal, "UTF-8"));
        //邮件主题
        message.setSubject(emailheader, "UTF-8");
        //邮件正文（可以使用html标签）
        message.setContent(emailcontent, "text/html;charset=UTF-8");
        //设置发件时间
        message.setSentDate(new Date());
        //保存设置
        message.saveChanges();
        return message;
    }

    /**
     * 函数名：私有函数 - 读取html邮件模版 - readHtml（）
     * 功能描述： 读取html邮件模版并输出为String
     * 输入参数：<按照参数定义顺序>
     *
     * @param htmlname string类型的html文件路径
     *                 返回值：String
     *                 异    常：无
     *                 创建人：CMAPLE
     *                 创建日期：2020-03-09
     *                 修改人：
     *                 级别：NULL
     *                 修改日期：
     */
    private static String readHtml(
            String htmlname
    ) throws Exception {
        // 1. 创建一封邮件
        File input = new File("/Users/congfeng/IDEA_Programe/honeycomb/src/main/resources/static/" + htmlname);
        Document doc = Jsoup.parse(input, "UTF-8");
        return doc.toString();
    }

    /**
     * 函数名：私有函数 - 分割模版内容 - splitEmail（）
     * 功能描述： 分割模版内容并输出为String[]
     * 输入参数：<按照参数定义顺序>
     *
     * @param emailcontent string类型的模版内容
     *                     返回值：String
     *                     异    常：无
     *                     创建人：CMAPLE
     *                     创建日期：2020-03-09
     *                     修改人：
     *                     级别：NULL
     *                     修改日期：
     */
    private static String[] splitEmail(
            String emailcontent
    ) {
        return emailcontent.split("-p-");
    }

    /**
     * 函数名：邮件工具函数 - 发送邮件函数 - sendEmail（）
     * 功能描述： 进行对邮件发送
     * 输入参数：<按照参数定义顺序>
     * <p>
     *
     * @param SMTPServer        String类型的SMTP服务器
     * @param emaillog          boolean类型的是否后台打印日志
     * @param sendpersonal      String类型的发件人昵称
     * @param sendmail          String类型的发件人邮箱地址
     * @param sendemailpassword String类型的发件人密码
     * @param receivepersonal   String类型的收件人昵称
     * @param receiveMail       String类型的收件人邮箱地址
     * @param emailheader       String类型的邮件标题
     * @param emailcontent      String类型的邮件内容
     * @param url               String类型邮件跳转网址
     * @param emailtype         String类型邮件类型 - 不同类型选择不同的模版进行邮件发送
     *                          返回值：String
     *                          异    常：无
     *                          创建人：CMAPLE
     *                          创建日期：2020-03-03
     *                          修改人：
     *                          级别：NULL
     *                          修改日期：
     */
    @Async
    public void sendMessage(
            String SMTPServer
            , String emaillog
            , String sendpersonal
            , String sendmail
            , String sendemailpassword
            , String receivepersonal
            , String receiveMail
            , String emailheader
            , String emailcontent
            , String url
            , int emailtype
    ) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Properties props = new Properties();                    // 参数配置
        props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", SMTPServer);   // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true");            // 需要请求认证
        //根据配置创建会话对象, 用于和邮件服务器交互
        Session session = Session.getInstance(props);
        session.setDebug(Boolean.parseBoolean(emaillog));       // 设置为debug模式, 可以查看详细的发送 log
        Transport transport = null;
        //读取需要的模版文件
        String htmlstring = null;
        //读取模版
        switch (emailtype) {
            //手动发送
            case 1:
                String[] email_notification = splitEmail(readHtml("email_notification.html"));
                htmlstring = email_notification[0] + receivepersonal + email_notification[1] + emailcontent + email_notification[2] + FormatTime.getFormatTime().formatYMDHMSToString(new Date()) + email_notification[3];
                break;
            //绑定账号
            case 2:
                String[] aoounut_email = splitEmail(readHtml("aoounut_email.html"));
                htmlstring = aoounut_email[0] + receivepersonal + aoounut_email[1] + url + aoounut_email[2] + FormatTime.getFormatTime().formatYMDHMSToString(new Date()) + aoounut_email[3];
                break;
            //账号异常
            case 3:
                String[] account_error = splitEmail(readHtml("account_error.html"));
                htmlstring = account_error[0] + receivepersonal + account_error[1] + url + account_error[2] + FormatTime.getFormatTime().formatYMDHMSToString(new Date()) + account_error[3];
                break;
            //最新资讯
            case 4:
                htmlstring = readHtml("new _information.html");
                break;
        }
        //拼接邮件内容
        //创建一封邮件
        MimeMessage message = createMimeMessage(session, sendpersonal, sendmail, receivepersonal, receiveMail, emailheader, htmlstring);
        //根据 Session 获取邮件传输对象
        transport = session.getTransport();
        transport.connect(sendmail, sendemailpassword);
        //发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
        transport.sendMessage(message, message.getAllRecipients());
        // 关闭连接
        transport.close();
    }


}
