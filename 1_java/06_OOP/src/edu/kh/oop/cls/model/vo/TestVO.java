package edu.kh.oop.cls.model.vo;

 class TestVO {
//접근제한자(default) :같은 패키지 내에서만 import가 가능
	 
	 
	 
	 
	 public void ex() {
		 System.out.println("같은 패키지");
		 
		 Student std = new Student();//학생 객체 생성
		 
		 System.out.print(std.t1);
		 System.out.print(std.t2);
		 System.out.print(std.t3);
		// System.out.print(std.t4);
		// t4는 private이기 때문에 같은 패키지여도
		// 다른 클래스에서 직접 접근 불가
	 }
}
