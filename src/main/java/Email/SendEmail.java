package Email;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
    String mailTo;
    String mailFrom;
    String password;
    String mailHost;
    Properties properties = System.getProperties();

    public SendEmail() {
        this.mailTo = "davidengman@hotmail.com";
        this.mailFrom = "StatisticHelper@outlook.com";
        this.password = "lolleman1";
        this.mailHost = "smtp.office365.com";
        serverSetup();
    }
    public void serverSetup() {
        properties.put("mail.smtp.host", mailHost);
        properties.put("mail.smtp.port", 587);
        //properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
    }

    public void sendEmail(String message, String subject) {
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(getMailFrom(), getPassword());
            }
        });
        try {
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(getMailFrom());
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(getMailTo()));
            mimeMessage.setSubject(subject);
            mimeMessage.setText(message);

            Transport.send(mimeMessage);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public String getMailTo() {
        return mailTo;
    }
    public void setMailTo(String mailTo) {
        this.mailTo = mailTo;
    }

    public String getMailFrom() {
        return mailFrom;
    }
    public void setMailFrom(String mailFrom) {
        this.mailFrom = mailFrom;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
