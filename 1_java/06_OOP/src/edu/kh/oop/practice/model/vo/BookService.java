package edu.kh.oop.practice.model.vo;


public class BookService {
	
	public void practice() {
		
		Book book1 = new Book();
		System.out.println(book1.toString());
		Book book2 = new Book("자바의정석",30000,0.2,"남궁성");
		System.out.println(book2.toString());
		System.out.println("===================");
		System.out.println("수정된 결과 확인");
	
		
		
		book1.setTitle("C언어");
		book1.setPrice(30000);
		book1.setDiscountRate(0.1);
		book1.setAuthor("홍길동");
		System.out.println(book1.toString());
		System.out.println("===================");
		
		System.out.println("도서명 = "+book1.getTitle());
		System.out.printf("할인된 가격 : %.0f원\n",book1.getPrice()-book1.getPrice()*book1.getDiscountRate());
		System.out.println("도서명 = "+book2.getTitle());
		System.out.printf("할인된 가격 : %.0f원\n",book2.getPrice()-book2.getPrice()*book2.getDiscountRate());
	}
	
	
	
	

}
