package json;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SampleDemo01 {
	//Map 集合、JavaBean、字符串 ->json对象
	
	public static void demo01() {
		Map<String,String> map = new HashMap<>();
		map.put("s01", "zs");
		map.put("s02", "ls");
		map.put("s03", "ww");
		JSONObject json = new JSONObject(map);
		System.out.println(json);
	}
	
	public static void demo02() {
		Person person = new Person();
		person.setName("zmh");
		person.setAge(23);
		Address address = new Address();
		address.setHomeAddress("home");
		address.setSchoolAddress("school");
		person.setAddress(address);
		JSONObject json = new JSONObject(person);
		System.out.println(json);
	}
	
	public static void demo03() {
		String str = "{\"name\":\"zmh\",\"age\":23}";
//		JSONObject json = new JSONObject(str);
		net.sf.json.JSONObject json = new net.sf.json.JSONObject();
		json = json.fromObject(str);
		System.out.println(json);
	}
	
	public void demo04() throws Exception {
		InputStream in = super.getClass().getClassLoader().getResourceAsStream("json/per.json");
		byte[] bs = new byte[10];
		int len = -1;
		StringBuffer sb = new StringBuffer();
		while(-1 != (len=in.read(bs))) {
			String str = new String(bs,0,len);
			sb.append(str);
		}
		String s = sb.toString();
		JSONObject json = new JSONObject(s);
		System.out.println(json);
	}
	
	public void demo05() throws Exception {
		String s = FileUtils.readFileToString(new File("D:\\wokspace\\JavaTip\\src\\json\\per.json"));
		JSONObject json = new JSONObject(s);
		System.out.println(json);
	}
	
	public static void demo06() throws Exception {
		Map<String,Person> map = new HashMap<>();
		Person p1 = new Person(23,"z1",new Address("x1","s1"));
		Person p2 = new Person(24,"z2",new Address("x2","s2"));
		Person p3 = new Person(25,"z3",new Address("x3","s3"));
		map.put("z1",p1);
		map.put("z2",p2);
		map.put("z3",p3);
		JSONObject json = new JSONObject(map);
		Writer writer = new FileWriter("D:\\p.obj");
		json.write(writer);
		writer.close();
	}
	
	// [{"name":"zs","age":23},{"name":"zs","age":23}]
	public static void demo07() {
		String jsonArrayStr = "[{\"name\":\"zs\",\"age\":23},{\"name\":\"zs\",\"age\":23}]";
		JSONArray jArray = new JSONArray(jsonArrayStr);
		System.out.println(jArray);
	}
	
	public static void demo08() {
		Map<String,String> map = new HashMap<>();
		map.put("s01", "zs");
		map.put("s02", "ls");
		map.put("s03", "ww");
		
		net.sf.json.JSONArray jArray = new net.sf.json.JSONArray();
		jArray = jArray.fromObject(map);
		System.out.println(jArray);
	}
	
	public static void demo09() {
		String jsonArrayStr = "[{\"name\":\"zs\",\"age\":23},{\"qwe\":\"ew\",\"kj\":23},{\"bv\":\"gf\",\"gfd\":23}]";
		net.sf.json.JSONArray jArray = new net.sf.json.JSONArray();
		jArray = jArray.fromObject(jsonArrayStr);
		
		Map<String,Object> map = new HashMap<>();
		
		for(int i = 0;i<jArray.size();i++) {
			Object o = jArray.get(i);
			net.sf.json.JSONObject json = (net.sf.json.JSONObject)o;
			Set<String> keySet = json.keySet();
			for(String key : keySet) {
				Object value = json.get(key);
				map.put(key, value);
			}
		}
		System.out.println(map);
	}
	
	public static void main(String[] args) throws Exception {
//		demo01();
//		demo02();
//		demo03();
//		SampleDemo01 sampleDemo01 = new SampleDemo01();
//		sampleDemo01.demo04();
//		sampleDemo01.demo05();
//		demo06();
//		demo07();
//		demo08();
		demo09();
	}
}
