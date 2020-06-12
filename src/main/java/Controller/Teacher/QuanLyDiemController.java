package Controller.Teacher;

import Model.Entities.Schedule;
import Model.Entities.ScoreSheet;
import Model.Entities.StudentsInClass;
import Model.Entities.Subject;
import Model.ManageClass;
import Model.ManageSchedule;
import Model.ManageScoreSheet;
import Model.ManageSubject;
import Util.CSVReaderUtil;
import Views.Teacher.QuanLyDiemView;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.*;
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
        view.tableAddMouseListener(new TableMouseClickListener());
        view.updateListener(new UpdateListener());
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
            if (scoreSheetList == null){
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

    private class TableMouseClickListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            JTable table = view.getTable();
            int i = table.getSelectedRow();
            view.setDiemGKField(table.getModel().getValueAt(i,2).toString());
            view.setDiemCKField(table.getModel().getValueAt(i,3).toString());
            view.setDiemKhacField(table.getModel().getValueAt(i,4).toString());
            view.setDiemTKField(table.getModel().getValueAt(i,5).toString());
            view.setUpdateLabel(table.getModel().getValueAt(i,0).toString());
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

    private class UpdateListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JTable table = view.getTable();
            int i = table.getSelectedRow();
            String mssv = table.getModel().getValueAt(i,0).toString();
            float diemGK = view.getDiemGKUpdated();
            float diemCK = view.getDiemCKUpdated();
            float diemKhac = view.getDiemKhacUpdated();
            float diemTK = view.getDiemTKUpdated();

            ManageSubject manageSubject = new ManageSubject();
            String subNo = manageSubject.getSubjectAtSubName(view.getSubName()).getSubNo();
            ManageClass manageClass = new ManageClass();
            int classNo = manageClass.getClassAtClassName(view.getClassName()).getStt();
            ManageScoreSheet manageScoreSheet = new ManageScoreSheet();
            ScoreSheet record = manageScoreSheet.getARecord(classNo,subNo,mssv);
            record.setMidTermScore(diemGK);
            record.setFinalTermScore(diemCK);
            record.setAnotherScore(diemKhac);
            record.setFinalGrade(diemTK);
            manageScoreSheet.dropARecord(classNo, subNo, mssv);
            manageScoreSheet.insertInto(record);

            view.showMessage("Cập nhật điểm thành công");
            view.clearDataTable();
        }
    }
}
