package com.gwy.manager.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

/**
 * @author Tracy
 * @date 2020/11/9 16:56
 */
@Component
@PropertySource(value = "classpath:mail.properties", encoding = "UTF-8")
public class MailUtil {

    @Value("${mail.sender}")
    private String sender;

    @Value("${mail.subject}")
    private String subject;

    @Autowired
    private JavaMailSender mailSender;

    /**
     * 创建复杂邮件实例
     * @return  创建出邮件
     */
    public MimeMessage createMimeMessage() {
        return mailSender.createMimeMessage();
    }

    /**
     * 创建复杂邮件编写工具
     * @return  返回结果
     */
    public MimeMessageHelper newMessageHelper(MimeMessage mimeMessage) {
        return new MimeMessageHelper(mimeMessage);
    }

    public void sendMimeMail(MimeMessage mimeMessage) {
        mailSender.send(mimeMessage);
    }

    /**
     * 定义邮件结果前缀
     * @return  prefix
     */
    public String getPrefix() {
        return "<head>\n" +
                    "    <base target=\"_blank\" />\n" +
                    "    <style type=\"text/css\">::-webkit-scrollbar{ display: none; }</style>\n" +
                    "    <style id=\"cloudAttachStyle\" type=\"text/css\">#divNeteaseBigAttach, #divNeteaseBigAttach_bak{display:none;}</style>\n" +
                    "    <style id=\"blockquoteStyle\" type=\"text/css\">blockquote{display:none;}</style>\n" +
                    "    <style type=\"text/css\">\n" +
                    "        body{font-size:14px;font-family:arial,verdana,sans-serif;line-height:1.666;padding:0;margin:0;overflow:auto;white-space:normal;word-wrap:break-word;min-height:100px}\n" +
                    "        td, input, button, select, body{font-family:Helvetica, 'Microsoft Yahei', verdana}\n" +
                    "        pre {white-space:pre-wrap;white-space:-moz-pre-wrap;white-space:-pre-wrap;white-space:-o-pre-wrap;word-wrap:break-word;width:95%}\n" +
                    "        th,td{font-family:arial,verdana,sans-serif;line-height:1.666}\n" +
                    "        img{ border:0}\n" +
                    "        header,footer,section,aside,article,nav,hgroup,figure,figcaption{display:block}\n" +
                    "        blockquote{margin-right:0px}\n" +
                    "    </style>\n" +
                    "</head>\n" +
                    "<body tabindex=\"0\" role=\"listitem\">\n" +
                    "<table width=\"700\" border=\"0\" align=\"center\" cellspacing=\"0\" style=\"width:700px;\">\n" +
                    "    <tbody>\n" +
                    "    <tr>\n" +
                    "        <td>\n" +
                    "            <div style=\"width:700px;margin:0 auto;border-bottom:1px solid #ccc;margin-bottom:30px;\">\n" +
                    "                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"700\" height=\"39\" style=\"font:12px Tahoma, Arial, 宋体;\">\n" +
                    "                    <tbody><tr><td width=\"210\"></td></tr></tbody>\n" +
                    "                </table>\n" +
                    "            </div>\n" +
                    "            <div style=\"width:680px;padding:0 10px;margin:0 auto;\">\n" +
                    "                <div style=\"line-height:1.5;font-size:14px;margin-bottom:25px;color:#4d4d4d;\">\n" +
                    "                    <strong style=\"display:block;margin-bottom:15px;\">尊敬的用户：<span style=\"color:#f60;font-size: 16px;\"></span>您好！</strong>\n" +
                    "                    <strong style=\"display:block;margin-bottom:15px;\">\n" +
                    "                        您正在进行<span style=\"color: red\">修改密码</span>操作，请于<span style=\"color: red\">5分钟</span>之内在验证码输入框中输入：<span style=\"color:#f60;font-size: 24px\">";
    }

    /**
     * 定义邮件返回后缀
     * @return  suffix
     */
    public String getSuffix() {
        return "</span>，以完成操作。\n" +
                    "                    </strong>\n" +
                    "                </div>\n" +
                    "                <div style=\"margin-bottom:30px;\">\n" +
                    "                    <small style=\"display:block;margin-bottom:20px;font-size:12px;\">\n" +
                    "                        <p style=\"color:#747474;\">\n" +
                    "                            注意：此操作可能会修改您的密码、登录邮箱。如非本人操作，请及时登录并修改密码以保证帐户安全\n" +
                    "                            <br>（管理员不会向你索取此验证码，请勿泄漏！)\n" +
                    "                        </p>\n" +
                    "                    </small>\n" +
                    "                </div>\n" +
                    "            </div>\n" +
                    "            <div style=\"width:700px;margin:0 auto;\">\n" +
                    "                <div style=\"padding:10px 10px 0;border-top:1px solid #ccc;color:#747474;margin-bottom:20px;line-height:1.3em;font-size:12px;\">\n" +
                    "                    <p>此为系统邮件，请勿回复。请保管好您的邮箱，避免账号被他人盗用。</p>\n" +
                    "                    \n" +
                    "                </div>\n" +
                    "            </div>\n" +
                    "\t\t\t<div style=\"width:700px;margin:0 auto;\">\n" +
                    "                <div style=\"padding:10px 10px 0;border-top:1px solid #ccc;color:#747474;margin-bottom:20px;line-height:1.3em;font-size:12px;\">\n" +
                    "                    <p>四川师范大学教务处</p>\n" +
                    "                </div>\n" +
                    "            </div>\n" +
                    "        </td>\n" +
                    "    </tr>\n" +
                    "    </tbody>\n" +
                    "</table>\n" +
                    "</body>\n";
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
