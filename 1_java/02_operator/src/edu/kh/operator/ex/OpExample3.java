package edu.kh.operator.ex;

import java.util.Scanner;

public class OpExample3 {
	public static void main(String[] args) {
	/*배수 확인 프로그램]
	 * 확인할 수 : 15
	 * 배수 : 7
	 * 
	 * 15는 7의 배수 입니까? false
	 * */
		Scanner sc = new Scanner(System.in);
		
		System.out.println("[배수 확인 프로그램]");
		System.out.print("확인할 수 :");
		int a = sc.nextInt();
		System.out.print("배수 :");
		int b = sc.nextInt();
		
		System.out.printf("%d는 %d의 배수 입니까? %b" ,a,b, a%b==0);
		
		
		
}
}
