package Controller;

import Views.LoginView;
import Views.TeacherManagerView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeacherManagerController {
    private TeacherManagerView view;
    private LoginView loginView;
    private LoginController loginController;

    public TeacherManagerController(TeacherManagerView view){
        this.view = view;

        view.logoutListener(new LogoutListener());
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

}
