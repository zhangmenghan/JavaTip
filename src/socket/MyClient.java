package socket;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class MyClient {

	public static void main(String[] args) throws Exception {
		Socket socket = new Socket("127.0.0.1", 9999);
		InputStream input = socket.getInputStream();
		byte[] bs = new byte[100];
		input.read(bs);
		
		OutputStream output = socket.getOutputStream();
		output.write("world".getBytes());
		
		input.close();
		output.close();
		socket.close();
	}
	
}
