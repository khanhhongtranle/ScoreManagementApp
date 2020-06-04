package Views.Teacher;

import Model.ManageClass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddAStudentView extends JFrame{
    private JPanel panel1;
    private JComboBox comboBoxClass;
    private JTextField MSSVtextField;
    private JTextField NametextField;
    private JComboBox comboBoxSex;
    private JTextField CMNDtextField;
    private JButton Button;

    public AddAStudentView(){
        this.setTitle("Thêm thông tin sinh viên");
        panel1.setBorder(BorderFactory.createLineBorder(Color.blue));
        panel1.setPreferredSize(new Dimension(300, 400));

        //ComboBoxClass
        ManageClass manageClass = new ManageClass();
        ArrayList<String> classes = (ArrayList<String>) manageClass.getAllOfClass();
        for (String str : classes) {
            comboBoxClass.addItem(str);
        }

        //ComboBoxSex
        comboBoxSex.addItem("Nữ");
        comboBoxSex.addItem("Nam");

        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
    }

    public String getMSSV(){
        return MSSVtextField.getText();
    }

    public String getNameOfStudent(){
        return NametextField.getText();
    }

    public String getSex(){
        return comboBoxSex.getSelectedItem().toString();
    }

    public String getClassNo(){
        return comboBoxClass.getSelectedItem().toString();
    }

    public String getCMND(){
        return CMNDtextField.getText();
    }

    public void buttonListener(ActionListener actionListener){
        Button.addActionListener(actionListener);
    }

    public void showMessage(String message){
        JOptionPane.showMessageDialog(panel1, message);
    }
}
