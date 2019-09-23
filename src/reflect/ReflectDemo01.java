package reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectDemo01 {
	//通过反射获取类
	public static void demo01() {
		//获取反射对象(反射入口):Class
		//1.Class.forName(全类名)
		try {
			Class<?> perClazz = Class.forName("reflect.Person");
			System.out.println(perClazz);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//2.XX.class
		Class<?> perClazz2 = Person.class;
		System.out.println(perClazz2);
		
		//3.对象.getClass()
		Person person = new Person();
		Class<?> perClazz3 = person.getClass();
		System.out.println(perClazz3);
	}
	
	//获取方法
	public static void demo02() {
		//Class入口
		Class<?> perClazz = null;
		try {
			perClazz = Class.forName("reflect.Person");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//获取所有的公共的方法(1.本类以及父类所有方法  2.符合访问修饰符规律)
		Method[] methods= perClazz.getMethods();
		for(Method method : methods) {
			System.out.println(method);
		}
		
		System.out.println("=======================");
		//获取当前类所有方法(1.只能是当前类  2.忽略访问修饰符限制)
		Method[] declaredMethods= perClazz.getDeclaredMethods();
		for(Method declaredMethod : declaredMethods) {
			System.out.println(declaredMethod);
		}
	}
	
	//获取所有的接口
	public static void demo03() {
		//Class入口
		Class<?> perClazz = null;
		try {
			perClazz = Class.forName("reflect.Person");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Class<?>[] interfaces = perClazz.getInterfaces();
		for(Class<?> inter : interfaces) {
			System.out.println(inter);
		}
	}
	
	//获取所有父类
	public static void demo04() {
		//Class入口
		Class<?> perClazz = null;
		try {
			perClazz = Class.forName("reflect.Person");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Class<?> superClass = perClazz.getSuperclass();
		System.out.println(superClass);
	}
	
	//获取所有构造方法
	public static void demo05() {
		//Class入口
		Class<?> perClazz = null;
		try {
			perClazz = Class.forName("reflect.Person");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Constructor<?>[] constructors = perClazz.getConstructors();
		for(Constructor<?> constructor : constructors) {
			System.out.println(constructor);
		}
	}
	
	//获取属性
	public static void demo06() {
		//Class入口
		Class<?> perClazz = null;
		try {
			perClazz = Class.forName("reflect.Person");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//获取所有公共属性
		Field[] fields = perClazz.getFields();
		for(Field field : fields) {
			System.out.println(field);
		}
		
		System.out.println("=======================");
		//获取当前类所有属性(忽略访问修饰符限制)
		Field[] declaredFields = perClazz.getDeclaredFields();
		for(Field declaredField : declaredFields) {
			System.out.println(declaredField);
		}
	}
	
	//获取当前反射所代表类(接口)的对象(实例)
	public static void demo07() {
		//Class入口
		Class<?> perClazz = null;
		try {
			perClazz = Class.forName("reflect.Person");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			Object instance = perClazz.newInstance();
			Person per = (Person)instance;
			per.interface2Method();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
//		demo01();
//		demo02();
//		demo03();
//		demo04();
//		demo05();
//		demo06();
		demo07();
	}
}
