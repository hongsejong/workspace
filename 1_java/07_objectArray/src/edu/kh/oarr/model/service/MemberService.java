package edu.kh.oarr.model.service;

import java.util.Arrays;
import java.util.Scanner;

import edu.kh.oarr.model.vo.Member;

public class MemberService {

	//Member 5칸짜리 객체 배열 선언 및 할당
	private Member[] memberArr = new Member[5];

	//현재 로그인한 회원의 정보를 저장할 변수 선언
	private Member loginMember =null;
	private Scanner sc = new Scanner(System.in);

	public MemberService() { // 기본 생성자

		// memberArr 배열 0,1,2인덱스 초기화
		memberArr[0]= new Member("user01", "pass01", "유저일", 20,"제주도" );
		memberArr[1]= new Member("user02", "pass02", "유저이", 80,"서울" );
		memberArr[2]= new Member("user03", "pass03", "유저삼", 33,"부산" );
		memberArr[3]= new Member("user04", "pass04", "유저사", 33,"부산" );

	}

	public void displayMenu() { //메뉴 화면 출력 기능

		int menuNum=0; //메뉴 선택용 변수

		do {
			System.out.println("==== 회원 정보 관리 프로그램 v2====");
			System.out.println("1.회원가입");
			System.out.println("2.로그인");
			System.out.println("3.회원 정보 조회");
			System.out.println("4.회원 정보 수정");
			System.out.println("5.회원 검색(지역)");
			System.out.println("0.프로그램 종료");

			System.out.println("메뉴 입력 >>");
			menuNum=sc.nextInt(); //필드에 작성된 Scanner sc 사용

			switch(menuNum) {
			case 1 : System.out.println( signUp() );  break;
			case 2 : System.out.println( login() );break;
			case 3 : System.out.println(selectMember());break;
			case 4 : System.out.println(updateMember());break;
			case 5 : searchRegion();break;
			case 0 : System.out.println("프로그램을 종료합니다.");  break;

			default:System.out.println("잘못 입력하셨습니다. 다시 입력해주세요");
			}
		}while(menuNum !=0);
	}

	//회원가입 메소드

	public String signUp() {
		System.out.println("\n*****회원 가입 *****");
		// 객체 배열(memberArr)에 가입한 회원 정보를 저장할 예정
		// -> 새로운 회원 정보를 저장할 공간이 있는지 확인하고
		//	  빈 공간의 인덱스 번호 얻어오기 --> 새로운 메소드 작성

		int index = emptyIndex(); //memberArr 배열에서 비어있는 인덱스를 반환 받음


		if(index== -1) { //비어있는 인덱스가 없을 경우 -> 회원 가입 불가
			return "회원 가입이 불가능합니다.(인원 수 초과)";
		}
		System.out.println("현재 회원 수 :"+index);
		System.out.print("아이디 : ");
		String memberId=sc.next();

		System.out.print("비밀번호 :");
		String memberPw=sc.next();

		System.out.print("비밀번호 확인:");
		String memberPw2=sc.next();

		System.out.print("이름 :");
		String memberName=sc.next();

		System.out.print("나이 :");
		int memberAge=sc.nextInt();
		System.out.print("지역 :");
		String region=sc.next();

		//비밀번호, 비밀번호 확인 일치시 회원가입
		if(memberPw.equals(memberPw2)) {

			//Member 객체를 생성해서 할당된 주소를 memberArr의 비어있는 인덱스에 대입
			memberArr[index]= new Member(memberId, memberPw, memberName, memberAge, region);
			return "회원가입 성공~!";
		}

		return "회원 가입 실패(비밀번호 불일치)";

	}

	// memberArr의 비어있는 인덱스 번호를 반환하는 메소드
	// 단, 비어있는 인덱스가 없으면 -1 반환
	public int emptyIndex() {

		// memberArr 배열을 0번 인덱스부터 끝까지 접근해서
		// 참조하는 값이 null인 경우의 인덱스 반환
		for(int i=0; i<memberArr.length;i++) {
			if(memberArr[i]==null) {
				return i; // 현재 메소드를 종료하고 호출한 곳으로 i값을 가지고 돌아감
			}
		}
		return -1;

		//for문을 수행했지만 return이 되지 않았다
		// == 배열에 빈칸이 없다. == -1 반환



	}

