package com.player.bgm.util;

import com.player.bgm.entity.User;
import com.player.bgm.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

@Component
public class SendMailUtil  {
    @Resource
    private UserService userService;
    /**
     * 解决平常类无法注入Mapper导致空指针错误问题
     */
    public static SendMailUtil sendMailUtil;
    @PostConstruct
    public void init(){
        sendMailUtil = this;
        sendMailUtil.userService = this.userService;
    }

    /**
     * 检查信息不能为空
     * @param email 邮箱
     * @param password 密码
     * @return 相关信息
     */
    public static String CheckInformation(String password,String email){
        if(password.replaceAll(" ","").length()<3){
            return "密码太简单";
        }
        User result = sendMailUtil.userService.queryById(email);
        if(result!=null){
            return "您已注册";
        }
        return "填写正确";
    }
    /**
     * 检查密码和重复密码是否一致
     * @param password 第一次
     * @param RepeatPassword 第二次
     * @return 结果
     */
    public boolean CheckPassword(String password,String RepeatPassword){
        return Objects.equals(password, RepeatPassword);
    }
    /**
     * 判断邮箱格式是否正确
     * @param email 邮箱
     * @return false或者true
     */
    public  boolean isEmail(String email) {
        if (email == null || email.length() < 1 || email.length() > 256) {
            return false;
        }
        Pattern pattern = Pattern.compile("^[\\w]+@[A-Za-z]+(\\.[A-Za-z0-9]+){1,4}$");
        return pattern.matcher(email).matches();
    }
    /**
     * 发送邮件(参数自己根据自己的需求来修改，发送短信验证码可以直接套用这个模板)
     * @param from_email    发送人的邮箱
     * @param pwd			发送人的授权码
     * @param receive		接收人的邮箱
     * @param code			验证码
     * @param name			收件人的姓名
     * @return 结果
     */
    public String sendQQEmail(String from_email, String pwd, String receive, String code, String name){

        Properties props = new Properties();
        props.setProperty("mail.smtp.ssl.enable", "true");
        props.setProperty("mail.smtp.host", "smtp.qq.com");		//主机地址
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.auth", "true");		//授权通过
        Session session = Session.getInstance(props);		//通过我们的这些配置，得到一个会话程序

        try {

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from_email));		//设置发件人
            message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receive,"用户","utf-8"));		//设置收件人
            message.setSubject("注册码","utf-8");		//设置主题
            message.setSentDate(new Date());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
            String str = "<!DOCTYPE html><html><head><meta charset='UTF-8'></head><body>"
                    + "<p style='font-size: 20px;font-weight:bold;'>尊敬的："+name+"，您好！</p>"
                    + "<p style='text-indent:2em; font-size: 20px;'>欢迎您本次的验证码是 "
                    + "<span style='font-size:30px;font-weight:bold;color:red'>" + code + "</span>，3分钟之内有效，请尽快使用！</p>"
                    + "<p style='text-align:right; padding-right: 20px;'"
                    + "<a href='http://121.41.1.169:8080' style='font-size: 18px'>悦音-BGM播放器</a></p>"
                    + "<span style='font-size: 18px; float:right; margin-right: 60px;'>"
                    + sdf.format(new Date()) + "</span></body></html>";

            Multipart mul=new MimeMultipart();  //新建一个MimeMultipart对象来存放多个BodyPart对象
            BodyPart mdp=new MimeBodyPart();  //新建一个存放信件内容的BodyPart对象
            mdp.setContent(str, "text/html;charset=utf-8");
            mul.addBodyPart(mdp);  //将含有信件内容的BodyPart加入到MimeMultipart对象中
            message.setContent(mul); //把mul作为消息内容

            message.saveChanges();

            //创建一个传输对象
            Transport transport = session.getTransport("smtp");
            //建立与服务器的链接 465 端口是 SSL传输

            transport.connect(from_email, pwd);

            //发送邮件
            transport.sendMessage(message,message.getAllRecipients());

            //关闭邮件传输
            transport.close();
            return "验证码发送成功";

        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
            return "邮箱可能不存在，验证码发送失败";
        }

    }
    /**生成随机的六位验证码*/
    public StringBuilder CreateCode() {
        String dates = "0123456789";
        StringBuilder code = new StringBuilder();
        Random r = new Random();
        for (int i = 0; i < 6; i++) {
            int index = r.nextInt(dates.length());
            char c = dates.charAt(index);
            code.append(c);
        }
        return code;
    }
}

