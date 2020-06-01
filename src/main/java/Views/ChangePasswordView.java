package Views;

import Util.MD5Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ChangePasswordView extends JFrame {
    private JPanel panel1;
    private JButton confirmButton;
    private JPasswordField currPasswordField;
    private JPasswordField newPasswordField2;
    private JPasswordField newAgainPasswordField3;

    public ChangePasswordView(){
        this.setTitle("Đổi mật khẩu");
        panel1.setBorder(BorderFactory.createLineBorder(Color.blue));
        //panel1.setLayout(new BorderLayout(1, 1));
        panel1.setPreferredSize(new Dimension(400, 300));
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
    }

    public void confirmListener(ActionListener actionListener){
        confirmButton.addActionListener(actionListener);
    }

    public String currPassword(){
        return new MD5Util().getMD5(new String(currPasswordField.getPassword()));
    }

    public String newPassword(){
        return new MD5Util().getMD5(new String(newPasswordField2.getPassword()));
    }

    public String newPasswordAgain(){
        return new MD5Util().getMD5(new String(newAgainPasswordField3.getPassword()));
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(panel1, message);
    }
}
