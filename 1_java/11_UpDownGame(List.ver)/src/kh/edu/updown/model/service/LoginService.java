package kh.edu.updown.model.service;

import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import kh.edu.updown.model.vo.Member;

public class LoginService  {
	
	private Scanner sc = new Scanner(System.in);

	// 업다운 게임 시작
	// 1 ~ 100 사이 숫자 중 랜덤하게 한 숫자를 지정하고 업/다운 게임을 진행
	// 맞춘 횟수가 현재 로그인한 회원의 최초 또는 최고 기록인 경우 회원의 highScore 필드 값을 변경
	public void startGame(Member loginMember) {
		
		System.out.println("[Game Start...]");
		
		
	      int random = (int)(Math.random() * 100 + 1);
	      
	      int count = 0; // 입력 시도 횟수 카운트
	      
	      try {
	         
	         while(true) {
	            
	            count++; // while문이 반복 될 때 마다 count를 1씩 증가시킴
	            
	            System.out.print( count + "번째 입력 : ");
	            int input = sc.nextInt();
	            sc.nextLine();
	            
	            if(random == input) { // 입력된 값이 발생한 난수와 같다면
	               System.out.println("정답!!");
	               System.out.println("입력 시도 횟수 : " + count);
	               
	               // 입력 시도 횟수가 최초 또는 최고 기록인 경우
	               if(loginMember.getHighScore() == 0 || loginMember.getHighScore() > count ) {
	                  System.out.println("*****최고 기록 달성******");
	                  
	                  // 매개변수로 전달 받은 loginMember의 highScore 필드에 시도 횟수 저장
	                  loginMember.setHighScore(count);
	               }
	               break; // while문 종료
	               
	            } else { // 입력된 값이 발생한 난수와 같지 않다면
	               if(random < input) {
	               
	                  System.out.println("-- DOWN --");
	               }else {
	                  System.out.println("-- UP --");
	               }
	               
	            }
	            
	            
	         }
	         
	      }catch(InputMismatchException e) {
	         System.out.println("1~100 사이 정수만 입력해주세요.");
	         sc.nextLine();
	      }
	      
	      
	   }
		
	

	
	// 내 정보 조회
	// 로그인한 멤버의 정보 중 비밀번호를 제외한 나머지 정보만 화면에 출력
	public void selectMyInfo(Member loginMember) {
		
		System.out.println("[내 정보 조회]");
		
		System.out.println(loginMember.toString());
		
	}

	// 전체 회원 조회
	// 전체 회원의 아이디, 이름, 최고점수를 출력
	public void selectAllMember(List<Member> members) {
		
		System.out.println("[전체 회원 조회]");
		
//		for(int i=0; i<members.size(); i++) {
//			System.out.println(members.get(i).toString());
			
//		}
		
		for(Member m : members) {
			System.out.println(m.toString());
		}
		
		
	}

	// 비밀번호 변경
	// 현재 비밀번호를 입력 받아 
	// 같은 경우에만 새 비밀번호를 입력 받아 비밀번호 변경
	public void updatePassword(Member loginMember) {
		
		System.out.println("[비밀번호 변경]");
		
		System.out.println("현재 비밀번호 입력 :");
		String inputPw=sc.next();
		if(loginMember.getMemberPw().equals(inputPw)) {
			System.out.println("새 비밀번호 입력 :");
			String inputPw2=sc.next();
			loginMember.setMemberPw(inputPw2);
		}else {
			System.out.println("비밀번호가 다름");
		}
		
		
	}



	
	
}
