package edu.kh.jsp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.filters.SetCharacterEncodingFilter;

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
		
		//요청 위임 구문 작성
		req.getRequestDispatcher("/WEB-INF/views/el_result.jsp").forward(req, resp);
		
	}

}
