package edu.kh.community.member.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import static edu.kh.community.common.JDBCTemplate.*;

import edu.kh.community.board.model.dto.BoardDetail;
import edu.kh.community.member.model.dto.Member;

public class MemberDAO {
	
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;
	
	//기본 생성자
	public MemberDAO(){
		try {
			prop= new Properties();
			
			String filePath = MemberDAO.class.getResource("/edu/kh/community/sql/member-sql.xml").getPath();
			
			prop.loadFromXML(new FileInputStream(filePath));
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**로그인 DAO
	 * @param mem
	 * @param conn
	 * @return loginMember
	 * @throws Exception
	 */
	public Member login(Member mem, Connection conn) throws Exception{
		Member loginMember = null; // 결과 저장용 변수
		
		try {
			String sql= prop.getProperty("login");
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, mem.getMemberEmail());
			pstmt.setString(2, mem.getMemberPw());
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				loginMember = new Member();
				loginMember.setMemberNo(rs.getInt("MEMBER_NO"));
	            loginMember.setMemberEmail(rs.getString("MEMBER_EMAIL"));
	            loginMember.setMemberNickname(rs.getString("MEMBER_NICK"));
	            loginMember.setMemberTel(rs.getString("MEMBER_TEL"));
	            loginMember.setMemberAddress(rs.getString("MEMBER_ADDR"));
	            loginMember.setProfileImage(rs.getString("PROFILE_IMG"));
	            loginMember.setEnrollDate(rs.getString("ENROLL_DT"));
			}
			
		}finally {
			close(rs);
			close(pstmt);
			
		}
		return loginMember;
	}
	
	
	