	//로그인 메소드
	public String login() {
		System.out.println("\n*****로그인*****");
		System.out.print("아이디 입력 :");
		String memberId = sc.next();

		System.out.print("비밀번호 입력 :");
		String memberPw = sc.next();

		//로그인 성공시
		//000님 환영합니다.

		//로그인 실패 시
		//아이디 또는 비밀번호가 일치하지 않습니다.

		//memberArr 배열 내 요소를 순서대로 접근
		for(int i=0; i<memberArr.length; i++) {
			if(memberArr[i]!=null) { //회원의 정보가 있을 경우
				//2)회원 정보의 아이디(memberArr[i], 비밀번호와
				//	입력 받은 아이디(memberId),비밀번호(memberPw)가 같은지 확인
				if(memberArr[i].getMemberId().equals(memberId) && memberArr[i].getMemberPw().equals(memberPw)) {
					loginMember=memberArr[i];
					break; // 더이상 같은 아이디, 비밀번호가 없기 떄문에 for문 종
				}
			}
		}
		//4) 로그인 성공,실패 여부에 따라서 결과값 변환
		if(loginMember!=null) { //로그인 성공 시
			return loginMember.getMemberName()+"님 환영합니다.";

		}else { //로그인 실패 시
			return "아이디 또는 비밀번호가 일치하지 않습니다.";
		}

	}

	//회원 정보 조회 메소드
	public String selectMember() {

		// 1) 로그인 여부 확인
		// 로그인이 안되어있는경우 " 로그인 후 이용해주세요." 반환

		//2) 로그인이 되어있는 경우
		// 회원정보를 출력할 문자열을 만들어서 반환
		// (단, 비밀번호는 제외)

		//이름 :
		//아이디 :
		//나이 :
		//지역 :
		if(loginMember==null) {
			return "로그인 후 이용해주세요.";
		}else { 
			return loginMember.toString();

		}
	}

	public String updateMember() {
		System.out.println("\n*****회원 수정 *****");
		if(loginMember==null) {
			return " 로그인 후 이용해주세요.";
		}else {
			System.out.print("수정할 이름 : ");
			String updateName=sc.next();
			System.out.print("수정할 나이 : ");
			int updateAge=sc.nextInt();
			System.out.print("수정할 지역 : ");
			String updateRegion=sc.next();

			System.out.println("비밀번호 입력 :");
			String inputPw = sc.next();

			if(loginMember.getMemberPw().equals(inputPw)) {

				loginMember.setMemberName(updateName);
				loginMember.setMemberAge(updateAge);
				loginMember.setregion(updateRegion);
				return "수정 완료";
			}else {
				return " 비밀번호가 다름";
			}
		}
	}

	public void searchRegion() {
		System.out.println("\n*****회원 검색(지역) *****");

		System.out.print("검색할 지역을 입력하세요 :");
		String inputRegion = sc.next();
		//있을경우
		//아이디 : user02, 이름: 유저이
		//아이디 : user04, 이름: 유저사
		boolean flag=true;
		//1) memberArr 배열의 모든 요소 순차 접근
		for(int i=0; i<memberArr.length;i++) {
			
			// 2)memberArr[i] 요소가 null 인 경우 반복 종료
			if(memberArr[i] == null) {
				break;
			}
			
			//3)memberArr[i] 요소에 저장된 지역이
			//	입력 받은 지역과 같을 경우 회원의 아이디, 이름 출력
			if(memberArr[i].getregion().equals(inputRegion)) {
				System.out.printf("아이디 : %s , 이름 : %s\n",memberArr[i].getMemberId(),memberArr[i].getMemberName());
				flag=false;
			}
		}
		if(flag) {
			System.out.print("일치하는 결과가 없습니다.\n");
		}

		//없을경우
		//일치하는 검색 결과가 없습니다.




		}








	}
