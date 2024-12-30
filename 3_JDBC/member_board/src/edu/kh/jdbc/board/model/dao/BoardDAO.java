
package edu.kh.jdbc.board.model.dao;

import static edu.kh.jdbc.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.jdbc.board.model.dto.Board;
import edu.kh.jdbc.board.model.dto.Reply;
import edu.kh.jdbc.member.dto.Member;
import edu.kh.jdbc.common.JDBCTemplate;
import edu.kh.jdbc.member.dto.Member;

import static edu.kh.jdbc.common.JDBCTemplate.getConnection;
import static edu.kh.jdbc.common.JDBCTemplate.close;



public class BoardDAO {
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop ;

	public BoardDAO() {
		prop= new Properties();
		try {
			prop.loadFromXML(new FileInputStream("board-sql.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}



	/**게시글 목록 조회
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public  List<Board> selectAll(Connection conn) throws Exception {

		List<Board> boardList = new ArrayList<>();

		try {

			//1.SQL 작성
			String sql = prop.getProperty("selectAll");

			// 2. Statement 객체 생성
			stmt = conn.createStatement();

			//3) SQL 수행 후 결과 반환 받기
			rs = stmt.executeQuery(sql);

			// 4) ResultSet을 한 행씩 접근하여 조회된 컬럼 값을 얻어와
			//     Member 객체에 저장
			while(rs.next()) {
				int boardNo = rs.getInt("BOARD_NO");
				String boardTitle = rs.getString("BOARD_TITLE");
				Date createDate = rs.getDate("CREATE_DATE");
				int readCount = rs.getInt("READ_COUNT");
				String memberName = rs.getString("MEMBER_NM");
				int replyCount = rs.getInt("REPLY_COUNT");

				Board board = new Board(boardNo, boardTitle, createDate, readCount, memberName, replyCount);


				boardList.add(board);

			}
		}finally {
			//6.사용한 JDBC 자원 반환(Conn 제외)
			close(rs);
			close(stmt);
		}
		//7. 결과 반환
		return boardList;

	}



	/**게시글 상세조회
	 * @param boardNo
	 * @param conn
	 * @return board
	 * @throws Exception
	 */
	public Board selectOne(int boardNo, Connection conn) throws Exception {
		Board board = null;

		try {
			//1)SQL 작성
			String sql =prop.getProperty("selectOne");

			//2) PreparedStatement
			pstmt = conn.prepareStatement(sql);

			//3 ) ? 위치 홀더에 알맞은 값 세팅
			pstmt.setInt(1, boardNo);

			// 4)SQL 수행(SELECT) 후 결과 반환 받기(ResultSet)

			rs = pstmt.executeQuery();

			// 5) 조회된 한 행(if)이 있을 경우 조회된 컬럼 값 얻어오기
			if(rs.next()) {
				//   int boardNo= rs.getInt("boardNo");
				//   ->입력 받은 boardNo와 조회된 BOARD_NO는 같으므로
				// 굳이 DB조회 결과에서 얻어오지 않아도 된다.

				String boardTitle=rs.getString("BOARD_TITLE");
				String boardContent=rs.getString("BOARD_CONTENT");
				Date createDate = rs.getDate("CREATE_DATE");
				int readCount = rs.getInt("READ_COUNT");
				int memberNo= rs.getInt("MEMBER_NO");
				String memberNm= rs.getString("MEMBER_NM");




				//6. Board 객체 생성하여 컬럼 값 세팅

				board = new Board();
				board.setBoardNo(boardNo);
				board.setBoardTitle(boardTitle);
				board.setBoardContent(boardContent);
				board.setCreateDate(createDate);
				board.setReadCount(readCount);
				board.setMemberNo(memberNo);
				board.setMemberName(memberNm);


			}

		}finally {
			//7. 사용한 자원 반환
			close(rs);
			close(pstmt);
		}

		//8.결과 반환
		return board; // null 또는 Board 객체 주소값 반환
	}



	/**
	 * @param boardNo
	 * @param conn
	 * @return replyList
	 * @throws Exception
	 */
	public List<Reply> selectReplyList(int boardNo, Connection conn) throws Exception {
		List<Reply> replyList = new ArrayList<>();

		try {

			//1.SQL 작성
			String sql = prop.getProperty("selectReplyList");

			// 2. Statement 객체 생성
			pstmt = conn.prepareStatement(sql);

			//3) SQL 수행 후 결과 반환 받기
			pstmt.setInt(1,boardNo);
			rs = pstmt.executeQuery();


			// 4) ResultSet을 한 행씩 접근하여 조회된 컬럼 값을 얻어와
			//     Member 객체에 저장
			while(rs.next()) {

				int replyNo=rs.getInt("REPLY_NO");
				String replyContent=rs.getString("REPLY_CONTENT");
				Date createDate=rs.getDate("CREATE_DATE");
				int memberNo=rs.getInt("MEMBER_NO");
				String memberName=rs.getString("MEMBER_NM");
				//            int boardNo=rs.getInt("BOARD_NO");
				//매개변수 이용

				Reply reply = new Reply(replyNo, replyContent, createDate, memberNo, boardNo, memberName);


				replyList.add(reply);

			}
		}finally {
			//6.사용한 JDBC 자원 반환(Conn 제외)
			close(rs);
			close(stmt);
		}
		//7. 결과 반환
		return replyList;


	}



	/**게시글 조회수 증가
	 * @param conn
	 * @param boardNo
	 * @return result
	 * @throws Exception
	 */
	public int increaseReadCount(Connection conn, int boardNo) throws Exception {
		int result=0;

		try {
			String sql= prop.getProperty("increaseReadCount");

			pstmt=conn.prepareStatement(sql);

			pstmt.setInt(1, boardNo);

			result= pstmt.executeUpdate();


		}finally {
			close(pstmt);

		}

		return result;
	}



	public int deleteBoard(int boardNo, Connection conn) throws Exception{
		int result=0;

		try {
			String sql= prop.getProperty("deleteBoard");

			pstmt=conn.prepareStatement(sql);

			pstmt.setInt(1, boardNo);

			result= pstmt.executeUpdate();

		}finally {
			close(pstmt);
		}
		return result;
	}







	/**게시글 수정
	 * @param conn
	 * @param boardNo
	 * @param boardTitle
	 * @param boardContent
	 * @return result
	 * @throws Exception
	 */
	public int updateBoard(Connection conn, int boardNo, String boardTitle, String boardContent)throws Exception {
		int result=0;


		try {
			String sql= prop.getProperty("updateBoard");

			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, boardTitle);
			pstmt.setString(2, boardContent);
			pstmt.setInt(3, boardNo);

			result= pstmt.executeUpdate();

		}finally {
			close(pstmt);
		}
		return result;
	}











	/** 댓글작성
	 * @param conn
	 * @param boardNo
	 * @param memberNo
	 * @param replyContent
	 * @return result
	 * @throws Exception
	 */
	public int insertReply(Connection conn, int boardNo, int memberNo, String replyContent)throws Exception {
		int result=0;

		try {
			String sql=prop.getProperty("insertReply");

			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, replyContent);
			pstmt.setInt(2, memberNo);
			pstmt.setInt(3, boardNo);

			result=pstmt.executeUpdate();

		}finally {
			close(pstmt);
		}

		return result;
	}







	public int updateReply(Connection conn, int input, String replyContent) throws Exception {
		int result=0;

		try {
			String sql=prop.getProperty("updateReply");

			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, replyContent);
			pstmt.setInt(2, input);

			result=pstmt.executeUpdate();

		}finally {
			close(pstmt);
		}

		return result;
	}


	/*
   public int deleteReply(Connection conn, int input2, String replyContent2) throws Exception{
      int result=0;

      try {
         String sql=prop.getProperty("deleteReply");

         pstmt=conn.prepareStatement(sql);
         pstmt.setString(1, replyContent2);
         pstmt.setInt(2, input2);

         result=pstmt.executeUpdate();

      }finally {
         close(pstmt);
      }

      return result;
   }
	 */


	public int deleteReply(Connection conn, int input2) throws Exception {
		int result=0;

		try {
			String sql=prop.getProperty("deleteReply");

			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, input2);

			result=pstmt.executeUpdate();

		}finally {
			close(pstmt);
		}

		return result;
	}



