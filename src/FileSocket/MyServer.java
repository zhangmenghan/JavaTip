package FileSocket;

import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
	
	public static void main(String[] args) throws Exception {
		ServerSocket serverSocket = new ServerSocket(9999);
		
		while(true) {
			Socket socket = serverSocket.accept();
			//下载线程  Runable->Thread
			new Thread(new MyDownload(socket)).start();
		}
	}
}
