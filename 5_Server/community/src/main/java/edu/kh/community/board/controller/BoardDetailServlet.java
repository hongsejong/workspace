package edu.kh.community.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.kh.community.board.model.dto.BoardDetail;
import edu.kh.community.board.model.dto.Reply;
import edu.kh.community.board.model.service.BoardService;
import edu.kh.community.board.model.service.ReplyService;
@WebServlet("/board/detail")
public class BoardDetailServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			// 파라미터 중 게시글 번호(no) 얻어오기
			int boardNo = Integer.parseInt(req.getParameter("no"));
			
			BoardService service = new BoardService();
			
			//게시글 정보 + 이미지 리스트 조회
			BoardDetail detail = service.selectBoardDetail(boardNo);
			//게시글 상세 조회된 내용이 있을 경우 댓글 목록 조회
			if(detail !=null) {
				List<Reply> replyList= new ReplyService().selectReplyList(boardNo);
				req.setAttribute("rList", replyList);
			}
			
			req.setAttribute("detail", detail);
			String path="/WEB-INF/views/board/boardDetail.jsp";
			req.getRequestDispatcher(path).forward(req, resp);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
