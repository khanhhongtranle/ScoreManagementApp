package Controller.Teacher;

import Model.Entities.AClass;
import Model.Entities.Schedule;
import Model.Entities.Student;
import Model.Entities.Subject;
import Model.ManageClass;
import Model.ManageSchedule;
import Model.ManageStudent;
import Model.ManageSubject;
import Util.CSVReaderUtil;
import Views.Teacher.QuanLyTKBView;
import com.opencsv.CSVReader;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.util.List;

public class QuanLyTKBController {
    private QuanLyTKBView view;

    public QuanLyTKBController(){
        view = new QuanLyTKBView();
        view.setVisible(true);
        view.selectFileListener(new SelectListener());
        view.confirmListener(new ConfirmListener());
    }

    private class SelectListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("D:\\"));
            fileChooser.setFileFilter(new FileNameExtensionFilter("CSV file","csv"));

            int returnValue = fileChooser.showOpenDialog(view);
            if (returnValue == JFileChooser.APPROVE_OPTION){
                File file = fileChooser.getSelectedFile();
                view.setFileURL(file.getPath());
            }
            else{
                view.showMessage("Không có file nào được chọn");
            }
        }
    }

    private class ConfirmListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.getFileURL().equals("")){
                view.showMessage("Trường file bị trống");
                return;
            }
            CSVReaderUtil reader = new CSVReaderUtil(view.getFileURL());
            reader.readList(1);
            reader.readList(2);
            List<Schedule> schedule = reader.getListSchedule();
            List<Subject> subjects = reader.getListSubject();
            ManageSchedule manageSchedule = new ManageSchedule();
            ManageSubject manageSubject = new ManageSubject();
            ManageClass manageClass = new ManageClass();
            int STTLop = manageClass.getClassAtClassName(view.getClassToImport()).getStt();
            for (Schedule schedule1 : schedule) {
                schedule1.setClassNo(STTLop);
                manageSchedule.insertInto(schedule1);
            }
            for (Subject subject : subjects){
                manageSubject.insertInto(subject);
            }

            //danh sach lop

            view.setVisible(false);
        }
    }
}
