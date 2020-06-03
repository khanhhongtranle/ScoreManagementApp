package Controller.Teacher;

import Views.Teacher.QuanLySinhVienView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuanLySinhVienController {
    private QuanLySinhVienView view;


    public QuanLySinhVienController(){
        view = new QuanLySinhVienView();
        view.setVisible(true);

        view.importListener(new ImportListener());
    }

    class ImportListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            ImportDSSVController importDSSVController = new ImportDSSVController();
        }
    }
}
