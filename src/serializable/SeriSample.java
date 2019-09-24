package serializable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class SeriSample {
	public static void main(String[] args) throws Exception {
		writeObject();
		readObject();
	}
	
	public static void writeObject() throws Exception {
		Person per1 = new Person("zmh1",23);
		Person per2 = new Person("zmh2",23);
		Person per3 = new Person("zmh3",23);
		List<Person> persons = new ArrayList<>();
		persons.add(per1);
		persons.add(per2);
		persons.add(per3);
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:\\per.obj"));
		oos.writeObject(persons);
		oos.close();
	}
	
	public static void readObject() throws Exception {
		ObjectInputStream oin = new ObjectInputStream(new FileInputStream("D:\\per.obj"));
		Object perObj = oin.readObject();
		List<Person> pers = (List<Person>)perObj;
		for (Person per : pers) {
			System.out.println(per.getName()+","+per.getAge());
		}
		oin.close();
	}
}
