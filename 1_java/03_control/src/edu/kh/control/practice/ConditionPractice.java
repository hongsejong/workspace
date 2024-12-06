package edu.kh.control.practice;

import java.util.Scanner;

public class ConditionPractice {



	public void practice1() {


		//키보드로 입력받은 정수가 양수이면서 짝수일때만"짝수입니다"를 출력하고
		//짝수가 아니면 "홀수입니다"를 출력하세요
		//양수가 아니면 "양수만 입력해주세요"를 출력하세요


		Scanner sc  = new Scanner(System.in);
		System.out.print("숫자를 한 개 입력하세요 : ");
		/*
		int number =sc.nextInt();
		String result; // 결과 저장용 변수

		if(number>0) { // 양수
			if(number %2 ==0) {
				result="짝수 입니다";
			}else {
				result="홀수 입니다";
			}
		}else {//양수아님
				result = "양수만 입력해주세요.";
		}
		System.out.println(result);

	}
		 */
		int num= sc.nextInt();

		if(num>0 && num%2==0)
		{
			System.out.println("짝수 입니다");
		} else if(num%2!=0 && num>0) {
			System.out.println("홀수 입니다");
		}else 
			System.out.println("양수만 입력해주세요.");
	}

	public void practice2() {
		/*국,영,수 세과목의 점수를 키보드로 입력받고 합계와 평균을 계산하고
		합계와 평균을 이용하여 합격/불합격 처리기능구현
		(합격조건 세과목의 점수가 각각 40이상이면서 평균이 60점이상일경우)

		합격했을경우 과목별 점수와합계,평균,"축하합니다,합격입니다!"를 출력하고
		불합격인경우에는 불합격입니다 를 출력하세요*/

		Scanner sc = new Scanner(System.in);
		System.out.print("국어 점수 :");
		int kor = sc.nextInt();
		System.out.print("수학 점수 :");
		int math = sc.nextInt();
		System.out.print("영어 점수 :");
		int eng = sc.nextInt();

		//합계
		int sum= kor + math + eng;
		//평균
		double avg = sum /3.0;


		if(kor >=40 && math >=40 && eng >=40 && avg >=60) { //합격
			System.out.println("국어:"+kor);
			System.out.println("수학:"+math);
			System.out.println("영어:"+eng);
			System.out.println("합계:"+sum);
			System.out.println("평균:"+avg);
			System.out.println("축하합니다 합격입니다");
		}else {//불합격
			System.out.println("불합격 입니다.");	
		}


		/*
		Scanner sc = new Scanner(System.in);
		System.out.print("국어점수 :");
		int k = sc.nextInt();
		System.out.print("영어점수 :");
		int e = sc.nextInt();
		System.out.print("수학점수 :");
		int m = sc.nextInt();

		double ave= (k+e+m)/3.0;
		int sum = (k+e+m);

		if(k>=40 && e>=40 && m>=40 && ave>=60) {
			System.out.printf("국어 : %d\n수학 : %d\n영어 : %d\n합계 : %d\n평균:%.1f\n축하합니다. 합격입니다!",k,m,e,sum,ave );
		}else {
			System.out.println("불합격입니다.");
		}
		 */
	}

	public void practice3() {
		//1~12사이의 수를 입력받아 해당 달의 일수를 출력하세요.(2월 윤달은 생각하지 않습니다.)
		//잘못 입력한 경우 "00월은 잘못 입력된 달입니다"를 출력하세요(switch문 사용)

		Scanner sc = new Scanner(System.in);

		System.out.print("1~12 사이의 정수를 입력 : ");
		int month = sc.nextInt();
		int day = 0; // 해당하는 달의 마지막 일(날짜)을 저장

		switch(month) {
		case 1 : case 3: case 5: case 7: case 8: case 10: case 12: day= 31; break;
		case 4:  case 6: case 9: case 11: day = 30; break;
		case 2: day=28; break;



		}
		if(day!=0) { // 1~12사이가 입력 되었을 때
			System.out.printf("%d월은 %d일까지 있습니다",month,day);

		}else {// 1~12사이가 입력되지 않았을 때
			System.out.println(month + "월은 잘못 입력된 달 입니다.");
		}

		/*
		Scanner sc = new Scanner(System.in);
		System.out.print("1~12 사이의 정수 입력 :");
		int month=sc.nextInt();
		String result;
		switch(month) {
		// 31일 : 1 3 5 7 8 10 12
		// 30일 : 4 6 9 11
		// 28일 : 2
		case 1 : case 3 : case 5: case 7: case 8: case 10: case 12:
			result="31일 까지 있습니다."; break;
		case 4 : case 6 : case 9 : case 11:
			result="30일 까지 있습니다."; break;
		case 2 : result = "28일까지 있습니다."; break;
		default : result = "잘못 입력된 달입니다."; break;




		}
		System.out.println(month +"월은"+" "+ result);
		 */
	}

	public void practice4() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("키(m)를 입력해 주세요 :");
		double height = sc.nextDouble();
		
		System.out.println("몸무기(kg)를 입력해 주세요 :");
		double weight = sc.nextDouble();
	
		double bmi = weight / (height * height);
		
