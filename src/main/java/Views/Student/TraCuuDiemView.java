package Views.Student;

import Model.Entities.ScoreSheet;
import Model.ManageStudentsInClass;
import Model.ManageSubject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TraCuuDiemView extends JFrame{
    private JPanel panel1;
    private JComboBox comboBox1;
    private JButton xemButton;
    private JTable table1;
    private JScrollPane scrollPane;

    public TraCuuDiemView(StudentManagerView fatherView){
        this.setTitle("Tra cứu điểm");
        panel1.setBorder(BorderFactory.createLineBorder(Color.blue));
        //panel1.setLayout(new BorderLayout(1, 1));
        panel1.setPreferredSize(new Dimension(400, 300));

        //comboBoxSubjects
        ManageStudentsInClass manageStudentsInClass = new ManageStudentsInClass();
        String MSSV = fatherView.getMSSV();
        List<String> listSubNo = manageStudentsInClass.getListSubjectsOfAStudent(MSSV);
        ManageSubject manageSubject = new ManageSubject();
        //List<String> listSubName = new ArrayList<>();
        for (String s : listSubNo){
            String s_t = manageSubject.getSubjectAtSubNo(s).getSubName();
            //listSubName.add(s_t);
            comboBox1.addItem(s_t);
        }

        //Table
        table1 = createTable();

        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
    }

    public JTable createTable(){
        DefaultTableModel defaultTableModel = (DefaultTableModel) table1.getModel();
        String columnName[] = {"Điểm GK", "Điểm CK", "Điểm khác", "Điểm tổng", "Ghi chú"};
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

    public void xemListener(ActionListener listener){
        xemButton.addActionListener(listener);
    }

    public void showMessage(String message){
        JOptionPane.showMessageDialog(panel1,message);
    }

    public String getSubName(){
        return comboBox1.getSelectedItem().toString();
    }
}
