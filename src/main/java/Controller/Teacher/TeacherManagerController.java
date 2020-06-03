package Controller.Teacher;

import Controller.ChangePasswordController;
import Controller.LoginController;
import Controller.Student.StudentManagerController;
import Model.Entities.Account;
import Views.LoginView;
import Views.Teacher.TeacherManagerView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeacherManagerController {
    private TeacherManagerView view;
    private LoginView loginView;
    private LoginController loginController;
    private Account account;

    public TeacherManagerController(TeacherManagerView view, Account account){
        this.view = view;
        this.account = account;
        view.logoutListener(new LogoutListener());
        view.changePasswordListener(new ChangePasswordListener());
        view.manageStudentsListener(new ManageStudentsListener());
    }

    public void showView(){
        view.setVisible(true);
    }

    public void closeView(){
        view.setVisible(false);
    }
    
    class LogoutListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            closeView();
            loginView = new LoginView();
            loginController = new LoginController(loginView);
            loginController.showLoginView();
        }
    }

    class ChangePasswordListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            ChangePasswordController changePasswordController = new ChangePasswordController(account);
        }
    }

    class ManageStudentsListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            QuanLySinhVienController QLSVController = new QuanLySinhVienController();
        }
    }
}
