package edu.kh.inheritance.practice.model.service;

import java.util.Scanner;

import edu.kh.inheritance.practice.model.vo.Employee;
import edu.kh.inheritance.practice.model.vo.Student;

public class PracticeService {
	private Scanner sc = new Scanner(System.in);

	public void homework() {

		Student[] std = new Student[3];

		std[0]=new Student("카리나",20,168.2,70.0,1,"정보시스템공학과" );
		std[1]=new Student("정해인",21,187.3,80.0,2,"경영학과" );
		std[2]=new Student("박서준",23,179.0,45.0,4,"정보통신공학과" );

		for(Student arr : std) {
			System.out.println(arr.toString());
		}
		Employee[] emp = new Employee[10];
		int count=0;
		boolean flag= true;
		while(flag) {
			
			System.out.print("이름 :");
			String name=sc.next();
			System.out.print("나이 :");
			int age = sc.nextInt();
			System.out.print("신장 :");
			double height= sc.nextDouble();
			System.out.print("몸무게 :");
			double weight= sc.nextDouble();
			System.out.print("급여 :");
			int salraly = sc.nextInt();
			System.out.print("부서 :");
			String dept = sc.next();
			emp[count]= new Employee(name,age,height,weight,salraly,dept);
			count++;
			while(true) {
				
				System.out.println("계속 추가하시겠습니까?");
				char input=sc.next().toUpperCase().charAt(0);
				if(count==10 || input=='N') {
					flag=false;
					break;
				}
				if(input!='Y') {
					System.out.println("잘못입력 하셨습니다");
					
				}else {
					break;
				}
			}
		
			
			
		}
		
		
		for(int i=0; i<count; i++) {
			System.out.println(emp[i].toString());
		}









	}
	
	public void homework2() {
		
		Student[] student = new Student[3];
		
		student[0]=new Student("카리나",20,168.2,70.0,1,"정보시스템공학과" );
		student[1]=new Student("정해인",21,187.3,80.0,2,"경영학과" );
		student[2]=new Student("박서준",23,179.0,45.0,4,"정보통신공학과" );
		
		for(Student std:student) {
			System.out.println(std);
		}
		
		Employee[] emp =new Employee[10];
		Scanner sc = new Scanner(System.in);
		
		int count=0;
		while(true) {
			System.out.print("이름 :");
			String name=sc.next();
			System.out.print("나이 :");
			int age = sc.nextInt();
			System.out.print("신장 :");
			double height= sc.nextDouble();
			System.out.print("몸무게 :");
			double weight= sc.nextDouble();
			System.out.print("급여 :");
			int salraly = sc.nextInt();
			System.out.print("부서 :");
			String dept = sc.next();
			
			emp[count++]= new Employee(name, age, height, weight, salraly, dept);
			
			System.out.println("계속 입력하시겠습니까? (y/n) :");
			char input = sc.next().toLowerCase().charAt(0);
									//소문자
			
			if(input !='y') {
				break;
			}
			
		}
		for(Employee e : emp) {
			if(e!=null) {
				
				System.out.println(e);
			}
		}
	}

}
