package edu.kh.control.loop;

import java.util.Scanner;

public class LoopExample2 {


	//구구단 2단 출력하기
	//2x1=2
	//2x2=4
	//2x3=6
	//2x4=8
	//2x5=10
	//2x6=12
	//2x7=14
	//2x8=16
	//2x9=18

	public void ex1() {

		for(int i=1; i<=9; i++) {
			System.out.printf("2 X %d= %d\n",i,2*i);
		}
	}
	// 입력 받은 단 출력하기

	// [실행 화면]
	// 단 입력 : 3

	// [구구단 3단]
	// 3 x 1 = 3
	// 3 x 2 = 6
	// ...
	// 3 x 9 = 27

	public void ex2() {

		Scanner sc = new Scanner(System.in);
		System.out.print("단 입력 :");
		int dan=sc.nextInt();
		System.out.println("[구구단 "+ dan + "단]");
		for(int i=1; i<=9; i++) {
			System.out.printf("\n%d X %d = %d",dan,i,dan*i);
		}
	}
	//입력 받은 단 출력하기
	//단, 입력 받은 값이 2~9 사이 일때만 구구단 출력
	//2~9가 아닐경우 "잘못 입력하셨습니다."

	public void ex3() {

		Scanner sc = new Scanner(System.in);
		System.out.print("단 입력 :");
		int dan = sc.nextInt();
		System.out.println("[구구단 "+ dan + "단]");
		if(dan>=2 && dan<=9) { // 2~9 사이일 때만
			for(int i=1; i<=9; i++) {
				System.out.printf("\n%d X %d = %d",dan,i,dan*i);
			}

		}else {
			System.out.println("잘못 입력 하셨습니다.");
		}

	}
	//for 예제 12- 시작,끝,증가할 수를 입력 받아
	//			지정된 범위까지 모두 출력 후
	//			합계도 출력
	//ex)
	//시작 : 1
	//끝: 5
	//증가할 수 :2
	// 1 3 5
	// 합계 : 9


	// for 예제 14 - 1부터 10까지 모든 정수 출력
	//				단, 홀수는 () 감싸서 출력
	//				+1은 "시작" 10은"끝" 이라고 출력

	// 시작 2 (3) 4 (5) 6 (7) 8 (9) 끝


	//중첩반복문
	//for 예제 4- 구구단 모두 출력하기
	public void ex4() {


		for(int dan = 2; dan<=9; dan++) { // 2~9단까지 차례로 증가

			//안쪽 for문이 반복하면서 하나의 단을 한 줄로 출력
			for(int num=1; num<=9; num++) { //각 단에 곱해질 수 1~9 //차례대로 증가

				System.out.printf("%2d X %2d = %2d ",dan,num,dan*num);

			}
			System.out.println(); //아무 내용 없는 println == 줄바꿈


		}

		// 이중 for문 예제5 - 구구단 역순 출력

		//9단 -> 2단 역방향
		//곱해지는 수는 1->9 정방향
	}
	public void ex5() {

		for(int dan=9; dan>=2; dan--) { //단 출력 9->2

			for(int num=1; num<=9; num++) { // 곱해지는 수 출력 1->9

				System.out.printf("%2d X %2d = %2d " , dan,num,dan*num);
			}

			System.out.println();
		}


	}
	//중첩 for 예제 6
	//12345
	//12345
	//12345
	//12345
	//12345
	public void ex6() {

		for(int i=1; i<=5; i++) { //5바퀴 반복하는 for문

			for(int j=1; j<=5; j++) { // 12345 한 줄 출력하는 for문
				System.out.print(j);
			}
			System.out.println(); //줄바꿈
		}

	}

	//중첩 for 예제 7
	//4321
	//4321
	//4321
	//4321

	public void ex7() {

		for(int i=1; i<=3; i++) { //줄반복

			for(int j=4; j>=1; j--) { //출력 반복
				System.out.print(j);
			}
			System.out.println();
		}

	}

	//구구단 출력하기
	//[조건]
	// 1입력시 2~9 순서로 출력
	// 2입력시 9~2 순서로 출력
	// 1,2 아니라면 "잘못 입력하셨습니다."




	public void ex8() {

		Scanner sc = new Scanner(System.in);
		System.out.println("1) 2-9순서대로  / 2) 9-2역순으로");
		int input=sc.nextInt();

		if(input==1) {
			for(int dan=2; dan<=9; dan++) {
				System.out.println("["+dan+"단]"); //제목
				for(int i=1; i<=9; i++) {
					System.out.printf("%2d X %2d = %2d\n",dan,i,dan*i);
				}
				System.out.println("=====================");
			}
		}else if(input==2) {
			for(int dan=9; dan>=2; dan--) {
				System.out.println("["+dan+"단]");
				for(int i=1; i<=9; i++) {
					System.out.printf("%2d X %2d = %2d\n",dan,i,dan*i);
				}
				System.out.println("=====================");
			}
		}else {
			System.out.println("잘못 입력 하셨습니다.");
		}

	}