	public int signUp(Member mem, Connection conn) {
	      int result = 0;
	      
	      try {
	         pstmt = conn.prepareStatement(prop.getProperty("signUp"));
	         pstmt.setString(1, mem.getMemberEmail());
	         pstmt.setString(2, mem.getMemberPw());
	         pstmt.setString(3, mem.getMemberNickname());
	         pstmt.setString(4, mem.getMemberTel());
	         pstmt.setString(5, mem.getMemberAddress());
	         
	         result = pstmt.executeUpdate();
	         
	      } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	           close(pstmt);
	           
	        }
	      
	      
	      return result;
	   }

	/**내 정보 수정 DAO
	 * @param mem
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public int update(Member mem, Connection conn) throws Exception{
		   int result = 0;
		      
		      try {
		    	  
//		    	  String sql = prop.getProperty("update");
		    	  
		    			  
		         pstmt = conn.prepareStatement(prop.getProperty("update"));
		         pstmt.setString(1, mem.getMemberNickname());
		         pstmt.setString(2, mem.getMemberTel());
		         pstmt.setString(3, mem.getMemberAddress());
		         pstmt.setInt(4, mem.getMemberNo());
		         
		         result = pstmt.executeUpdate();
		         
		      } catch (Exception e) {
		            e.printStackTrace();
		        } finally {
		           close(pstmt);
		           
		        }
		      
		      
		      return result;
	}


	public int changePw(String currentPw, String newPw, int memberNo, Connection conn) throws Exception {
		
		   int result = 0;
		      
		      try {
		         pstmt = conn.prepareStatement(prop.getProperty("changePw"));
		         pstmt.setString(1, newPw);
		         pstmt.setInt(2, memberNo);
		         pstmt.setString(3, currentPw);
		         
		         result = pstmt.executeUpdate();
		         
		      } catch (Exception e) {
		            e.printStackTrace();
		        } finally {
		           close(pstmt);
		        }
		      
		      return result;
	}

	
	public int secession(Member mem ,Connection conn) {
		  int result = 0;
	      
	      try {
	         pstmt = conn.prepareStatement(prop.getProperty("secession"));
	         pstmt.setInt(1, mem.getMemberNo());
	         pstmt.setString(2, mem.getMemberPw());
	         
	         result = pstmt.executeUpdate();
	         
	      } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	           close(pstmt);
	        }
	      
	      return result;
	}

	public int emailDupCheck(String memberEmail, Connection conn) throws Exception {
  int result = 0;
	      
	      try {
	    	  String sql=prop.getProperty("emailDupCheck");
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, memberEmail);
	         
	         rs= pstmt.executeQuery();
	         
	         if(rs.next()) {
	        	 result=rs.getInt(1); //번 컬럼의 결과를 result에 대입
	         }
	         
	      } finally {
	    	  
	    	  close(rs);
	           close(pstmt);
	        }
	      
	      return result;
	}

	
	
	/**닉네임 중복검사
	 * @param memberNickname
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public int nicknameDupCheck(String memberNickname, Connection conn) throws Exception {
  int result = 0;
	      
	      try {
	    	  String sql=prop.getProperty("nicknameDupCheck");
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, memberNickname);
	         
	         rs= pstmt.executeQuery();
	         
	         if(rs.next()) {
	        	 result=rs.getInt(1); //번 컬럼의 결과를 result에 대입
	         }
	         
	      } finally {
	    	  
	    	  close(rs);
	           close(pstmt);
	        }
	      
	      return result;
	}

	public Member selectOne(Member mem, Connection conn) throws Exception{
		Member member = null;
	      
	      try {
	    	 String sql=prop.getProperty("selectOne");
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, mem.getMemberEmail());
	         
	         rs= pstmt.executeQuery();
	         
				if(rs.next()) {
					member=new Member();
					member.setMemberEmail(rs.getString("MEMBER_EMAIL"));
					member.setMemberNickname(rs.getString("MEMBER_NICK"));
					member.setMemberTel(rs.getString("MEMBER_TEL"));
					member.setMemberAddress(rs.getString("MEMBER_ADDR"));
					member.setEnrollDate(rs.getString("ENROLL_DT"));
				}
	         
	      } finally {
	    	  
	    	  close(rs);
	           close(pstmt);
	        }
	      
	      return member;
	}

	public Member selectAll(Member memberAll, Connection conn) throws Exception {
		Member member = null;
		Member members =null;
	      
	      try {
	    	 String sql=prop.getProperty("selectAll");
	         pstmt = conn.prepareStatement(sql);
	         
	         rs= pstmt.executeQuery();
	         
				while(rs.next()) {
					member=new Member();
					member.setMemberEmail(rs.getString("MEMBER_EMAIL"));
					member.setMemberNickname(rs.getString("MEMBER_NICK"));
					member.setMemberTel(rs.getString("MEMBER_TEL"));
					member.setMemberAddress(rs.getString("MEMBER_ADDR"));
					member.setEnrollDate(rs.getString("ENROLL_DT"));
					int count=1;
				}
	         
	      } finally {
	    	  
	    	  close(rs);
	           close(pstmt);
	        }
	      
	      return member;
	}

	
	
	/**회원 목록 조회 ajax
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public List<Member> selectAll(Connection conn) throws Exception {
		
		List<Member> memberList = new ArrayList<>(); //결과 저장용 변수
	      
	      try {
	    	 String sql=prop.getProperty("selectAll");
	    	 
	    	 stmt=conn.createStatement();
	         rs= stmt.executeQuery(sql);
	         
				while(rs.next()) {
					Member member=new Member();
					member.setMemberEmail(rs.getString("MEMBER_EMAIL"));
					member.setMemberNickname(rs.getString("MEMBER_NICK"));
					member.setMemberNo(rs.getInt("MEMBER_NO"));
					
					memberList.add(member); //리스트에 추가
				}
	         
	      } finally {
	    	  
	    	  close(rs);
	           close(pstmt);
	        }
	      
	      return memberList;
	}

	/** 프로필 이미지 변경
	 * @param memberNo
	 * @param profileImage
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public int profileChange(int memberNo, String profileImage, Connection conn) throws Exception {
		
		   int result = 0;
		      
		      try {
		         pstmt = conn.prepareStatement(prop.getProperty("changeProfile"));
		         pstmt.setString(1,profileImage);
		         pstmt.setInt(2, memberNo);
		         
		         result = pstmt.executeUpdate();
		         System.out.println(result);
		         
		      
		         
		      } catch (Exception e) {
		            e.printStackTrace();
		        } finally {
		           close(pstmt);
		        }
		      
		      return result;
	}
	
	

	
	

}
