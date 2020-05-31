package Controller;

import Model.Entities.Account;
import Model.ManageAccount;
import Views.LoginView;
import Views.StudentManagerView;
import Views.TeacherManagerView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.List;

public class LoginController {
    private LoginView view;
    private StudentManagerView studentView;
    private TeacherManagerView teacherView;
    ManageAccount manageAccount = new ManageAccount();
    List accountsList = manageAccount.listAllAccounts();

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

            boolean success = false;

            if (accountsList != null) {
                for (int i=0;i < accountsList.size();i++ ) {
                    Account accountInDB = (Account) accountsList.get(i);
                    if (accountInDB.getUsername().equals(account.getUsername()) && accountInDB.getPassword().equals(account.getPassword())) {
                        success = true;
                        account = accountInDB;
                        break;
                    }
                }
                if (success){
                    //login successfully
                    if (account.isTeacher()){

                    }else{
                        studentView = new StudentManagerView(account);
                    }
                }
                else {
                    view.showMessage("Tên đăng nhập hoặc mật khẩu chưa đúng");
                }
            } else {
                view.showMessage("Lỗi. Vui lòng thực hiện lại sau giây lát.");
            }
        }
    }

}
