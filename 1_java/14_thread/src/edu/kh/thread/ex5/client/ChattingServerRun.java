package edu.kh.thread.ex5.client;

import edu.kh.thread.ex5.Server.ChattingServer;

public class ChattingServerRun {
	public static void main(String[] args) {
		ChattingServer server = new ChattingServer();
		server.start();
	}

}
