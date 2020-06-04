package Model.Entities;

import java.io.Serializable;

public class Subject implements Serializable {
    protected String subNo;
    protected String subName;

    public Subject(){

    }

    public Subject(String no, String name){
        this.subNo = no;
        this.subName = name;
    }

    public String getSubName() {
        return subName;
    }

    public String getSubNo() {
        return subNo;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public void setSubNo(String subNo) {
        this.subNo = subNo;
    }
}
