package Model.Entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class PrimaryKey_Re_Examination implements Serializable {
    protected int id;
    protected String beginDate;
    protected String endingDate;

    public PrimaryKey_Re_Examination(){

    }

    public PrimaryKey_Re_Examination(String d1, String d2){
        beginDate = d1;
        endingDate = d2;
    }

    public int getId() {
        return id;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public String getEndingDate() {
        return endingDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public void setEndingDate(String endingDate) {
        this.endingDate = endingDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrimaryKey_Re_Examination that = (PrimaryKey_Re_Examination) o;
        return id == that.id &&
                Objects.equals(beginDate, that.beginDate) &&
                Objects.equals(endingDate, that.endingDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, beginDate, endingDate);
    }
}
