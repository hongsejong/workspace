package edu.kh.thread.ex2;

public class ThreadControlRun {
	public static void main(String[] args) {
		
		Thread sleepTest = new Thread(new SleepThread1());
		Thread MyClock = new Thread(new MyClock());
		InterruptTest it =new InterruptTest();
//		sleepTest.start();
//		MyClock.start();
//		it.test();
		StopWatchController stopWatch = new StopWatchController();
		stopWatch.watchStart();
		
		
	}
	
	
	
	

}
