package edu.kh.community.board.model.service;

import static edu.kh.community.common.JDBCTemplate.close;
import static edu.kh.community.common.JDBCTemplate.commit;
import static edu.kh.community.common.JDBCTemplate.getConnection;
import static edu.kh.community.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import edu.kh.community.board.model.dao.ReplyDAO;
import edu.kh.community.board.model.dto.Reply;
import edu.kh.community.common.Util;
import edu.kh.community.member.model.dto.Member;

public class ReplyService {
	
	private ReplyDAO dao = new ReplyDAO();

	/**댓글 목록 조회
	 * @param boardNo
	 * @return
	 * @throws Exception
	 */
	public List<Reply> selectReplyList(int boardNo) throws Exception{
		
		Connection conn = getConnection();
	      
	      List<Reply> replyList = dao.selectReplyList(conn,boardNo);
	      
	      close(conn);
	      
	      return replyList;
		
	}

	public int insertReply(String replyContent, int memberNo, int boardNo) throws Exception {
		int result=0;
		
		Connection conn = getConnection();
		
		//Cross Site Scripting 공격 방지 처리
		replyContent=Util.XSSHandling(replyContent); //common에 util있음
	      
		// 개행문자 변경 처리
		// textarea에 줄바꿈 문자 입력 시 \n, \r, \r\n, \n\r 중 하나로 입력됨(브라우저,OS 따라 다름)
		// 이 문자들을 HTML에서 줄바꿈으로 인식할 수 있도록 "<br>"태그로 변경
//		reply.getReplyContent().replaceAll("정규표현식","바꿀 문자열");
//		replyContent.replaceAll("(\n|\r|\r\n|\n\r)", "<br>");
		
		
		//->댓글 등록/수정, 게시글 등록/수정 에서 사용
		//->static으로 선언해둔 개행 문자 변경 메소드 사용
		
//		Util.newLineHandling(replyContent);
//		reply.setReplyContent(Util.newLineHandling(replyContent));
		replyContent=Util.newLineHandling(replyContent);
		
	       result = dao.insertReply(replyContent,memberNo,boardNo,conn);
	      if (result == 0) {rollback(conn);
	      } else {commit(conn);}
	      
	      close(conn);
	      
		
		
		return result;
	}

	public int deleteReply(int replyNo) throws Exception {
		int result=0;
		Connection conn = getConnection();
	      
		 result = dao.deleteReply(conn,replyNo);
	      if (result == 0) {rollback(conn);
	      } else {commit(conn);}
	      
	      close(conn);
	      
	      return result;
	}

	public int updateReply(int replyNo, String updateContent)  throws Exception{
		int result=0;
		Connection conn = getConnection();
		updateContent=Util.XSSHandling(updateContent); //common에 util있음
	      
		updateContent=Util.newLineHandling(updateContent);
	      
		 result = dao.updateReply(conn,replyNo,updateContent);
	      if (result == 0) {rollback(conn);
	      } else {commit(conn);}
	      
	      close(conn);
	      
	      return result;
	}



}
