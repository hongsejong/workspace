package edu.kh.community.board.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import edu.kh.community.board.model.dto.BoardDetail;
import edu.kh.community.board.model.dto.Reply;
import edu.kh.community.board.model.service.BoardService;
import edu.kh.community.board.model.service.ReplyService;
import edu.kh.community.member.model.dto.Member;

// Controller : 요청에 따라 알맞은 서비스를 호출하고
// 요청 처리 결과를 내보내줄(응답할) view를 선택

// *** Front Controller 패턴 ***
// 하나의 Servlet이 여러 요청을 받아 들이고 제어하는 패턴

@WebServlet("/reply/*") // /reply로 시작하는 모든 요청을 받음
public class ReplyController extends HttpServlet{
	// /reply/selectReplyList
	// /reply/insert
	// /reply/update
	// /reply/delete
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();
		String command = uri.substring(  (contextPath + "/reply/").length()  );
						// length() -> 17 ~ 끝까지 자름
		
		try {
			//댓글 목록 조회 요청인 경우
			ReplyService service = new ReplyService();
			if(command.equals("selectReplyList")) {
				
				
				
				//파라미터 얻어오기
				int boardNo= Integer.parseInt(req.getParameter("boardNo"));
				
				//댓글 목록 조회 서비스 호출 후 결과 반환 받기
				List<Reply> replyList= service.selectReplyList(boardNo);
				
				new Gson().toJson(replyList, resp.getWriter());
			}
			
			//----------------------------------
			
			
//			댓글 작성인 경우
			if(command.equals("insert")) {
				
				//파라미터 얻어오기
				String replyContent=req.getParameter("replyContent");
				int memberNo= Integer.parseInt(req.getParameter("memberNo"));
				int boardNo= Integer.parseInt(req.getParameter("boardNo"));
				
//				Reply reply = new Reply();
//				reply.setBoardNo(boardNo);
//				reply.setMemberNo(memberNo);
//				reply.setReplyContent(replyContent); 객체로 해도됨
				
//				int result= service.insertReply(reply, memberNo, boardNo);
				//댓글 등록 조회 서비스 호출 후 결과 반환 받기
				int result= service.insertReply(replyContent,memberNo,boardNo);
//				new Gson().toJson(result, resp.getWriter());
				resp.getWriter().println(result);
			}
			
			
			//댓글 삭제인 경우
			if(command.equals("delete")) {
				
				int replyNo= Integer.parseInt(req.getParameter("replyNo"));
				
				
				// 댓글 삭제 서비스 호출 후 결과 반환 받기
				int result= service.deleteReply(replyNo);
				resp.getWriter().println(result);
			}
			
			//댓글 수정
			if(command.equals("update")) {
				
				int replyNo= Integer.parseInt(req.getParameter("replyNo"));
				String updateContent = req.getParameter("updateContent");
				
				
				// 댓글 수정 서비스 호출 후 결과 반환 받기
				int result= service.updateReply(replyNo,updateContent);
				resp.getWriter().println(result);
			}
			
			
			
			
			

			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp); // POST로 전달된 요청을 doGet()으로 전달하여 수행
	}
	

}
