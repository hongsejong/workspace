package edu.kh.jdbc.member.view;

import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc.member.dto.Member;
import edu.kh.jdbc.member.model.dao.MemberDAO;
import edu.kh.jdbc.member.model.service.MemberService;

public class MemberView { //회원 관련 화면 입출력

	private Scanner sc = new Scanner(System.in);

	//회원 관련 서비스 제공 객체 생성 및 참조
	private MemberService service = new MemberService();




	/**
	 * 회원 가입 화면 출력용 메소드
	 */
	public void signUp() {
		System.out.println("[회원 가입]");



		try {
			String memberId = null;
			String memberPw = null;
			String memberPw2 = null;
			String memberNm = null;
			char memberGender =' ';
			while(true) {

				System.out.print("아이디 :");
				memberId=sc.next();

				//아이디 중복 검사 (DB에 일치하는 아이디가 있으면 " 중복" -> 다시 아이디 입력받기)
				int result = service.duplicateCheck(memberId);

				if(result ==0) {
					System.out.println("사용 가능한 아이디 입니다.");
					break;
				}else {
					System.out.println("이미 사용중인 아이디 입니다. 다시 입력해주세요");
				}
			} //아이디 중복 검사 while 종료

			//비밀번호, 비밀번호 확인을 각각 입력 받아서 
			//일치할 때 까지 무한 반복
			while(true) {
				System.out.print("비밀번호 :");
				memberPw=sc.next();
				System.out.print("비밀번호 확인:");
				memberPw2=sc.next();
				if(memberPw.equals(memberPw2)) {
					System.out.println("비밀번호 일치");
					break;
				}else {
					System.out.println("비밀번호가 일치하지 않습니다 다시 입력해주세요.");
				}
			}


			//이름 입력
			System.out.print("이름 입력 :");
			memberNm= sc.next();

			//성별이 'M' 또는 'F'가 입력될 때 까지 반복
			while(true) {
				System.out.print("성별 입력(M/F)");
				memberGender =sc.next().toUpperCase().charAt(0);
				if(memberGender!='M' && memberGender!='F') {
					System.out.println("성별은 M 또는 F만 입력해주세요.");
				}else {
					break;
				}
			}

			//입력 받은 값을 하나의 객체(Member)에 저장

			Member signUpMember = new Member(memberId, memberPw2, memberNm, memberGender);

			//회원 가입 Service 호출 후 결과 반환 받기

			int result = service.signUp(signUpMember);

			// service 결과에 따른 화면 처리
			if(result==0) {
				System.out.println("회원가입 실패");
			}else {
				System.out.println("회원가입 성공");
			}

		}catch(Exception e) {
			e.printStackTrace();
		}

	}
	public void login() {
		System.out.print("아이디 입력 :");
		String inputId=sc.next();
		System.out.print("비밀번호 입력 :");
		String inputPw=sc.next();

		int result = 0;
		try {
			result = service.login(inputId,inputPw);
		} catch (Exception e) {
			e.printStackTrace();
		}




		if(result==0) {
			System.out.println("로그인 실패");
		}else {
			System.out.println("로그인 성공");
		}
	}
	/**로그인 메소드
	 * @return loginMember
	 */
	public Member login_1() {
		System.out.println("[로그인]");

		System.out.print("아이디 : ");
		String memberId=sc.next();
		System.out.print("비밀번호 :");
		String memberPw=sc.next();

		//로그인 Service 수행 후 결과 반환 받기
		Member loginMember = null;
		try {
			loginMember = service.login_1(memberId, memberPw);

			if(loginMember != null) { //로그인 성공 시
				System.out.println("\n*** " + loginMember.getMemberNm()+ "님 환영합니다");
			}else {//로그인 실패(아이디 / 비밀번호 불일치 또는 탈퇴한 회원)
				System.out.println("\n아이디 또는 비밀번호가 일치하지 않습니다.\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return loginMember;
	}
	public void myInfo(Member loginMember) {
		System.out.println("[내 정보 조회]");
		System.out.println(loginMember);

		System.out.println("회원 번호 : " + loginMember.getMemberNo());
		System.out.println("아이디 : " + loginMember.getMemberId());
		System.out.println("이름 : " + loginMember.getMemberNm());
		System.out.println("성별 : " + loginMember.getMemberGender());
		System.out.println("가입일 : " + loginMember.getEnrollDate());

	}
	public void selectAll() {
		System.out.println("[가입된 회원 목록 조회]");
		try {
			//회원 정보 조회 Service 호출 후 결과 반환 받기
			List<Member> memberList = service.selectAll();

			if(memberList.isEmpty()) { //비어있음 == 조회 결과 없음
				System.out.println("조회 결과가 없습니다.");

			}else {
				//향상된 for문 
				for(Member mem : memberList) {
					System.out.printf("%10s %10s %s\n",mem.getMemberId(),
							mem.getMemberNm(),mem.getEnrollDate().toString());
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}




	}
	/**내 정보 수정
	 * @param loginMember
	 */
	public void updateMyinfo(Member loginMember) {
		System.out.println("[내 정보 수정(이름,성별)");

		System.out.println("변경할 이름 : ");
		String memberName = sc.next();

		System.out.println("변경할 성별(M/F)");
		char memberGender = sc.next().toUpperCase().charAt(0);

		//입력 받은 값 + 로그인한 회원 번호를 하나의 Member 객체에 저장
		int result = 0;
		try {
			result = service.updateMyinfo(memberName,memberGender, loginMember);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if(result==0) {
			System.out.println("수정 실패");
		}else {
			System.out.println("수정 성공");
		}




	}
	public void updateMyinfo_1(Member loginMember) {
		System.out.println("[내 정보 수정(이름,성별)");

		System.out.println("변경할 이름 : ");
		String memberName = sc.next();

		System.out.println("변경할 성별(M/F)");
		char memberGender = sc.next().toUpperCase().charAt(0);

		//입력 받은 값 +로그인한 회원 번호를 하나의 객체로 전달

		Member updateMember = new Member();
		updateMember.setMemberNm(memberName);
		updateMember.setMemberGender(memberGender);
		updateMember.setMemberNo(loginMember.getMemberNo());

		try {
			int result = service.updateMyinfo_1(updateMember);

			if(result != 0) {
				System.out.println("\n회원 정보가 수정되었습니다\n");

				//DB의 수정된 내용과 현재 로그인한 회원 정보 일치 시키기
				//얕은 복사 : 참조 주소만 복사하여 같은 객체를 참조
				//-> 특징 : 복사된 주소(매개변수 loginMember)를 참조하여 수정하면
				//		  원본 객체(MainView loginMember)가 수정된다.

				loginMember.setMemberNm(memberName);
				loginMember.setMemberGender(memberGender);
			}else {
				System.out.println("\n회원 정보 수정에 실패했습니다.\n");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}





	/**
	 * 비밀번호 변경
	 */
	public void updatePw(Member loginMember) {
		String newPw=null;
		String newPw2=null;
		System.out.println("[비밀번호 변경]");

		System.out.println("현재 비밀번호 : ");
		String currentPw=sc.next();
		sc.nextLine();

		System.out.println(loginMember.getMemberPw());

		if(currentPw.equals(loginMember.getMemberPw())) {
			//새 비밀번호 , 새 비밀번호 확인이 서로 일치할 때 까지 입력
			while(true) {
				System.out.println("새 비밀번호 입력 :");
				newPw=sc.next();
				System.out.println("새 비밀번호 입력 :");
				newPw2=sc.next();

				if(newPw.equals(newPw2)) {
					//					int result = service.updatePw(loginMember);
					break;
				}else {
					System.out.println("새 비밀번호가 일치하지 않습니다. 다시입력해주세요\n");
				}

			}

			try {
				int result = service.updatePw(loginMember.getMemberNo(),currentPw,newPw);

				if(result>0) {
					System.out.println("\n비밀번호가 변경되었습니다\n");
				}else {
					System.out.println("\n비밀번호 변경 실패\n");
				}

			}catch(Exception e) {
				e.printStackTrace();
			}

		}else {
			System.out.println("비밀번호가 다릅니다");
		}



		// 성공 : "비밀번호가 변경되었습니다."

		// 실패 : "비밀번호 변경 실패"
	}




	/**회원 탈퇴
	 * @param loginMember
	 */
	public Member secession(Member loginMember) {
		
		//loginMember = null;
		//매개변수로전달 받은 값(주소 복사본)을 저장 할 뿐이다.
		//-> 복사본이 사라진다고 해도 원본(MainView의 loginMember)은 사라지지 않는다!
		//--> 로그아웃이 안됨!!
		
		System.out.println("[회원 탈퇴]");
		//1. 현재 비밀번호 입력 받기
		System.out.println("현재 비밀번호를 입력하세요 :");
		String currentPw=sc.next();

		// 2. 정말 탈퇴하시겠습니까?(y/n)
		System.out.println("정말 탈퇴하시겠습니까?(y/n) :");
		char ch = sc.next().toUpperCase().charAt(0);

		int result=0;
		if(ch =='Y') {

			try {
				result = service.secession(loginMember.getMemberNo(), currentPw);
				if(result>0) {
					System.out.println("탈퇴 되었습니다.");
					 loginMember=null;
				}else {
					System.out.println("비밀번호 일치안함");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}


		}else {
			System.out.println("회원 탈퇴 취소");

			//3. (Y 입력 시) 탈퇴 Service 수행

			//4. 성공 : "탈퇴 되었습니다." -> 로그아웃
			//	 실패 : "비밀번호가 일치하지 않습니다."

			//5. (N입력시 ) 회원 탈퇴 취소
		}
		return loginMember;


	}
}
