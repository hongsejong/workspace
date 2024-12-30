package edu.kh.objarray.practice.model.service;

import java.util.Scanner;

import edu.kh.objarray.practice.model.vo.Student;

public class PracticeService {

	private Scanner sc = new Scanner(System.in);
	public void Start() {

		Student[] studentArr= new Student[10];
		Student std = new Student();
		String input=null;
		int count=0;
		while(true) {
			System.out.print("학년 :");
			int grade=sc.nextInt();
			System.out.print("반 :");
			int classroom=sc.nextInt();
			System.out.print("이름 :");
			String name=sc.next();
			System.out.print("국어 :");
			int kor=sc.nextInt();
			System.out.print("영어 :");
			int eng=sc.nextInt();
			System.out.print("수학 :");
			int math=sc.nextInt();
			sc.nextLine();

			studentArr[count]=new Student(grade,classroom,name,kor,eng,math);
			count++;
			if(count==10) {
				break;
			}
			while(true) {
				System.out.println("계속 입력하시겠습니까? ( y / n )");
				input = sc.nextLine();
				if(input.equals("y")) {
					break;
				}
				if(input.equals("n")) {
					break; 
				}else {
					System.out.println("잘못 입력하셨습니다. 다시 입력해 주세요");

				}


			}if(input.equals("n")) {
				break;
			}

		}

		for(int i=0; i<count; i++) {

				System.out.println(studentArr[i].toString());

			
		}

	}


}
