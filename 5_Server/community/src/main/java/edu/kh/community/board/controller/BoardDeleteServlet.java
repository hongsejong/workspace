package edu.kh.community.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.kh.community.board.model.service.BoardService;

@WebServlet("/board/delete")
public class BoardDeleteServlet extends HttpServlet{
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		try {
			int boardNo = Integer.parseInt(req.getParameter("no"));
			int type = Integer.parseInt(req.getParameter("type"));
			System.out.println(boardNo);
			BoardService service = new BoardService();
			
			int result= service.BoardDelete(boardNo);
			HttpSession session = req.getSession();
			
			String path = null;
			//삭제 성공 시 : 게시글이 삭제되었습니다.
			//				해당 게시판 목록 1페이지로 이동
			if(result >0){ // 성공
				session.setAttribute("message", "게시글이 삭제되었습니다.");
				path = "list?type="+ type;
//				path =req.getHeader("referer");
				System.out.println(path);
				
				//삭제 실패 시 : 게시글 삭제 실패
				//			 게시글 상세 화면
			}else { //실패
				session.setAttribute("message", "게시글 삭제 실패.");
//				path = "detail?type=" + type + "&no="+boardNo;
				path =req.getHeader("referer");
				//상세 페이지 == 이전 페이지 요청 주소 == referer
				
				//상세 페이지
			}
			
			resp.sendRedirect(path);
			
		}catch( Exception e) {
			e.printStackTrace();
		}
	}
	

}
