import helper.JSONReadFromFile;
import mail.ReceiveMailImap;
import mail.ReceiveMailPop3;
import mail.SendMail;
import models.Email;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Controller {

    public static String RunMainLogic(String emailBody, String emailSubject, String[] fileAddress) {

        JSONReadFromFile jsonReadFromFile = new JSONReadFromFile("./jsonExample.json");
        List<Email> emailList = jsonReadFromFile.ParseJsonEmail();


        Set<String> setEmails = new HashSet<>();

        for (Email email : emailList) {

            if (email.getPop3() != null) {
                System.out.println("Start getting pop3 emails address for " + email.getPop3().getEmail());
                ReceiveMailPop3 pop3 = new ReceiveMailPop3(email.getPop3());
                try {
                    pop3.getMessage(setEmails);
                } catch (NoSuchProviderException e) {
                    System.out.println(e.getMessage());
                    return "(NoSuchProviderException) - Error in ReceiveMailPop3 with getting emails from " + email.getPop3().getEmail();
                } catch (MessagingException e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                    return "(MessagingException) - Error in ReceiveMailPop3 with getting emails from " + email.getPop3().getEmail();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    return "(Exception) - Error in ReceiveMailPop3 with getting emails from " + email.getPop3().getEmail();
                }
                System.out.println("\nFinish getting pop3 emails address for " + email.getPop3().getEmail());
            }


            if (email.getImap() != null) {
                System.out.println("Start getting imap emails address for " + email.getImap().getEmail());
                ReceiveMailImap imap = new ReceiveMailImap(email.getImap());
                try {
                    imap.getMessage(setEmails);
                } catch (NoSuchProviderException e) {
                    System.out.println(e.getMessage());
                    return "(NoSuchProviderException) - Error in ReceiveMailImap with getting emails from " + email.getImap().getEmail();
                } catch (MessagingException e) {
                    System.out.println(e.getMessage());
                    return "(MessagingException) - Error in ReceiveMailImap with getting emails from " + email.getImap().getEmail();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    return "(Exception) - Error in ReceiveMailImap with getting emails from " + email.getImap().getEmail();
                }
                System.out.println("\nFinish getting imap emails address for " + email.getImap().getEmail());
            }


            setEmails.remove(email.getSmtp().getEmail());
            SendMail sendMail = new SendMail(email.getSmtp());
            System.out.println("Start getting sending emails by " + email.getSmtp().getEmail());


            try {
                sendMail.SendMailTo(setEmails, emailBody, emailSubject, fileAddress);
            } catch (MessagingException e) {
                System.out.println(e.getMessage());
                return "(Exception) - Error in SendMailTo with sending emails from " + email.getSmtp().getEmail();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return "(Exception) - Error in SendMailTo with sending emails from " + email.getSmtp().getEmail();
            }

            System.out.println("\nFinish sending emails by " + email.getSmtp().getEmail());

            setEmails.clear();
        }

        return "Mails was sent";
    }
}
