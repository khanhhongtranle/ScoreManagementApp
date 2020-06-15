package Model.Entities;

import java.io.Serializable;

public class Re_Examination implements Serializable {
    protected PrimaryKey_Re_Examination key;

    public Re_Examination(){
        key = new PrimaryKey_Re_Examination();
    }

    public void setKey(PrimaryKey_Re_Examination key) {
        this.key = key;
    }

    public PrimaryKey_Re_Examination getKey() {
        return key;
    }
}
