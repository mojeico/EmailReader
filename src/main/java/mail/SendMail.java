package mail;

import helper.ProgressLine;
import models.Smtp;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

public class SendMail {

    private final Smtp smtp;

    public SendMail(Smtp smtp) {
        this.smtp = smtp;
    }

    public void SendMailTo(Set<String> setEmails, String emailBody, String emailSubject, String[] fileAddress) throws MessagingException {


        // Sender's email ID needs to be mentioned
        String from = smtp.getEmail();

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", smtp.getHost());
        properties.put("mail.smtp.port", smtp.getPort());
        properties.put("mail.smtp.ssl.enable", smtp.getSsl());
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(smtp.getEmail(), smtp.getPass());
            }
        });

        // Used to debug SMTP issues
        //session.setDebug(true);

        // Create a default MimeMessage object.
        MimeMessage message = new MimeMessage(session);

        // Set From: header field of the header.
        message.setFrom(new InternetAddress(from));
        // Set Subject: header field
        message.setSubject(emailSubject);
        // Now set the actual message
        message.setText(emailBody);

        if (fileAddress[0] != "") {
            message.setFileName(fileAddress[0]);
        }

        long startTime = System.currentTimeMillis();
        int i = 0;
        int size = setEmails.size();

        for (Iterator<String> it = setEmails.iterator(); it.hasNext(); ) {
            String receiver = it.next();

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
            Transport.send(message);

                i++;
                ProgressLine.PrintProgress(startTime, size, i);
            }


    }

}
