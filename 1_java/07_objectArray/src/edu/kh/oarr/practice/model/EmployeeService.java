package edu.kh.oarr.practice.model;

import java.util.Scanner;

import edu.kh.oarr.practice.model.vo.Employee;

public class EmployeeService {
	private Employee[] employeeArr = new Employee[3];
	//사원의 정보를 저장할 Employee배열


	private Scanner sc= new Scanner(System.in);
	public void displayMenu() { //메뉴 화면 출력 기능

		int menuNum=0; //메뉴 선택용 변수

		do {
			System.out.println("==== 직원 관리 프로그램====");
			System.out.println("1.직원 정보 입력 (3명)");
			System.out.println("2.모든 직원 정보 출력");
			System.out.println("3.특정 직원 정보 출력(이름검색)");
			System.out.println("4.특정 직원 급여/연봉 출력(사번 검색)");
			System.out.println("5.모든 직원 급여 합/연봉 합 출력");
			System.out.println("6.모든 직원중 급여가 가장 높은 직원의 이름,부서,급여 출력");
			System.out.println("0.종료");

			System.out.println("메뉴 입력 >>");
			menuNum=sc.nextInt(); //필드에 작성된 Scanner sc 사용

			switch(menuNum) {
			case 1 : addInfo(); break;
			case 2 : showInfo(); break;
			case 3 : searchName(); break;
			case 4 : searchNumber();break;
			case 5 : allAdd() ;break;
			case 6 : topSalary() ;break;
			case 0 : System.out.println("프로그램을 종료합니다.");  break;

			default:System.out.println("잘못 입력하셨습니다. 다시 입력해주세요");
			}
		}while(menuNum !=0);
	}

	public void addInfo() {

		for(int i=0; i<employeeArr.length;i++) {
			System.out.print("==="+(i+1)+"번째 사원 정보 입력 ===\n");
			System.out.print("사번 :");
			int number=sc.nextInt();
			System.out.print("이름 :");
			String name=sc.next();
			System.out.print("부서 :");
			String team=sc.next();
			System.out.print("직급 :");
			String level=sc.next();
			System.out.print("급여 :");
			int money=sc.nextInt();

			employeeArr[i]= new Employee(number, name, team, level, money);
		}

	}

	public void showInfo() {
		for(int i=0; i<employeeArr.length; i++) {
			System.out.println(employeeArr[i].toString());
		}

	}
	//showInfo 메소드를 문자열로 반환하는 방법
	public String allEmployeesInformation() {
		String str = "";
		for(int i=0; i<employeeArr.length; i++) {
			str+=employeeArr[i].toString() + "\n";
		}
		return str;
	}



	public void searchName() {
		System.out.print("=== 특정 사원 정보 출력(이름 검색)\n");
		System.out.print("이름 입력 :");
		String inputName=sc.next();
		boolean flag=true;
		for(int i=0; i<employeeArr.length; i++) {
			if(employeeArr[i].getName().equals(inputName)) {

				System.out.println(employeeArr[i].toString());
				flag=false;
			}

		}
		if(flag) {
			System.out.print("일치하는 이름의 사원이 없습니다.\n");
		}
	}
	

	public void searchNumber() {
		System.out.print("===급여/연봉 조회===\n");
		System.out.print("사번 입력 : ");
		boolean flag=true;
		int inputNumber=sc.nextInt();

		for(int i=0; i<employeeArr.length; i++) {
			if(employeeArr[i].getNumber()==inputNumber) {
				flag=false;
				System.out.printf("급여 :%d / 연봉 : %d\n",employeeArr[i].getMoney(),employeeArr[i].getMoney()*12);
			}
		}
		if(flag) {
			System.out.print("\n사번이 일치하는 직원 없습니다.\n");
		}
	}

	public void allAdd() {
		int sum=0;
		int yearSum=0;

		System.out.println("===모든 사원 급여 합/연봉 합 ===");
		for(int i=0; i<employeeArr.length; i++) {
			sum+=employeeArr[i].getMoney();
			yearSum+=employeeArr[i].getMoney()*12;
		}

		System.out.printf("전 직원 급여 합 : %d\n" , sum);
		System.out.printf("전 직원 연봉 합 : %d\n" , yearSum);
	}

	public void best() {
		int money=0;
		int best=0;
		for(int i=0; i<employeeArr.length; i++) {
			if(employeeArr[i].getMoney()>money) {
				money=employeeArr[i].getMoney();
				best=employeeArr[i].getNumber();

			}
		}
		for(int i=0; i<employeeArr.length;i++) {
			if(employeeArr[i].getNumber()==best) {
				System.out.printf("이름 : %s, 부서 : %s , 급여 : %d\n",employeeArr[i].getName(),employeeArr[i].getTeam(),employeeArr[i].getMoney());
			}
		}
	}
	
	//모든 직원 중 급여가 가장 높은 직원 출력
	public void topSalary() {
		int max = employeeArr[0].getMoney();
		
		//가장 높은 급여를 찾기 위한 for문
		for(int i=0; i<employeeArr.length; i++) {
			if(max < employeeArr[i].getMoney()) {
				max = employeeArr[i].getMoney();
			}
		}
		for(int i=0; i<employeeArr.length;i++) {
			if(max == employeeArr[i].getMoney()) {
				System.out.printf("이름 : %s, 부서 : %s , 급여 : %d\n",employeeArr[i].getName(),employeeArr[i].getTeam(),employeeArr[i].getMoney());
			}
		}
	}



}
