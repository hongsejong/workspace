package edu.kh.control.practice;

import java.util.Scanner;

public class LoopPractice {


	public void practice1() {

		//사용자로부터 한 개의 값을 입력 받아 1부터 그 숫자까지의 숫자들을 모두 출력하세요
		//단 입력한 수는 1보다 크거나 같아야함
		//만일 1미만의 숫자가 입력됐다면 "1 이상의 숫자를 ㅇ비력해주세요"를 출력하세요.

		//ex 1이상의숫자를 입력하세요 : 4
		//	1 2 3 4

		Scanner sc = new Scanner(System.in);

		System.out.print("1 이상의 숫자를 입력하세요 :");
		int input = sc.nextInt();

		if(input>0) {
			for(int i=1; i<=input; i++) {
				System.out.print(i+ " ");
			}
		}else {
			System.out.println("1이상의 숫자를 입력해주세요.");
		}
	}

	public void practice2() {
		//한개 입력받아 1부터 그 숫자까지 거꾸로

		Scanner sc = new Scanner(System.in);

		System.out.println("1이상의 숫자를 입력하세요 :");
		int input = sc.nextInt();
		//5  5 4 3 2 1
		if(input>0) {
			for(int i=input; i>=1; i--) {
				System.out.print(i+" ");
			}
		}else {
			System.out.println("1이상의 숫자를 입력해주세요.");
		}


	}

	public void practice3() {
		//1부터 입력받은 수까지 합
		//ex(정수를 하나 입력하세요 : ) // 1 + 2 + 3 + 4 + 5 = ㅇㅇ

		Scanner sc = new Scanner(System.in);

		System.out.print("정수를 하나 입력하세요 :");
		int input = sc.nextInt();
		int sum=0;

		for(int i=1; input>=i; i++) {
			sum+=i;
			if(input!=i) {
				System.out.printf("%d + ",i);
			}else {
				System.out.printf("%d  ",i);
			}
		}
		System.out.println("= "+sum);

	}
	//두개 입력받아 두사이 모두출력 /1미만이면 1입력하세요
	public void practice4() {



		Scanner sc = new Scanner(System.in);

		System.out.print("첫 번째 숫자 :");
		int input1 = sc.nextInt();
		System.out.print("첫 번째 숫자 :");
		int input2 = sc.nextInt();
		// 입력 받은 두 수가 모두 1이상인가?
		if(input1 >=1 && input2 >=1) {
			if(input1 <= input2) { //먼저 입력한 수가 작거나 같을때
				for(int i=input1; i<=input2; i++) {
					System.out.println(i + " ");
				}
			}else { //나중에 입력한 수가 작을때
				for(int i=input2; i<=input1; i++) {
					System.out.println(i + " ");
				}
			}
		}else {
			System.out.println("1이상의 숫자를 입력해주세요");
		}
		/*

		      Scanner sc = new Scanner(System.in);

		      System.out.print("첫번째 숫자 : ");
		      int input1 = sc.nextInt();
		      System.out.print("두번째 숫자 : ");
		      int input2 = sc.nextInt();


		      if(input1 <= 0 || input2 <= 0) {
		         System.out.println("1 이상의 숫자를 입력해주세요.");
		      } else if (input1 > input2) {
		         for( ; input2 <= input1 ; input2++) {
		            System.out.print(input2 + " ");
		         }
		      } else {
		         for( ; input1 <= input2 ; input1++) {
		            System.out.print(input1 + " ");
		         }
		      }*/
	}

	//사용자로부터 입력받은 숫자의 단을 출력

