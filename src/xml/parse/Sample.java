package xml.parse;

import java.util.List;

//解析xml
public class Sample {
	public static void main(String[] args) throws Exception {
		//输入一个XML文件名路径,输出一个List<Dog>
		XmlParseUtil xmlParseUtil = new XmlParseUtil();
		List<Dog> result = xmlParseUtil.parseXmlToList("src/xml/parse/dogs.xml");
		for(int i = 0;i<result.size();i++) {
			System.out.println(result.get(i).toString());
		}
	}
}
