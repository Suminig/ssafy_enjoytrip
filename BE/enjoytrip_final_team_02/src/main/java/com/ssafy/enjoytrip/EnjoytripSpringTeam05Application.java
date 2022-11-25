package com.ssafy.enjoytrip;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@MapperScan(basePackages = { "com.ssafy.enjoytrip.**.mapper" })
public class EnjoytripSpringTeam05Application {

	public static void main(String[] args) {
		SpringApplication.run(EnjoytripSpringTeam05Application.class, args);
	}

}
