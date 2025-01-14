package edu.kh.community.board.model.dao;

import static edu.kh.community.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.community.board.model.dto.Reply;
import edu.kh.community.member.model.dao.MemberDAO;
import edu.kh.community.member.model.dto.Member;

public class ReplyDAO {
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;
	public ReplyDAO() {
		try {
			prop= new Properties();
			
			String filePath = ReplyDAO.class.getResource("/edu/kh/community/sql/reply-sql.xml").getPath();
			
			prop.loadFromXML(new FileInputStream(filePath));
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public List<Reply> selectReplyList(Connection conn, int boardNo) throws Exception {
		List<Reply> replyList = new ArrayList<>(); 
	      
	      try {
		    	 String sql=prop.getProperty("selectReplyList");
		         pstmt = conn.prepareStatement(sql);
		         pstmt.setInt(1, boardNo);
		         
		         rs= pstmt.executeQuery();
				while(rs.next()) {
					Reply reply=new Reply();
					reply.setMemberNo(rs.getInt("REPLY_NO"));
					reply.setReplyContent(rs.getString("REPLY_CONTENT"));
					reply.setCreateDate(rs.getString("CREATE_DT"));
					reply.setMemberNo(rs.getInt("MEMBER_NO"));
					reply.setMemberNickname(rs.getString("MEMBER_NICK"));
					reply.setProfileImage(rs.getString("PROFILE_IMG"));
					reply.setBoardNo(rs.getInt("BOARD_NO"));
					

					replyList.add(reply); 
				}
	         
	      } finally {
	    	  
	    	  close(rs);
	           close(pstmt);
	        }
	      
	      return replyList;
	}

	public int insertReply(String replyContent, int memberNo, int boardNo, Connection conn) throws Exception {
		int result=0;
		try {
	         pstmt = conn.prepareStatement(prop.getProperty("insertReply"));
	         pstmt.setString(1, replyContent);
	         pstmt.setInt(2, memberNo);
	         pstmt.setInt(3, boardNo);
	         
	         result = pstmt.executeUpdate();
	         
	      }  finally {
	           close(pstmt);
	        }
		
		return result;
	}


	
	
	

}
