package Controller.Teacher;

import Model.Entities.Student;
import Model.ManageClass;
import Model.ManageStudent;
import Views.Teacher.QuanLySinhVienView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class QuanLySinhVienController {
    private QuanLySinhVienView view;


    public QuanLySinhVienController(){
        view = new QuanLySinhVienView();
        view.setVisible(true);

        view.importListener(new ImportListener());
        view.confirmListener(new ConfirmListener());
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
            int STT = manageClass.getClassAtClassName(view.getStringInComBox()).getStt();
            List<Student> list = manageStudent.getAllOfStudentInClass(STT);
            view.setUpDataTable(list);
        }
    }
}
