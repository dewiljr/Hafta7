package patika_clone.Model;

import patika_clone.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class StudentCourse {
    private int course_id;
    private int patika_id;
    private String course_name;
    private String patika_name;
    private User course;

    public StudentCourse(int course_id, int patika_id, String course_name, String patika_name) {
        this.course_id = course_id;
        this.patika_id = patika_id;
        this.course_name = course_name;
        this.patika_name = patika_name;
        this.course = User.getFetchCourse(patika_id).getEducator();
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public int getPatika_id() {
        return patika_id;
    }

    public void setPatika_id(int patika_id) {
        this.patika_id = patika_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getPatika_name() {
        return patika_name;
    }

    public void setPatika_name(String patika_name) {
        this.patika_name = patika_name;
    }

    public static ArrayList<Course> getListCourse() {
        ArrayList<Course> courseList = new ArrayList<>();
        Course obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM patika_course" );

            while (rs.next()) {
                int course_id = rs.getInt("course_id");
                int patikaID = rs.getInt("patika_id");
                String name = rs.getString("course_name");
                String patika_name = rs.getString("patika_name");

                obj = new Course(course_id, patikaID, name, patika_name);
                courseList.add(obj);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return courseList;
    }

    public static boolean add(int course_id, int patika_id, String course_name, String patika_name) {

        String query = "INSERT INTO student (course_id,patika_id,course_name,patika_name) VALUES (?,?,?,?)";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, course_id);
            pr.setInt(2, patika_id);
            pr.setString(3, course_name);
            pr.setString(4, patika_name);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return true;
    }
    public static boolean addCourse(Course coures) {

        String query = "INSERT INTO patika_course (course_id,patika_id,course_name,patika_name) VALUES (?,?,?,?)";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, coures.getId());
            pr.setInt(2, coures.getPatika_id());
            pr.setString(3, coures.getName());
            pr.setString(4, coures.getLanguage());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return true;
    }

    public static Course getFetchInt(int patika_id){
        Course obj = null;
        String sql = "SELECT*FROM course WHERE patika_id = ?";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(sql);
            pr.setInt(1,patika_id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                obj = new Course(rs.getInt("id"), rs.getInt("patika_id"),rs.getString("name"),rs.getString("language") );

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  obj;
    }


}
