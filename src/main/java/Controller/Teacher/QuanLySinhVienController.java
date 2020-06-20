package Controller.Teacher;

import Model.Entities.Student;
import Model.ManageClass;
import Model.ManageStudent;
import Views.Teacher.QuanLySinhVienView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class QuanLySinhVienController {
    private QuanLySinhVienView view;


    public QuanLySinhVienController(){
        view = new QuanLySinhVienView();
        view.setVisible(true);

        view.importListener(new ImportListener());
        view.confirmListener(new ConfirmListener());
        view.addStudentListener(new AddListener());
    }

    private class ImportListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            ImportDSSVController importDSSVController = new ImportDSSVController();
        }
    }

    private class ConfirmListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            ManageClass manageClass = new ManageClass();
            ManageStudent manageStudent = new ManageStudent();
            List<Student> list = new ArrayList<>();
            int STT = 0;
            if (view.getStringInComBox().equalsIgnoreCase("Tất cả")){
                list = manageStudent.getAllOfStudent();
            }
            else{
                STT = manageClass.getClassAtClassName(view.getStringInComBox()).getStt();
                list = manageStudent.getAllOfStudentInClass(STT);
            }
            view.setUpDataTable(list);
        }
    }

    private class AddListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            AddAStudentController addAStudentController = new AddAStudentController();
        }
    }
}
