package Controller.Teacher;

import Model.Entities.Student;
import Model.Entities.StudentsInClass;
import Model.ManageStudentsInClass;
import Views.Teacher.QuanLyLopView;
import Views.Teacher.ThemSvVaoLopTheoMon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ThemSvVaoLopTheoMonController {
    private ThemSvVaoLopTheoMon view;

    public ThemSvVaoLopTheoMonController(QuanLyLopView fatherView){
        view = new ThemSvVaoLopTheoMon(fatherView);
        view.setVisible(true);
        view.confirmListener(new ConfirmListener());
    }

    private class ConfirmListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            //kiem tra sinh vien do co o trong lop hay ko
            int STTLop = view.getClassNo();
            String MaMonHoc = view.getSubNo();
            ManageStudentsInClass manageStudentsInClass = new ManageStudentsInClass();
            List<String> listMSSV = manageStudentsInClass.getListMSSVInAClass(STTLop, MaMonHoc);
            if (listMSSV.contains(view.getMSSV())){
                view.showMessage("Sinh viên này đã nằm trong danh sách lớp này");
                return;
            }
            //sv chua duoc them vao thi them vao danhsachlop
            StudentsInClass newStudent = new StudentsInClass();
            newStudent.setMSSV(view.getMSSV());
            newStudent.setSubNo(MaMonHoc);
            newStudent.setClassNo(STTLop);
            manageStudentsInClass.insertInto(newStudent);
            view.showMessage("Đã thêm sinh viên vào danh sách lớp thành công");
            view.setVisible(false);
        }
    }
}
