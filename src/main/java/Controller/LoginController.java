package Controller;

import Controller.Student.StudentManagerController;
import Controller.Teacher.TeacherManagerController;
import Model.Entities.Account;
import Model.ManageAccount;
import Views.LoginView;
import Views.Student.StudentManagerView;
import Views.Teacher.TeacherManagerView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {
    private LoginView view;
    private StudentManagerView studentView;
    private StudentManagerController studentController;
    private TeacherManagerView teacherView;
    private TeacherManagerController teacherController;
    private ManageAccount manageAccount = new ManageAccount();

    public LoginController(LoginView view){
        this.view = view;
        view.loginListener(new LoginListener());
    }

    public void showLoginView(){
        view.setVisible(true);
    }

    class LoginListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Account account = view.getAccount();

            Account accountInDB = manageAccount.getAccountRecord(account.getUsername());

            if (accountInDB != null) {
                if (accountInDB.getUsername().equals(account.getUsername()) && accountInDB.getPassword().equals(account.getPassword())) {

                    account = accountInDB;
                    //login successfully
                    if (account.isTeacher()) {
                        view.setVisible(false);
                        view.dispose();
                        teacherView = new TeacherManagerView(account);
                        teacherController = new TeacherManagerController(teacherView, account);
                        teacherController.showView();
                    } else {
                        view.setVisible(false);
                        view.dispose();
                        studentView = new StudentManagerView(account);
                        studentController = new StudentManagerController(studentView, account);
                        studentController.showView();
                    }
                }
                else {
                    view.showMessage("Tên đăng nhập hoặc mật khẩu chưa đúng");
                }
            }
            else{
                view.showMessage("Tên đăng nhập hoặc mật khẩu chưa đúng");
            }
        }
    }
}
