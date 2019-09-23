package reflect;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Properties;

public class ReflectDemo02 {

	//获取对象的实例,并操作该对象
	public static void demo01() {
		//Class入口
		Class<?> perClazz = null;
		try {
			perClazz = Class.forName("reflect.Person");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			Person per = (Person)perClazz.newInstance();
			per.setName("zmh");
			per.setAge(22);
			System.out.println(per.getName());
			System.out.println(per.getAge());
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	//操作属性
	public static void demo02() {
		//Class入口
		Class<?> perClazz = null;
		try {
			perClazz = Class.forName("reflect.Person");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			Person per = (Person)perClazz.newInstance();
			Field idField = perClazz.getDeclaredField("id");
			//访问的是private修饰的ID,但是private只有奔雷才可以访问
			//修改属性的访问权限
			idField.setAccessible(true);
			idField.set(per, 1);
			System.out.println(per.getId());
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}
	
	//操作方法
	public static void demo03() {
		//Class入口
		Class<?> perClazz = null;
		try {
			perClazz = Class.forName("reflect.Person");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			Person per = (Person)perClazz.newInstance();
			Method privateMethod = perClazz.getDeclaredMethod("privateMethod", null);
			//访问的是private修饰的ID,但是private只有本类才可以访问
			//修改属性的访问权限
			privateMethod.setAccessible(true);
			privateMethod.invoke(per, null);
			
			Method privateMethod2 = perClazz.getDeclaredMethod("privateMethod2", String.class);
			//访问的是private修饰的ID,但是private只有本类才可以访问
			//修改属性的访问权限
			privateMethod2.setAccessible(true);
			privateMethod2.invoke(per, "privateMethod2...");
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}
	
	//操作构造方法
	public static void demo04() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		//Class入口
		Class<?> perClazz = null;
		try {
			perClazz = Class.forName("reflect.Person");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//获取指定的构造方法
		//基本类型和包装类型不同
		Constructor<?> constructor = perClazz.getConstructor(Integer.class);
		System.out.println(constructor);
		Person instance = (Person)constructor.newInstance(12);
		System.out.println(instance);
		
		Constructor<?> constructor1 = perClazz.getConstructor();
		System.out.println(constructor1);
		Person instance1 = (Person)constructor1.newInstance();
		System.out.println(instance1);
		
		Constructor<?> constructor2 = perClazz.getDeclaredConstructor(String.class);
		System.out.println(constructor2);
		constructor2.setAccessible(true);
		Person instance2 = (Person)constructor2.newInstance("zmh");
		System.out.println(instance2);
	}
	
	//动态加载类名和方法
	public static void demo05() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, FileNotFoundException, IOException {
		Properties prop = new Properties();
		prop.load(new FileReader("class.txt"));
		String classname = prop.getProperty("classname");
		String methodname = prop.getProperty("methodname");
		
		//Class入口
		Class<?> perClazz = null;
		try {
			perClazz = Class.forName(classname);
			System.out.println(perClazz);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Method method = perClazz.getMethod(methodname);
		method.invoke(perClazz.newInstance());
	}
	
	//反射可以越过泛型检查
	public static void demo06() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		ArrayList<Integer> list = new ArrayList<>();
		list.add(123);
		Class<?> listClazz = list.getClass();
		Method method = listClazz.getMethod("add", Object.class);
		method.invoke(list, "zmh...");
		System.out.println(list);
	}
	
	public static void demo07() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Person per = new Person();
		PropertyUtil.setProperty(per, "name", "zs");
		PropertyUtil.setProperty(per, "age", 22);
		Student stu = new Student();
		PropertyUtil.setProperty(stu, "score", 98);
		
		System.out.println(per.getName() + "    " + per.getAge());
		System.out.println(stu.getScore());
	}
	
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, FileNotFoundException, IOException, NoSuchFieldException {
//		demo01();
//		demo02();
//		demo03();
//		demo04();
//		demo05();
//		demo06();
		demo07();
	}
}
