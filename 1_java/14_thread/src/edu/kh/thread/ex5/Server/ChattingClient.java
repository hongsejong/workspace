package edu.kh.thread.ex5.Server;

import java.net.Socket;
import java.util.Scanner;


import edu.kh.thread.ex5.thread.Recevier;
import edu.kh.thread.ex5.thread.Sender;

//클라이언트
public class ChattingClient implements Runnable {
	
	public void start() {
		Scanner sc = new Scanner(System.in);
		
		//1.ip, port 입력 받기
		
		System.out.print("접속할 IP : ");
		String serverIP = sc.next();
		
		System.out.print("포트 번호 : ");
		int port = sc.nextInt();
		sc.nextLine(); //입력 버퍼에 남아있는 개행문자 제거
		
		Socket socket = null;
		
		try {
			//2.서버 접속
			socket = new Socket(serverIP,port);
			
			if(socket!=null) { //서버 접속 성공 시
				System.out.println("<<서버 접속 성공!>>");
				
				System.out.print("클라이언트 이름 입력 :");
				String clientName = sc.nextLine();
				
				//스레드를 이용해서 코드 작성 예정
				//-------------------------------
				Sender sender = new Sender(socket, clientName);
				
				Recevier receiver = new Recevier(socket);
				//-------------------------------
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(socket !=null) socket.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
