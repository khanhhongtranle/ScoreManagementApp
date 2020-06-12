package Controller.Student;

import Controller.ChangePasswordController;
import Controller.LoginController;
import Model.Entities.Account;
import Views.LoginView;
import Views.Student.StudentManagerView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentManagerController {
    private StudentManagerView view;
    private LoginView loginView;
    private LoginController loginController;
    private Account account;

    public StudentManagerController(StudentManagerView view, Account account){
        this.account = account;
        this.view = view;

        view.logoutListener(new LogoutListener());
        view.changePasswordListener(new ChangePasswordListener());
        view.checkMarkListener(new CheckMarkListener());
    }

    public void showView(){
        view.setVisible(true);
    }

    public void closeView(){
        view.setVisible(false);
    }

    private class LogoutListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            closeView();
            loginView = new LoginView();
            loginController = new LoginController(loginView);
            loginController.showLoginView();
        }
    }

    private class ChangePasswordListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            ChangePasswordController changePasswordController = new ChangePasswordController(account);
        }
    }

    private class CheckMarkListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            TraCuuDiemController traCuuDiemController = new TraCuuDiemController(view);
        }
    }
}
