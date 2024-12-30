package edu.kh.oarr.model.service;

import java.util.Random;
import java.util.Scanner;

import edu.kh.oarr.model.vo.Student;

public class StudentService {
	//필드
	private Student[] studentArr= new Student[10];
	Scanner sc = new Scanner(System.in);
	// 학생 객체 참조 변수 10칸짜리 묶음 Student[]을 할당하고
	// 그 주소를 studentArr에 대입

	// -기본생성자
	// 학생 객체 배열에 들어갈 샘플 학생 데이터 3개 추가
	public StudentService() {

		studentArr[0] = new Student(1,3,22,"홍길동");
		studentArr[1] = new Student(2,5,38,"김길동");
		studentArr[2] = new Student(3,1,2,"박길동");
		Random random = new Random(); // 랜덤 객체 생성(자바에서 제공)
		for(int i=0; i<studentArr.length;i++) {
			// studentArr[3]부터 참조하는 학생 객체가 없음 == null
			if(studentArr[i] == null) {
				break; //학생 객체가 없으면 반복문 멈춤
			}
			//random.nextInt(101);
			//->0 이상 101미만의 정수형 난수를 반환
			studentArr[i].setKor(random.nextInt(101));
			studentArr[i].setEng(random.nextInt(101));
			studentArr[i].setMath(random.nextInt(101));
		}
	}

	//학생 추가 서비스
	public boolean addStudent(int grade, int ban, int number, String name) {

		// 1.studentArr의 요소 중
		//	 참조하는게 없는 배열 요소를 찾아서
		//	 그 중 index 번호가 가장 낮은 요소에 학생 객체 주소를 대입 후
		//	 true 반환
		for(int i=0; i<studentArr.length; i++) {
			if(studentArr[i]==null) { //참조하는게 없을 경우

				// 학생 객체를 생성하여 주소를 대입
				studentArr[i]= new Student(grade,ban,number,name);

				// return : 현재 메소드를 즉시 멈추고 호출한 곳으로 돌아감
				return true;
			}
		}


		// 2. studentArr의 요소 중
		//	  참조하는게 없는 배열 요소가 없을 경우 (꽉찬 경우)
		//	  false 반환

		return false;

	}

	//학생 전체 조회 서비스
	public Student[] selectAll() {
		return studentArr;

	}

	// 학생 1명 정보 조회(인덱스) 서비스
	//index에 학생 객체가 있으면 그 객체의 주소를 반환
	//index에 학생이 없거나 범위가 초과되면 null 반환
	public Student selectIndex(int index) {

		//student[index] 요소가 저장한 값이 null 이거나
		//index가 배열 범위를 초과한 경우
		if(studentArr[index]==null || index>=studentArr.length || index<0) {
			return null;

		}

		//index번째 요소에 학생 객체를 참조하는 주소가 있는 경우
		// 그 주소를 반환
		return studentArr[index];
	}
	//학생 1명 점수 조회(점수,합계,평균) 서비스
	public String selectScore(int index) {
		if( index>=studentArr.length || index<0 || studentArr[index]==null ) {
			return "해당 인덱스에 학생 정보가 존재하지 않습니다";
		}
		// studentArr[index] 쓰기 번거롭기 때문에 짧은 이름의 변수에 주소만 복수
		Student s = studentArr[index];
		int sum= s.getKor()+s.getEng()+s.getMath();
/*
		return "["+ s.getName() +"]" + 
        " 국어: " + s.getKor() + 
        ", 영어: " + s.getEng() +
        ", 수학: " + s.getMath() +
        ", 합계: " + sum +
        ", 평균: " + avg;
  */
		return String.format("[%s] 국어:%d, 영어:%d, 수학:%d, 합계:%d, 평균 %.1f", s.getName(),s.getKor(),s.getEng(),s.getMath(),sum,sum/3.0);

	}

	public void updateStudent(int kor, int eng, int math, Student std) {
		
		std.setKor(kor);
		std.setEng(eng);
		std.setMath(math);
		
		//return;

	}
}