	public void ex8_1(){ // 위에 문제 switch 문으로
		Scanner sc = new Scanner(System.in);
		System.out.println("1) 2-9순서대로  / 2) 9-2역순으로");
		int input=sc.nextInt();

		switch(input) {
		case 1 : 
			for(int dan=2; dan<=9; dan++) {
				System.out.println("["+dan+"단]"); //제목
				for(int i=1; i<=9; i++) {
					System.out.printf("%2d X %2d = %2d\n",dan,i,dan*i);
				}
				System.out.println("=====================");
			}
			break;
		case 2 : 
			for(int dan=9; dan>=2; dan--) {
				System.out.println("["+dan+"단]");
				for(int i=1; i<=9; i++) {
					System.out.printf("%2d X %2d = %2d\n",dan,i,dan*i);
				}
				System.out.println("=====================");
			}break;
		default :
			System.out.println("잘못 입력 하셨습니다.");
		}
	}

	// for 예제 9 - 2중 for 문을 이용하여 다음 모양을 출력하시오.

	//1
	//12
	//123
	//1234

	public void ex9() {

		for(int j=1; j<=4; j++) {

			for(int i=1; i<=j; i++) {
				System.out.print(i);
			}
			System.out.println();
		}

		// j가 1일 때 i ==1
		// j가 2일 때 i ==1,2
		// j가 3일 때 i ==1,2,3
		// j가 4일 때 i ==1,2,3,4

	}

	//4321
	//321
	//21
	//1
	public void ex10() {

		for(int j=4; j>=1; j--) {

			for(int i=j; i>=1; i--) {
				System.out.print(i);
			}
			System.out.println();
		}

		// j 가 4일 때 i ==4,3,2,1
		// j 가 3일 때 i ==3,2,1
		// j 가 2일 때 i ==2,1
		// j 가 1일 때 i ==1


	}

	//for 예제 11 - 다음과 같은 모양을 출력하시오
	// 1  2  3  4
	// 5  6  7  8
	// 9 10 11 12

	public void ex11() {

		int count = 1;

		for(int x=1; x<=3; x++) { //줄 반복
			for(int i=1; i<=4; i++) { //숫자 출력
				System.out.printf("%3d",count);
				count++;
			}
			System.out.println();
		}
	}

	// 중첩 for문 예제 12
	//(0,0) (0,1) (0,2)
	//(1,0) (1,1) (1,2)

	public void ex12() {
		//() 내부 첫번째 칸의 값 0 1  
		for(int x=0; x<=1; x++) {
			//() 내부 두번쨰 칸의 값 0 1 2 
			for(int i=0; i<=2; i++) {
				System.out.printf("(%d,%d) ",x,i);
			}
			System.out.println();
		}
	}

	// 1부터 20까지 1씩 증가하면서
	// 3의 배수의 총 개수 출력와 합계 출력

	//3 6 9 12 15 18 :6개
	//3의 배수의 합계 : 63

	public void ex13() {
		int sum=0; // 3의 배수의 합계를 구하기 위한 변수
		int count=0; // 3의 배수의 개수를 세기 위한 변수
		for(int i=1; i<=20; i++) {
			if(i%3==0) {		
				System.out.print(i+" ");
				sum+=i;
				count++;
			}
		}
		System.out.println(":" + count + "개");
		System.out.println("3의 배수의 합계 : " +sum);
	}

	//예제 14 - 행/열의 크기를 입력 받아 출력하시오

	// 행 : 2
	// 열 : 3

	// 1 2 3 (1행)
	// 4 5 6 (2행)
	public void ex14() {


		Scanner sc = new Scanner(System.in);
		System.out.print("행 :");
		int j = sc.nextInt();
		System.out.print("열 :");
		int i = sc.nextInt();
		int count=0;

		// 초기식,조건식,증감식은 상황에 따라서
		// 작성하지 않을 수 있다!

		// for(; ; ) --> 무한루프 (조건문X ->flase안됨 ->종료 X)

		for(; j>=1; j--) { // 2 1 (2바퀴)   
			for(int y=i; y>=1; y--) { //3 2 1(3바퀴)   
				System.out.print(++count+" ");

			}
			System.out.println();
		}


		/*
		for(int k=1; k<=j;k++) {
			for(int q=1; q<=i; q++) {
				count ++;
				System.out.print(count+" ");
			}
			System.out.println();
		}

		 */

	}
	
	








}
