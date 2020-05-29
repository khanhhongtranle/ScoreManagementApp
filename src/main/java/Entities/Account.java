package Entities;

public class Account {
    protected String username;
    protected String password;
    protected int MSSV;

    public Account(){

    }

    public Account(String u, String p, int m){
        this.username = u;
        this.password = p;
        this.MSSV = m;
    }

    public int getMSSV() {
        return MSSV;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setMSSV(int MSSV) {
        this.MSSV = MSSV;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
