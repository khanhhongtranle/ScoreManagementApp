package Views.Student;

import Model.Entities.Re_Examination;
import Model.Entities.RequestsForRe;
import Model.ManageReExamination;
import Model.ManageRequestForRe;
import Model.ManageStudent;
import Model.ManageSubject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class XemDonPhucKhaoView extends JFrame{
    private JPanel panel1;
    private JComboBox comboBox1;
    private JButton xemButton;
    private JScrollPane scrollPane;
    private JTable table1;

    public XemDonPhucKhaoView(){
        this.setTitle("Kết quả phúc khảo");
        panel1.setBorder(BorderFactory.createLineBorder(Color.blue));
        panel1.setPreferredSize(new Dimension(700, 400));

        //comboBox1
        ManageReExamination manageReExamination = new ManageReExamination();
        List<Re_Examination> listAllReExam = manageReExamination.getAllRecord();
        for (Re_Examination re : listAllReExam){
            comboBox1.addItem("Đợt " + re.getKey().getBeginDate() + " - " + re.getKey().getEndingDate());
        }
        comboBox1.addItem("Tất cả");

        //table1
        table1 = createTable();

        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
    }

    public JTable createTable(){
        DefaultTableModel defaultTableModel = (DefaultTableModel) table1.getModel();
        String columnName[] = {"Mã môn học", "Tên môn học", "Cột điểm phúc khảo", "Điểm số mong muốn", "Lý do", "Tình trạng"};
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

        ManageSubject manageSubject = new ManageSubject();

        for (int i = 0; i < listData.size(); i++) {
            String[] data = new String[6];

            data[0] = listData.get(i).getKey().getSubNo();
            data[1] = manageSubject.getSubjectAtSubNo(listData.get(i).getKey().getSubNo()).getSubName();
            data[2] = listData.get(i).getScoreCol();
            data[3] = String.valueOf(listData.get(i).getNewScore());
            data[4] = listData.get(i).getTheReason();
            data[5] = listData.get(i).getStatus();

            defaultTableModel.addRow(data);
        }
        table1.setModel(defaultTableModel);
        scrollPane.setViewportView(table1);
    }

    public void xemListener (ActionListener listener){
        xemButton.addActionListener(listener);
    }

    public String getItemStringInComboBox(){
        return comboBox1.getSelectedItem().toString();
    }
}
