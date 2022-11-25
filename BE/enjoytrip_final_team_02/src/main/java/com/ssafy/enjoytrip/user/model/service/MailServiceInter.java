package com.ssafy.enjoytrip.user.model.service;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public interface MailServiceInter {
	// 메일 내용 작성 
    MimeMessage creatFindId(String to, String id) throws MessagingException, UnsupportedEncodingException;

	// 메일 내용 작성 
    MimeMessage creatFindPw(String to) throws MessagingException, UnsupportedEncodingException;

    // 랜덤 인증코드 생성
    String createKey();
    
    // 메일 발송
    void sendFindIdMessage(String to, String id) throws Exception;
    
    String sendFindPwMessage(String to) throws Exception;
}
