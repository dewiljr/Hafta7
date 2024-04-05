package patika_clone.Model;

import patika_clone.Helper.DBConnector;
import patika_clone.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Student {

    private int id;

    private String name;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Student() {
    }

    public static ArrayList<Patika> getListByPatika() {
        ArrayList<Patika> patikaList = new ArrayList<>();
        String query = "SELECT * FROM student";
        Patika obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                obj = new Patika(rs.getInt("id"), rs.getString("name"));
                patikaList.add(obj);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return patikaList;
    }

    public static boolean add(int id, String name) {

        String query = "INSERT INTO student (id,name) VALUES (?,?)";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            pr.setString(2, name);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
