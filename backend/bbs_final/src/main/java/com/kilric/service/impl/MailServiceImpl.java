package com.kilric.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MailServiceImpl {
	
	@Autowired
	private JavaMailSenderImpl javaMailSender;
	
	@Value ("${spring.mail.username}")
	private String from;
	
	public void sendPassword(String to,String subject,String content){
		SimpleMailMessage mailMessage = new SimpleMailMessage ();
		mailMessage.setFrom (from);
		mailMessage.setTo (to);
		mailMessage.setSubject (subject);
		mailMessage.setText (content);
		mailMessage.setSentDate (new Date ());
		javaMailSender.send (mailMessage);
	}
}
