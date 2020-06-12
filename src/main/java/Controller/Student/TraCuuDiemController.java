package Controller.Student;

import Model.Entities.ScoreSheet;
import Model.ManageScoreSheet;
import Model.ManageSubject;
import Views.Student.StudentManagerView;
import Views.Student.TraCuuDiemView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TraCuuDiemController {
    private TraCuuDiemView view;
    private StudentManagerView fatherView;

    public TraCuuDiemController(StudentManagerView fatherView){
        this.fatherView = fatherView;
        view = new TraCuuDiemView(fatherView);
        view.setVisible(true);
        view.xemListener(new XemListener());
    }

    private class XemListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            view.clearDataTable();
            String MSSV = fatherView.getMSSV();
            String subName = view.getSubName();
            ManageSubject manageSubject = new ManageSubject();
            String subNo = manageSubject.getSubjectAtSubName(subName).getSubNo();
            ManageScoreSheet manageScoreSheet = new ManageScoreSheet();
            ScoreSheet record = manageScoreSheet.getARecordBySubNoAndMSSV(subNo, MSSV);
            if (record == null){
                view.showMessage("Môn này chưa cập nhật điểm số");
                return;
            }
            List<ScoreSheet> list = new ArrayList<>();
            list.add(record);
            view.setUpDataTable(list);
        }
    }
}
