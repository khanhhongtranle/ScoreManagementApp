package Views.Teacher;

import Model.Entities.Student;
import Model.ManageClass;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class QuanLySinhVienView extends JFrame {
    private JPanel panel1;
    private JButton importButton;
    private JComboBox comboBox;
    private JButton confirmButton;
    private JButton addStudentButton;
    private JTable table1;
    private JScrollPane scrollPane;

    public QuanLySinhVienView(){
        this.setTitle("Quản lý sinh viên");
        panel1.setBorder(BorderFactory.createLineBorder(Color.blue));
        panel1.setPreferredSize(new Dimension(400, 500));

        //ComboBox
        ManageClass manageClass = new ManageClass();
        ArrayList<String> classes = (ArrayList<String>) manageClass.getAllOfClass();
        for (String str : classes) {
            comboBox.addItem(str);
        }

        //Table
        table1 = createTable();

        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
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

    public void importListener(ActionListener actionListener){
        importButton.addActionListener(actionListener);
    }

    public void confirmListener(ActionListener actionListener){
        confirmButton.addActionListener(actionListener);
    }

    public String getStringInComBox(){
        return comboBox.getSelectedItem().toString();
    }
}
