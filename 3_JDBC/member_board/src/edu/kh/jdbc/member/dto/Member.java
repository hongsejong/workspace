package edu.kh.jdbc.member.dto;

import java.sql.Date;

public class Member {
	
	private int memberNo;
	private String memberId;
	private String memberPw;
	private String memberNm;
	private char memberGender;
	private Date enrollDate;
	public Member(String memberId, String memberPw, String memberNm, char memberGender) {
		super();
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.memberNm = memberNm;
		this.memberGender = memberGender;
	}
	private char secessionFl;
	public Member(int memberNo, String memberId, String memberPw, String memberNm, char memberGender, Date enrollDate,
			char secessionFl) {
		super();
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.memberNm = memberNm;
		this.memberGender = memberGender;
		this.enrollDate = enrollDate;
		this.secessionFl = secessionFl;
	}
	public Member() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return " [회원번호=" + memberNo + " 아이디=" + memberId + "  회원이름="
				+ memberNm + " 성별=" + memberGender + " 가입일=" + enrollDate + " ]";
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPw() {
		return memberPw;
	}
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}
	public String getMemberNm() {
		return memberNm;
	}
	public void setMemberNm(String memberNm) {
		this.memberNm = memberNm;
	}
	public char getMemberGender() {
		return memberGender;
	}
	public void setMemberGender(char memberGender) {
		this.memberGender = memberGender;
	}
	public Date getEnrollDate() {
		return enrollDate;
	}
	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}
	public char getSecessionFl() {
		return secessionFl;
	}
	public void setSecessionFl(char secessionFl) {
		this.secessionFl = secessionFl;
	}

}


