package edu.kh.variable;

import java.util.Scanner;

public class ScannerExample5 {

		//main 작성 후 ctrl + space -> enter : 메인 메소드 자동완성
	public static void main(String[] args) {
		
		// Scanner 생성하기
		Scanner sc = new Scanner(System.in);
		
		//ex)
		// 이름 :카리나
		// 나이 :28
		// 성별 :여자
		// 키  :168.4 
		// 연봉 : 10000000000
		
		//카리나 님은 28세 여자이고 키는 168.4, 연봉은 10000000000 입니다.
		String result = "";
		
		System.out.print("이름 :");
		String name= sc.next(); // 입력 받은 문자열을 name에 저장
		result = name + "님은 ";
		System.out.println(result);
		
		System.out.print("나이 :");
		int age= sc.nextInt();
		
		result = result +age+"세 ";
		System.out.println(result);
		System.out.print("성별 :");
		String gender= sc.next();
		result = result + gender +"이고 키는 ";
		System.out.println(result);
		System.out.print("키 :");
		double height=sc.nextDouble();
		result = result + height + "cm,연봉은 ";
				
		System.out.print("연봉 :");
		long salary=sc.nextLong();
		result = result + salary + " 입니다. ";
		
		
		System.out.println(result);
		System.out.printf("%s 님은 %d세 %s이고 키는 %.1f, 연봉은 %d 입니다.", name,age,gender,height,salary );
	
		
	
		
		
		
	}

}
