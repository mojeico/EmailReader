package mail;

import helper.Helper;
import models.Pop3;

import javax.mail.*;
import java.util.Properties;
import java.util.Set;

public class ReceiveMailPop3 {

    Properties properties = null;
    private Session session = null;
    private Store store = null;
    private Folder inbox = null;

    public Pop3 pop3;

    public ReceiveMailPop3(Pop3 pop3) {
        this.pop3 = pop3;
    }

    public void getMessage(Set<String> setEmails) {

        properties = new Properties();

        properties.setProperty("mail.pop3.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");

        properties.setProperty("mail.pop3.socketFactory.fallback", "false");
        properties.setProperty("mail.pop3.host", pop3.getHost());
        properties.setProperty("mail.pop3.port", String.valueOf(pop3.getPort()));
        properties.setProperty("mail.pop3.socketFactory.port", String.valueOf(pop3.getPort()));

        session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(pop3.getEmail(), pop3.getPass());
                    }
                });

        try {
            store = session.getStore("pop3");
            store.connect();
            inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            Message messagesPop3[] = inbox.getMessages();
            Helper.GetAllEmails(setEmails, messagesPop3);
            System.out.println("All pop3 email address was save in set");

        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

}