	public int updateReply(Connection conn, Reply reply) throws Exception {
		int result=0;

		try {
			String sql=prop.getProperty("updateReply");

			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, reply.getReplyContent());
			pstmt.setInt(2, reply.getReplyNo());

			result=pstmt.executeUpdate();

		}finally {
			close(pstmt);
		}

		return result;
	}



	public int insertBoard(Connection conn, int memberNo, String boardTitle, String boardContent) throws Exception {
		int result=0;

		String sql = prop.getProperty("insertBoard");

		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1,boardTitle);
		pstmt.setString(2, boardContent);
		pstmt.setInt(3, memberNo);

		result=pstmt.executeUpdate();
		return result;
	}



	/**
	 * @param conn
	 * @param menuNum
	 * @param keyword
	 * @return boardList
	 * @throws Exception
	 */
	public List<Board> searchBoard(Connection conn, int menuNum, String keyword) throws Exception{

		List<Board> boardList = new ArrayList<>();

		try {


			//SQL 작성(menuNum에 따라서 SQL 조합)
			String sql = prop.getProperty("searchBoard1")
					+ prop.getProperty("condition"+menuNum)
					+ prop.getProperty("searchBoard2");

			pstmt = conn.prepareStatement(sql);

			//위치 홀더에 알맞은 값 세팅
			//(주의!) 제목+내용을 검색하는 조건(3번)은 위치 홀더가 2개다!

			pstmt.setString(1, keyword);
			if(menuNum==3) pstmt.setString(2, keyword);

			rs = pstmt.executeQuery();

			while(rs.next()) {
				int boardNo = rs.getInt("BOARD_NO");
				String boardTitle = rs.getString("BOARD_TITLE");
				Date createDate = rs.getDate("CREATE_DATE");
				int readCount = rs.getInt("READ_COUNT");
				String memberName = rs.getString("MEMBER_NM");
				int replyCount = rs.getInt("REPLY_COUNT");

				Board board = new Board(boardNo, boardTitle, createDate, readCount, memberName, replyCount);


				boardList.add(board);


			}
		}finally {
			//6.사용한 JDBC 자원 반환(Conn 제외)
			close(rs);
			close(stmt);
		}
		//7. 결과 반환
		return boardList;
	}





}
