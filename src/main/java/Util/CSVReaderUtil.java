package Util;

import Model.Entities.Schedule;
import Model.Entities.ScoreSheet;
import Model.Entities.Student;
import Model.Entities.Subject;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReaderUtil {
    private String FileURL;
    private CSVReader reader;
    private List<Student> listStudent;
    private List<Schedule> listSchedule;
    private List<Subject> listSubject;
    private List<ScoreSheet> listScore;

    public CSVReaderUtil(String url){
        this.FileURL = url;
        this.listStudent = null;
        this.listSchedule = null;
        this.listSubject = null;
        this.listScore = null;
    }

    public List<Schedule> getListSchedule() {
        return listSchedule;
    }

    public List<Student> getListStudent() {
        return listStudent;
    }

    public List<Subject> getListSubject() {
        return listSubject;
    }

    public List<ScoreSheet> getListScore() {
        return listScore;
    }

    public void readList(int mode){
        reader = null;
        try{
            reader = new CSVReader(new FileReader(FileURL));
            String[] line;
            line = reader.readNext();
            switch (mode){
                case 0:
                    List<Student> students = new ArrayList<Student>();
                    while ((line = reader.readNext()) != null){
                        Student student = new Student();
                        student.setMSSV(line[1]);
                        student.setStudentName(line[2]);
                        student.setStudentSex(line[3]);
                        student.setStudentNationID(line[4]);
                        students.add(student);
                    }
                    this.listStudent = students;
                    break;
                case 1:
                    List<Schedule> schedules = new ArrayList<Schedule>();
                    while ((line = reader.readNext()) != null){
                        Schedule schedule = new Schedule();
                        schedule.setSubNo(line[1]);
                        schedule.setRoom(line[3]);
                        schedules.add(schedule);
                    }
                    listSchedule = schedules;
                    break;
                case 2:
                    List<Subject> subjects = new ArrayList<Subject>();
                    while ((line = reader.readNext()) != null){
                        Subject subject = new Subject();
                        subject.setSubNo(line[1]);
                        subject.setSubName(line[2]);
                        subjects.add(subject);
                    }
                    listSubject = subjects;
                    break;
                case 3:
                    List<ScoreSheet> scoreSheets = new ArrayList<>();
                    while ((line = reader.readNext()) != null){
                        ScoreSheet scoreSheet1 = new ScoreSheet();
                        scoreSheet1.getKey().setMSSV(line[1]);
                        scoreSheet1.setStudentName(line[2]);
                        scoreSheet1.setMidTermScore(Float.parseFloat(line[3]));
                        scoreSheet1.setFinalTermScore(Float.parseFloat(line[4]));
                        scoreSheet1.setAnotherScore(Float.parseFloat(line[5]));
                        scoreSheet1.setFinalGrade(Float.parseFloat(line[6]));
                        scoreSheets.add(scoreSheet1);
                    }
                    listScore = scoreSheets;
                    break;
                default:
                    //
            }

        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

}
