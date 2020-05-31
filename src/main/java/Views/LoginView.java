package Views;

import Model.Entities.Account;
import Model.ManageAccount;
import org.mortbay.jetty.security.Password;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.List;

public class LoginView extends JFrame{
    private JPanel panel1;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginView() {
        this.setTitle("Quản lý điểm sinh viên");
        panel1.setBorder(BorderFactory.createLineBorder(Color.blue));
        //panel1.setLayout(new BorderLayout(1, 1));
        panel1.setPreferredSize(new Dimension(400, 200));
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
    }

    public Account getAccount(){
        Account account = new Account(usernameField.getText(),getMD5(new String(passwordField.getPassword())),null);
        return account;
    }

    public void loginListener(ActionListener actionListener){
        loginButton.addActionListener(actionListener);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(panel1, message);
    }

    public String getMD5(String md5){
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            String s;
            byte[] array = md.digest(md5.getBytes());
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i<array.length; i++){
                stringBuilder.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return stringBuilder.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
