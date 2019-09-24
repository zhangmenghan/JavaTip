package FileSocket;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

//处理下载的线程
public class MyDownload implements Runnable{
	private Socket socket;
	
	public MyDownload() {
	}

	public MyDownload(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		try {
			OutputStream output = socket.getOutputStream();
			File file = new File("D:\\class.txt");
			//将此文件从硬盘读入到内存中
			InputStream filrIn = new FileInputStream(file);
			//定义每次发送的文件大小
			byte[] fileBytes = new byte[100];
			int len = -1;
			//发送
			while(-1 != (len = filrIn.read(fileBytes))) {
				output.write(fileBytes,0,len);
			}
			
			filrIn.close();
			output.close();
			socket.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
