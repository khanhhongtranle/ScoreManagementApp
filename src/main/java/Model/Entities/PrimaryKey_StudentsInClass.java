package Model.Entities;

import java.io.Serializable;
import java.util.Objects;

public class PrimaryKey_StudentsInClass implements Serializable {
    private int classNo;
    private String subNo;
    private String MSSV;

    public PrimaryKey_StudentsInClass(){

    }

    public void setClassNo(int classNo) {
        this.classNo = classNo;
    }

    public String getSubNo() {
        return subNo;
    }

    public void setSubNo(String subNo) {
        this.subNo = subNo;
    }

    public int getClassNo() {
        return classNo;
    }

    public String getMSSV() {
        return MSSV;
    }

    public void setMSSV(String MSSV) {
        this.MSSV = MSSV;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrimaryKey_StudentsInClass that = (PrimaryKey_StudentsInClass) o;
        return classNo == that.classNo &&
                Objects.equals(subNo, that.subNo) &&
                Objects.equals(MSSV, that.MSSV);
    }

    @Override
    public int hashCode() {
        return Objects.hash(classNo, subNo, MSSV);
    }
}
