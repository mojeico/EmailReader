package models;

public class Smtp {

    public String email;
    public String pass;
    public String server;
    public Integer port;
    public Boolean ssl;


    public Smtp(String email, String pass, String server, Integer port, Boolean ssl) {
        this.email = email;
        this.pass = pass;
        this.server = server;
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

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
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
        return "Smtp{" +
                "email='" + email + '\'' +
                ", pass='" + pass + '\'' +
                ", server='" + server + '\'' +
                ", port=" + port +
                ", ssl=" + ssl +
                '}';
    }
}


