package Model.Entities;

import java.io.Serializable;

public class ScoreSheet implements Serializable {
    private PrimaryKey_StudentsInClass key;
    private String studentName;
    private float midTermScore;
    private float finalTermScore;
    private float anotherScore;
    private float finalGrade;

    public ScoreSheet(){
        key = new PrimaryKey_StudentsInClass();
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setAnotherScore(float anotherScore) {
        this.anotherScore = anotherScore;
    }

    public void setFinalGrade(float finalGrade) {
        this.finalGrade = finalGrade;
    }

    public void setFinalTermScore(float finalTermScore) {
        this.finalTermScore = finalTermScore;
    }

    public void setMidTermScore(float midTermScore) {
        this.midTermScore = midTermScore;
    }

    public void setKey(PrimaryKey_StudentsInClass key) {
        this.key = key;
    }

    public String getStudentName() {
        return studentName;
    }

    public float getAnotherScore() {
        return anotherScore;
    }

    public float getFinalGrade() {
        return finalGrade;
    }

    public float getFinalTermScore() {
        return finalTermScore;
    }

    public float getMidTermScore() {
        return midTermScore;
    }

    public PrimaryKey_StudentsInClass getKey() {
        return key;
    }
}
