package file.split.merge;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class SplitFile {
	public static void main(String[] args) throws Exception {
		//原文件(待拆分的文件)
		File resFile = new File("D:\\ILSVRC.zip");
		//拆分后的目录
		File splitDir = new File("D:\\splitDir");
		splitFile(resFile,splitDir);
		
	}
	
	//拆分  1个输入流  n个输出流
	//合并  1个输出流  n个输入流 (顺序不能乱)
	
	//执行拆分任务
	public static void splitFile(File resFile,File splitDir) throws Exception {
		if(!splitDir.exists()) {
			splitDir.mkdir();
		}
		
		//拆分
		InputStream in = new FileInputStream(resFile);
		OutputStream out = null;
		//定义缓冲区为1M
		byte[] buf = new byte[1024*1024];
		int len = -1;
		int count = 1;
		while(-1 != (len=in.read(buf))) {
			out = new FileOutputStream(new File(splitDir,count++ + ".part"));
			out.write(buf,0,len);
			out.flush();
		}
		
		//生成一个配置文件保存描述信息
		//方式一
//		out = new FileOutputStream(new File(splitDir,"config.properties"));
//		//查询当前系统的换行符
//		String lineSeperator = System.getProperty("line.separator");
//		out.write(("filename="+resFile.getName()+lineSeperator).getBytes());
//		out.write(("partcount=" + (count-1)).getBytes());
		
		//方式二
		out = new FileOutputStream(new File(splitDir,"config.properties"));
		Properties prop = new Properties();
		prop.setProperty("filename", resFile.getName());
		prop.setProperty("partcount", (count-1)+"");
		//写入硬盘(持久化)
		prop.store(out, "file configuration...");
		
		out.close();
		in.close();
	}
}
