package utils;

import java.util.Scanner;

public class SecurityDemo {
	String uname;
	String pwd;
	Scanner input = new Scanner(System.in);
	
	public boolean register() throws Exception{
		boolean flag = true;
		
		System.out.println("Input your username:");
		uname = input.next();
		System.out.println("Input your password");
		pwd = input.next();
		pwd = SecurityUtil.md5Encode(pwd.getBytes());
		
		flag = true;
		return flag;
	}
	
	public boolean login() {
		boolean result = false;
		System.out.println("Input your username:");
		String loginName = input.next();
		System.out.println("Input your password");
		String loginPwd = input.next();
		loginPwd = SecurityUtil.md5Encode(loginPwd.getBytes());
		
		if(loginName.equals(uname) && loginPwd.equals(pwd)) {
			result = true;
		}
		return result;
	}
	
	public static void main(String[] args) {
		
	}
}
