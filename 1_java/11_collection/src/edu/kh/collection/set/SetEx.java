package edu.kh.collection.set;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import edu.kh.collection.list.model.vo.Student;

public class SetEx {

	// 컬렉션 특징 : 크기 제한X, 다양한 기능,객체만 저장 가능

	//Set(집합)
	//-중복 데이터 저장 불가 (같은 객체를 덮어 씌움)
	//-순서를 유지하지 않음 (index 없음)
	//->get() 메소드 없음

	// 대표적인 자식 클래스
	// HashSet : hashCode()를 이용해서 중복 비교
	//			 hashCode() 오버라이딩 필수
	//			 빠른 검색 지원

	//LinkedHashSet : 순서가 유지되는 set
	//				  hashCode() 오버라이딩 필수

	//TreeSet : 저장되는 값을 자동으로 정렬
	//			Comparable 상속 필수

	public void ex1() {
		// 기본적인 Set 사용법

		//String,Integer 등 Java 제공 객체는
		//기본적으로 hashCode(), equals(), comparaTo() 등이
		//오버라이딩 되어 있음

		Set<String> set = new HashSet<String>();
		//Cannot instantiate the type Set :인터페이스는 객체로 생성 불가!!

		//1. boolean set.add(E e) : 추가

		set.add("네이버");
		set.add("카카오");
		set.add("라인");
		set.add("쿠팡");
		set.add("배달의 민족");
		set.add("당근마켓");
		set.add("토스");

		System.out.println(set);
		//HashSet은 순서 유지X


		//중복 저장 확인
		set.add("토스");
		set.add("토스");
		set.add("토스");
		set.add("토스");
		set.add("토스");
		set.add("토스");

		System.out.println(set);

		//set 에 null도 저장가능 하나 1개만 가능(중복X)
		set.add(null);
		set.add(null);
		set.add(null);
		System.out.println(set);

		//2. int set.size() : 저장된 객체의 수 반환
		System.out.println(set.size());

		//3. boolean remove(E e) :
		// e와 똑같은 요소가 set에 있으면 지우면 true 반환
		// 없으면 false 반환

		if(set.remove("배달의 민족")) {
			System.out.println("배달의 민족이 제거되었습니다.");
		}else {
			System.out.println("배달의 민족이 없습니다.");
		}
		System.out.println(set);

		//4.boolean set.contains(E e) :
		//set 에 저장된 객체 중 e와 똑같은 객체가 있으면 true, 아니면 false

		System.out.println("네이버 "+(set.contains("네이버") ? "있음" : "없음"));
		System.out.println("직방 "+(set.contains("직방") ? "있음" : "없음"));



		//5. void set.clear() : set에 저장된 객체를 모두 지움
		set.clear();
		System.out.println("clear 후 size : " + set.size());

		//6. boolean set.isEmpty() :
		//set에 저장된 객체가 없다면(비어있다면) true, 아니면 false
		// set.size() == 0 과 같은 의미
		set.isEmpty();
		System.out.println("set이 "+(set.isEmpty()? "비어있음": "비어있지 않음" ));

	}

	public void ex2() {
		//Set에 저장된 값 꺼내쓰는 방법

		//1. Iterator (반복자)
		//- 컬렉션 객체의 요소를 하나씩 꺼내서 반복 접근할 수 있는 객체
		//	(순서대로 요소 하나씩 꺼내는 객체)

		Set<String> set = new HashSet<>();
		set.add("쌀과자");
		set.add("오예스");
		set.add("초코파이");
		set.add("몽쉘");
		set.add("빅파이");

		//Iterator set.iterator() :
		// set의 내용을 순차(반복) 접근할 수 있는 형태로 변경해서 반환
		Iterator<String> it = set.iterator();

		//boolean it.hasNext():
		// 다음으로 꺼낼 요소가 있으면 true, 없으면 false

		while(it.hasNext()) { //하나씩 꺼냈을 때 다음 값이 없는 경우 == 끝
			//->다음 값이 있으면 반복!

			// E it.next() : 다음 요소 꺼내서 반환
			String temp = it.next();
			System.out.println(temp);

		}


		//---------------------------------------------
		System.out.println("------------------------------");
		//2.향상된 for문
		for(String s : set) {
			System.out.println(s);
		}

	}

