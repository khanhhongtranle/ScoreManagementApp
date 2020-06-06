package Model.Entities;

import java.io.Serializable;

public class StudentsInClass implements Serializable {
    private PrimaryKey_StudentsInClass key;

    public StudentsInClass(){
        key = new PrimaryKey_StudentsInClass();
    }

    public StudentsInClass(int s, String cn, String m){
        key = new PrimaryKey_StudentsInClass();
        this.key.setClassNo(s);
        this.key.setSubNo(cn);
        this.key.setMSSV(m);
    }

    public PrimaryKey_StudentsInClass getKey() {
        return key;
    }

    public void setKey(PrimaryKey_StudentsInClass key) {
        this.key = key;
    }

    public void setClassNo(int s){
        this.key.setClassNo(s);
    }

    public void setSubNo(String s){
        this.key.setSubNo(s);
    }

    public void setMSSV(String s){
        this.key.setMSSV(s);
    }

    public int getClassNo(){
        return this.key.getClassNo();
    }

    public String getSubNo(){
        return this.key.getSubNo();
    }

    public String getMSSV(){
        return this.key.getMSSV();
    }
}
