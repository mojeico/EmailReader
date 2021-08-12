package models;

public class Email {

    public Imap imap;
    public Smtp smtp;

    public Email(Imap imap, Smtp smtp) {
        this.imap = imap;
        this.smtp = smtp;
    }

    public Imap getImap() {
        return imap;
    }

    public void setImap(Imap imap) {
        this.imap = imap;
    }

    public Smtp getSmtp() {
        return smtp;
    }

    public void setSmtp(Smtp smtp) {
        this.smtp = smtp;
    }

    @Override
    public String toString() {
        return "Email{" +
                "imap=" + imap +
                ", smtp=" + smtp +
                '}';
    }
}
