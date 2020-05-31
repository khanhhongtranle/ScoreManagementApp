import Controller.LoginController;
import Views.LoginView;
import Views.StudentManagerView;

import java.awt.*;

public class App {
    public static void main(String[] agrs){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                LoginView view = new LoginView();
                LoginController controller = new LoginController(view);
                controller.showLoginView();
            }
        });
    }
}
