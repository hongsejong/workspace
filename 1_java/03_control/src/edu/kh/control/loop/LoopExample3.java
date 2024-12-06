package edu.kh.control.loop;

import java.util.Scanner;

public class LoopExample3 {

	/* while문
	 * -반복 조건만을 설정하는 반복문
	 *  별도의 초기식, 증감식이 존재하지 않음
	 * -조건식이 true인 경우에 계속 반복
	 * ->조건식이 false가 되는 상황을 같이 구현해야함
	 * 
	 * [작성법]
	 * while(조건식){
	 * 		조건식이 true일 때 수행할 구문;
	 * 		(+조건식이 특정 상황에서 false가 되도록 하는 구문도 같이 작성
	 * 			->안하면 무한루프)
	 * }
	 * 
	 **/

	public void ex1() {
		//숫자 0이 입력 될 때 까지 프로그램 종료 X
		Scanner sc= new Scanner(System.in);
		int input=-1;
		
		while(input!=0) { //input이 0이 아닐때 true
			
			// true일 때 반복 수행
			System.out.print("숫자를 입력해 주세요 : ");
			 input= sc.nextInt();
		}
		
		
	}
	//숫자 0이 입력 될 때 까지 프로그램 종료 X
	//+0이 입력 되기 전까지 입력된 모든 숫자의 합 출력
	// 정수 입력:5
	// 정수 입력:4
	// 정수 입력:2
	// 정수 입력:0
	//입력된 정수의 합 : 11
	public void ex2() {
		Scanner sc = new Scanner(System.in);
		int input=-1; //입력 값 저장 + while문이 종료되지 않게하는 값 대입
		int sum=0; //합계 저장용 변수
		while(input!=0) {
			System.out.print("정수 입력 : ");
			input=sc.nextInt();
			sum+=input; //입력한 값을 sum에 누적
		}
		System.out.print("입력된 정수의 합 :  "+ sum);
			
	}
	
	// 분식집 주문 프로그램
	public void ex3() {
		
		Scanner sc = new Scanner(System.in);
		
		int input=0; // 입력한 메뉴 번호를 저장할 변수
		String menu = ""; //빈문자열 -> 문자열을 이어쓰기 하더라도 아무런 영향이 없는 문자열
		int sum=0; //주문한 메뉴의 가격 합계를 저장할 변수
		while(input !=9) { //9 입력 시 조건이 false가 되어 while문 종료
			
			System.out.println("=== 메뉴 ===");
			System.out.println("1.벌교꼬막미니김밥(14000)");
			System.out.println("2.키토삼겹묵은지(12500)");
			System.out.println("3.왕돈까스(14500)");
			System.out.println("9.주문 완료");
			
			System.out.print("메뉴 선택 >>>");
			input = sc.nextInt(); // input에 메뉴 번호 저장
			switch(input) {
		
			case 1: sum +=14000; menu+="벌교꼬막미니김밥 " ;break; 
			case 2: sum +=12500; menu+="키토삼겹묵은지 "; break;
			case 3: sum +=14500; menu+="왕돈까스 " ;break;
			case 9: System.out.println("주문 완료!"); break;
			default : System.out.println("<<메뉴에 작성된 번호만 눌러주세요.>>"); 
			}
			
		}
		System.out.println("주문한 메뉴 :" +menu);
		System.out.println("총 가격 : " +sum);
		//주문한 메뉴 : 벌교꼬막미니김밥 키토삼겹묵은지
		//총 가격 : 26500
		
	}
	// do-while문 : 최소 1번은 수행
	// while문의 조건식 확인 위치를 끝으로 보낸 while
	public void ex4() {
		
		Scanner sc = new Scanner(System.in);
		int sum=0; //합계
		
		int input=0; // 입력 값을 저장할 변수
		do{
			System.out.print("숫자 입력 :");
			input = sc.nextInt();
			sum+=input;
		}while(input!=0);
		
		System.out.println("합계 : " + sum);
	}
	
	
	

}
