package edu.kh.jdbc.member.model.dao;

import java.io.FileInputStream;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Properties;

import edu.kh.jdbc.common.JDBCTemplate;
import edu.kh.jdbc.member.dto.Member;

import static edu.kh.jdbc.common.JDBCTemplate.getConnection;
import static edu.kh.jdbc.common.JDBCTemplate.close; 
// DAO(Data Access Object) : 데이터가 저장되어있는 DB,파일 등에 접근하는 객체
//						-> DB에 접근할 수 있다 == SQL을 수행하고 결과를 반환 받을 수 있다.

// Java에서 DB에 접근하고 결과를 반환받기 위한 프로그래밍 API를 제공함
// ==JDBC(Connection, Statement, PreparedStatement, ResultSet)
public class MemberDAO {

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	Properties prop ;
	public MemberDAO() {
		prop= new Properties();
		try {
			prop.loadFromXML(new FileInputStream("member-sql.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}


	/**아이디 중복 검사 DAO
	 * @param conn
	 * @param memberId
	 * @return result
	 */
	public int duplicateCheck(Connection conn, String memberId) throws Exception {
		// 1. 결과 저장용 변수
		int result =0;

		try {
			//2. SQL 작성
			String sql = "SELECT COUNT(*)  FROM MEMBER WHERE MEMBER_ID =? AND SECESSION_FL ='N'";

			//3. PreparedStatement 객체 생성( Connection, sql필요)
			pstmt= conn.prepareStatement(sql);

			//4. 위치 홀더에 알맞은 값 세팅
			pstmt.setString(1, memberId);
			//5. SQL 수행 결과 반환 받기
			rs = pstmt.executeQuery(); // SELECT 수행 결과 ResultSet 반환 받음

			//6. 조회 결과를 한 행씩 접근하여 원하는 컬럼 값 얻어오기

			if(rs.next()) {
				result = rs.getInt(1); // 1은 컬럼 순서
			}

		}finally { //try -finally 구문( catch는 throws에 의해서 생략)
			//7. 사용한 JDBC 자원 반환 (conn 제외)
			close(rs);
			close(pstmt);

		}

		//8. SQL 수행 결과 반환

		return result;
	}



	public int signUpMember(Connection conn ,Member signUpMember) throws Exception {
		int result = 0;
		try {
			//2. SQL 작성
			String sql = prop.getProperty("signupsql");

			//3. PreparedStatement 객체 생성( Connection, sql필요)
			pstmt= conn.prepareStatement(sql);

			//4. 위치 홀더에 알맞은 값 세팅
			pstmt.setString(1, signUpMember.getMemberId());
			pstmt.setString(2, signUpMember.getMemberPw());
			pstmt.setString(3, signUpMember.getMemberNm());
			pstmt.setString(4, signUpMember.getMemberGender()+"");
			//5. SQL 수행 결과 반환 받기
			result =  pstmt.executeUpdate();



		}finally { //try -finally 구문( catch는 throws에 의해서 생략)
			//7. 사용한 JDBC 자원 반환 (conn 제외)
			close(rs);
			close(pstmt);

		}
		return result;
	}



	public int login(Connection conn ,String inputId,String inputPw) throws Exception {
		int result=0;
		try {
			//2. SQL 작성
			String sql = prop.getProperty("login");


			//3. PreparedStatement 객체 생성( Connection, sql필요)
			pstmt= conn.prepareStatement(sql);

			//4. 위치 홀더에 알맞은 값 세팅
			pstmt.setString(1, inputId);
			pstmt.setString(2, inputPw);
			//5. SQL 수행 결과 반환 받기

			rs =pstmt.executeQuery();
			if(rs.next()) {
				result=1;
			}



		}finally { //try -finally 구문( catch는 throws에 의해서 생략)
			//7. 사용한 JDBC 자원 반환 (conn 제외)
			close(rs);
			close(pstmt);

		}
		return result;
	}


	/**로그인
	 * @param conn
	 * @param memberId
	 * @param memberPw
	 * @return loginMember
	 */
	public Member login_1(Connection conn, String memberId, String memberPw) throws Exception{

		Member loginMember = null;

		try {
			String sql = prop.getProperty("login");

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPw);

			rs = pstmt.executeQuery();

			if(rs.next()) {
				int memberNo= rs.getInt("MEMBER_NO");
				String memberId2= rs.getString("MEMBER_ID");
				String memberName= rs.getString("MEMBER_NM");
				char memberGender= rs.getString("MEMBER_GENDER").charAt(0);
				Date enrollDate = rs.getDate("ENROLL_DATE");

				loginMember = new Member();
				loginMember.setMemberNo(memberNo);
				loginMember.setMemberId(memberId2);
				loginMember.setMemberNm(memberName);
				loginMember.setMemberGender(memberGender);
				loginMember.setEnrollDate(enrollDate);
				loginMember.setMemberPw(memberPw);
			}
		}finally {
			close(rs);
			close(pstmt);

		}


		return loginMember;
	}


	
	
	/**
	 * 회원 목록 조회
	 * @return memberList
	 * @throws Exception
	 */
	public List<Member> selectAll(Connection conn) throws Exception {
		
		//결과 저장용 변수
		List<Member> memberList = new ArrayList<>();
		
		try {
			//1.SQL 작성
			String sql = prop.getProperty("selectAll");
			
			// 2. Statement 객체 생성
			stmt = conn.createStatement();
			
			//3) SQL 수행 후 결과 반환 받기
			rs = stmt.executeQuery(sql);
			
			// 4) ResultSet을 한 행씩 접근하여 조회된 컬럼 값을 얻어와
			//	  Member 객체에 저장
			while(rs.next()) {
				String memberId=rs.getString("MEMBER_ID");
				String memberName=rs.getString("MEMBER_NM");
				Date enrollDate=rs.getDate("ENROLL_DATE");
				
				Member member = new Member();
				member.setMemberId(memberId);
				member.setMemberNm(memberName);
				member.setEnrollDate(enrollDate);
				
				//5) 생성된 Member 객체를 List에 추가
				
				memberList.add(member);
				
			}
		}finally {
			//6.사용한 JDBC 자원 반환(Conn 제외)
			close(rs);
			close(stmt);
		}
		//7. 결과 반환
		return memberList;


		

	}


	public int updateMyinfo(Connection conn, String memberName, char memberGender, Member loginMember) throws Exception {
		int result = 0;
		try {
			//2. SQL 작성
			String sql = prop.getProperty("updateMyinfo");

			//3. PreparedStatement 객체 생성( Connection, sql필요)
			pstmt= conn.prepareStatement(sql);

			//4. 위치 홀더에 알맞은 값 세팅
			pstmt.setString(1,memberName);
			pstmt.setString(2,memberGender+"");
			pstmt.setInt(3,loginMember.getMemberNo());
			
			loginMember.setMemberNm(memberName);
			loginMember.setMemberGender(memberGender);

			//5. SQL 수행 결과 반환 받기
			result =  pstmt.executeUpdate();



		}finally { //try -finally 구문( catch는 throws에 의해서 생략)
			//7. 사용한 JDBC 자원 반환 (conn 제외)
			close(rs);
			close(pstmt);

		}
		return result;
	}


	/**내 정보 수정
	 * @param conn
	 * @param updateMember
	 * @return result
	 */
	public int updateMyinfo_1(Connection conn, Member updateMember) throws Exception{
		
		int result=0;
		try {
			String sql= prop.getProperty("updateMyinfo");
			
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1,  updateMember.getMemberNm());
			pstmt.setString(2, updateMember.getMemberGender()+"");
			pstmt.setInt(3,  updateMember.getMemberNo());
			
			result= pstmt.executeUpdate();
					
			
		}finally {
			close(rs);
			close(pstmt);
			
		}
		
		return result;
	}


	public int updatePw(Connection conn, int memberNo, String currentPw, String newPw ) throws Exception {
		int result=0;
		try {
			String sql= prop.getProperty("updatePw");
			
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, newPw );
			pstmt.setInt(2, memberNo );
			pstmt.setString(3, currentPw );

			
			result= pstmt.executeUpdate();
					
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		
		return result;
	}


	/**회원 탈퇴
	 * @param conn
	 * @param loginMember
	 * @return result
	 * @throws Exception
	 */
	public int secession(Connection conn, int memberNo, String currentPw) throws Exception {
		int result=0;
		try {
			String sql= prop.getProperty("secession");
			
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setInt(1, memberNo );
			pstmt.setString(2, currentPw);
/*
 * UPDATE MEMBER SET 
SECESSION_FL ='Y' 
WHERE MEMBER_NO= ?
AND MEMBER_PW=? 
 * */
			
			result= pstmt.executeUpdate();
					
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}


}
