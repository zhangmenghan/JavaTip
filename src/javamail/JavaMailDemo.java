package javamail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMailDemo {
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
		message.setContent("正文内容", "text/html;charset=utf-8");
		//普通收件人TO  抄送CC 密送BCC
		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiver,"收件人","UTF-8"));
		message.setSentDate(new Date());
		message.saveChanges();
		return message;
	}
}
