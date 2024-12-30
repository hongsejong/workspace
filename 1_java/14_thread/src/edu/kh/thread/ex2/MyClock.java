package edu.kh.thread.ex2;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyClock implements Runnable{

	@Override
	public void run() {
		
		
		
		
	
		// SimpleDateFormat : 간단하게 날짜/시간 형식을 지정하는 객체
		SimpleDateFormat sdf = new SimpleDateFormat("HH시mm분ss초");
		// HH ->시 (24시간)
		// mm ->분
		// ss ->초

		while(true) {
			
			Date date = new Date();
			
			for(int i=0; i<50; i++) System.out.println();
			
			System.out.println(sdf.format(date));
			try {
				Thread.sleep(1000); //시간 출력 후 1초 일시정지
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		//19시 15분 24초
		
	}

}
