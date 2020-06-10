package Views.Teacher;

import Model.Entities.Student;
import Model.ManageClass;
import Model.ManageStudentsInClass;
import Model.ManageSubject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class XoaSvKhoiLopTheoMon extends JFrame{
    private JPanel panel1;
    private JComboBox comboBox1;
    private JButton confirmButton;
    private JLabel titleLabel;
    private QuanLyLopView fatherView;

    public XoaSvKhoiLopTheoMon(QuanLyLopView fatherView){

        this.fatherView = fatherView;

        this.setTitle("Xóa sinh viên khỏi danh sách lớp");
        panel1.setBorder(BorderFactory.createLineBorder(Color.blue));
        panel1.setPreferredSize(new Dimension(400, 300));

        //titleLabel
        String subName = fatherView.getSubName();
        String className = fatherView.getClassName();
        titleLabel.setText(titleLabel.getText() + " " + subName + " - " + className);

        //comboBoxStudents
        ManageStudentsInClass manageStudentsInClass = new ManageStudentsInClass();
        List<String> listMSSV = manageStudentsInClass.getListMSSVInAClass(getClassNo(), getSubNo());
        for (String s : listMSSV){
            comboBox1.addItem(s);
        }

        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
    }

    public void confirmListener (ActionListener listener){
        confirmButton.addActionListener(listener);
    }

    public void showMessage(String message){
        JOptionPane.showMessageDialog(panel1,message);
    }

    public String getMSSV(){
        return comboBox1.getSelectedItem().toString();
    }

    public String getSubNo(){
        String subName = fatherView.getSubName();
        ManageSubject manageSubject = new ManageSubject();
        return manageSubject.getSubjectAtSubName(subName).getSubNo();
    }

    public int getClassNo(){
        String className = fatherView.getClassName();
        ManageClass manageClass = new ManageClass();
        return manageClass.getClassAtClassName(className).getStt();
    }
}
