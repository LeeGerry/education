package edu.auburn.utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtils {
	static Properties mailServerProperties;
	static Session getMailSession;
	static MimeMessage generateMailMessage;
	static String GMAIL_SERVER = "aptsystem.au@gmail.com", GMAIL_PASSWORD = "shelby2303";
	public static void generateAndSendEmail(String email, String code) throws AddressException, MessagingException {
		// Step1
		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.port", "587");
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");
		// Step2
		getMailSession = Session.getDefaultInstance(mailServerProperties, null);
		generateMailMessage = new MimeMessage(getMailSession);
		generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
		generateMailMessage.setSubject("Use code to reset your password");
		String emailBody = "This is from administrator of APT system. Your received this email is because someone try to reset your password. Please use the code <b>" + code + "</b> to reset your password. <br> Best regards, <br><br><br> Administrator of APT";
		generateMailMessage.setContent(emailBody, "text/html");
		// Step3
		Transport transport = getMailSession.getTransport("smtp");
		// Enter your correct gmail UserID and Password
		// if you have 2FA enabled then provide App Specific Password
		transport.connect("smtp.gmail.com", GMAIL_SERVER, GMAIL_PASSWORD);
		transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
		transport.close();
	}
	public static void main(String[] args) {
		try {
			generateAndSendEmail("gzl0023@auburn.edu","3333");
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
