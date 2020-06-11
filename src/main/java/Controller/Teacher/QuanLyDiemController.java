package Controller.Teacher;

import Model.Entities.Schedule;
import Model.Entities.ScoreSheet;
import Model.Entities.Subject;
import Model.ManageClass;
import Model.ManageSchedule;
import Model.ManageScoreSheet;
import Model.ManageSubject;
import Util.CSVReaderUtil;
import Views.Teacher.QuanLyDiemView;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class QuanLyDiemController {
    private QuanLyDiemView view;

    public QuanLyDiemController(){
        view = new QuanLyDiemView();
        view.setVisible(true);
        view.classComboBoxListener(new ClassComboBoxItemListener());
        view.importListener(new ImportListener());
        view.watchListener(new WatchListener());
    }

    private class ClassComboBoxItemListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                ManageClass manageClass = new ManageClass();
                Object item = e.getItem();
                if (item.toString().equals(" ")){
                    return;
                }
                int STTLop = manageClass.getClassAtClassName(item.toString()).getStt();
                ManageSchedule manageSchedule = new ManageSchedule();
                ManageSubject manageSubject = new ManageSubject();
                ArrayList<Schedule> schedule = (ArrayList<Schedule>) manageSchedule.getAtClass(STTLop);
                view.cleanItemInComboBoxSubjects();
                for (Schedule sch : schedule) {
                    Subject sub = manageSubject.getSubjectAtSubNo(sch.getSubNo());
                    view.addItemInComboBoxSubjects(sub.getSubName());
                }
            }
        }
    }

    private class ImportListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            //show window
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("D:\\"));
            fileChooser.setFileFilter(new FileNameExtensionFilter("CSV file","csv"));

            int returnValue = fileChooser.showOpenDialog(view);
            if (returnValue == JFileChooser.APPROVE_OPTION){
                File file = fileChooser.getSelectedFile();

                //bat dau insert into db
                CSVReaderUtil reader = new CSVReaderUtil(file.getPath());
                reader.readList(3);
                List<ScoreSheet> scoreSheetList = reader.getListScore();
                String className = view.getClassName();
                ManageClass manageClass = new ManageClass();
                int classNo =  manageClass.getClassAtClassName(className).getStt();
                String subName = view.getSubName();
                ManageSubject manageSubject = new ManageSubject();
                String subNo = manageSubject.getSubjectAtSubName(subName).getSubNo();

                ManageScoreSheet manageScoreSheet = new ManageScoreSheet();

                for(ScoreSheet scoreSheet : scoreSheetList){
                    scoreSheet.getKey().setSubNo(subNo);
                    scoreSheet.getKey().setClassNo(classNo);

                    manageScoreSheet.insertInto(scoreSheet);
                }

                view.showMessage("Đã import bảng điểm thành công");

                view.setVisible(false);
            }
            else{
                view.showMessage("Không có file nào được chọn");
            }
        }
    }

    private class WatchListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            ManageClass manageClass = new ManageClass();
            int classNo = manageClass.getClassAtClassName(view.getClassName()).getStt();
            ManageSubject manageSubject = new ManageSubject();
            String subNo = manageSubject.getSubjectAtSubName(view.getSubName()).getSubNo();
            ManageScoreSheet manageScoreSheet = new ManageScoreSheet();
            List<ScoreSheet> scoreSheetList =  manageScoreSheet.getScoreSheetInAClass(classNo, subNo);
            if (scoreSheetList.isEmpty()){
                view.showMessage("Lớp này chưa có bảng điểm");
                return;
            }
            //set table
            view.setUpDataTable(scoreSheetList);

            //set labels
            view.setSumStudentsLabel(scoreSheetList.size());
            int passed = 0;
            for (ScoreSheet s : scoreSheetList){
                if (s.getFinalGrade() >=5){
                    passed++;
                }
            }
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            float percent = ((float) passed / (float) scoreSheetList.size()) * 100;
            percent = Float.valueOf(decimalFormat.format(percent));
            view.setPassLabel(passed,percent);
            view.setFailedLabel(scoreSheetList.size() - passed, 100 - percent);
        }
    }
}
