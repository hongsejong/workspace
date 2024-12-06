package edu.kh.collection.list.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import edu.kh.collection.list.model.service.StudentService;
import edu.kh.collection.list.model.vo.Student;

public class StudentView   {
	
	//필드
	private Scanner sc = new Scanner(System.in);
	private StudentService service = new StudentService();
	// alt + shift + j : 메소드/클래스 설명용 주석
	/**
	 * 메뉴 출력용 view 메소드
	 */
	public void displayMenu() {
	      
	      int menuNum = 0; // 입력된 메뉴 번호 저장
	      
	      do {
	         try { // 예외가 발생할 수 있는 코드 작성 부분
	            
	            System.out.println();
	            System.out.println("\n***** 학생 관리 프로그램 *****\n");
	            System.out.println("1. 학생 추가");
	            System.out.println("2. 학생 전체 조회");
	            System.out.println("3. 학생 1명 조회(index)");
	            System.out.println("4. 학생 1명 삭제(index)");
	            System.out.println("5. 성별 조회");
	            System.out.println("6. 같은 학년 조회");
	            System.out.println("7. 이름으로 조회");
	            System.out.println("8. 중복 확인");
	            System.out.println("9. 성적 순서로 조회(정렬)");
	            
	            System.out.println("0. 프로그램 종료");
	            
	            System.out.println("메뉴 선택 >> ");
	            menuNum = sc.nextInt(); //InputMismatchException 발생 가능성 있음
	            
	            switch(menuNum) {
	            case 1 : addStudent();break;
	            case 2 : selectAllStudent();break;
	            case 3 : selectOne(); break;
	            case 4 : deleteStudent(); break;
	            case 5 : selectGender(); break;
	            case 6 : selectGrade(); break;
	            case 7 : selectName(); break;
	            case 8 : duplicateValidation(); break;
	            case 9 : sortScore(); break;
	            
	            case 0 : System.out.println("<프로그램 종료>");break;
	            default : System.out.println("잘못입력하셨습니다.");
	            }
	            
	         }catch(InputMismatchException e) {  // try에서 발생한 예외를 잡아서 처리하는 부분
	            // InputMismatchException : Scanner 입력 타입이 잘못됨
	            
	            System.out.println("* 메뉴 번호만 입력해주세요 *");
	            menuNum= -1; //예외 발생 시 while문 종료를 막기 위해 변경
	            // Scanner 입력 버퍼에 남아있는
	            // 잘못 입력된 값을 제거하는 코드
	            sc.nextLine();
	         }
	         
	      }while(menuNum != 0);
	      
	}
	
	
	/**
	 * 학생 추가 view 메소드
	 */
	public void addStudent() {
		
		System.out.println(" \n ----- 학생 추가  -----\n");
		
		System.out.print("학년 : ");
		int grade = sc.nextInt();
		
		System.out.print("반 : ");
		int ban = sc.nextInt();
		
		System.out.print("번호 : ");
		int number = sc.nextInt();
		
		System.out.print("이름 : ");
		String name = sc.next();
		
		System.out.print("성별(M/F) : ");
		char gender = sc.next().charAt(0);
		
		System.out.print("점수 : ");
		int score = sc.nextInt();
		
		//서비스 객체의 addStudent() 호출(값 6개 전달) 후 boolean 값 반환
		boolean result = service.addStudent(grade,ban,number,name,gender,score);
	
		if(result) { // result 가 true인 경우
			System.out.println(name+" 학생이 추가되었습니다.");
			
		}else {
			System.out.println("학생 추가 실패");
		}
	}
	
	/**
	 * 학생 전체 조회 view 메소드
	 */
	// 학생 전체 조회 메서드
	private void selectAllStudent() {
	    System.out.println("\n----- 학생 전체 조회 -----\n");
	    
	    // Service에 있는 studentList를 통째로 얻어와 for문 이용하여 출력
	    List<Student> studentList = service.selectAllStudent();
	    
	    // studentList의 크기만큼 반복하여 각 학생 정보를 출력
	    for (int i = 0; i < studentList.size(); i++) {
	        System.out.printf("[%d] %s \n", i, studentList.get(i));
	    }
	}


	
	/**
	 * 학생 1명 조회 view 메소드
	 */
	private void selectOne() {
		System.out.println("\n---- 학생 1명 조회(index) ----\n");
		
		System.out.print("조회할 학생의 index 입력 :");
		int index = sc.nextInt();
		
		//Service의 studentList에서 index가 일치하는 학생 1명 반환 받기
		Student std =service.selectOne(index);
		
		//std == null 또는 학생 1명(Student) 객체 주소
		
		if(std ==null) {
			System.out.println("해당 학생이 존재하지 않습니다.");
		}else {
			System.out.println(std); // std.toString()
		}
		
		//존재하는 경우 : 해당 학생 정보 출력
		//존재하지 않는 경우 : "해당 index에 학생이 존재하지 않습니다." 출력
	}
	
