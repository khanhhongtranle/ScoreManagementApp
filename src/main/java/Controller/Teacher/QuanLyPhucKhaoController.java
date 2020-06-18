package Controller.Teacher;

import Model.Entities.Re_Examination;
import Model.Entities.RequestsForRe;
import Model.ManageReExamination;
import Model.ManageRequestForRe;
import Util.DateUtil;
import Views.Teacher.QuanLyPhucKhaoView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class QuanLyPhucKhaoController {
    private QuanLyPhucKhaoView view;

    public QuanLyPhucKhaoController(){
        view = new QuanLyPhucKhaoView();
        view.setVisible(true);
        view.createListener(new CreateListener());
        view.xemListener(new XemListener());
        view.updateListener(new UpdateListener());
        view.tableMouseListener(new TableMouseListener());
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
            //kiem tra ngay ket thuc va ngay bat dau
            if (DateUtil.compareDateWithDate(view.getBeginDate(),view.getEndingDate()) > 0){
                view.showMessage("Ngày bắt đầu sau ngày kết thúc, vô lý. Mời nhập lại.");
                return;
            }
            //kiem tra dot phuc khao nay co trung voi dot phuc khao khac hay khong
            List<Re_Examination> list = manageReExamination.getAllRecord();
            if (list != null) {
                Re_Examination last = list.get(list.size()-1);
                if (DateUtil.compareDateWithDate(view.getBeginDate(),last.getKey().getEndingDate()) <=0 ){
                    view.showMessage("Không thể tạo phúc khảo, vì trong khoảng thời gian này đã tồn tại một đợt phúc khảo khác");
                    return;
                }
            }
            //kiem tra
            Re_Examination newRecord = new Re_Examination();
            newRecord.getKey().setBeginDate(view.getBeginDate());
            newRecord.getKey().setEndingDate(view.getEndingDate());
            manageReExamination.insertInto(newRecord);
            view.showMessage("Đã tạo thành công");

            view.setVisible(false);
        }
    }

    private class XemListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            ManageReExamination manageReExamination = new ManageReExamination();
            List<Re_Examination> listAll = manageReExamination.getAllRecord();
            if (listAll == null){
                view.showMessage("Chưa có đợt phúc khảo nào trong lịch sử");
                return;
            }
            int index = view.getIndexInComboBox();
            int id_re = listAll.get(index).getKey().getId();
            ManageRequestForRe manageRequestForRe = new ManageRequestForRe();
            List<RequestsForRe> listRequests = manageRequestForRe.getRecordsById(id_re);
            if (listRequests == null){
                view.showMessage("Không có đơn phúc nào trong đợt này");
                return;
            }
            view.setUpDataTable(listRequests);
        }
    }

    private class UpdateListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JTable table = view.getTable1();
            String stt = view.getSttInUpdateComboBox();
            ManageReExamination manageReExamination = new ManageReExamination();
            List<Re_Examination> listReExam = manageReExamination.getAllRecord();
            int id_re = listReExam.get(view.getIndexInComboBox()).getKey().getId();
            String mssv = table.getValueAt(table.getSelectedRow(),0).toString();
            String subNo = table.getValueAt(table.getSelectedRow(),2).toString();
            ManageRequestForRe manageRequestForRe = new ManageRequestForRe();
            RequestsForRe request = manageRequestForRe.getARecord(id_re,mssv,subNo);
            manageRequestForRe.dropRecord(id_re,mssv, subNo);
            request.setStatus(stt);
            manageRequestForRe.insertInto(request);
            view.showMessage("Cập nhật trạng thái thành công");

            view.setEnableUpdateComboBox(false);
            view.setEnableUpdateButton(false);
        }
    }

    private class TableMouseListener implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            view.setEnableUpdateButton(true);
            view.setEnableUpdateComboBox(true);
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}
