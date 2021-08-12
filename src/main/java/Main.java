
import helper.JSONReadFromFile;
import mail.ReceiveMailImap;
import mail.ReceiveMailPop3;
import models.Email;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.mail.*;

public class Main {

    public static void main(String[] args) throws MessagingException {

        JSONReadFromFile jsonReadFromFile = new JSONReadFromFile("./jsonExample.json");
        List<Email> emailList = jsonReadFromFile.ParseJsonEmail();

        System.out.println(emailList);


        Set<String> setEmails = new HashSet<>();

        ReceiveMailPop3 pop3 = new ReceiveMailPop3();
        pop3.getMessage(setEmails);

        ReceiveMailImap imap = new ReceiveMailImap();
        imap.getMessage(setEmails);

        System.out.println(setEmails);

    }
}
