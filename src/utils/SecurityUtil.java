package utils;

import java.lang.reflect.Method;

import org.apache.commons.codec.digest.DigestUtils;

public class SecurityUtil {

	//异或
	public static String xor(String input) {
		char[] chs = input.toCharArray();
		for(int i = 0;i<chs.length;i++) {
			chs[i] = (char) (chs[i]^3000);
		}
		
		return new String(chs);
	}
	
	//MD5
	public static String md5Encode(byte[] input) {
		return DigestUtils.md5Hex(input);
	}
	
	//SHA256
	public static String shaEncode(byte[] input) {
		return DigestUtils.sha256Hex(input);
	}
	
	//BASE64 加密
	public static String baseEncode(byte[] input) throws Exception {
		String result = null;
		Class clazz = Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
		Method method = clazz.getMethod("encode", byte[].class);
		result = (String) method.invoke(null, input);
		return result;
	}
	
	//BASE64 解密
	public static byte[] baseDecode(String input) throws Exception{
		byte[] result = null;
		Class clazz = Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
		Method method = clazz.getMethod("decode", String.class);
		result = (byte[]) method.invoke(null, input);
		return result;
	}
	
	public static void main(String[] args) throws Exception {
//		String str = "hello";
//		str = xor(str);
//		System.out.println(str);
//		str = xor(str);
//		System.out.println(str);
		
//		String str = "hello";
//		str = md5Encode(str.getBytes());
//		System.out.println(str);
		
//		String str = "hello";
//		str = shaEncode(str.getBytes());
//		System.out.println(str);
		
		String str ="hello";
		str = baseEncode(str.getBytes());
		System.out.println(str);
		byte[] result = baseDecode(str);
		System.out.println(new String(result));
	}
}
