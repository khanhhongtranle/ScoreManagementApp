package Controller.Teacher;

import Model.Entities.AClass;
import Model.Entities.Student;
import Model.ManageClass;
import Model.ManageStudent;
import Util.CSVReaderUtil;
import Views.Teacher.ImportDSSVView;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;


public class ImportDSSVController {
    private ImportDSSVView view;

    public ImportDSSVController(){
        view = new ImportDSSVView();
        view.setVisible(true);
        view.selectListener(new SelectListener());
        view.confirmListener(new ConfirmListener());
    }

    class SelectListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("D:\\"));
            fileChooser.setFileFilter(new FileNameExtensionFilter("CSV file","csv"));

            int returnValue = fileChooser.showOpenDialog(view);
            if (returnValue == JFileChooser.APPROVE_OPTION){
                File file = fileChooser.getSelectedFile();
                view.setTextField(file.getPath());
            }
            else{
                view.showMessage("Không có file nào được chọn");
            }
        }
    }

    class ConfirmListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            if (view.getTenLop().equals("") || view.getURLFILE().equals("")){
                view.showMessage("Trường tên lớp hoặc trường file bị trống");
                return;
            }
            ManageClass manageClass = new ManageClass();
            ManageStudent manageStudent = new ManageStudent();
            if (manageClass.getClassAtClassName(view.getTenLop())!=null){
                view.showMessage("Danh sách lớp" + view.getTenLop() + " đã được import");
                return;
            }
            AClass newClass = new AClass(view.getTenLop());
            manageClass.insertInto(newClass);
            newClass = manageClass.getClassAtClassName(view.getTenLop());
            CSVReaderUtil readerUtil = new CSVReaderUtil(view.getURLFILE());
            List<Student> studentList = readerUtil.read();
            for (Student std : studentList){
                std.setClassNo(newClass.getStt());
                manageStudent.insertInto(std);
            }

            view.setVisible(false);
        }
    }
}
