package edu.kh.jsp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/scope")
public class ScopeServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Scope 관련 내장 객체
		
		//1. page -> JSP에서 확인
		
		//----------------------------------
		
		// 2. request : 요청/위임 받은 페이지에서 유지
		// (위임 되는 동안 계속 유지, 최소 2페이지)
		req.setAttribute("requestMessage", "request scope입니다.");
		
		//---------------------------------------		
		
		//3. session : 클라이언트 브라우저당 1개
		//				브라우저 종료 또는 session 만료 시 까지 유지
		//				(브라우저 종료 전 까지 어디서든 사용 가능)
		
		//3-1) session 범위 객체 얻어오기
		HttpSession session = req.getSession();
		
		// 3-2) session 에 값 세팅
		
		session.setAttribute("sessionMessage", "session scope 입니다.");
		
		
		//-------------------------------
		
		//4. application : 서버에 1개만 존재
		//					서버 종료 시 까지 유지
		//					(서버 종료 전 까지 어디서든 사용 가능)
		
		//4-1) application scope 객체 얻어오기
		ServletContext application = req.getServletContext();
		
		//4-2) application 에 값 세팅
		application.setAttribute("applicationMessage", "application scope 입니다.");
		
		//-------------------------------
		
		//5.우선 순위 확인
		
		//-> 모든 범위에 같은 key 값으로 속성 추가
		req.setAttribute("str", "request scope에 세팅된 문자열");
		session.setAttribute("str", "session scope에 세팅된 문자열");
		application.setAttribute("str", "application scope에 세팅된 문자열");
		
		
		
		
		//-------------------------------
		RequestDispatcher dispatcher
		 = req.getRequestDispatcher("/WEB-INF/views/scope.jsp");
		 dispatcher.forward(req, resp);
	}
	
	

}
