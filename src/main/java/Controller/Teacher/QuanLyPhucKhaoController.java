package Controller.Teacher;

import Model.Entities.Re_Examination;
import Model.ManageReExamination;
import Util.DateUtil;
import Views.Teacher.QuanLyPhucKhaoView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuanLyPhucKhaoController {
    private QuanLyPhucKhaoView view;

    public QuanLyPhucKhaoController(){
        view = new QuanLyPhucKhaoView();
        view.setVisible(true);
        view.createListener(new CreateListener());
    }

    private class CreateListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.getBeginDate().equals("") || view.getEndingDate().equals("")){
                view.showMessage("Các trường không thể bỏ trống");
                return;
            }
            if (DateUtil.isDateStringValid(view.getBeginDate()) == false
                || DateUtil.isDateStringValid(view.getEndingDate()) == false){
                view.showMessage("Trường ngày tháng năm không hợp lệ");
                return;
            }
            ManageReExamination manageReExamination = new ManageReExamination();
            Re_Examination record = manageReExamination.getARecord(view.getBeginDate(), view.getEndingDate());
            if (record != null){
                view.showMessage("Đợt phúc khảo trong khoảng thời gian này đã được tạo rồi");
                return;
            }
            Re_Examination newRecord = new Re_Examination();
            newRecord.getKey().setBeginDate(view.getBeginDate());
            newRecord.getKey().setEndingDate(view.getEndingDate());
            manageReExamination.insertInto(newRecord);
            view.showMessage("Đã tạo thành công");
        }
    }
}