	/**
	 *  학생 1명 삭제 view 메소드
	 */
	private void deleteStudent() {
	System.out.println("\n---- 학생 1명 삭제(index) ----\n");
		
		System.out.print("삭제할 학생의 index 입력 :");
		int index = sc.nextInt();
		
		//Service의 studentList에서 index가 일치하는 학생 제거 후 반환
		Student std =service.deleteStudent(index);
		
		if(std ==null) {
			System.out.println("해당 index에 학생이 존재하지 않습니다.");
		}else {
			System.out.println(std.getName()+" 학생의 정보가 삭제되었습니다.");
		}
		
		//제거된 경우 : 000 학생의 정보가 삭제되었습니다.
		//존재하지 않는 경우 : "해당 index에 학생이 존재하지 않습니다." 출력
	}
	
	/**
	 * 성별 조회 view 메소드
	 */
	private void selectGender() {
		System.out.println("\n----- 성별 조회 -----\n");
		System.out.print("조회할 성별을 입력해주세요(M/F) : ");
		char gender = sc.next().toUpperCase().charAt(0); //-> 무조건 대문자
					//String / 대문자변경 / 0번째 문자 하나
		
		//잘못 입력한 경우
		if(gender!='F' && gender!='M') {
			
			System.out.println("* M 또는 F만 입력해주세요.*");
			return;
		}
		
		// M 또는 F를 입력한 경우
		// Service의 성별 조회 메소드 호출(입력 받은 성별 전달) 후
		// 일치하는 성별만 담긴 한색 목록을 반환받기
		
		List<Student> searchList =service.selectGender(gender);
			
		// 만약 검색 결과가 없을 경우
		// ==searchList가 비어있을 경우
//		if(searchList.isEmpty()==true) {
		if(searchList.size()==0) {
			System.out.println("검색 결과가 없습니다.");
		}else { //검색 결과가 있을 경우
			for(Student std : searchList) {
				System.out.println(std);
			}
			
		}
		
		
	}
	
	/**
	 * 같은 학년 조회 view 메소드
	 */
	private void selectGrade() {
		System.out.println("\n-----같은 학년 조회-----\n");
		System.out.println("조회할 학년을 입력해주세요 : ");
		int grade= sc.nextInt();
		
		//학년을 재대로 입력 시( 학년은 1~6학년 까지만 존재)
		if(grade<1 || grade>6) {
			System.out.println("* 1~6학년만 입력해주세요*");
			return;
		}
		
				
		//해당 학년이 존재하는 경우
		List<Student> gradeList =service.selectGrade(grade);
		
		if(gradeList.isEmpty()) {
			//검색 결과 없을 경우 " 검색 결과가 존재하지 않습니다."
			System.out.println("검색 결과가 없습니다.");
		}else { //존재하는 경우
			//해당 학년의 학생목록 조회 후 출력
			for(Student std : gradeList) {
				System.out.println(std);
			}
		}
	
		
	}
	/**
	 * 이름 검색 view 메소드
	 */
	private void selectName() {
		System.out.println("\n-----이름 조회-----\n");
		System.out.println("조회할 이름을 입력해주세요 : ");
		String name= sc.next();
		
		List<Student> nameList = service.selectName(name);
		
		if(nameList.isEmpty()) {
			System.out.println("검색 결과가 없습니다.");
		}else {
			for(Student stdName : nameList) {
				System.out.println(stdName);
			}
		}
		
	}
	
	/**
	 * 중복 확인 view 메소드
	 */
	private void duplicateValidation() {
		
		//학년,반,번호,이름,성별,점수를 입력 받아
		//모두 똑같은 학생이 존재하는지 확인
		
		System.out.println("\n-----중복 확인 -----\n");

		System.out.print("학년 : ");
		int grade = sc.nextInt();
		
		System.out.print("반 : ");
		int ban = sc.nextInt();
		
		System.out.print("번호 : ");
		int number = sc.nextInt();
		
		System.out.print("이름 : ");
		String name = sc.next();
		
		System.out.print("성별(M/F) : ");
		char gender = sc.next().charAt(0);
		
		System.out.print("점수 : ");
		int score = sc.nextInt();
		
		// 입력 받은 값을 서비스로 전달하여
		//중복이면 true, 아니면 false 반환 받기
		
		boolean result = service.duplicateValidation(grade,ban,number,name,gender,score);
	
		
		if(result) {
			System.out.println("같은 학생이 있습니다.");
			
		}else {
			System.out.println("같은 학생이 없습니다.");
		}
	}
	
	/**
	 *  성적 순서로 조회(정렬)
	 */
	private void sortScore() {
		System.out.println("\n----- 성적 순서로 조회(정렬) -----\n");
		
		
		List<Student> sortList =  service.sortScore();
		
		for(Student std : sortList) {
			System.out.println(std);
			
		}
		
	}
	
	
	
}