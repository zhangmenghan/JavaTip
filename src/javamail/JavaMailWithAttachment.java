package javamail;

import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class JavaMailWithAttachment {
	public static void main(String[] args) throws Exception {
		Properties prop = new Properties();
		prop.setProperty("mail.transport.protocol", "smtp");
		prop.setProperty("mail.smtp.host", "smtp.qq.com");
		prop.setProperty("mail.smtp.port", "465");
		prop.setProperty("mail.smtp.auth", "true");
		prop.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		prop.setProperty("mail.smtp.socketFactory.fallback", "false");
		prop.setProperty("mail.smtp.socketFactory.port", "465");
		Session session = Session.getInstance(prop);
		session.setDebug(true);
		
		MimeMessage message = createMimeMessage(session,"2423306508@qq.com","2423306508@qq.com");
		Transport transport = session.getTransport();
		transport.connect("2423306508@qq.com","wdneflvkwjyfebgb");
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
	}
	
	public static MimeMessage createMimeMessage(Session session,String sender,String receiver) throws Exception {
		MimeMessage message = new MimeMessage(session);
		Address address = new InternetAddress(sender,"发件人名字","UTF-8");
		message.setFrom(address);
		message.setSubject("标题","UTF-8");
		
		//创建图片节点
		MimeBodyPart imagePart = new MimeBodyPart();
		DataHandler imageDataHandler = new DataHandler(new FileDataSource("D:\\CSDN.png"));
		imagePart.setDataHandler(imageDataHandler);
		imagePart.setContentID("MyCSDNImage");
		
		//创建文本节点,为了加载图片节点
		MimeBodyPart textBody = new MimeBodyPart();
		textBody.setContent("正文内容image:<image src='cid:MyCSDNImage' />","text/html;charset=utf-8");
		
		//将文本节点,图片节点组装成复合节点
		MimeMultipart mm_text_img = new MimeMultipart();
		mm_text_img.addBodyPart(imagePart);
		mm_text_img.addBodyPart(textBody);
		
		//设置两个节点的关系
		mm_text_img.setSubType("related");
		
		//正文中只能出现普通节点,不能出现复合节点
		MimeBodyPart text_image_bodyPart = new MimeBodyPart();
		text_image_bodyPart.setContent(mm_text_img);
		
		//附件
		MimeBodyPart attachment = new MimeBodyPart();
		DataHandler attachDataHandler = new DataHandler(new FileDataSource("D:\\Github.png"));
		attachment.setDataHandler(attachDataHandler);
		attachment.setFileName(MimeUtility.encodeText(attachDataHandler.getName()));
		attachment.setDataHandler(attachDataHandler);
		
		MimeMultipart mm = new MimeMultipart();
		mm.addBodyPart(text_image_bodyPart);
		mm.addBodyPart(attachment);
		mm.setSubType("mixed");
		
		message.setContent(mm, "text/html;charset=utf-8");
		
		//普通收件人TO  抄送CC 密送BCC
		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiver,"收件人","UTF-8"));
		message.setSentDate(new Date());
		message.saveChanges();
		return message;
	}
}
