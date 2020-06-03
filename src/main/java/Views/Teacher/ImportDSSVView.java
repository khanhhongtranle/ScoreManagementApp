package Views.Teacher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ImportDSSVView extends JFrame {
    private JPanel panel1;
    private JTextField textField1;
    private JButton selectButton;
    private JButton confirmButton;
    private JTextField textField2;

    public ImportDSSVView(){
        this.setTitle("Import file danh sách sinh viên");
        panel1.setBorder(BorderFactory.createLineBorder(Color.blue));
        //panel1.setLayout(new BorderLayout(1, 1));
        panel1.setPreferredSize(new Dimension(300, 200));
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
    }

    public String getURLFILE(){
        return textField1.getText();
    }

    public void setTextField(String text){
        textField1.setText(text);
    }

    public String getTenLop(){
        return this.textField2.getText();
    }

    public void selectListener(ActionListener actionListener){
        selectButton.addActionListener(actionListener);
    }

    public void confirmListener(ActionListener actionListener){
        confirmButton.addActionListener(actionListener);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(panel1, message);
    }
}
