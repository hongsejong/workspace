package edu.kh.project.member.model.service;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class) // 컨테이너 객체를 생성해 테스트에 사용할 빈을 주입
@WebAppConfiguration // Controller 및 web 환경에 사용되는 bean들을 자동으로 생성하여 등록

// 스프링 설정 파일의 위치를 작성하여 컨테이너가 인식할 수 있도록 함
@ContextConfiguration(locations = {"file:src/main/resources/spring/appServlet/servlet-context.xml",
									"file:src/main/resources/spring/*.xml"})

@Slf4j
public class MemberServiceTest {
	
	@Autowired 
	private WebApplicationContext wac;
	// 현재 실행중인 애플리케이션의 구성을 제공하는 인터페이스
	
	private MockMvc mockMvc;
	// client 요청 내용을 controller에서 받아 처리한느 것과 같은 테스트를 진행할 수 있는 클래스

	@Before // JUnit 테스트 진행 전 먼저 실행하는 것을 지정하는 어노테이션
	public void setup() {
	      this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	      log.info("--------------- 테스트 준비 완료 --------------");
	   }


	   @Test //  독립적으로 테스트를 수행할 메소드를 지정
	   public void testLogin() {
	      log.info("--------------- 로그인 테스트 시작 --------------");
	      try {
	         mockMvc.perform( post("/member/login").param("memberEmail","user01@kh.or.kr").param("memberPw","pass01!") )
	               .andDo(print())
	               .andExpect(status().isOk());
	         // perform() : 테스트 메소드 내부에 mockMvc를 이용하여 매핑될 url과 필요한 데이터가 담긴 가상의 요청을 작성
	         // andDo(print()) : 처리된 내용 출력
	         // andExpect(status().isOk()) : 응답 상태 값이 정상상태(status가 200)인 지 검증

	         log.info("\n--------------- 로그인 테스트 성공 --------------");
	      } catch (Exception e) {
	         log.error("\n--------------- 로그인 테스트 실패 --------------");
	         log.error(e.getMessage()); // 에러 원인을 간단하게 문자열로 출력
	      }

	   }

}
