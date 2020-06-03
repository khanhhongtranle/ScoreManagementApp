package Model.Entities;

import java.io.Serializable;

public class Student implements Serializable {
    protected String MSSV;
    protected String studentName;
    protected String studentSex;
    protected String studentNationID;
    protected int classNo;

    public Student(){

    }

    public Student(String m, String n, String s, String id, int sl){
        this.MSSV = m;
        this.studentName = n;
        this.studentSex = s;
        this.studentNationID = id;
        this.classNo = sl;
    }

    public int getClassNo() {
        return classNo;
    }

    public void setMSSV(String MSSV) {
        this.MSSV = MSSV;
    }

    public String getMSSV() {
        return MSSV;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getStudentNationID() {
        return studentNationID;
    }

    public String getStudentSex() {
        return studentSex;
    }

    public void setClassNo(int classNo) {
        this.classNo = classNo;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setStudentNationID(String studentNationID) {
        this.studentNationID = studentNationID;
    }

    public void setStudentSex(String studentSex) {
        this.studentSex = studentSex;
    }
}
