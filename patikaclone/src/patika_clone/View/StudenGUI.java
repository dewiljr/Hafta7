package patika_clone.View;

import patika_clone.Helper.Config;
import patika_clone.Helper.Helper;
import patika_clone.Model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StudenGUI extends JFrame {
    private JPanel wrapper;
    private JTabbedPane pnl_student;
    private JPanel panel_patika;
    private JScrollPane scroll_patika;
    private JTable table_stu_patika;
    private JPanel panel_your_patika;
    private JScrollPane scroll_your_patika;
    private JTable tbl_stu_ownpatika;
    private JTextField fld_patika_add;
    private JButton btn_patika_add;
    private JPanel pnl_student_courses;
    private JTable tbl_student_courses;
    private JTextField fld_stundet_courseadd;
    private JButton btn_student_courseadd;
    private DefaultTableModel model_stu_patika;
    private Object[] row_stu_patika;
    private int id;

    private DefaultTableModel model_stu_ownpatika;
    private Object[] row_stu_ownpatika;
    private DefaultTableModel model_student_courses;

    private Object[] row_student_coureses;
    private int patika_id;


    public StudenGUI(int user_id) {
        add(wrapper);
        setSize(900, 500);
        setLocation(Helper.screenCenter("x", getSize()), Helper.screenCenter("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);
        setResizable(false);

        model_stu_patika = new DefaultTableModel();
        Object[] col_stu_patika = {"ID", "Patika"};
        model_stu_patika.setColumnIdentifiers(col_stu_patika);
        row_stu_patika = new Object[col_stu_patika.length];
        table_stu_patika.setModel(model_stu_patika);
        table_stu_patika.getTableHeader().setReorderingAllowed(false);
        table_stu_patika.getColumnModel().getColumn(0).setMaxWidth(60);
        loadPatika();

        model_stu_ownpatika = new DefaultTableModel();
        Object[] col_stu_ownpatika = {"ID", "Patika"};
        model_stu_ownpatika.setColumnIdentifiers(col_stu_patika);
        row_stu_ownpatika = new Object[col_stu_ownpatika.length];
        tbl_stu_ownpatika.setModel(model_stu_ownpatika);
        tbl_stu_ownpatika.getTableHeader().setReorderingAllowed(false);
        tbl_stu_ownpatika.getColumnModel().getColumn(0).setMaxWidth(60);
        loadPatikaOwn();

        model_student_courses = new DefaultTableModel();
        Object[] col_stu_courese = {"ID", "Patika ID", "Course Name", "Patika Name"};
        model_student_courses.setColumnIdentifiers(col_stu_courese);
        row_student_coureses = new Object[col_stu_courese.length];
        tbl_student_courses.setModel(model_student_courses);
        tbl_student_courses.getTableHeader().setReorderingAllowed(false);
        tbl_student_courses.getColumnModel().getColumn(0).setMaxWidth(60);
        loadPatikaCourse();


        btn_patika_add.addActionListener(e -> {
            if (fld_patika_add.getText().isEmpty()) {
                Helper.showMsg("fill");
            } else {
                int ID = Integer.parseInt(fld_patika_add.getText());
                String name = String.valueOf(Patika.getFetchByPatikaName(ID));
                if (Student.add(ID, name)) {
                    Helper.showMsg("done");
                    loadPatikaOwn();
                    fld_patika_add.setText(null);
                }
                StudentCourse.addCourse(StudentCourse.getFetchInt(ID));
                loadPatikaCourse();

            }


        });
    }


    private void loadPatikaCourse() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_student_courses.getModel();
        clearModel.setRowCount(0);

        ArrayList<Course> courses = StudentCourse.getListCourse();

        for (Course path : courses) {
            int i = 0;
            row_student_coureses[i++] = path.getId();
            row_student_coureses[i++] = path.getPatika_id();
            row_student_coureses[i++] = path.getName();
            row_student_coureses[i++] = path.getLanguage();
            model_student_courses.addRow(row_student_coureses);
        }
    }


    private void loadPatikaOwn() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_stu_ownpatika.getModel();
        clearModel.setRowCount(0);
        ArrayList<Patika> patika = Student.getListByPatika();
        if (patika.isEmpty()) {
            Helper.showMsg("fill");
        } else {
            for (Patika path : patika) {
                int i = 0;
                row_stu_ownpatika[i++] = path.getId();
                row_stu_ownpatika[i++] = path.getName();
                model_stu_ownpatika.addRow(row_stu_ownpatika);

            }
        }

    }

    public void loadPatika() {
        DefaultTableModel clearModel = (DefaultTableModel) table_stu_patika.getModel();
        clearModel.setRowCount(0);
        ArrayList<Patika> patika = Patika.getListByUser();
        if (patika.isEmpty()) {
            Helper.showMsg("There is no path to choose :( Please contact with Patika to ask adding new courses :(");
        } else {
            for (Patika path : patika) {
                int i = 0;
                row_stu_patika[i++] = path.getId();
                row_stu_patika[i++] = path.getName();
                model_stu_patika.addRow(row_stu_patika);

            }
        }

    }
}
