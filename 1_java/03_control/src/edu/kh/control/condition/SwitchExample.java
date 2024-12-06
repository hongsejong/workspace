package edu.kh.control.condition;

import java.util.Scanner;

public class SwitchExample { // 기능용 클래스


	/*switch문
	 *  -식 하나의 결과로 많은 경우의 수를 처리할 때 사용하는 조건문
	 * -> 식의 결과에 맞는 case 구문이 수행됨
	 * 
	 * [작성법]
	 * 
	 * switch(식){
	 * case 결과 1 : 결과1의 수행코드; break;
	 * case 결과 2 : 결과2의 수행코드; break;
	 * case 결과 3 : 결과3의 수행코드; break;
	 * ...
	 * 
	 * default : 모든 case가 만족하지 못할 때 수행할 코드;
	 * 
	 * }
	 * 
	 * */

	public void ex1() {
		Scanner sc = new Scanner(System.in);
		//키보드로 정수를 입력 받아
		// 1: RED
		// 2: ORANGE
		// 3: YELLOW
		// 4: GREEN
		// 5: BLUE
		// 1~5가 아니면 : WHITE

		//if 버전

		System.out.print("정수 입력 :");
		int c=sc.nextInt();	
		String result;
		/*
		if(c ==1) {
			result = "RED";
		}else if(c == 2){
				result = "ORANGE";

		}else if(c==3) {
				result = "YELLOW";

		}else if(c==4) {
				result = "GREEN";
		}else if(c==5) {
				result="BLUE";
		}else {
			result="WHITE";
	}*/
		//switch 버전
		//여러 값이 나오는 식
		switch(c) {
		case 1 : result = "RED" ;break;
		// number에 입력된 값이 1인 경우(case)
		// result 변수에 "RED"를 대입하고 switch문을 멈춤(break)
		case 2 : result = "ORANGE" ;break;
		case 3 : result = "YELLOW" ;break;
		case 4 : result = "GREEN" ;break;
		case 5 : result = "BLUE" ;break;

		default : result = "WHITE";
		// default == 기본값
		}
		System.out.println(result);
	}

	public void ex2() {

		//음료 결정 프로그램
		//난수(Math.random() ), switch문

		// 1부터 5사이의 난수를 발생시켜
		// 일치하는 수의 메뉴를 출력

		// Math.random() : 0.0 <= x < 1.0
		// Math.random() *5 : 0.0<= x * 5 < 5.0
		// Math.random() *5+1 : 1.0<= x * 5 + 1< 6.0
		//(int)(Math.random() * *5+1) : 1 <= (int)(x*5+1) <6

		//1 ~ 5 난수 생성
		int randomNumber = (int)(Math.random()*5+1);
		String menu= null; // null : 참조하는 것이 없다.

		switch(randomNumber) {

		case 1 : menu = "아이스 아메리카노" ; break;
		case 2 : menu = "바닐라 라떼" ; break;
		case 3 : menu = "녹차 라떼" ; break;
		case 4 : menu = "아인슈페너" ; break;
		case 5 : menu = "쑥라떼" ; break;


		}

		System.out.printf("가위바위보 진사람이 [%s] 사주기",menu);
	}

	public void ex3() {
		//달(월) 입력시 계절을 판별하겠다

		Scanner sc = new Scanner(System.in);
		System.out.println("달 입력 :");
		int m = sc.nextInt();
		String m2=null;

		// break : 멈추다
		// -> 해당 case를 수행한 후 switch문을 멈춰서 빠져 나가라

		// * break가 없는 경우 *
		// - break를 만나거나 swtich문이 끝날 때 까지
		// 다음 case를 연달아서 실행
		switch(m) {
		case 1: case 2: case 12 : m2 = "겨울"; break;
		case 3 : case 4 : case 5 : m2 = "봄"; break; 
		case 6 :  case 7 :  case 8 : m2 = "여름"; break;
		case 9 :  case 10 : case 11: m2 = "가을"; break;

		default : m2 = "해당하는 계절이 없습니다";
		}
		System.out.println(m2);
	}

