package edu.kh.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletEx2 extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("요청 확인");
		String orderer=req.getParameter("orderer");
		System.out.println("주문자 : "+ orderer);
		
		String[] coffee= req.getParameterValues("coffee");
		for(String a : coffee) {
			System.out.println(a);
		}
		
	}

}
