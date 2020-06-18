package Controller.Student;

import Model.Entities.Account;
import Model.Entities.Re_Examination;
import Model.Entities.RequestsForRe;
import Model.ManageReExamination;
import Model.ManageRequestForRe;
import Model.ManageSubject;
import Util.DateUtil;
import Views.Student.NopPhucKhaoView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class NopPhucKhaoController {
    private NopPhucKhaoView view;
    private Account account;

    public NopPhucKhaoController(Account account){
        this.account = account;
        view = new NopPhucKhaoView(account);
        view.setVisible(true);
        view.submitListener(new SubmitListener());
    }

    private class SubmitListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.getNewScore().equals("") || view.getTheReason().equals("")){
                view.showMessage("Không được để trống các trường");
                return;
            }
            //tim ra dot phuc khao
            ManageReExamination manageReExamination = new ManageReExamination();
            List<Re_Examination> listRe_Exam = manageReExamination.getAllRecord();
            if (listRe_Exam == null){
                view.showMessage("Không có đợt phúc khảo nào trong thời gian này");
                return;
            }

            RequestsForRe request = new RequestsForRe();
            boolean checkDate = false;

            for (Re_Examination re : listRe_Exam){
                String currDate = DateUtil.getCurrDate();
                if (DateUtil.compareDateWithDate(currDate, re.getKey().getBeginDate()) >= 0
                    && DateUtil.compareDateWithDate(currDate, re.getKey().getEndingDate()) <= 0){
                    request.getKey().setId_re(re.getKey().getId());
                    checkDate = true;
                    break;
                }
            }
            if (checkDate == false){
                view.showMessage("Không có đợt phúc khảo nào trong thời gian này");
                return;
            }
            request.getKey().setMssv(account.getMSSV().toString());
            String subName = view.getChosenSubject();
            ManageSubject manageSubject = new ManageSubject();
            request.getKey().setSubNo(manageSubject.getSubjectAtSubName(subName).getSubNo());
            request.setScoreCol(view.getScoreCol());
            request.setNewScore(Float.parseFloat(view.getNewScore()));
            request.setTheReason(view.getTheReason());
            request.setStatus("Chưa xem");

            ManageRequestForRe manageRequestForRe = new ManageRequestForRe();
            manageRequestForRe.insertInto(request);
            view.showMessage("Đã gửi đơn phúc khảo");
            view.setVisible(false);
        }
    }
}
