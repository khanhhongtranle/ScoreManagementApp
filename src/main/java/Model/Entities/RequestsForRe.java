package Model.Entities;

import java.io.Serializable;

public class RequestsForRe implements Serializable {
    protected PrimaryKey_RequestsForRe key;
    protected String scoreCol;
    protected float newScore;
    protected String theReason;
    protected String status;

    public RequestsForRe(){
        key = new PrimaryKey_RequestsForRe();
    }

    public void setKey(PrimaryKey_RequestsForRe key) {
        this.key = key;
    }

    public float getNewScore() {
        return newScore;
    }

    public String getScoreCol() {
        return scoreCol;
    }

    public String getStatus() {
        return status;
    }

    public String getTheReason() {
        return theReason;
    }

    public void setNewScore(float newScore) {
        this.newScore = newScore;
    }

    public void setScoreCol(String scoreCol) {
        this.scoreCol = scoreCol;
    }

    public PrimaryKey_RequestsForRe getKey() {
        return key;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTheReason(String theReason) {
        this.theReason = theReason;
    }
}
