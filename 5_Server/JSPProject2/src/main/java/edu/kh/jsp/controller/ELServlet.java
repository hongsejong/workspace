package edu.kh.jsp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.filters.SetCharacterEncodingFilter;

import edu.kh.jsp.dto.Person;

@WebServlet("/el")
public class ELServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path="/WEB-INF/views/el.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
		
	}
	
	
	
	//form 태그 post 방식 요청
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//POST 방식 문자 인코딩 처리
		req.setCharacterEncoding("UTF-8");
		
		//새로운 값을 HttpServletRequest에 속성으로 추가
		//req.setAttribute("K",V);
		
		//기본 자료형, String 추가
		req.setAttribute("color", "red");
		req.setAttribute("score", 100);
		
		//컬렉션(List , Set , Map) 추가
		List<String> strList = new ArrayList<>();
		
		strList.add("티라미수");
		strList.add("스초생");
		strList.add("딸기요거트케이크");
		strList.add("고구마케이크");
		
		req.setAttribute("strList", strList);
		
		//DTO 또는 모든 객체 추가 가능
		
		Person p1= new Person("홍길동",35,182.3);
		req.setAttribute("person", p1);
		
		//------------------------------------------------------
		//null, 빈칸 , 비어있음 확인
		String test1 = null; // null
		
		String test2 = ""; //빈칸
		
		List<String> test3= null; // null
		
		List<String> test4= new ArrayList<>(); // 비어있음
		
		List<String> test5= new ArrayList<>(); // 비어있지 않음
		test5.add("바나나");
		test5.add("복숭아");
		
		req.setAttribute("test1", test1);
		req.setAttribute("test2", test2);
		req.setAttribute("test3", test3);
		req.setAttribute("test4", test4);
		req.setAttribute("test5", test5);
		
		
		
		
		//----------------------------------------------
		
		
		
		//요청 위임 구문 작성
		req.getRequestDispatcher("/WEB-INF/views/el_result.jsp").forward(req, resp);
		
	}

}