	public void ex3() {
		//Set이 중복을 확인하는 방법
		//->새로 추가하려는 객체와 Set에 있는 객체가
		//	equals() 수행시 true 
		//	hashCode() 비교 시 true 이면 중복으로 판단

		// *Set을 이용하려면 equals()와 hashCode() 필수로 오버라이딩 *

		//A.equals(B) : A와 B가 가지고 있는 필드 값이 모두 같으면 true, 아니면 false


		//hashCode() : 필드 값이 다르면 중복되지 않는 숫자를 만드는 메소드
		// -> 왜 만들까? 빠른 데이터 검색을 위해서(객체가 어디에 있는지 빨리 찾기 위해서)


		//Hash 함수 : 입력된 단어를 지정된 길이의 문자열로 변환하는 함수(중복X)
		//ex) 입력 : 123		->"asdf" (5글자)
		//ex) 입력 : 1234567	->"qwert"(5글자)

		//HashSet() : 중복 없이 데이터 저장(Set)하고 데이터 검색이 빠름(Hash)

		Set<Student> set = new HashSet<>();

		Student std1 = new Student(3,4,5,"홍길동",'M',100);
		Student std2 = new Student(3,4,5,"홍길동",'M',100);
		Student std3 = new Student(3,4,6,"김길동",'M',100);

		set.add(std1);
		set.add(std2);
		set.add(std3);

		//		set.add(new Student(3,4,5,"홍길동",'M',100));
		//		set.add(new Student(3,4,5,"홍길동",'M',100));
		//		set.add(new Student(3,4,6,"홍길동",'M',100));

		System.out.println("size : " + set.size());

		for(Student std : set) {
			System.out.println(std);
			System.out.println(std.hashCode());
		}

	}

	public void createLotto() {
		//로또 번호(1~45난수,6개 중복X,오름차순)
		Random random = new Random(); //난수 생성 객체

		//컬렉션은 기본 자료형 저장 불가
		//->Wrapper 클래스 이용
		//		Set<Integer> lotto = new HashSet<>();
		//		Set<Integer> lotto = new LinkedHashSet<>(); //순서 유지
		Set<Integer> lotto = new TreeSet<>(); // 오름차순 정렬 set
		//Integer는 equals(), hashCode() 오버라이딩 완료 상태

		while(lotto.size()<6) { // lotto에 저장된 난수가 6개 미만이면 반복
			lotto.add(random.nextInt(45)+1);
			// int값이 자동으로 Integer로 포장(AutoBoxing)됨

			//			System.out.println(lotto);
		}
		System.out.println(lotto);
	}


	public void lottoVersion2() {
		//금액을 입력 받아서(천원 단위)
		//3천원이면 3회, 5천원이면 5회 치
		//로또 번호를 자동으로 생성해서 출력

		//단, 1000원 미만으로 입력한 경우
		// "1000원 이상만 입력해주세요" 출력

		Random random = new Random();

		Scanner sc = new Scanner(System.in);
		System.out.println("금액을 입력하세요(천원 단위) :");
		int input=sc.nextInt();
		if(input<1000) {
			System.out.println("1000원 이상만 입력하세요");
			return;
		}

		Set<Integer> lotto = new TreeSet<>();
		for(int i=0; i<input/1000; i++) {
			while(lotto.size()<6) {
				lotto.add(random.nextInt(45)+1);

			}
			System.out.println(lotto);
		}
	}
	public void lottoVersion2_() {

		Scanner sc = new Scanner(System.in);
		
		try {
			while(true) {
				System.out.print("금액 입력 : ");
				int price = sc.nextInt();
				if(price==0) {
					break;
				}
				if(price < 1000) {
					System.out.println("1000원 이상만 입력 해주세요.");
					//				continue;
				}
				for(int i=0; i<price /1000; i++) {
					createLotto();
				}
				
				System.out.println("거스름돈 : "+ price%1000);
			}
		}catch(InputMismatchException e) {
			System.out.println("숫자만 입력해주세요");
			sc.nextLine(); //입력버퍼에 남은 문자열 제거
			
		}


	}
	public void lottoVersion_list() {
		//금액을 입력 받아서(천원 단위)
		//3천원이면 3회, 5천원이면 5회 치
		//로또 번호를 자동으로 생성해서 출력

		//단, 1000원 미만으로 입력한 경우
		// "1000원 이상만 입력해주세요" 출력

		Random random = new Random();

		Scanner sc = new Scanner(System.in);
		System.out.println("금액을 입력하세요(천원 단위) :");
		int input=sc.nextInt();
		if(input<1000) {
			System.out.println("1000원 이상만 입력하세요");
			return;
		}

		for(int i=0; i<input/1000; i++) {
			List<Integer> lotto = new ArrayList<>();
			System.out.println("로또 번호 " + (i + 1) + ": " + lotto);
			while(lotto.size()<6) {
				lotto.add(random.nextInt(45)+1);
			}
			System.out.println("로또 번호 " + (+ 1) + ": " + lotto);
		}
		
		List<Integer> arr = new ArrayList<>();
		arr.add(2);
		System.out.println(arr);
		arr.add(3);
		System.out.println(arr);
		arr.add(4);
		System.out.println(arr);
		
		List<Integer> arr2 = new ArrayList<>();
		arr2.add(2);
		System.out.println(arr2);
		arr2 = new ArrayList<>();
		arr2.add(3);
		System.out.println(arr2);
		arr2 = new ArrayList<>();
		arr2.add(4);
		System.out.println(arr2);
		
		
		
	}










}
