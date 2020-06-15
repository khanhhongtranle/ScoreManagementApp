package Views.Teacher;

import Model.Entities.ScoreSheet;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class QuanLyPhucKhaoView extends JFrame {
    private JPanel panel1;
    private JTextField fromField;
    private JTextField toField;
    private JButton createButton;
    private JScrollPane scrollPane;
    private JTable table1;
    private JButton xemButton;

    public QuanLyPhucKhaoView(){
        this.setTitle("Quản lý đơn phúc khảo");
        panel1.setBorder(BorderFactory.createLineBorder(Color.blue));
        panel1.setPreferredSize(new Dimension(700, 400));

        //Table
        table1 = createTable();

        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
    }

    public JTable createTable(){
        DefaultTableModel defaultTableModel = (DefaultTableModel) table1.getModel();
        String columnName[] = {"MSSV", "Họ tên", "Mã môn học", "Cột điểm phúc khảo", "Điểm số hiện tại", "Điểm số mong muốn", "Lý do", "Tình trạng"};
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

    public void setUpDataTable(List<ScoreSheet> listData){

        clearDataTable();

        DefaultTableModel defaultTableModel = (DefaultTableModel) table1.getModel();

        for (int i = 0; i < listData.size(); i++) {
            String[] data = new String[7];

            //data[0] = listData.get(i).getKey().getMSSV();
            //data[1] = listData.get(i).getStudentName();
            data[0] = String.valueOf(listData.get(i).getMidTermScore());
            data[1] = String.valueOf(listData.get(i).getFinalTermScore());
            data[2] = String.valueOf(listData.get(i).getAnotherScore());
            data[3] = String.valueOf(listData.get(i).getFinalGrade());
            if (listData.get(i).getFinalGrade() >= 5){
                data[4] = "Đậu";
            }
            else{
                data[4] = "Rớt";
            }

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
