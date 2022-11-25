package com.ssafy.enjoytrip.user.model.service;

import java.io.UnsupportedEncodingException;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:config.properties")
public class RegisterMail implements MailServiceInter{
	@Value("${NAVER_SENDER_ID}")
	private String naverSenderId;
	
	@Autowired
	JavaMailSender emailsender; // Bean 등록해둔 MailConfig 를 emailsender 라는 이름으로 autowired

	private String ePw; // 인증번호
	
	@Override
	public MimeMessage creatFindId(String to, String id) throws MessagingException, UnsupportedEncodingException {
		MimeMessage message = emailsender.createMimeMessage();

		message.addRecipients(RecipientType.TO, to);// 보내는 대상
		message.setSubject("EnjoyTrip 아이디 찾기");// 제목

		String msgg = "";
		msgg += "<div style='margin:100px;'>";
		msgg += "<h1> 안녕하세요</h1>";
		msgg += "<h1> 언제든지 떠나고 싶은 당신을 위한 서비스 EnjoyTrip 입니다</h1>";
		msgg += "<br>";
		msgg += "<p> 회원님의 아이디를 확인 후 로그인해주세요</p>";
		msgg += "<br>";
		msgg += "<p> 저희 사이트를 이용해주셔서 감사합니다!</p>";
		msgg += "<br>";
		msgg += "<div align='center' style='border:1px solid black; font-family:verdana';>";
		msgg += "<h3 style='color:blue;'>회원님의 아이디입니다.</h3>";
		msgg += "<div style='font-size:130%'>";
		msgg += "ID : <strong>";
		msgg += id + "</strong><div><br/> "; // 메일에 인증번호 넣기
		msgg += "</div>";
		message.setText(msgg, "utf-8", "html");// 내용, charset 타입, subtype
		// 보내는 사람의 이메일 주소, 보내는 사람 이름
		message.setFrom(new InternetAddress(naverSenderId, "EnjoyTrip_Admin"));// 보내는 사람

		return message;
	}


	@Override
	public MimeMessage creatFindPw(String to) throws MessagingException, UnsupportedEncodingException {
		MimeMessage message = emailsender.createMimeMessage();

		message.addRecipients(RecipientType.TO, to);// 보내는 대상
		message.setSubject("EnjoyTrip 비밀번호 찾기");// 제목

		String msgg = "";
		msgg += "<div style='margin:100px;'>";
		msgg += "<h1> 안녕하세요</h1>";
		msgg += "<h1> 언제든지 떠나고 싶은 당신을 위한 서비스 EnjoyTrip 입니다</h1>";
		msgg += "<br>";
		msgg += "<p> 회원님의 임시 비밀번호로 로그인 후 회원정보를 수정해주세요</p>";
		msgg += "<br>";
		msgg += "<p> 저희 사이트를 이용해주셔서 감사합니다!</p>";
		msgg += "<br>";
		msgg += "<div align='center' style='border:1px solid black; font-family:verdana';>";
		msgg += "<h3 style='color:blue;'>회원님의 임시 비밀번호입니다.</h3>";
		msgg += "<div style='font-size:130%'>";
		msgg += "CODE : <strong>";
		msgg += ePw + "</strong><div><br/> "; // 메일에 인증번호 넣기
		msgg += "</div>";
		message.setText(msgg, "utf-8", "html");// 내용, charset 타입, subtype
		// 보내는 사람의 이메일 주소, 보내는 사람 이름
		message.setFrom(new InternetAddress(naverSenderId, "EnjoyTrip_Admin"));// 보내는 사람

		return message;
	}
	
	
	@Override
	public String createKey() {
		StringBuffer key = new StringBuffer();
		Random rnd = new Random();

		for (int i = 0; i < 8; i++) { // 인증코드 8자리
			int index = rnd.nextInt(3); // 0~2 까지 랜덤, rnd 값에 따라서 아래 switch 문이 실행됨

			switch (index) {
			case 0:
				key.append((char) ((int) (rnd.nextInt(26)) + 97));
				// a~z (ex. 1+97=98 =&gt; (char)98 = 'b')
				break;
			case 1:
				key.append((char) ((int) (rnd.nextInt(26)) + 65));
				// A~Z
				break;
			case 2:
				key.append((rnd.nextInt(10)));
				// 0~9
				break;
			}
		}

		return key.toString();
	}

	@Override
	public void sendFindIdMessage(String to, String id) throws Exception {
		MimeMessage message = creatFindId(to, id); // 메일 발송
		
		try {// 예외처리
			emailsender.send(message);

		} catch (MailException es) {
			es.printStackTrace();
			throw new IllegalArgumentException();
		}


	}


	@Override
	public String sendFindPwMessage(String to) throws Exception {

		ePw = createKey(); // 랜덤 인증번호 생성

		// TODO Auto-generated method stub
		MimeMessage message = creatFindPw(to); // 메일 발송
		try {// 예외처리
			emailsender.send(message);
		} catch (MailException es) {
			es.printStackTrace();
			throw new IllegalArgumentException();
		}

		return ePw; // 메일로 보냈던 인증 코드를 서버로 반환
	}

}
