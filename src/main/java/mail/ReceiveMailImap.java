package mail;

import helper.Helper;

import java.util.*;
import javax.mail.*;

public class ReceiveMailImap {

    public ReceiveMailImap() {
    }

    public void getMessage(Set<String> setEmails) throws MessagingException {
        Folder folder = null;
        Store store = null;
        try {
            Properties props = System.getProperties();
            props.setProperty("mail.store.protocol", "imaps");

            Session session = Session.getDefaultInstance(props, null);
            //session.setDebug(true);
            store = session.getStore("imaps");
            store.connect("imap.gmail.com", "", "");

            folder = store.getFolder("[Gmail]/All Mail");
            /* Others GMail folders :
             * [Gmail]/All Mail   This folder contains all of your Gmail messages.
             * [Gmail]/Drafts     Your drafts.
             * [Gmail]/Sent Mail  Messages you sent to other people.
             * [Gmail]/Spam       Messages marked as spam.
             * [Gmail]/Starred    Starred messages.
             * [Gmail]/Trash      Messages deleted from Gmail.
             */

            folder.open(Folder.READ_WRITE);
            Message[] messagesImap = folder.getMessages();
            Helper.GetAllEmails(setEmails, messagesImap);
            System.out.println("All imap email address was save in set");


        } finally {
            if (folder != null) {
                folder.close(true);
            }
            if (store != null) {
                store.close();
            }
        }
    }


}