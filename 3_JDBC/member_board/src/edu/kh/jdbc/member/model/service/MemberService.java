package edu.kh.jdbc.member.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import edu.kh.jdbc.common.JDBCTemplate;
import edu.kh.jdbc.member.dto.Member;
import edu.kh.jdbc.member.model.dao.MemberDAO;
//import static 구문 : static 메소드를 import 하여
//					 클래스명.static메소드() 형태에서
//					클래스명을 생략할 수 있게 하는 구문

import static edu.kh.jdbc.common.JDBCTemplate.getConnection;
import static edu.kh.jdbc.common.JDBCTemplate.close;
import static edu.kh.jdbc.common.JDBCTemplate.*; //모든 메소드 import
// Seervice : 데이터 가공(요청에 맞는 데이터를 만드는 것)
//				+트랜잭션 제어 처리
//				->하나의 Service 메소드에서 n개의 DAO 메소드를 호출할 수 있음
//				->n 개의 DAO에서 수행된 SQL을 한 번에 commit / rollback 

public class MemberService {

	//회원 관련 SQL 수행 및 결과를 반환할 DAO 객체 생성 및 참조
	private MemberDAO dao = new MemberDAO();

	/**아이디 중복 검사 Service
	 * @param memberId
	 * @return
	 */
	public int duplicateCheck(String memberId) throws Exception {
		//1. Connection 객체 생성
		//-> JDBCTemplate에 작성된 getConnection() 메소드를 이용해
		// 	 커넥션 생성 후 얻어옴
		Connection conn = getConnection();

		//2. DAO 메소드 호출 후 결과 반환 받기
		int result =dao.duplicateCheck(conn,memberId);

		//3. 사용한 Connection 객체 반환
		//(SELECT문은 별도의 트랜잭션 제어 필요 X)
		close(conn);

		//4. 중복 검사 결과  View로 반환

		return result;
	}

	/**회원가입 Service
	 * @param signUpMember
	 * @return result
	 */
	public int signUp(Member signUpMember) throws Exception{
		// 1. Connection 생성
		Connection conn = getConnection();
		// 2. 회원 가입 DAO 메소드 호출 후 결과 반환받기
		int result = dao.signUpMember(conn,signUpMember);

		//3. DAO 수행 결과에 따라 트랜잭션 처리
		try {
			if(result>0) commit(conn);

			else conn.rollback();

		} catch (Exception e) {
			e.printStackTrace();
		}
		//4. 사용한 Connection 반환
		JDBCTemplate.close(conn);
		//5. DAO수행 결과 View로 반환

		return result;


	}

	public int login(String inputId, String inputPw)throws Exception {
		Connection conn = getConnection();

		int result= dao.login(conn, inputId, inputPw);

		try {
			if(result==1) conn.commit();

			else conn.rollback();

		} catch (Exception e) {
			e.printStackTrace();
		}
		//4. 사용한 Connection 반환
		JDBCTemplate.close(conn);
		//5. DAO수행 결과 View로 반환

		return result;
	}

	/**로그인
	 * @param memberId
	 * @param memberPw
	 * @return loginMember
	 */
	public Member login_1(String memberId, String memberPw) throws Exception{

		Connection conn = getConnection();

		Member loginMember = dao.login_1(conn,memberId,memberPw);

		//(SELECT 수행 -> 트랜젝션 제어 처리 X)

		close(conn);
		return loginMember;
	}




	/**회원 목록 조회
	 * @return memberList
	 */
	public List<Member> selectAll() throws Exception{

		Connection conn = getConnection();


		List<Member> memberList = dao.selectAll(conn);

		close(conn);

		return memberList;

		//(SELECT 수행 -> 트랜젝션 제어 처리 X)



	}

	public int updateMyinfo(String memberName, char memberGender, Member loginMember) throws Exception{

		// 1. Connection 생성
		Connection conn = getConnection();
		// 2.  정보수정 DAO 메소드 호출 후 결과 반환받기
		int result = dao.updateMyinfo(conn,memberName,memberGender,loginMember);

		//3. DAO 수행 결과에 따라 트랜잭션 처리
		try {
			if(result>0) commit(conn);

			else conn.rollback();

		} catch (Exception e) {
			e.printStackTrace();
		}
		//4. 사용한 Connection 반환
		close(conn);
		//5. DAO수행 결과 View로 반환
		return result;
	}




	/**내 정보 수정
	 * @param updateMember
	 * @return result
	 */
	public int updateMyinfo_1(Member updateMember) throws Exception{
		Connection conn = getConnection();
		// 2.  정보수정 DAO 메소드 호출 후 결과 반환받기
		int result = dao.updateMyinfo_1(conn,updateMember);

		//3. DAO 수행 결과에 따라 트랜잭션 처리
		try {
			if(result>0) commit(conn);

			else conn.rollback();

		} catch (Exception e) {
			e.printStackTrace();
		}
		//4. 사용한 Connection 반환
		close(conn);
		//5. DAO수행 결과 View로 반환
		return result;
	}

	public int updatePw(int memberNo, String currentPw,String newPw ) throws Exception {
		Connection conn = getConnection();
		// 2.  비밀번호변경 DAO 메소드 호출 후 결과 반환받기
		int result = dao.updatePw(conn,memberNo,currentPw,newPw);

		//3. DAO 수행 결과에 따라 트랜잭션 처리
		try {
			if(result>0) commit(conn);

			else conn.rollback();

		} catch (Exception e) {
			e.printStackTrace();
		}
		//4. 사용한 Connection 반환
		close(conn);
		//5. DAO수행 결과 View로 반환
		return result;
		
		
	}

	public int secession(int memberNo, String currentPw ) throws Exception {
		Connection conn = getConnection();
		// 2.  비밀번호변경 DAO 메소드 호출 후 결과 반환받기
		int result = dao.secession(conn, memberNo,currentPw);

		//3. DAO 수행 결과에 따라 트랜잭션 처리
		try {
			if(result>0) commit(conn);

			else conn.rollback();

		} catch (Exception e) {
			e.printStackTrace();
		}
		//4. 사용한 Connection 반환
		close(conn);
		//5. DAO수행 결과 View로 반환
		return result;
		
	}

}
