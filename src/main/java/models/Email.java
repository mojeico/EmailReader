package models;

public class Email {

    public Imap imap;
    public Pop3 pop3;

    public Email(Imap imap, Pop3 smtp) {
        this.imap = imap;
        this.pop3 = smtp;
    }

    public Imap getImap() {
        return imap;
    }

    public void setImap(Imap imap) {
        this.imap = imap;
    }


    public Pop3 getPop3() {
        return pop3;
    }

    public void setPop3(Pop3 pop3) {
        this.pop3 = pop3;
    }

    @Override
    public String toString() {
        return "Email{" +
                "imap=" + imap +
                ", pop3=" + pop3 +
                '}';
    }
}
