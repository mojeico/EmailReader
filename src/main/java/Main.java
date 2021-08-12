import helper.JSONReadFromFile;
import mail.ReceiveMailImap;
import mail.ReceiveMailPop3;
import mail.SendMail;
import models.Email;

import javax.mail.MessagingException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws MessagingException {

        JSONReadFromFile jsonReadFromFile = new JSONReadFromFile("./jsonExample.json");
        List<Email> emailList = jsonReadFromFile.ParseJsonEmail();


        Set<String> setEmails = new HashSet<>();

        for (Email email : emailList) {

            if(email.getPop3() != null){
                System.out.println("Start getting pop3 emails address for " + email.getPop3().getEmail());
                ReceiveMailPop3 pop3 = new ReceiveMailPop3(email.getPop3());
                pop3.getMessage(setEmails);
                System.out.println("\nFinish getting pop3 emails address for " + email.getPop3().getEmail());
            }

            if (email.getImap() != null) {
                System.out.println("Start getting imap emails address for " + email.getImap().getEmail());
                ReceiveMailImap imap = new ReceiveMailImap(email.getImap());
                imap.getMessage(setEmails);
                System.out.println("\nFinish getting imap emails address for " + email.getImap().getEmail());
            }

            setEmails.remove(email.getSmtp().getEmail());

            SendMail sendMail = new SendMail(email.getSmtp());
            System.out.println("Start getting sending emails by " + email.getSmtp().getEmail());
            sendMail.SendMailTo(setEmails);
            System.out.println("\nFinish sending emails by " + email.getSmtp().getEmail());

            setEmails.clear();
        }
    }
}
