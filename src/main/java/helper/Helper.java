package helper;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import java.util.Set;

public class Helper {

    public static void GetAllEmails(Set<String> setEmails, Message[] messagesImap) {
        for (int i = 0; i < messagesImap.length; i++) {

            Message message = messagesImap[i];
            Address[] to = new Address[0];
            Address[] from = new Address[0];

            try {
                to = message.getAllRecipients();
                from = message.getFrom();
            } catch (MessagingException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (to != null) {
                Address addressTo = to[0];
                String receiver = ((InternetAddress) addressTo).getAddress();
                setEmails.add(receiver);
            }

            if (from != null) {
                Address addressFrom = from[0];
                String sender = ((InternetAddress) addressFrom).getAddress();
                setEmails.add(sender);
            }

        }
    }

}
