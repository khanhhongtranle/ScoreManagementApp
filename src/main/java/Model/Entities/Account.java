package Model.Entities;

import java.io.Serializable;


public class Account implements Serializable {
    protected String username;
    protected String password;
    protected Integer MSSV;

    public Account(){

    }

    public Account(String u, String p, Integer m){
        this.username = u;
        this.password = p;
        this.MSSV = m;
    }


    public Integer getMSSV() {
        return MSSV;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setMSSV(Integer MSSV) {
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
