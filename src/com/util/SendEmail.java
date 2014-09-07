package com.util;

import java.io.File;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

/**
 *
 * @author Saurabh Pandit
 */
public class SendEmail {

	private static Logger log = Logger.getLogger(SendEmail.class.getName());

	//private static String smtpAuthUser = "";
	//private static String smtpAuthPass = "";
	private static String smtpHostName = "";
	//private static String smtpPort = "";
	//private static String smtpAuthRequired = "true";
	//private static String enableSMTPStartTLS = "false";
	private String toAddresses = null;
	private String subject = "";
	private String emailHTMLBody = "";
	private String emailTextBody = "";
	//private File[] attachment = new File[10];
	private String[] attachmentName = new String[10];
	private String cc = null;
	private String bcc = null;
	private String from  = null;
	private String protocol = null;
	private boolean appendDisclaimer = false;
	
	// Class Constants
	public static final String TEXT = "text/plain; charset=UTF-8";
	public static final String HTML = "text/html; charset=UTF-8";
	
	private String format = TEXT;

	public SendEmail(String subject, String emailHTMLBody, String emailTextBody) {
		this.subject = subject;
		this.emailHTMLBody = emailHTMLBody;
		this.emailTextBody = emailTextBody;
	}
	
	public void run() {
		log.debug("Entering method run() in class SendEmail.");
		try {
			smtpHostName = PropFileUtil.getValue("MAIL_HOST");
			//bcc = PropFileUtil.getValue("MAIL_BCC");
			//cc = PropFileUtil.getValue("MAIL_CC");
			from = PropFileUtil.getValue("MAIL_FROM");
			toAddresses = PropFileUtil.getValue("MAIL_TO");
			protocol = PropFileUtil.getValue("MAIL_TRANSPORT_PROTOCOL");
			
			Properties props = new Properties();
			Session session = null;
			props.put("mail.transport.protocol", protocol);
			props.put("mail.smtp.sendpartial", smtpHostName);
			props.put("mail.smtp.host", smtpHostName);
			session = Session.getDefaultInstance(props);
			
			props.put("mail.debug", "true");
			session.setDebug(true);

			// create a message
			MimeMessage msg = new MimeMessage(session);

			InternetAddress addressFrom = new InternetAddress(this.from);
			msg.setFrom(addressFrom);
			if (toAddresses != null && !"".equals(toAddresses)) {
				InternetAddress[] addressTo = InternetAddress.parse(toAddresses);
				msg.setRecipients(Message.RecipientType.TO, addressTo);
				if (bcc != null && !"".equals(bcc)) {
					InternetAddress[] addressBcc = InternetAddress.parse(bcc);
					msg.setRecipients(Message.RecipientType.BCC, addressBcc);
				}
				if (cc != null && !"".equals(cc)) {
					InternetAddress[] addressCc = InternetAddress.parse(cc);
					msg.setRecipients(Message.RecipientType.CC, addressCc);
				}
				// Setting the Subject and Content Type
				msg.setHeader("Content-Type", format);
				msg.setSubject(subject, "UTF-8");

				// create the mail root multipart
				MimeMultipart mpRoot = new MimeMultipart("related");

				// Create the content multipart (for text and HTML)
				MimeMultipart mpContent = new MimeMultipart("alternative");

				// Create a body part to house the multipart/alternative Part
				MimeBodyPart contentPartRoot = new MimeBodyPart();
				contentPartRoot.setContent(mpContent);

				// Add the root body part to the root multipart
				mpRoot.addBodyPart(contentPartRoot);

				// Plain text message
				//emailTextBody = appendDisclaimer(emailTextBody,false);
				BodyPart plainMessageBodyPart = new MimeBodyPart();
				plainMessageBodyPart.setContent(emailTextBody, format);
				mpContent.addBodyPart(plainMessageBodyPart);

				// html message
				//emailHTMLBody = appendDisclaimer(emailHTMLBody,true);
				BodyPart htmlMessageBodyPart = new MimeBodyPart();
				htmlMessageBodyPart.setContent(emailHTMLBody, format);
				mpContent.addBodyPart(htmlMessageBodyPart);

				// attachment
				/*int i = 0;
				if (attachmentName != null) {
					for (String attachName : attachmentName) {
						if (attachName != null && !"".equals(attachName)) {

							BodyPart messageBodyPart = new MimeBodyPart();
							DataSource source = new FileDataSource(attachment[i]);
							messageBodyPart.setDisposition(Part.ATTACHMENT);
							messageBodyPart.setDataHandler(new DataHandler(source));
							messageBodyPart.setFileName(attachName);
							mpRoot.addBodyPart(messageBodyPart);
						}
						i++;
					}
				}*/
				msg.setContent(mpRoot);
				log.debug("About to send Email with following details.");
				log.debug("To			 - "+this.toAddresses);
				log.debug("Cc			 - "+this.cc);
				log.debug("Bcc			 - "+this.bcc);
				log.debug("From			 - "+this.from);
				log.debug("Subject		 - "+this.subject);
				log.debug("EmailTextBody - "+this.emailTextBody);
				log.debug("EmailHTMLBody - "+this.emailHTMLBody);
				log.debug("AttachmentName- "+this.attachmentName);
				Transport.send(msg);
				log.debug("Mail sent.");
			}	
		} catch (Exception e) {
				log.debug("Exception occured while sending email"+ e.getLocalizedMessage());
				e.printStackTrace();
		}
		log.debug("Exiting method run() in class SendEmail.");
	}

	private static class SMTPAuthenticator extends javax.mail.Authenticator {
		private String smtpAuthUser = "";
		private String smtpAuthPass = "";
		SMTPAuthenticator(String user,String pass){
			this.smtpAuthPass = pass;
			this.smtpAuthUser = user;
		}
		@Override
		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(smtpAuthUser, smtpAuthPass);
		}
	}

	private String stripHTML(String emailHTMLBody) {
		String emailTextBody = emailHTMLBody.replaceAll("<br>", "\n");
		emailTextBody = emailTextBody.replaceAll("\\<.*?>", "");
		emailTextBody = emailTextBody.replaceAll("&nbsp;", " ");
		return emailTextBody;
	}

	private String appendDisclaimer(String emailBody, boolean html) {

		if ("true".equals(PropFileUtil.getValue("MAIL_DISCLAIMER_ENABLED").trim()) && this.appendDisclaimer) {
			String disclaimer = PropFileUtil.getValue("MAIL_DISCLAIMER_TEXT");
			if (!html) {
				disclaimer = stripHTML(disclaimer);
			}
			emailBody += disclaimer;
		}

		return emailBody;
	}
	
	public static void main(String[] args) throws Exception{
		PropFileUtil.load();
		//Send out test email!
		String emailBody = "The following error occured while running the daily synch job: "+ "\n\nDon't be alaramed! This is only a test!";
		SendEmail sendEmailTest = new SendEmail("AECOM Salesforce Auto-Deactivation process Test Email", emailBody, emailBody);
		sendEmailTest.run();
	}
}