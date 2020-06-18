package Views.Teacher;

import Model.Entities.Re_Examination;
import Model.Entities.RequestsForRe;
import Model.Entities.ScoreSheet;
import Model.ManageReExamination;
import Model.ManageScoreSheet;
import Model.ManageStudent;
import Model.ManageSubject;
import javafx.scene.control.ComboBox;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.List;

public class QuanLyPhucKhaoView extends JFrame {
    private JPanel panel1;
    private JTextField fromField;
    private JTextField toField;
    private JButton createButton;
    private JScrollPane scrollPane;
    private JTable table1;
    private JButton xemButton;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JButton updateButton;

    public QuanLyPhucKhaoView(){
        this.setTitle("Quản lý đơn phúc khảo");
        panel1.setBorder(BorderFactory.createLineBorder(Color.blue));
        panel1.setPreferredSize(new Dimension(750, 400));

        //Table
        table1 = createTable();

        //ComboBox1
        ManageReExamination manageReExamination = new ManageReExamination();
        List<Re_Examination> listAll = manageReExamination.getAllRecord();
        if (listAll != null) {
            for (Re_Examination re : listAll) {
                comboBox1.addItem("Đợt " + re.getKey().getBeginDate() + " - " + re.getKey().getEndingDate());
            }
        }

        //editTextField
        fromField.setText("dd-MM-yyyy");
        toField.setText("dd-MM-yyyy");

        //update area
        comboBox2.setEnabled(false);
        updateButton.setEnabled(false);
        comboBox2.addItem("Đã cập nhật điểm");
        comboBox2.addItem("Không cập nhật điểm");

        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
    }

    public JTable createTable(){
        DefaultTableModel defaultTableModel = (DefaultTableModel) table1.getModel();
        String columnName[] = {"MSSV", "Họ tên", "Mã môn học", "Tên môn học", "Cột điểm phúc khảo", "Điểm số mong muốn", "Lý do", "Tình trạng"};
        for(int i = 0; i < columnName.length; i++) {
            defaultTableModel.addColumn(columnName[i]);
        }
        defaultTableModel.setColumnIdentifiers(columnName);
        table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table1.setModel(defaultTableModel);
        scrollPane.setViewportView(table1);
        return table1;
    }

    public int getIndexInComboBox(){
        return comboBox1.getSelectedIndex();
    }

    public void clearDataTable(){
        DefaultTableModel defaultTableModel = (DefaultTableModel)table1.getModel();
        while(defaultTableModel.getRowCount() > 0)
        {
            defaultTableModel.removeRow(0);
        }
    }

    public void setUpDataTable(List<RequestsForRe> listData){

        clearDataTable();

        DefaultTableModel defaultTableModel = (DefaultTableModel) table1.getModel();

        ManageStudent manageStudent = new ManageStudent();
        ManageSubject manageSubject = new ManageSubject();

        for (int i = 0; i < listData.size(); i++) {
            String[] data = new String[8];

            data[0] = listData.get(i).getKey().getMssv();
            data[1] = manageStudent.getStudentAtMSSV(data[0]).getStudentName();
            data[2] = listData.get(i).getKey().getSubNo();
            data[3] = manageSubject.getSubjectAtSubNo(data[2]).getSubName();
            data[4] = listData.get(i).getScoreCol();
            data[5] = String.valueOf(listData.get(i).getNewScore());
            data[6] = listData.get(i).getTheReason();
            data[7] = listData.get(i).getStatus();

            defaultTableModel.addRow(data);
        }
        table1.setModel(defaultTableModel);
        scrollPane.setViewportView(table1);
    }

    public String getBeginDate(){
        return fromField.getText();
    }

    public String getEndingDate(){
        return toField.getText();
    }

    public void tableMouseListener(MouseListener mouseAdapter){
        table1.addMouseListener(mouseAdapter);
    }

    public JTable getTable1(){
        return table1;
    }

    public void setEnableUpdateComboBox(boolean b){
        comboBox2.setEnabled(b);
    }

    public void setEnableUpdateButton(boolean b){
        updateButton.setEnabled(b);
    }

    public String getSttInUpdateComboBox(){
        return comboBox2.getSelectedItem().toString();
    }

    public void updateListener(ActionListener listener){
        updateButton.addActionListener(listener);
    }

    public void createListener(ActionListener listener){
        createButton.addActionListener(listener);
    }

    public void xemListener(ActionListener listener){
        xemButton.addActionListener(listener);
    }

    public void showMessage(String message){
        JOptionPane.showMessageDialog(panel1,message);
    }
}
