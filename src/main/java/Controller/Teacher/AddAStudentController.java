package Controller.Teacher;

import Model.Entities.Schedule;
import Model.Entities.Student;
import Model.Entities.StudentsInClass;
import Model.ManageClass;
import Model.ManageSchedule;
import Model.ManageStudent;
import Model.ManageStudentsInClass;
import Views.Teacher.AddAStudentView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AddAStudentController {
    AddAStudentView view;

    public AddAStudentController(){
        view = new AddAStudentView();
        view.setVisible(true);
        view.buttonListener(new ButtonListener());
    }

    private class ButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.getMSSV().equals("")
                    || view.getNameOfStudent().equals("")
                    || view.getCMND().equals("")){
                view.showMessage("Không được bỏ trống bất kỳ trường nào");
                return;
            }
            ManageClass manageClass = new ManageClass();
            ManageStudent manageStudent = new ManageStudent();
            int STTClass = manageClass.getClassAtClassName(view.getClassNo()).getStt();
            if (manageStudent.getStudentAtMSSV(view.getMSSV()) != null){
                view.showMessage("MSSV này đã tồn tại");
                return;
            }
            Student student = new Student(view.getMSSV(),view.getNameOfStudent(),view.getSex(),view.getCMND(),STTClass);
            manageStudent.insertInto(student);

            //them sinh vien do vao ds lop theo mon
            ManageSchedule manageSchedule = new ManageSchedule();
            ManageStudentsInClass manageStudentsInClass = new ManageStudentsInClass();
            List<Schedule> listSchedule = manageSchedule.getAtClass(student.getClassNo());
            if (listSchedule!=null){
                for (Schedule s : listSchedule){
                    StudentsInClass std = new StudentsInClass();
                    std.getKey().setMSSV(student.getMSSV());
                    std.getKey().setSubNo(s.getSubNo());
                    manageStudentsInClass.insertInto(std);
                }
            }

            view.setVisible(false);
        }
    }
}
