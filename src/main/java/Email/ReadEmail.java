package Email;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.apache.commons.mail.util.MimeMessageParser;
import org.apache.poi.util.IOUtils;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import org.jsoup.Jsoup;

import javax.mail.*;
import javax.mail.internet.ContentType;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class ReadEmail {


    public List<VBox> checkMailInbox() {
        String host = "outlook.office365.com";
        String mailStoreType = "pop3";
        String user = "statistichelper@outlook.com";
        String password = "lolleman1";
        List<VBox> vBoxes = new ArrayList<>();
        try {
            Properties properties = new Properties();

            properties.put("mail.store.protocol", "pop3");
            properties.put("mail.pop3.host", host);
            properties.put("mail.pop3.port", 995);
            properties.put("mail.pop3.starttls.enable", "true");

            Session emailSession = Session.getDefaultInstance(properties);

            Store store = emailSession.getStore("pop3s");

            store.connect(host, user, password);

            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);


            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            Message[] messages = emailFolder.getMessages();

            StringBuilder sb = new StringBuilder();


            for (Message message : messages) {
                String subject = message.getSubject();
                String date = message.getSentDate().toString();
                String from = Arrays.toString(message.getFrom());
                String text = getTextFromMessage(message);

                String[] dateSplit = date.split(" ");
                sb.append(dateSplit[2]).append(" ").append(dateSplit[1]).append(" ");
                String[] timeSplit = dateSplit[3].split(":");
                sb.append(timeSplit[0]).append(":").append(timeSplit[1]);

                date = sb.toString();

                Label subjectLabel = new Label(subject);
                subjectLabel.setPrefSize(100, 20);
                subjectLabel.setId("inbox-label-subject");

                Label dateLabel = new Label(date);
                dateLabel.setPrefSize(100, 20);
                dateLabel.setAlignment(Pos.BOTTOM_RIGHT);
                dateLabel.setId("inbox-label-date");

                Label fromLabel = new Label(from);
                fromLabel.setPrefSize(200, 20);
                fromLabel.setId("inbox-label-from");

                Label messageLabel = new Label(text);
                messageLabel.setPrefSize(130, 20);
                messageLabel.setId("inbox-label-message");

                HBox subjectAndDateLine = new HBox();
                subjectAndDateLine.setPrefSize(200, 20);
                subjectAndDateLine.setId("subject-and-date-line");
                subjectAndDateLine.getChildren().addAll(subjectLabel, dateLabel);

                VBox messageBox = new VBox();
                messageBox.setPrefSize(200, 60);
                messageBox.setAlignment(Pos.TOP_LEFT);
                messageBox.setId("message-box");
                messageBox.getChildren().addAll(fromLabel, subjectAndDateLine, messageLabel);

                vBoxes.add(messageBox);
                sb.delete(0, 100);

                System.out.println("subject = " + subject);
                System.out.println("Date = " + date);
                System.out.println("From: " + from);
                System.out.println("Message:\n" + text);
                System.out.println(message.getContentType());
            }

            emailFolder.close(false);
            store.close();


        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        int size = vBoxes.size();
        List<VBox> reversedList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            VBox box = vBoxes.get(vBoxes.size() - 1);
            reversedList.add(box);
            vBoxes.remove(box);
        }
        return reversedList;
    }

    public boolean checkIfAnyUnread() {
        String host = "outlook.office365.com";
        String mailStoreType = "pop3";
        String user = "statistichelper@outlook.com";
        String password = "lolleman1";
        boolean unread = true;
        try {
            Properties properties = new Properties();

            properties.put("mail.pop3.host", host);
            properties.put("mail.pop3.port", 995);
            properties.put("mail.pop3.starttls.enable", "true");

            Session emailSession = Session.getDefaultInstance(properties);

            Store store = emailSession.getStore("pop3s");

            store.connect(host, user, password);

            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);

            if (emailFolder.getUnreadMessageCount() > 0) {
                unread = true;
            } else {
                unread = false;
            }

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return unread;
    }

    public String getTextFromMessage(Message message) {
        String result = "";
        try{
            if(message.isMimeType("text/plain")) {
                result = message.getContent().toString();
            } else if(message.isMimeType("multipart/*")) {
                MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
                result = getTextFromMimeMultiPart(mimeMultipart);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public String getTextFromMimeMultiPart(MimeMultipart mimeMultipart) {
        String result = "";
        try {

            int count = mimeMultipart.getCount();
            if (count == 0)
                throw new MessagingException("Multipart with no body parts not supported.");
            boolean multipartAlt = new ContentType(mimeMultipart.getContentType()).match("multipart/alternative");
            if (multipartAlt)
                // alternatives appear in an order of increasing
                // faithfulness to the original content. Customize as req'd.
                return getTextFromBodyPart(mimeMultipart.getBodyPart(count - 1));
            for (int i = 0; i < count; i++) {
                BodyPart bodyPart = mimeMultipart.getBodyPart(i);
                result += getTextFromBodyPart(bodyPart);
            }

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String getTextFromBodyPart(BodyPart bodyPart) {
        try {
            String result = "";
            if (bodyPart.isMimeType("text/plain")) {
                result = (String) bodyPart.getContent();
            } else if (bodyPart.isMimeType("text/html")) {
                String html = (String) bodyPart.getContent();
                result = org.jsoup.Jsoup.parse(html).text();
            } else if (bodyPart.getContent() instanceof MimeMultipart){
                result = getTextFromMimeMultiPart((MimeMultipart)bodyPart.getContent());
            }
            return result;
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
