package mx.uin.mail.sender.service;

import mx.uin.mail.sender.model.MessageData;
import mx.uin.mail.sender.service.reader.DataReader;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class EmailSenderService implements SenderService {

  private String username;
  private String password;
  private final Properties prop;
  private DataReader dataReader;

  public EmailSenderService(String host, int port, String username, String password){
    prop = new Properties();
    prop.put("mail.smtp.auth", true);
    prop.put("mail.smtp.starttls.enable", "true");
    prop.put("mail.smtp.host", host);
    prop.put("mail.smtp.port", port);
    prop.put("mail.smtp.ssl.trust", host);

    this.username = username;
    this.password = password;

  }

  @Override
  public int send(MessageData messageData) {
    try {
      Session session = Session.getInstance(prop, new Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
          return new PasswordAuthentication(username, password);
        }
      });

      Message message = new MimeMessage(session);
      message.setFrom(new InternetAddress(messageData.getFrom()));
      message.setRecipients(
        Message.RecipientType.TO, InternetAddress.parse(messageData.getTo()));
      message.setSubject(messageData.getSubject());

      String msg = messageData.getBody();

      MimeBodyPart mimeBodyPart = new MimeBodyPart();
      mimeBodyPart.setContent(msg, "text/html; charset=utf-8");

      Multipart multipart = new MimeMultipart();
      multipart.addBodyPart(mimeBodyPart);

      message.setContent(multipart);

      Transport.send(message);

    } catch (MessagingException e) {
      e.printStackTrace();
    }
    return 0;
  }

}
