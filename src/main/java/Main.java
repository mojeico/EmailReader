import helper.JSONReadFromFile;
import mail.ReceiveMailImap;
import mail.ReceiveMailPop3;
import models.Email;

import javax.mail.MessagingException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws MessagingException {

        JSONReadFromFile jsonReadFromFile = new JSONReadFromFile("./jsonExample.json");
        List<Email> emailList = jsonReadFromFile.ParseJsonEmail();

        System.out.println(emailList);

        Set<String> setEmails = new HashSet<>();

        for (Email email : emailList) {


            ReceiveMailPop3 pop3 = new ReceiveMailPop3(email.getPop3());
            pop3.getMessage(setEmails);
            System.out.println("Get pop3 emails for " + email.getPop3().getEmail());

            ReceiveMailImap imap = new ReceiveMailImap(email.getImap());
            imap.getMessage(setEmails);
            System.out.println("Get imap emails for " + email.getImap().getEmail());

        }

        System.out.println(setEmails.size());
        System.out.println(setEmails);


    }
}
