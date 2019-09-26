package xml.parse;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlParseUtil {
	public List<Dog> parseXmlToList(String fileName) throws Exception {
		List<Dog> result = new ArrayList<>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();//产生一个Dog工厂实例
		DocumentBuilder builder = factory.newDocumentBuilder();
		//准备输入流
		FileInputStream input = new FileInputStream(fileName);
		//解析为一个可以用 java 处理的对象
		Document document = builder.parse(input);
		//获取所有的节点
		Element element = document.getDocumentElement();
		NodeList nodeList = element.getElementsByTagName("dog");
		for(int i =0;i<nodeList.getLength();i++) {
			Dog dog = new Dog();
			Element dogElement = (Element)nodeList.item(i);
			int id = Integer.parseInt(dogElement.getAttribute("id"));
			dog.setId(id);
			NodeList childNodes = dogElement.getChildNodes();
			for(int j = 0;j<childNodes.getLength();j++) {
				Node dogChild = childNodes.item(j);
				if(dogChild.getNodeType() == Node.ELEMENT_NODE) {
					if(dogChild.getNodeName().equals("name")) {
						String name = dogChild.getFirstChild().getNodeValue();
						dog.setName(name);
					}else if(dogChild.getNodeName().equals("score")) {
						Double score = Double.parseDouble(dogChild.getFirstChild().getNodeValue());
						dog.setScore(score);
					}else if(dogChild.getNodeName().equals("level")) {
						dogChild.getFirstChild().getNodeValue();
						int level = Integer.parseInt(dogChild.getFirstChild().getNodeValue());
						dog.setLevel(level);
					}
				}
			}
			result.add(dog);
		}
		return result;
	}
}
