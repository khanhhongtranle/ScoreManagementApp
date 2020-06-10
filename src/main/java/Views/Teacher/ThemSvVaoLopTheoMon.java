package Views.Teacher;

import Model.Entities.Student;
import Model.ManageClass;
import Model.ManageStudent;
import Model.ManageSubject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class ThemSvVaoLopTheoMon extends JFrame{
    private JPanel panel1;
    private JLabel titleLabel;
    private JComboBox comboBox1;
    private JButton confirmButton;
    private JLabel MaMonHocLabel;
    private QuanLyLopView fatherView;

    public ThemSvVaoLopTheoMon(QuanLyLopView fatherView){

        this.fatherView = fatherView;

        this.setTitle("Thêm sinh viên vào lớp");
        panel1.setBorder(BorderFactory.createLineBorder(Color.blue));
        panel1.setPreferredSize(new Dimension(400, 300));

        //titleLabel
        String subName = fatherView.getSubName();
        String className = fatherView.getClassName();
        titleLabel.setText(titleLabel.getText() + " " + subName + " - " + className);

        //MaMonHocLabel
        MaMonHocLabel.setText(MaMonHocLabel.getText() + " " + getSubNo());

        //comboBoxStudents
        ManageStudent manageStudent = new ManageStudent();
        List<Student> listStudents = manageStudent.getAllOfStudent();
        for (Student s : listStudents){
            comboBox1.addItem(s.getMSSV() + " - " + s.getStudentName());
        }

        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
    }

    public void confirmListener(ActionListener listener){
        confirmButton.addActionListener(listener);
    }

    public void showMessage(String message){
        JOptionPane.showMessageDialog(panel1,message);
    }

    public int getClassNo(){
        ManageClass manageClass = new ManageClass();
        return manageClass.getClassAtClassName(fatherView.getClassName()).getStt();
    }

    public String getSubNo(){
        ManageSubject manageSubject = new ManageSubject();
        String subName = fatherView.getSubName();
        return manageSubject.getSubjectAtSubName(subName).getSubNo();
    }

    public String getMSSV(){
        String str = comboBox1.getSelectedItem().toString();
        int index = -1;
        for (int i = 0 ; i < str.length() ; i++){
            if (str.charAt(i) == '-'){
                index = i;
                break;
            }
        }
        str = str.substring(0,index-1);
        return str;
    }
}
