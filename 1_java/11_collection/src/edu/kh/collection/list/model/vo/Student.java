package edu.kh.collection.list.model.vo;

import java.util.Objects;

//Comparable<Student> 인터페이스
//->Student 객체 정렬 기준을 정하기 위한 기능
//	comparaTo()를 제공하는 인터페이스


public class Student implements Comparable<Student>{
	
	private int grade;
	private int ban;
	private int number;
	private String name;
	private char gender;
	private int score;
	
	
	public Student() {
		
	}


	public Student(int grade, int ban, int number, String name, char gender, int score) {
		super();
		this.grade = grade;
		this.ban = ban;
		this.number = number;
		this.name = name;
		this.gender = gender;
		this.score = score;
	}


	public int getGrade() {
		return grade;
	}


	public void setGrade(int grade) {
		this.grade = grade;
	}


	public int getBan() {
		return ban;
	}


	public void setBan(int ban) {
		this.ban = ban;
	}


	public int getNumber() {
		return number;
	}


	public void setNumber(int number) {
		this.number = number;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public char getGender() {
		return gender;
	}


	public void setGender(char gender) {
		this.gender = gender;
	}


	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
	}


	@Override
	public String toString() {
		return "Student [grade=" + grade + ", ban=" + ban + ", number=" + number + ", name=" + name + ", gender="
				+ gender + ", score=" + score + "]";
	}



/*
	@Override //자동완성으로 만든거
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return ban == other.ban && gender == other.gender && grade == other.grade && Objects.equals(name, other.name)
				&& number == other.number && score == other.score;
	}
	
	//(중요!!) equals()를 오버라이딩 했으면
	//hashCode()도 오버라이딩을 해야한다!!
	//hashCode() :  해시 테이블(객체 주소록)에서 
	//				객체를 빨리 찾아가게 하기 위한 번호
	*********************************
	*A.equals(B)의 결과가 true라면
	*A.hashCode() == B.hashCode() 의 결과도 true여야 한다
	***********************************
	@Override //Object의 hashCode()오버라이딩
	public int hashCode() {
		//필드 값이 같은 객체는 같은 정수를 반환해야 한다.
		//== 필드 값을 이용해서 정수를 만들면 된다.
		
		int result =1; //결과 저장 변수
		
		final int PRIME =31; // 31이 연산 속도가 빠른 소수 중 하나
				 //소수
		
		result = result * PRIME * ban;
		
		result =result * PRIME * (name == null ? 0 : name.hashCode());
		
		return result;
		
	}
*/

	
	// A.equals(B)
	// A객체와 B객체가 가지고 있는 필드의 값이 모두 같으면 true,아니면 false
	
	
/*	@Override // Object.equals() 오버라이딩
	public boolean equals(Object obj) {
		
		if(this == obj) { //같은 객체 참조
			return true;
		}
		
		if(obj==null) { //비교 대상이 null
			return false;
			
		}
		if(!(obj instanceof Student)) { //비교 대상이 Student가 아니라면
			return false;
		}
		
		//obj 참조 변수를 Student로 다운 캐스팅
		Student other = (Student)obj;
		
		//현재 객체(this)와 다른 객체(other)의 필드를 비교
		return this.grade == other.grade
			&& this.ban==other.ban
			&& this.number==other.number
			&& this.name.equals(other.name)
			&& this.gender==other.gender
			&& this.score==other.score;
				
	}*/
	
	//비교 기준을 제시하는 메소드
	
	@Override
	public int compareTo(Student o) {
		
		//this : 현재 객체
		// o : 비교 객체
		
		return this.score-o.score;
	}


	@Override
	public int hashCode() {
		return Objects.hash(ban, gender, grade, name, number, score);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return ban == other.ban && gender == other.gender && grade == other.grade && Objects.equals(name, other.name)
				&& number == other.number && score == other.score;
	}
	
		
		
	
	
	

}
