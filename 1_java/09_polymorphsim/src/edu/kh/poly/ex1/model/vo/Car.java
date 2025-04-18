package edu.kh.poly.ex1.model.vo;

public class Car extends Object{
					//extends Object : 미작성 시 컴파일러가 자동 추가
	
	
	private int wheel; // 바퀴 개수
	private int seat; // 좌석 수
	private String fuel; //연료
	
	
	//기본 생성자
	public Car() {
		super(); //부모 생성자 (Object)
	}

	//매개변수 생성자
	//alt+shift+s-> o -> enter
	public Car(int wheel, int seat, String fuel) {
		super();
		this.wheel = wheel;
		this.seat = seat;
		this.fuel = fuel;
	}

	
	//getter / setter 자동완성
	//ale+shift +s -> r -> tab -> space -> shift+tab->enter
	public int getWheel() {
		return wheel;
	}

	public void setWheel(int wheel) {
		this.wheel = wheel;
	}

	public int getSeat() {
		return seat;
	}

	public void setSeat(int seat) {
		this.seat = seat;
	}

	public String getFuel() {
		return fuel;
	}

	public void setFuel(String fuel) {
		this.fuel = fuel;
	}

	//Object의 toString() 오버라이딩 자동완성
	//alt +shift +s ->s ->enter
	@Override
	public String toString() {
		return "Car [wheel=" + wheel + ", seat=" + seat + ", fuel=" + fuel + "]";
	}
	
	public void bindingTest() {
		System.out.println("Car 자료형 입니다.");
	}
	
	

}
