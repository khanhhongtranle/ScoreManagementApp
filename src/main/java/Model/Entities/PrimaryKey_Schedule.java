package Model.Entities;

import java.io.Serializable;
import java.util.Objects;

public class PrimaryKey_Schedule implements Serializable {
    protected int classNo;
    protected String subNo;

    public PrimaryKey_Schedule(){

    }

    public int getClassNo() {
        return classNo;
    }

    public void setSubNo(String subNo) {
        this.subNo = subNo;
    }

    public String getSubNo() {
        return subNo;
    }

    public void setClassNo(int classNo) {
        this.classNo = classNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrimaryKey_Schedule that = (PrimaryKey_Schedule) o;
        return classNo == that.classNo &&
                Objects.equals(subNo, that.subNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(classNo, subNo);
    }
}
