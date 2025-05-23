package edu.kh.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
						//보안 관련 자동 설정 사용 X
@SpringBootApplication(exclude=SecurityAutoConfiguration.class)
// Spring Boot Application을 만들고 
// 수행하는데 필요한 필수 어노테이션을 모아둔 어노테이션
public class BoardProjectBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoardProjectBootApplication.class, args);
	}

}