		String str; // 결과 저장용 변수
		if(bmi<18.5) {
			str = "저체중";
		}else if (bmi<23) {
			str = "정상체중";
		}else if(bmi<25) {
			str = "과체중";
		}else if(bmi<30) {
			str = "비만";
		}else {
			str="고도비만";
		}
		System.out.println("BMI 지수 : " + bmi);
		System.out.println(str);
	
	}

		//키,몸무게를 double로 입력 받고 BMI 지수를 계산하여 계산 결과에 따라 저체중/정상체중/과체중/비만을 출력하세요

		//BMI= 몸무게 / (키(m)* 키(m)
		//BMI가 18.5미만일경우 저체중 / 18.5이상 23미만일경우 정상체중
		//BMI가 23이상 25미만일경우 과체중 / 25이상 30미만일경우 비만
		//BMI가 30이상일경우 고도비만
		
		/*
		Scanner sc = new Scanner(System.in);
		System.out.print("키(m)를 입력해 주세요 :");
		double m = sc.nextDouble();
		System.out.print("몸무게(kg)를 입력해 주세요 :");
		double kg = sc.nextDouble();
		double bmi= kg / (m*m);
		String bmi2;

		if(bmi<18.5) {
			bmi2="저체중";
		}else if (bmi<23) {
			bmi2="정상체중";
		}else if (bmi<25) {
			bmi2="과체중";
		}else if(bmi<30) {
			bmi2="비만";
		}else {
			bmi2="고도 비만";
		}
		System.out.println("BMI 지수 : " + bmi);
		System.out.println(bmi2);
	}
*/
	//***************************
/*	메소드 명 : public void practice5(){}												//
	중간고사, 기말고사, 과제점수, 출석횟수를 입력하고 Pass 또는 Fail을 출력하세요.
	평가 비율은 중간고사 20%, 기말고사 30%, 과제 30%, 출석 20%로 이루어져 있고
	이 때, 출석 횟수는 총 강의 횟수 20회 중에서 출석한 날만 따진 값으로 계산하세요.
	70점 이상일 경우 Pass, 70점 미만이거나 전체 강의에 30% 이상 결석 시 Fail을 출력하세요.
*/
/*	[예시]
	중간 고사 점수 : 80
	기말 고사 점수 : 30
	과제 점수 : 60
	출석 횟수 : 18
	================= 결과 =================
	중간 고사 점수(20) : 16.0
	기말 고사 점수(30) : 9.0
	과제 점수 (30) : 18.0
	출석 점수 (20) : 18.0
	총점 : 61.0
	Fail [점수 미달]
	*/
	
	public void practice5() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("중간 고사 점수 : ");
		int midTerm = sc.nextInt();
		System.out.print("기말 고사 점수 : ");
		int finalTerm = sc.nextInt();
		System.out.print("과제 점수 : ");
		int homework = sc.nextInt();
		System.out.print("출석 점수 : ");
		int attendance = sc.nextInt();
		
		System.out.println("===================결과==============");
		
		//출석 횟수가 부족한 경우  (6번 이상 결석 == 14번 이하 출석)
		if(attendance <= 20* (1-0.3)) {
			System.out.printf("Fail [출석 횟수부족 (%d/20]",attendance);
		}else { //출석은 잘 했을때
			// 점수 환산
			double midScore = midTerm*0.2;
			double finalScore = finalTerm*0.3;
			double homeworkScore = homework*0.3;
			double attScore = attendance*0.2*5; // ==attendance
			
			//총점
			double sum = midScore + finalScore + homeworkScore + attScore;
			
			System.out.println("중간 고사 점수(20) :" + midScore);
			System.out.println("기말 고사 점수(30) :" + finalScore);
			System.out.println("과제 점수   (30) :" + homeworkScore);
			System.out.println("출석 점수   (20) :" + attScore);
			System.out.println("총점 :" + midScore);
			
			if(sum>=70) {
				System.out.println("PASS");
			}else {
				System.out.println("Fail [점수 미달]");
			}
		}
		
		
		
		/*Scanner sc = new Scanner(System.in);
		System.out.print("중간 고사 점수 :");
		double j=sc.nextDouble();
		System.out.print("기말 고사 점수 :");
		double k=sc.nextDouble();
		System.out.print("과제 점수 :");
		double g=sc.nextDouble();
		System.out.print("출석 횟수 :");
		double c=sc.nextDouble();
		
		double j2= j*0.2;
		double k2= k*0.3;
		double g2= g*0.3;
		double c2= c*5*0.2;
		
		
		if(j2+k2+g2+c2>=70 && c2>14) {
			System.out.println("=======결과======");
			System.out.println("중간 고사 점수(20) : " +j2);
			System.out.println("기말 고사 점수(30) : " +k2);
			System.out.println("과제 점수 (30) : "+g2);
			System.out.println("출석 점수 (20) : "+c2);
			System.out.println("총점 :" + (j2+k2+g2+c2));
			System.out.println("PASS");
		}else if(c2<=14){
			System.out.println("=======결과======");
			System.out.println("Fail [출석 횟수 부족("+(int)c2+"/20)]" );
		
			
		}else {
			System.out.println("=======결과======");
			System.out.println("중간 고사 점수(20) : " +j2);
			System.out.println("기말 고사 점수(30) : " +k2);
			System.out.println("과제 점수 (30) : "+g2);
			System.out.println("출석 점수 (20) : "+c2);
			System.out.println("총점 :" + (j2+k2+g2+c2));
			System.out.println("Fail[점수미달]");
		}
	*/
	}
	
	
 




}
