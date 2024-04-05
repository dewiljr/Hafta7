package com.patikaClone;

import javax.swing.*;
import java.awt.*;

public class KullanıcıGiris extends JFrame {
    private JPanel panel1;
    private JPanel wrapper;
    private JPanel wrapTop;
    private JPanel wrapBot;
    private JLabel TopTopLine;
    private JLabel TopBotLine;
    private JLabel BotTopLine;
    private JTextField fld_username;
    private JLabel BotMidLine;
    private JPasswordField fld_password;
    private JButton btn_login;
    private JButton btn_forgetpass;
    private JButton btn_signIn;

    public KullanıcıGiris() {
        this.add(wrapper);
        setSize(600, 400);
        setTitle("Kullanıcı Girişi");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width)/2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height)/2;
        setLocation(x,y);
        setVisible(true);
        btn_login.addActionListener(e -> {
            if (fld_username.getText().length() == 0 || fld_password.getText().length() == 0){
                JOptionPane.showMessageDialog(null,"Tüm Alanları Doldurunuz!","Hata", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        btn_forgetpass.addActionListener(e -> {

        });
        btn_signIn.addActionListener(e -> {

        });
    }
}
