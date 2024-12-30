package edu.kh.variable;

import java.util.Scanner;

public class ScannerExample2 {

	public static void main(String[] agrs) {
		
		//사직 연산 계산기
		// -> 두 실수를 입력 받아 사칙연산 결과를 모두 출력 ( 소수점 둘째 자리 까지)
		
		
		
		//출력 예시
		//1.23 + 2.01 = 3.24
		//1.23 - 2.01 = 
		//1.23 * 2.01 = 
		//1.23 / 2.01 = 
		Scanner sc = new Scanner(System.in);
		
		System.out.println("첫번째 입력");
		double input1 = sc.nextDouble();
		// nextDoulbe() : 입력 받은 다음 실수를 읽어옴
		System.out.println("두번째 입력");
		double input2 = sc.nextDouble();
		
		System.out.printf("%.2f + %.2f = %.2f\n", input1 ,input2, input1+input2 );
		System.out.printf("%.2f - %.2f = %.2f\n", input1 ,input2, input1-input2 );
		System.out.printf("%.2f * %.2f = %.2f\n", input1 ,input2, input1*input2 );
		System.out.printf("%.2f / %.2f = %.2f\n", input1 ,input2, input1/input2 );
		
		
		
	}
}
