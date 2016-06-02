package com.wzf.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class MailUtils {
	public static void sendMail(String to,String code) throws MessagingException{
		Properties props=new Properties();
		props.setProperty("mail.smtp.host", "smtp.163.com");
		props.setProperty("mail.smtp.auth", "true");
		Session session=Session.getInstance(props, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication("wzf_sparkle@163.com", "ehmhrvyzwhepknti");
			}
			
		});
		
		Message message=new MimeMessage(session);
		
		message.setFrom(new InternetAddress("wzf_sparkle@163.com"));
		
		message.setRecipient(RecipientType.TO, new InternetAddress(to));
		
		message.setSubject("来自XX网上商城的激活邮件");
		
		message.setContent("<h1>来自XX网上商城的激活邮件</h1><h3><a href='http://192.168.10.101:8080/ssh_shop/user_active.action?code="+code+"'>http://192.168.10.101:8080/ssh_shop/user_active.action?code="+code+"</a></h3>", "text/html;charset=utf-8");
		
		Transport.send(message);
	}
}
