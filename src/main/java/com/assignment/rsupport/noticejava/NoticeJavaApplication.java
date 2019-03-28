package com.assignment.rsupport.noticejava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // createdAt, modifiedAt, createdBy, modifiedBy등을 자동으로 주입시킬 수있도록 해주는 설정
public class NoticeJavaApplication {
	public static void main(String[] args) {
		SpringApplication.run(NoticeJavaApplication.class, args);
	}
}
