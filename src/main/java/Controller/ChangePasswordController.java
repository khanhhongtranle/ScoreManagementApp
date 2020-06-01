package Controller;

import Model.Entities.Account;
import Model.ManageAccount;
import Views.ChangePasswordView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ChangePasswordController {
    private ChangePasswordView view;
    private Account account;
    ManageAccount manageAccount = new ManageAccount();

    public ChangePasswordController(Account account){
        this.account = account;
        view = new ChangePasswordView();
        view.setVisible(true);
        view.confirmListener(new ConfirmListener());
    }

    class ConfirmListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String curr = view.currPassword();
            String pwInDB = manageAccount.getPasswordAt(account.getUsername());
            if (pwInDB.equals(curr)==false){
                view.showMessage("Mật khẩu không đúng");
                return;
            }
            String new_ = view.newPassword();
            String new_again = view.newPasswordAgain();
            if (new_.equals(new_again)==false){
                view.showMessage("Xác nhận mật khẩu chưa khớp");
                return;
            }
            account.setPassword(new_);
            view.showMessage("Đổi mật khẩu thành công");
            manageAccount.updatePassword(account);
            //close
            view.setVisible(false);
            view.dispose();
        }
    }

}
