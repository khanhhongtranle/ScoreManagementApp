package Controller.Student;

import Model.Entities.Account;
import Model.Entities.Re_Examination;
import Model.Entities.RequestsForRe;
import Model.ManageReExamination;
import Model.ManageRequestForRe;
import Views.Student.XemDonPhucKhaoView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class XemDonPhucKhaoController {
    private XemDonPhucKhaoView view;
    private Account account;

    public XemDonPhucKhaoController(Account account){
        this.account = account;

        view = new XemDonPhucKhaoView();
        view.setVisible(true);

        view.xemListener(new XemListener());
    }

    private class XemListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            ManageRequestForRe manageRequestForRe = new ManageRequestForRe();
            List<RequestsForRe> listRequest = new ArrayList<RequestsForRe>();
            String mssv = account.getMSSV().toString();
            if (view.getItemStringInComboBox().equalsIgnoreCase("Tất cả")){
                listRequest = manageRequestForRe.getRecordsByMSSV(mssv);
            }
            else{
                ManageReExamination manageReExamination = new ManageReExamination();
                List<Re_Examination> listReExam = manageReExamination.getAllRecord();
                int id_re = listReExam.get(view.getIndexInComboBox()).getKey().getId();
                listRequest = manageRequestForRe.getRecordsByMSSVAndId_Re( id_re, mssv);
            }
            view.setUpDataTable(listRequest);
        }
    }
}
