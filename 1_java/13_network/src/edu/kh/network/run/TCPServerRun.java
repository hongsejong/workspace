package edu.kh.network.run;

import edu.kh.network.model.service.TCPClient;
import edu.kh.network.model.service.TCPServer;

public class TCPServerRun {
	public static void main(String[] args) {
		TCPServer server = new TCPServer();
		
		server.serverStart();
		
	
	}

}
