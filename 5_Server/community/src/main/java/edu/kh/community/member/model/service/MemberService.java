package edu.kh.community.member.model.service;

import java.sql.Connection;
import java.util.List;

import static edu.kh.community.common.JDBCTemplate.*;
import edu.kh.community.member.model.dao.MemberDAO;
import edu.kh.community.member.model.dto.Member;

public class MemberService {

	
	private MemberDAO dao = new MemberDAO();
	/**로그인 서비스
	 * @param mem
	 * @return loginMember
	 */
	public Member login(Member mem) throws Exception {
		//Connection 얻어오기
		Connection conn = getConnection();
		
		//DAO 수행
		Member loginMember = dao.login(mem,conn);
		
		//Connection 반환
		close(conn);
		
		//결과 반환
		return loginMember;
	}
	
	
	/**
	    * @param mem
	    * @return result
	    * @throws Exception
	    */
	//회원가입 서비스
	   public int signUp(Member mem) throws Exception {
	      
	      Connection conn = getConnection();
	      
	      int result = dao.signUp(mem, conn);
	      if (result == 0) {rollback(conn);
	      } else {commit(conn);}
	      
	      close(conn);
	      
	      return result;
	      
	   }


	/** 내정보수정
	 * @param mem
	 * @return
	 * @throws Exception
	 */
	public int update(Member mem) throws Exception{
		Connection conn = getConnection();
	      
	      int result = dao.update(mem, conn);
	      if (result == 0) {rollback(conn);
	      } else {commit(conn);}
	      
	      close(conn);
	      
	      return result;
	}





	public int changePw(String currentPw, String newPw, int memberNo) throws Exception {
		
		Connection conn = getConnection();
	      
	      int result = dao.changePw(currentPw,newPw,memberNo,conn);
	      if (result == 0) {rollback(conn);
	      } else {commit(conn);}
	      
	      close(conn);
	      
	      return result;
	}


	public int secession(Member mem) {
		Connection conn = getConnection();
	      
	      int result = dao.secession(mem, conn);
	      if (result == 0) {rollback(conn);
	      } else {commit(conn);}
	      
	      close(conn);
	      
	      return result;
	}


	public int emailDupCheck(String memberEmail) throws Exception{
		Connection conn = getConnection();
	      
	      int result = dao.emailDupCheck(memberEmail, conn);
	      
	      close(conn);
	      
	      return result;
	}


	public int nicknameDupCheck(String memberNickname) throws Exception{
		Connection conn = getConnection();
	      
	      int result = dao.nicknameDupCheck(memberNickname, conn);
	      
	      close(conn);
	      
	      return result;
	}


	public Member selectOne(Member mem) throws Exception{
		Connection conn = getConnection();
	      
	      Member member = dao.selectOne(mem, conn);
	      
	      close(conn);
	      
	      return member;
	}





	public List<Member> selectAll()  throws Exception{
		Connection conn = getConnection();
	      
	      List<Member> memberList = dao.selectAll(conn);
	      
	      close(conn);
	      
	      return memberList;
	}


	public int profileChange(int memberNo, String profileImage) throws Exception {
		
		Connection conn = getConnection();
	      
	      int result = dao.profileChange(memberNo,profileImage,conn);
	      if (result == 0) {rollback(conn);
	      } else {commit(conn);}
	      
	      close(conn);
	      
	      return result;
	}




}
