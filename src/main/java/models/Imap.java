package models;

public class Imap {

    private String email;
    private String pass;
    private String host;
    private Integer port;
    private Boolean ssl;

    public Imap(String email, String pass, String server, Integer port, Boolean ssl) {
        this.email = email;
        this.pass = pass;
        this.host = server;
        this.port = port;
        this.ssl = ssl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Boolean getSsl() {
        return ssl;
    }

    public void setSsl(Boolean ssl) {
        this.ssl = ssl;
    }

    @Override
    public String toString() {
        return "Imap{" +
                "email='" + email + '\'' +
                ", pass='" + pass + '\'' +
                ", host='" + host + '\'' +
                ", port=" + port +
                ", ssl=" + ssl +
                '}';
    }
}
