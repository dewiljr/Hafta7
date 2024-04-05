package com.patikaClone.View;

import com.patikaClone.Helper.Config;
import com.patikaClone.Helper.Helper;
import com.patikaClone.Model.Operator;
import com.patikaClone.Model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class OperatorGUI extends JFrame {

    private JPanel panel1;
    private JPanel wrapper;
    private JTabbedPane tab_operator;
    private JPanel pnl_user_list;
    private JLabel lbl_welcome;
    private JButton btn_logut;
    private JScrollPane scrl_user_list;
    private JTable tbl_user_list;
    private DefaultTableModel mdl_user_list;
    private Object[] row_user_list;

    private final Operator operator;

    public OperatorGUI(Operator operator){
        this.operator = operator;
        Helper.setLayout();
        add(wrapper);
        setSize(800,800);
        int x = Helper.ScreenCenterizer("x",getSize());
        int y = Helper.ScreenCenterizer("y",getSize());
        setLocation(x,y);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);
        lbl_welcome.setText("Hoşgeldiniz : " + operator.getName());
        //ModeUserList
        mdl_user_list = new DefaultTableModel();
        Object[] col_user_list = {"ID", "Ad Soyad", "Kullanıcı Adı", "Şifre", "Üyelik Tipi"};
        mdl_user_list.setColumnIdentifiers(col_user_list);
        for (User obj : User.getList()){
            Object[] row = new Object[col_user_list.length];
            row[0] = obj.getId();
            row[1] = obj.getName();
            row[2] = obj.getUsername();
            row[3] = obj.getPassword();
            row[4] = obj.getType();
            mdl_user_list.addRow(row);
        }

        tbl_user_list.setModel(mdl_user_list);
        tbl_user_list.getTableHeader().setReorderingAllowed(false);
    }
    public static void main(String[] args){
        Operator op = new Operator();
        op.setId(1);
        op.setName("Mustafa Erdi REYHAN");
        op.setUsername("merdi");
        op.setPassword("1234");
        op.setType("Operator");
        OperatorGUI opGui = new OperatorGUI(op);
    }
}
