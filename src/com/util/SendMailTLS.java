package com.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
 
public class SendMailTLS {
	private static Logger log = Logger.getLogger(SendMailTLS.class.getName());

	public static void main(String[] args) throws Exception {
 
		PropFileUtil.load();
		final String username = PropFileUtil.getValue("MAIL_USERNAME");
		final String password = PropFileUtil.getValue("MAIL_PASSWORD");
 
		Properties props = new Properties();
		props.put("mail.smtp.auth", PropFileUtil.getValue("MAIL_SMTP_AUTH"));
		props.put("mail.smtp.starttls.enable", PropFileUtil.getValue("MAIL_SMTP_STARTTLS_ENABLE"));
		props.put("mail.smtp.host", PropFileUtil.getValue("MAIL_HOST"));
		props.put("mail.smtp.port", PropFileUtil.getValue("MAIL_PORT"));
 
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
 
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(PropFileUtil.getValue("MAIL_USERNAME")));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse("simplysou@gmail.com"));
			message.setSubject("Testing Subject");
			message.setText("Dear Mail Crawler,"
				+ "\n\n No spam to my email, please!");
 
			Transport.send(message);
 
			System.out.println("Done");
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static boolean SendEmail(String sendFrom, String sendTo, String subject, String body){
		final String username = PropFileUtil.getValue("MAIL_USERNAME");
		final String password = PropFileUtil.getValue("MAIL_PASSWORD");
		boolean emailSent = false;
		Properties props = new Properties();
		props.put("mail.smtp.auth", PropFileUtil.getValue("MAIL_SMTP_AUTH"));
		props.put("mail.smtp.starttls.enable", PropFileUtil.getValue("MAIL_SMTP_STARTTLS_ENABLE"));
		props.put("mail.smtp.host", PropFileUtil.getValue("MAIL_HOST"));
		props.put("mail.smtp.port", PropFileUtil.getValue("MAIL_PORT"));
 
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
 
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(sendFrom));
			message.setRecipients(Message.RecipientType.TO,	InternetAddress.parse(sendTo));
			message.setSubject(subject);
			message.setText(body);
			Transport.send(message);
 
			System.out.println("Done");
			emailSent = true;
 
		} catch (MessagingException e) {
			e.printStackTrace();
			log.error("Error occured while sending email to the user, error msg: "+e.getMessage());
			throw new RuntimeException(e);
			
		}
		return emailSent;
	}
}