package Views.Teacher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class QuanLySinhVienView extends JFrame {
    private JPanel panel1;
    private JTable table1;
    private JButton importButton;
    private JComboBox comboBox;
    private JButton confirmButton;
    private JButton addStudentButton;

    public QuanLySinhVienView(){
        this.setTitle("Quản lý sinh viên");
        panel1.setBorder(BorderFactory.createLineBorder(Color.blue));
        //panel1.setLayout(new BorderLayout(1, 1));
        panel1.setPreferredSize(new Dimension(500, 400));
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
    }

    public void importListener(ActionListener actionListener){
        importButton.addActionListener(actionListener);
    }
}
