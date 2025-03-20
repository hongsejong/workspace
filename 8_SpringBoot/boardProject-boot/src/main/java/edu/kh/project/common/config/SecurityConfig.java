package edu.kh.project.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration // 스프링 애플리케이션을 구성하기 위한 설정용 bean 생성 클래스
public class SecurityConfig {
	
	@Bean
	public BCryptPasswordEncoder bCrpytPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
