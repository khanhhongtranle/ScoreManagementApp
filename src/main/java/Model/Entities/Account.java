package Model.Entities;

import java.io.Serializable;


public class Account implements Serializable {
    protected String username;
    protected String password;
    protected String MSSV;

    public Account(){

    }

    public Account(String u, String p, String m){
        this.username = u;
        this.password = p;
        this.MSSV = m;
    }


    public String getMSSV() {
        return MSSV;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setMSSV(String MSSV) {
        this.MSSV = MSSV;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isTeacher(){
        if (getMSSV()==null){
            return true;
        }
        return false;
    }
}
