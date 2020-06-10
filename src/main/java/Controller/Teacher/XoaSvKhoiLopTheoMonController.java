package Controller.Teacher;

import Model.Entities.PrimaryKey_StudentsInClass;
import Model.Entities.StudentsInClass;
import Model.ManageStudentsInClass;
import Views.Teacher.QuanLyLopView;
import Views.Teacher.XoaSvKhoiLopTheoMon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class XoaSvKhoiLopTheoMonController {
    private XoaSvKhoiLopTheoMon view;

    public XoaSvKhoiLopTheoMonController(QuanLyLopView fatherView){
        view = new XoaSvKhoiLopTheoMon(fatherView);
        view.setVisible(true);
        view.confirmListener(new ConfirmListener());
    }

    private class ConfirmListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int STTLop = view.getClassNo();
            String MaMonHoc = view.getSubNo();
            String MSSV = view.getMSSV();

            ManageStudentsInClass manageStudentsInClass = new ManageStudentsInClass();
            manageStudentsInClass.dropARecord(STTLop, MaMonHoc, MSSV);
            view.showMessage("Đã xóa thành công");
            view.setVisible(false);
        }
    }
}
