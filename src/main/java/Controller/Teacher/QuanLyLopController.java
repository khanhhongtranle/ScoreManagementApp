package Controller.Teacher;

import Model.*;
import Model.Entities.Schedule;
import Model.Entities.Student;
import Views.Teacher.QuanLyLopView;

import Model.Entities.Subject;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class QuanLyLopController {
    private QuanLyLopView view;

    public QuanLyLopController(){
        view = new QuanLyLopView();
        view.setVisible(true);

        view.comboBoxClassListener(new ComboBoxClassListener());
        view.xemButtonListener(new XemListener());
    }

    private class ComboBoxClassListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                ManageClass manageClass = new ManageClass();
                Object item = e.getItem();
                if (item.toString().equals(" ")){
                    return;
                }
                int STTLop = manageClass.getClassAtClassName(item.toString()).getStt();
                ManageSchedule manageSchedule = new ManageSchedule();
                ManageSubject manageSubject = new ManageSubject();
                ArrayList<Schedule> schedule = (ArrayList<Schedule>) manageSchedule.getAtClass(STTLop);
                view.cleanItemInComboBoxSubjects();
                for (Schedule sch : schedule) {
                    Subject sub = manageSubject.getSubjectAtSubNo(sch.getSubNo());
                    view.addItemInComboBoxSubjects(sub.getSubName());
                }

            }
        }
    }

    private class XemListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            ManageClass manageClass = new ManageClass();
            ManageSubject manageSubject = new ManageSubject();
            ManageStudent manageStudent = new ManageStudent();
            ManageStudentsInClass manageStudentsInClass = new ManageStudentsInClass();

            int STTLop = manageClass.getClassAtClassName(view.getClassName()).getStt();
            String MaMonHoc = manageSubject.getSubjectAtSubName(view.getSubName()).getSubNo();

            List<String> dsMSSV = manageStudentsInClass.getListMSSVInAClass(STTLop, MaMonHoc);
            List<Student> dsHs = new ArrayList<Student>();
            for (String s : dsMSSV){
                Student std = manageStudent.getStudentAtMSSV(s);
                dsHs.add(std);
            }

            view.setUpDataTable(dsHs);
        }
    }
}