	//ex.
	//숫자 : 4
	//====4단====
	//...
	public void practice5() {

		Scanner sc = new Scanner(System.in);

		System.out.print("숫자 : ");
		int dan= sc.nextInt();
		System.out.printf("======%d단=====\n",dan);
		for(int i=1;i<=9;i++) {

			System.out.printf("%d * %d = %d\n" , dan,i,i*dan);
		}



	}
	//사용자 입력받은 수부터 9단까지
	//2~9아닌수 입력시 2~9 사이의 숫자만 입력해주세요
	public void practice6() {

		Scanner sc = new Scanner(System.in);

		System.out.print("숫자 :");
		int dan= sc.nextInt();
		if(dan>=2 && dan<=9) {

			for(int i=dan; i<=9; i++) {
				System.out.printf("======%d단=====\n",i);
				for(int j=1; j<=9; j++) {
					System.out.printf("%d X %d = %d\n",i,j,i*j);
				}
			}

		}else {
			System.out.println("2~9사이의 숫자만 입력해주세요.");
		}


	}
	//ex
	//정수입력 :4
	//*
	//**
	//***
	//****
	public void practice7() {

		Scanner sc = new Scanner(System.in);

		System.out.print("정수 입력 :");
		int input= sc.nextInt();

		for(int j=1; j<=input; j++) { 
			for(int i=1; i<=j; i++ ) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
	//ex)
	//정수입력 :4
	//****
	//***
	//**
	//*

	public void practice8() {

		Scanner sc = new Scanner(System.in);

		System.out.print("정수 입력 :");

		int input = sc.nextInt(); // 4

		for(int j=input; j>=0; j--) {// 3

			for(int i=1;i<=j;i++) {
				System.out.print("*");

			}
			System.out.println();

		}


	}
	//ex.정수입력 : 4 
	//    * 		
	//   **			
	//  ***
	// ****
	public void practice9() {

		Scanner sc = new Scanner(System.in);

		System.out.print("정수 입력 :");

		int input = sc.nextInt();


		// 1) for문 하나 더 작성

		// * 1개 출력 전에 띄어쓰기 3번
		// * 2개 출력 전에 띄어쓰기 2번
		// * 3개 출력 전에 띄어쓰기 1번
		// * 4개 출력 전에 띄어쓰기 0번

		for(int x=1; x<= input; x++) {

			for(int i =1; i<=input-x; i++) {
				System.out.print(" ");
			}
			for(int i=1; i<=x; i++)
				System.out.print("*");
			System.out.println();
		}

		/*
		// 2) for + if else

		for(int x=1; x<= input; x++) {

		for(int i=1; i<=input; i++) {

			if(i<=input -x) {
				System.out.print(" ");
			}else {

				System.out.print("*");
			}
		}
		System.out.println();
		}
		 */


	}


	//ex
	//정수 입력: 3
	//*
	//**
	//***
	//**
	//*
	public void practice10() {

		Scanner sc = new Scanner(System.in);
		System.out.print("정수 입력 : ");
		int input = sc.nextInt();

		//위쪽 삼각형
		for(int x=1; x<=input; x++) {

			for(int y=1; y<=x; y++) {
				System.out.print("*");
			}
			System.out.println(); //줄바꿈
		}

		//아래쪽 삼각형
		for(int y = input-1; y>=1; y--) {
			for(int i=1; y>=i; i++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
	/*
	 *    *
	 *   **
	 *  ***
	 */ 

	public void ex1() {

		Scanner sc = new Scanner(System.in);

		System.out.println("정수 입력");
		int input = sc.nextInt();


		for(int i=1; i<=input;i++) {
			System.out.println();
			for(int j=1; j<=input-i;j++) {

				System.out.print(" ");
			}
			for(int k=1; k<=i;k++) {
				System.out.print("*");
			}
		}

	}
	//	*
	// **
	//***
	// **
	//  *

	public void ex2() {

		for(int x=1; x<= 3; x++) {

			for(int i =1; i<=3-x; i++) {
				System.out.print(" ");
			}
			for(int i=1; i<=x; i++)
				System.out.print("*");
			System.out.println();
		}
		for(int j=1; j<2; j++) {
			for(int i=1; j<2; i++) {
				System.out.println();
			}
		}


	}
	
	public void practice11() {
		 Scanner sc = new Scanner(System.in);
	      System.out.print("정수 입력 :");
	      int input= sc.nextInt();
	      for(int i=1; i<=input; i++) { //입력받은 input만큼 줄 출력
	         for(int j=input; j>=i; j--) { // 공백 출력 for문
	            System.out.print(" ");
	         }
	      
	      for(int k=1; k<=(i*2-1); k++) {
	         System.out.print("*");
	      
	      }
	      System.out.println();
	      }
	}
	
	public void practice11_1() {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("정수 입력 :");
		int input = sc.nextInt();
		
		for(int x=1; x<input; x++) {
			//공백 출력 for문
			for(int i=input-x; i>=1; i--) {
				System.out.print(" ");
			}
			//*출력 for문
			// 1 3 5 7 9
			
			for(int i=1;i<=2*x-1; i++) {
				System.out.print("*");
			}
			System.out.println();
		
			
			
		}
		
	}
	//ex. 정수 입력 :5
	//*****
	//*   *
	//*   *
	//*   *
	//*****
	public void practice12() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("정수 입력 :");
		int input = sc.nextInt();
		//row:행(줄)
		//col(column): 열(칸)
		/*
		for(int row=1; row<=input; row++) { //행
			
			for(int col=1; col<=input; col++) { //열
				//테두리만 * 출력
				//row 또는 col이 1 또는 input인 경우 
				if(row ==1 || row==input ||col==1 || col==input) {
					
				System.out.print("*");
				}else { //내부
					System.out.print(" ");
				}
			}
			System.out.println();
			*/
			
		
		
		for(int i = 1; i<=input; i++) {
			
			
			for(int j=1; j<=input; j++) {
				if(i==1 || i==input || j==1 || j==input) {
					
				System.out.print("*");
				}else {
					System.out.print(" ");
				}
				
			}
			System.out.println();	
		;
		
			
		}
		
	
	}
	
	/*1부터 사용자에게 입력받은 수까지 중에서
	 * 1)2와 3의 배수를 모두 출력하고
	 * 2)2와 3의 공배수의 개수를 출력하세요
	 * 
	 * ->공배수: 둘 이상의 공통인 배수라는뜻으로 어떤 수를 해당 수들로 나눴을때 모두 나머지가 0이 나오는 수
	 * ex. 자연수 하나를 입력하세요 : 15
	 * 2 3 4 6 8 9 10 12 14 15
	 * count : 2
	*/
	public void practice13() {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("자연수 하나를 입력하세요 : ");
		int input = sc.nextInt();
		int count =0; //2와 3의 공배수의 개수를 세기 위한 변수
		
		for(int i=1; i<=input; i++) {
			// i가 2의배수 또는 3의 배수
			if(i%2==0 || i%3==0) {					
			System.out.print(i+" ");
				if(i%2==0 && i%3==0) { //2와 3의 공배수인 경우				
				count++;
				}
			}
						
		}
		System.out.println();
		System.out.print("\ncount : " +count);
	}
	
}
