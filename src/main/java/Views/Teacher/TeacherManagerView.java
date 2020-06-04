package Views.Teacher;

import Model.Entities.Account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TeacherManagerView extends JFrame {
    private JPanel panel1;
    private JButton changePasswordButton;
    private JButton manageStudentsButton;
    private JButton manageScheduleButton;
    private JButton manageClassButton;
    private JButton manageMarkButton;
    private JButton manageComplainButton;
    private JButton logoutButton;
    private JLabel label;

    public TeacherManagerView(Account account){
        this.setTitle("Giáo vụ");
        panel1.setBorder(BorderFactory.createLineBorder(Color.blue));
        //panel1.setLayout(new BorderLayout(1, 1));
        panel1.setPreferredSize(new Dimension(400, 300));
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        label.setText(label.getText() + " " + account.getUsername());
    }


    public void logoutListener(ActionListener actionListener){
        logoutButton.addActionListener(actionListener);
    }

    public void changePasswordListener(ActionListener actionListener){
        changePasswordButton.addActionListener(actionListener);
    }

    public void manageStudentsListener(ActionListener actionListener){
        manageStudentsButton.addActionListener(actionListener);
    }

    public void manageScheduleListener(ActionListener actionListener){
        manageScheduleButton.addActionListener(actionListener);
    }
}
