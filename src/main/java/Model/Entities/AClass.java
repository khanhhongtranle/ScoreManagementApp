package Model.Entities;

import javafx.scene.media.AudioClip;

import java.io.Serializable;

public class AClass implements Serializable{
    protected int stt;
    protected String className;

    public AClass(){

    }

    public AClass(int stt, String cn){
        this.stt= stt;
        this.className = cn;
    }

    public AClass(String className){
        this.className = className;
    }

    public int getStt() {
        return stt;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }
}
