package file.split.merge;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class ConfigureReader {
	public static void main(String[] args) throws Exception {
		//读取配置文件
		File conFile = new File("D:\\splitDir\\config.properties ");
		readConfig(conFile);
	}
	
	public static void readConfig(File conFile) throws Exception {
		BufferedReader bf = new BufferedReader(new FileReader(conFile));
		String line = null;
		while(null != (line=bf.readLine())) {
			String[] arr = line.split("=");
			if(line.startsWith("filename")) {
				System.out.println("filename"+arr[1]);
			}
			if(line.startsWith("partcount")) {
				System.out.println("partcount"+arr[1]);
			}
		}
	}
}
