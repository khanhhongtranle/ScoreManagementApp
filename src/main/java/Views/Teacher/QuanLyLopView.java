package Views.Teacher;

import Model.Entities.Student;
import Model.Entities.Subject;
import Model.ManageClass;
import Model.ManageSubject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class QuanLyLopView extends JFrame{
    private JPanel panel1;
    private JComboBox comboBoxClass;
    private JComboBox comboBoxSubject;
    private JTable table1;
    private JButton addButton;
    private JButton xemButton;
    private JScrollPane scrollPane;
    private JButton deleteButton;

    public QuanLyLopView(){
        this.setTitle("Quản lý lớp học");
        panel1.setBorder(BorderFactory.createLineBorder(Color.blue));
        panel1.setPreferredSize(new Dimension(400, 500));

        //ComboBoxClass
        ManageClass manageClass = new ManageClass();
        ArrayList<String> classes = (ArrayList<String>) manageClass.getAllOfClass();
        comboBoxClass.addItem(" ");
        for (String str : classes) {
            comboBoxClass.addItem(str);
        }

        //ComboBoxSubject

        //Table
        table1 = createTable();

        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
    }

    public void comboBoxClassListener(ItemListener listener){
        comboBoxClass.addItemListener(listener);
    }

    public JTable createTable(){
        DefaultTableModel defaultTableModel = (DefaultTableModel) table1.getModel();
        String columnName[] = {"MSSV", "Họ tên", "Giới tính", "CMND"};
        for(int i = 0; i < columnName.length; i++) {
            defaultTableModel.addColumn(columnName[i]);
        }
        defaultTableModel.setColumnIdentifiers(columnName);
        table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table1.setModel(defaultTableModel);
        scrollPane.setViewportView(table1);
        return table1;
    }

    public void clearDataTable(){
        DefaultTableModel defaultTableModel = (DefaultTableModel)table1.getModel();
        while(defaultTableModel.getRowCount() > 0)
        {
            defaultTableModel.removeRow(0);
        }
    }

    public void setUpDataTable(List<Student> listData){

        clearDataTable();

        DefaultTableModel defaultTableModel = (DefaultTableModel) table1.getModel();

        for (int i = 0; i < listData.size(); i++) {
            String[] data = new String[7];

            data[0] = listData.get(i).getMSSV();
            data[1] = listData.get(i).getStudentName();
            data[2] = listData.get(i).getStudentSex();
            data[3] = listData.get(i).getStudentNationID();

            defaultTableModel.addRow(data);
        }
        table1.setModel(defaultTableModel);
        scrollPane.setViewportView(table1);
    }

    public void showMessage(String message){
        JOptionPane.showMessageDialog(panel1,message);
    }

    public String getClassName(){
        return comboBoxClass.getSelectedItem().toString();
    }

    public String getSubName() {
        return comboBoxSubject.getSelectedItem().toString();
    }

    public void addItemInComboBoxSubjects(String item){
        comboBoxSubject.addItem(item);
    }

    public void cleanItemInComboBoxSubjects(){
        comboBoxSubject.removeAllItems();
    }

    public void xemButtonListener(ActionListener listener){
        xemButton.addActionListener(listener);
    }

    public void addButtonListener(ActionListener listener){
        addButton.addActionListener(listener);
    }

    public void deleteButtonListener(ActionListener listener){
        deleteButton.addActionListener(listener);
    }
}
