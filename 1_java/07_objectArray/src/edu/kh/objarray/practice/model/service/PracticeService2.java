package edu.kh.objarray.practice.model.service;

import java.util.Scanner;

import edu.kh.objarray.practice.model.vo.Student2;

public class PracticeService2 {

	public void start() {

		Student2[] std = new Student2[10];

		Scanner sc = new Scanner(System.in);
		int count =0;
		boolean flag=true; //바깥쪽 while문 제어용
		while(flag) {
			System.out.print("학년 :");
			int grade = sc.nextInt();
			System.out.print("반 :");
			int classroom = sc.nextInt();
			System.out.print("이름 :");
			String name = sc.next();
			System.out.print("국어점수 :");
			int kor = sc.nextInt();
			System.out.print("영어점수 :");
			int eng = sc.nextInt();
			System.out.print("수학점수 :");
			int math = sc.nextInt();

			std[count]= new Student2(grade, classroom, name, kor, eng, math);
			count++;
			while(true) {

				System.out.println("계속 입력 하시겠습니까? (y/n) :");
				char input = sc.next().toUpperCase().charAt(0);
									  //문자열을 대문자로 바꿈

				if(count == 10 ||  input=='N') {
					flag=false;
					break;
				}
				
				if(input != 'Y') {
					System.out.println("잘못 입력하셨습니다. 다시 입력해주세요");
					
				}else {
					break; //가장 가까운 반복문 탈출
				}
				
			}
		}
		for(int i=0; i<count;i++) {
			System.out.println(std[i].toString());
		}
	}

}
