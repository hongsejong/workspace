package edu.kh.oop.method.model.vo;

public class Member {
	
	
	//속성(필드)
	private String memberId; // 아이디
	private String memberPw; //비밀번호
	private String memberName; // 이름
	private int memberAge; // 나이
	//기본 생성자
	public Member() {
		
	}
	
	//매개변수 생성자
	public Member(String memberId, String memberPw, String memberName, int memberAge) {
		//오버로딩 적용(
		
		//전달 받은 값을 필드로 옮겨 담기
		
		this.memberId=memberId;
		this.memberPw=memberPw;
		this.memberName=memberName;
		this.memberAge=memberAge;
	}

	//기능 (getter / setter)
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

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public int getMemberAge() {
		return memberAge;
	}

	public void setMemberAge(int memberAge) {
		this.memberAge = memberAge;
	}
	
	
	
	// alt + shift + s -> generate toString.. -> Generate

	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", memberName=" + memberName + ", memberAge="
				+ memberAge + "]";
	}
	
	
	

}