	public void ex4() {

		//문자열로 메뉴를 입력 받아서
		//해당 메뉴의 가격을 출력

		//[실행화면]
		// 메뉴를 선택해주세요(치즈케이크, 티라미수, 고구마케이크) : 티라미수
		//티라미수의 가격은 6000원 입니다.

		// 메뉴를 선택해주세요(치즈케이크, 티라미수, 고구마케이크) : 딸기케이크
		//존재하지 않는 메뉴입니다.

		Scanner sc = new Scanner(System.in);
		System.out.print(" 메뉴를 선택해주세요(치즈케이크, 티라미수, 고구마케이크) :");
		String menu = sc.next();
		int price =0; // 가격 저장용 변수 선언

		switch(menu){

		case "치즈케이크" : price=5000; break;
		case "티라미수" : price=6000; break;
		case "고구마케이크" :price =4000; break;
		default : price = -1; // 잘못 입력되었다는 신호
		}

		// * 값에 의미를 부여해서 조건식에 사용하는 방법을 이해해야 한다! *

		if(price== -1) { // 없는 메뉴를 선택했을 경우
			System.out.println("존재하지 않는 메뉴입니다");
		}else { // 제대로 입력한 경우
			System.out.printf("%s의 가격은 %d원 입니다.",menu,price);	
		}


	}
	public void ex5() {
		// 산술 연산 계산기

		// - 두 정수와 1개의 연산자(+-*/%)를 입력 받아 연산 결과를 출력

		// [실행화면]
		// 정수1 입력 : 5
		// 연산자 : +
		// 정수2 입력 : 4
		// 5 + 4 = 9

		// 정수1 입력 : 5
		// 연산자 : /
		// 정수2 입력 : 4
		// 5 / 4 = 1

		// 정수1 입력 : 2
		// 연산자 : @
		// 정수2 입력 : 10
		// 존재하지 않는 연산자 입니다.
		Scanner sc = new Scanner(System.in);
		System.out.print("정수1 입력 :");
		int num1 = sc.nextInt();
		System.out.print("연산자 입력 : ");
		String op= sc.next();// 한글자만 입력해도 String
		// sc.nextChar(); 스캐너는 문자 하나(char)를 입력 받는 기능이 별도로 없음
		// (char)'a' !=(String)"a"
		System.out.print("정수2 입력 :");
		int num2 = sc.nextInt();
		int result=0; //계산 결과 저장용 변수 선언 및 초기화
		//-> 초기화를 했기 떄문에
		//	switch문에서 값이 대입되지 않아도
		// 이후 result 출력 시 문제 발생 X

		boolean flag= false; // 신호용 변수 (상태를 기록하고 조건문에 사용할 변수)
		// false일 때 : 연산자를 정상 입력
		// true 일 때 : 연산자를 잘못 입력
		/*
		switch(op) {

		case"+": result = num1+num2; break;
		case"-": result = num1-num2; break;
		case"*": result = num1*num2; break;
		case"%": result = num1%num2; break;
		case"/":  


			if(num2==0) {//오류가 발생되는 경우
				System.out.println("0으로 나눌 수 없습니다.");
			}else {
				result=num1/num2;
			}

		default : flag=true;// 연산자를 잘못 입력 -> flag를 true로 변경

		}
		if(!flag) { //정상 입력 == flag가 false // flag == false와 같은 결과
			System.out.printf("%d %s %d = %d", num1,op,num2,result);
		}else {
			System.out.println("존재하지 않는 연산자입니다.");


		}
		 */

		switch(op) {
		//case에 작성되는 값은 switch식의 결과값 자료형의 리터럴 표기법이다!

		case "+" : System.out.printf("%d %s %d = %d", num1,op,num2,num1+num2); break;
		case "-" : System.out.printf("%d %s %d = %d", num1,op,num2,num1-num2); break;
		case "*" : System.out.printf("%d %s %d = %d", num1,op,num2,num1*num2); break;
		case "/" : 
			if(num2==0) { //오류가 발생되는 경우
				System.out.println("0으로 나눌수 없습니다."); break;
			}else {

				System.out.printf("%d %s %d = %d", num1,op,num2,num1/num2);break;
			}

		case "%" : System.out.printf("%d %s %d = %d", num1,op,num2,num1%num2); break;
		default : System.out.println("존재하지 않는 연산자입니다.");
		}







	}



}











