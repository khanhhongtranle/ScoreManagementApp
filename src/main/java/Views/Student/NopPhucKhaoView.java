package Views.Student;

import Model.Entities.Account;
import Model.ManageStudentsInClass;
import Model.ManageSubject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class NopPhucKhaoView extends JFrame{
    private JPanel panel1;
    private JComboBox subjectsComboBox;
    private JComboBox scoreColComboBox;
    private JTextField newScoreField;
    private JTextArea reasonTextArea;
    private JButton submitButton;
    private Account account;

    public NopPhucKhaoView(Account account){

        this.setTitle("Nộp đơn phúc khảo");
        panel1.setBorder(BorderFactory.createLineBorder(Color.blue));
        panel1.setPreferredSize(new Dimension(600, 300));

        //subjects comboBox
        ManageStudentsInClass manageStudentsInClass = new ManageStudentsInClass();
        String MSSV = account.getMSSV().toString();
        List<String> listSubNo = manageStudentsInClass.getListSubjectsOfAStudent(MSSV);
        ManageSubject manageSubject = new ManageSubject();
        for (String s : listSubNo){
            String s_t = manageSubject.getSubjectAtSubNo(s).getSubName();
            subjectsComboBox.addItem(s_t);
        }

        //score col comboBox
        scoreColComboBox.addItem("Điểm giữa kỳ");
        scoreColComboBox.addItem("Điểm cuối kỳ");
        scoreColComboBox.addItem("Điểm khác");
        scoreColComboBox.addItem("Điểm tổng kết");

        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
    }

    public String getChosenSubject(){
        return subjectsComboBox.getSelectedItem().toString();
    }

    public String getScoreCol(){
        return scoreColComboBox.getSelectedItem().toString();
    }

    public String getNewScore(){
        return newScoreField.getText();
    }

    public String getTheReason(){
        return reasonTextArea.getText();
    }

    public void submitListener(ActionListener listener){
        submitButton.addActionListener(listener);
    }

    public void showMessage(String message){
        JOptionPane.showMessageDialog(panel1,message);
    }
}
