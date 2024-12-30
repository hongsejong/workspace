package edu.kh.thread.ex3;

public class MoveRun {
	
	public static void main(String[] args) {
		
		Thread th1 = new Thread(new MoveHeart());
		Thread th2 = new Thread(new MoveStar());
		
		th2.start();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} //th1, th2의 실행 시간에 0.5초 차이를 줌
		th1.start();
	}

}
