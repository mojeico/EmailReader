package mail;

import helper.Helper;

import java.util.Properties;
import java.util.Set;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;

public class ReceiveMailPop3 {

    Properties properties = null;
    private Session session = null;
    private Store store = null;
    private Folder inbox = null;
    private String userName = "";// provide user name
    private String password = "";// provide password

    public ReceiveMailPop3() {
    }

    public void getMessage(Set<String> setEmails) {
        properties = new Properties();
        properties.setProperty("mail.pop3.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.pop3.socketFactory.fallback", "false");
        properties.setProperty("mail.pop3.host", "pop.gmail.com");
        properties.setProperty("mail.pop3.port", "995");
        properties.setProperty("mail.pop3.socketFactory.port", "995");
        session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(userName, password);
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
            e.printStackTrace();
        }
    }

}