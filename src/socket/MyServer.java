package socket;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
	
	public static void main(String[] args) throws Exception {
		ServerSocket serverSocket = new ServerSocket(9999);
		Socket socket = serverSocket.accept();
		
		OutputStream output = socket.getOutputStream();
		String info = "hello";
		//String->byte
		output.write(info.getBytes());
		
		InputStream input = socket.getInputStream();
		byte[] bs = new byte[100];
		input.read(bs);
		
		input.close();
		output.close();
		serverSocket.close();
		socket.close();
	}

}
