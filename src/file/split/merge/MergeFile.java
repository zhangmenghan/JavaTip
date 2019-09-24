package file.split.merge;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

public class MergeFile {
	public static void main(String[] args) throws Exception {
//		//方法一
//		//读取拆分后的多个文件(inputs 输入流的集合)
//		List<FileInputStream> inputs = new ArrayList<>();
//		for(int i = 1;i < 14 ;i++) {
//			inputs.add(new FileInputStream("D:\\splitDir\\" + i + ".part"));
//		}
//		
//		//指定合并后的文件
//		OutputStream out = new FileOutputStream("D:\\ILSVRC.zip");
//		
//		//将多个输入流依次读入内存,最后再一次性输出
//		byte[] buf = new byte[1024*1024];
//		for(FileInputStream in : inputs) {
//			int len = in.read(buf);
//			out.write(buf,0,len);
//		}
//		out.close();
//		for(FileInputStream in : inputs) {
//			in.close();
//		}
		
		//方法二
		//指定拆分后文件位置
		File spliDir = new File("D:\\splitDir");
		mergeFile(spliDir);
	}
	
	public static Properties getProperties() throws Exception {
		String configFileName = "D:\\splitDir\\config.properties";
		Properties prop = new Properties();
		prop.load(new FileInputStream(configFileName));
		return prop;
	}
	
	public static void mergeFile(File splitDir) throws Exception {
		Properties prop = getProperties();
		String filename = (String) prop.get("filename");
		int partcount = Integer.parseInt((String) prop.get("partcount"));
		
		List<FileInputStream> inputs = new ArrayList<>();
		for(int i = 1;i < partcount ;i++) {
			inputs.add(new FileInputStream("D:\\splitDir\\" + i + ".part"));
		}
		Enumeration<FileInputStream> en = Collections.enumeration(inputs);
		SequenceInputStream sin = new SequenceInputStream(en);
		OutputStream out = new FileOutputStream("D:\\"+filename);
		byte[] buf = new byte[1024];
		int len = -1;
		while(-1 != (len = sin.read(buf))) {
			out.write(buf, 0, len);
		}
		
		out.close();
		sin.close();
	}
}
