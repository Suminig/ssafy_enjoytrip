package com.ssafy.enjoytrip.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@PropertySource("classpath:config.properties")
public class MailConfig {
	@Value("${NAVER_SENDER_ID}")
	private String naverSenderId;
	
	@Value("${NAVER_SENDER_PW}")
	private String naverSenderPw;
	
	@Bean
	public JavaMailSender javaMailService() {
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		
		javaMailSender.setHost("smtp.naver.com");
		javaMailSender.setUsername(naverSenderId);
		javaMailSender.setPassword(naverSenderPw);
		
		javaMailSender.setPort(465);
		
		javaMailSender.setJavaMailProperties(getMailProperties());
		
		return javaMailSender;
	}

	private Properties getMailProperties() {
		Properties properties = new Properties();
		
		properties.setProperty("mail.transport.protocol", "smtp"); // 프로토콜 설정
        properties.setProperty("mail.smtp.auth", "true"); // smtp 인증
        properties.setProperty("mail.smtp.starttls.enable", "true"); // smtp strattles 사용
        properties.setProperty("mail.debug", "true"); // 디버그 사용 
        properties.setProperty("mail.smtp.ssl.trust", "smtp.naver.com"); // ssl 인증 서버 (smtp 서버명) 
        properties.setProperty("mail.smtp.ssl.enable", "true"); // ssl 사용 
		
		return properties;
	}
}
