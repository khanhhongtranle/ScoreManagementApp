package Controller.Teacher;

import Model.*;
import Model.Entities.*;
import Util.CSVReaderUtil;
import Views.Teacher.QuanLyTKBView;
import com.opencsv.CSVReader;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class QuanLyTKBController {
    private QuanLyTKBView view;

    public QuanLyTKBController(){
        view = new QuanLyTKBView();
        view.setVisible(true);
        view.selectFileListener(new SelectListener());
        view.confirmListener(new ConfirmListener());
        view.xemListener(new XemListener());
    }

    private class SelectListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("D:\\"));
            fileChooser.setFileFilter(new FileNameExtensionFilter("CSV file","csv"));

            int returnValue = fileChooser.showOpenDialog(view);
            if (returnValue == JFileChooser.APPROVE_OPTION){
                File file = fileChooser.getSelectedFile();
                view.setFileURL(file.getPath());
            }
            else{
                view.showMessage("Không có file nào được chọn");
            }
        }
    }

    private class ConfirmListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.getFileURL().equals("")){
                view.showMessage("Trường file bị trống");
                return;
            }
            CSVReaderUtil reader = new CSVReaderUtil(view.getFileURL());
            reader.readList(1);
            reader.readList(2);
            List<Schedule> schedule = reader.getListSchedule();
            List<Subject> subjects = reader.getListSubject();
            ManageSchedule manageSchedule = new ManageSchedule();
            ManageSubject manageSubject = new ManageSubject();
            ManageClass manageClass = new ManageClass();
            int STTLop = manageClass.getClassAtClassName(view.getClassToImport()).getStt();
            for (Schedule schedule1 : schedule) {
                schedule1.setClassNo(STTLop);
                manageSchedule.insertInto(schedule1);
            }
            //mon hoc nao da ton tai thi khong them nua
            for (Subject subject : subjects){
                if (manageSubject.getSubjectAtSubNo(subject.getSubNo()) == null) {
                    manageSubject.insertInto(subject);
                }
            }

            //danh sach lop
            ManageStudent manageStudent = new ManageStudent();
            List<Student> studentList = manageStudent.getAllOfStudentInClass(STTLop); //lay danh sach tat ca hoc sinh cua lop do
            //mac dinh 1 hs hoc tat ca mon trong thoi khoa bieu

            List<StudentsInClass> studentsInClassList = new ArrayList<StudentsInClass>();

            for (Schedule schedule1 : schedule){
                for (Student std : studentList) {
                    StudentsInClass s = new StudentsInClass();
                    s.setClassNo(schedule1.getKey().getClassNo());
                    s.setSubNo(schedule1.getKey().getSubNo());
                    s.setMSSV(std.getMSSV());
                    studentsInClassList.add(s);
                }
            }

            ManageStudentsInClass manageStudentsInClass = new ManageStudentsInClass();
            for (StudentsInClass s : studentsInClassList){
                manageStudentsInClass.insertInto(s);
            }

            view.showMessage("Thêm thời khóa biểu thành công");
            view.setVisible(false);
        }
    }

    private class XemListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String className = view.getClassToSee();
            ManageClass manageClass = new ManageClass();
            int STTLop = manageClass.getClassAtClassName(className).getStt();
            List<Schedule> listSchedule =new ArrayList<>();
            ManageSchedule manageSchedule = new ManageSchedule();
            listSchedule = manageSchedule.getAtClass(STTLop);
            List<ScheduleToSee> toSeeList = new ArrayList<>();
            ManageSubject manageSubject = new ManageSubject();
            for (Schedule str : listSchedule){
                ScheduleToSee toSee = new ScheduleToSee();
                toSee.setMaMonHoc(str.getSubNo());
                toSee.setTenMonHoc(manageSubject.getSubjectAtSubNo(str.getSubNo()).getSubName());
                toSee.setPhongHoc(str.getRoom());
                toSeeList.add(toSee);
            }

            view.setUpDataTable(toSeeList);
        }
    }
}
