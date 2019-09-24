package FileSocket;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class MyClient {

	public static void main(String[] args) throws Exception {
		Socket socket = new Socket("127.0.0.1", 9999);
		InputStream input = socket.getInputStream();
		//接受每次发来的文件切片
		byte[] bs = new byte[100];
		int len = -1;
		OutputStream fileOut = new FileOutputStream("D:\\c.txt");
		while (-1 != (len = input.read(bs))) {
			fileOut.write(bs,0,len);
		}
		
		fileOut.close();
		input.close();
		socket.close();
	}
	
}
