package Model.Entities;

import java.io.Serializable;
import java.util.Objects;

public class PrimaryKey_RequestsForRe implements Serializable {
    protected int id_re;
    protected String mssv;
    protected String subNo;

    public PrimaryKey_RequestsForRe(){

    }

    public void setSubNo(String subNo) {
        this.subNo = subNo;
    }

    public String getSubNo() {
        return subNo;
    }

    public int getId_re() {
        return id_re;
    }

    public void setId_re(int id_re) {
        this.id_re = id_re;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }

    public String getMssv() {
        return mssv;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrimaryKey_RequestsForRe that = (PrimaryKey_RequestsForRe) o;
        return id_re == that.id_re &&
                Objects.equals(mssv, that.mssv) &&
                Objects.equals(subNo, that.subNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_re, mssv, subNo);
    }
}
