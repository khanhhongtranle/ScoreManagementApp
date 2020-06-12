package Views.Teacher;

import Controller.Teacher.QuanLyDiemController;
import Model.Entities.ScoreSheet;
import Model.Entities.Student;
import Model.ManageClass;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class QuanLyDiemView extends JFrame{
    private JPanel panel1;
    private JComboBox classComboBox;
    private JComboBox subjectComboBox;
    private JButton importButton;
    private JButton watchButton;
    private JTable table1;
    private JLabel sumStudentsLabel;
    private JLabel passLabel;
    private JLabel failedLabel;
    private JScrollPane scrollPanel;
    private JTextField diemGKField;
    private JTextField diemCKField;
    private JTextField diemKhacField;
    private JTextField diemTKField;
    private JButton updateButton;
    private JLabel updateLabel;

    public QuanLyDiemView(){
        this.setTitle("Quản lý điểm của sinh viên");
        panel1.setBorder(BorderFactory.createLineBorder(Color.blue));
        panel1.setPreferredSize(new Dimension(800, 600));

        //ComboBoxClass
        ManageClass manageClass = new ManageClass();
        ArrayList<String> classes = (ArrayList<String>) manageClass.getAllOfClass();
        classComboBox.addItem(" ");
        for (String str : classes) {
            classComboBox.addItem(str);
        }

        //Table
        table1 = createTable();

        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
    }

    public JTable createTable(){
        DefaultTableModel defaultTableModel = (DefaultTableModel) table1.getModel();
        String columnName[] = {"MSSV", "Họ tên", "Điểm GK", "Điểm CK", "Điểm khác", "Điểm tổng", "Ghi chú"};
        for(int i = 0; i < columnName.length; i++) {
            defaultTableModel.addColumn(columnName[i]);
        }
        defaultTableModel.setColumnIdentifiers(columnName);
        table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table1.setModel(defaultTableModel);
        scrollPanel.setViewportView(table1);
        return table1;
    }

    public void clearDataTable(){
        DefaultTableModel defaultTableModel = (DefaultTableModel)table1.getModel();
        while(defaultTableModel.getRowCount() > 0)
        {
            defaultTableModel.removeRow(0);
        }
    }

    public void setUpDataTable(List<ScoreSheet> listData){

        clearDataTable();

        DefaultTableModel defaultTableModel = (DefaultTableModel) table1.getModel();

        for (int i = 0; i < listData.size(); i++) {
            String[] data = new String[7];

            data[0] = listData.get(i).getKey().getMSSV();
            data[1] = listData.get(i).getStudentName();
            data[2] = String.valueOf(listData.get(i).getMidTermScore());
            data[3] = String.valueOf(listData.get(i).getFinalTermScore());
            data[4] = String.valueOf(listData.get(i).getAnotherScore());
            data[5] = String.valueOf(listData.get(i).getFinalGrade());
            if (listData.get(i).getFinalGrade() >= 5){
                data[6] = "Đậu";
            }
            else{
                data[6] = "Rớt";
            }

            defaultTableModel.addRow(data);
        }
        table1.setModel(defaultTableModel);
        scrollPanel.setViewportView(table1);
    }

    public void classComboBoxListener(ItemListener listener){
        classComboBox.addItemListener(listener);
    }


    public void addItemInComboBoxSubjects(String item){
        subjectComboBox.addItem(item);
    }

    public void cleanItemInComboBoxSubjects(){ subjectComboBox.removeAllItems(); }

    public void importListener(ActionListener listener){
        importButton.addActionListener(listener);
    }

    public void watchListener(ActionListener listener){
        watchButton.addActionListener(listener);
    }

    public void showMessage(String message){
        JOptionPane.showMessageDialog(panel1, message);
    }

    public String getClassName(){
        return classComboBox.getSelectedItem().toString();
    }

    public String getSubName(){
        return subjectComboBox.getSelectedItem().toString();
    }

    public void setSumStudentsLabel(int x){
        sumStudentsLabel.setText("Tổng số sinh viên: " + x);
    }

    public void setPassLabel(int pass, float percent){
        passLabel.setText("Tổng số sinh viên đậu: " + pass + " (" + percent + "%)");
    }

    public void setFailedLabel(int failed, float percent){
        failedLabel.setText("Tổng số sinh viên rớt: " + failed + " (" + percent + "%)");
    }

    public void tableAddMouseListener(MouseListener mouseAdapter){
        table1.addMouseListener(mouseAdapter);
    }

    public void updateListener(ActionListener listener){
        updateButton.addActionListener(listener);
    }

    public void setDiemGKField(String x){
        diemGKField.setText(x);
    }

    public void setDiemCKField(String x){
        diemCKField.setText(x);
    }

    public void setDiemKhacField(String x){
        diemKhacField.setText(x);
    }

    public void setDiemTKField(String x){
        diemTKField.setText(x);
    }

    public float getDiemGKUpdated(){
        return Float.parseFloat(diemGKField.getText());
    }

    public float getDiemCKUpdated(){
        return Float.parseFloat(diemCKField.getText());
    }

    public float getDiemKhacUpdated(){
        return Float.parseFloat(diemKhacField.getText());
    }

    public float getDiemTKUpdated(){
        return Float.parseFloat(diemTKField.getText());
    }

    public void setUpdateLabel(String m){
        updateLabel.setText("Cập nhật điểm của sinh viên " + m + ":");
    }

    public JTable getTable(){
        return table1;
    }
}
