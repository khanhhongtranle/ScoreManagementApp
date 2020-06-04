package Model.Entities;

import javax.persistence.*;
import java.io.Serializable;

//@Entity
//@Table (name = "thoikhoabieu",uniqueConstraints = { @UniqueConstraint(columnNames = { "STTLop","MaMonHoc" }) })
public class Schedule implements Serializable {

    //@IdClass(value = Schedule.primaryKey.class)
    /*protected class primaryKey{
       // @Id
        //@Column (name = "STTLop")
        protected int classNo;

        //@Id
        //@Column (name = "MaMonHoc")
        protected String subNo;
    }*/

    protected String room;

    //@Column (name = "PhongHoc")
    protected primaryKey_Schedule key;

    public primaryKey_Schedule getKey() {
        return key;
    }

    public void setKey(primaryKey_Schedule key) {
        this.key = key;
    }

    public Schedule(){
        key = new primaryKey_Schedule();
    }

    public Schedule(int cno, String sno, String r){
        this.key.classNo = cno;
        this.key.subNo = sno;
        this.room = r;
    }

    public int getClassNo() {
        return key.classNo;
    }

    public void setSubNo(String subNo) {
        this.key.subNo = subNo;
    }

    public String getSubNo() {
        return key.subNo;
    }

    public void setClassNo(int classNo) {
        this.key.classNo = classNo;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}
