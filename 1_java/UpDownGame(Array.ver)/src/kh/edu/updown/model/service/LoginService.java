package kh.edu.updown.model.service;


import java.util.Random;
import java.util.Scanner;

import kh.edu.updown.model.vo.Member;

public class LoginService {
	
	private Scanner sc = new Scanner(System.in);

	// 업다운 게임 시작
	// 1 ~ 100 사이 숫자 중 랜덤하게 한 숫자를 지정하고 업/다운 게임을 진행
	// 맞춘 횟수가 현재 로그인한 회원의 최초 또는 최고 기록인 경우 회원의 highScore 필드 값을 변경
	public void startGame(Member loginMember) {
		
		System.out.println("[Game Start...]");
		Random random = new Random();
		int random1=random.nextInt(101);
		int count=1;
		while(true) {
			System.out.print(count+"번째 입력 :");
			int input =sc.nextInt();
			sc.nextLine();
			
			if(input==random1) {
				System.out.println("정답!!");
				System.out.println("입력 시도 횟수 : "+ count);
				if(loginMember.getHighScore()==0 || loginMember.getHighScore()>count) {
					System.out.print("***최고 기록 달성 ***");
					
					loginMember.setHighScore(count);
				}
				break;
			}
			if(input<random1) {
				System.out.println("-- UP  --");
			}
			if(input>random1) {
				System.out.println("-- DOWN --");
			}
			count++;
		}
		
		
	}

	
	// 내 정보 조회
	// 로그인한 멤버의 정보 중 비밀번호를 제외한 나머지 정보만 화면에 출력
	public void selectMyInfo(Member loginMember) {
		
		System.out.println("[내 정보 조회]");
		System.out.print("아이디 : "+loginMember.getMemberId()+"\n"+"이름 : "+loginMember.getMemberName()+"\n"+"최고점수 : " +loginMember.getHighScore()+"회");
	}

	// 전체 회원 조회
	// 전체 회원의 아이디, 이름, 최고점수를 출력
	public void selectAllMember(Member[] members) {
		
		System.out.println("[전체 회원 조회]");
		System.out.println("[아이디]\t[이름]\t[최고점수]");
		for(int i=0; i<members.length; i++) {
			if(members[i]!=null) {
				System.out.print(members[i].getMemberId()+"\t"+members[i].getMemberName()+ "\t" +members[i].getHighScore()+"회\n");
			}
		}
		
	}

	// 비밀번호 변경
	// 현재 비밀번호를 입력 받아 
	// 같은 경우에만 새 비밀번호를 입력 받아 비밀번호 변경
	public void updatePassword(Member loginMember) {
	
		
		System.out.println("[비밀번호 변경]");
		
		System.out.print("현재 비밀번호 입력 :");
		String userPw=sc.nextLine();
		if(loginMember.getMemberPw().equals(userPw)) {
			System.out.print("새 비밀번호 :");
			String newPw=sc.nextLine();
			loginMember.setMemberPw(newPw);

			
				
				
			
			System.out.println("비밀번호가 변경되었습니다.");
			return;
		}else {
			System.out.print("현재 비밀번호가 일치하지 않습니다.");
		}
		
		
		
		
		
	}

	
	
}
