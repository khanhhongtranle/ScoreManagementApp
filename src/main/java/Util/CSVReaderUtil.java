package Util;

import Model.Entities.Student;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReaderUtil {
    private String fileURL;
    private CSVReader reader;

    public CSVReaderUtil(String url){
        this.fileURL = url;
    }

    public List<Student> read(){
        reader = null;
        try{
            reader = new CSVReader(new FileReader(fileURL));
            String[] line;
            line = reader.readNext();
            List<Student> studentList = new ArrayList<Student>();
            while ((line = reader.readNext()) != null){
                Student student = new Student();
                student.setMSSV(line[0]);
                student.setStudentName(line[1]);
                student.setStudentSex(line[2]);
                student.setStudentNationID(line[3]);
                studentList.add(student);
            }
            return studentList;
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
