package Views;

import Model.Entities.Account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentManagerView {
    private JPanel panel1;
    private JButton checkMarkButton;
    private JButton complainButton;
    private JButton changePasswordButton;
    private JButton logoutButton;
    private JLabel label;

    public StudentManagerView(Account account) {

        JFrame frame = new JFrame("Quản lý điểm sinh viên");
        panel1.setBorder(BorderFactory.createLineBorder(Color.blue));
        //panel1.setLayout(new BorderLayout(1, 1));
        panel1.setPreferredSize(new Dimension(400, 300));
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        label.setText(label.getText() + account.getUsername());

        checkMarkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        complainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        changePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
