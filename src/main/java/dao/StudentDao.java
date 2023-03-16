package dao;

import connection.MyConnection;
import model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StudentDao {
    public static List<Student> getAll() {
        List<Student> studentList = new ArrayList<>();
        try {
            Connection conn = MyConnection.getConnection();
            final String sql = "SELECT * FROM students";
            Statement state = conn.createStatement();
            ResultSet rSet = state.executeQuery(sql);
            while (rSet.next()) {
                Student s = new Student();
                s.setId(rSet.getString("id"));
                s.setFull_name(rSet.getString("full_name"));
                s.setGender(rSet.getInt("gender"));
                s.setDate_of_birth(rSet.getString("date_of_birth"));
                s.setAddress(rSet.getString("address"));
                s.setPhone(rSet.getString("phone"));
                s.setEmail(rSet.getString("email"));
                s.setPoint_GPA(rSet.getFloat("point_GPA"));
                studentList.add(s);
            }
            rSet.close();
            state.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentList;
    }
    public static void InsertStudent(Student s) {
        try {
            Connection conn = MyConnection.getConnection();
            String sql = String.format("INSERT IGNORE INTO students VALUES ('%s','%s','%d','%s','%s','%s','%s','%f')",
                    s.getId(), s.getFull_name(), s.getGender(), s.getDate_of_birth(), s.getAddress(), s.getPhone(), s.getEmail(), s.getPoint_GPA()
            );
            Statement state = conn.createStatement();
            int rSet = state.executeUpdate(sql);

            String result = (rSet == 0) ? "Thêm không thành công!" : "Thêm thành công";
            System.out.println(result);

            state.close();
            conn.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public Student getById(String id) {
        Student s = null;
        try {
            Connection conn = MyConnection.getConnection();
            final String sql =  String.format("SELECT * FROM students WHERE id = '%s'", id);
            Statement state = conn.createStatement();
            ResultSet rSet = state.executeQuery(sql);
            if (rSet.next()) {
                s = new Student();
                s.setId(rSet.getString("id"));
                s.setFull_name(rSet.getString("full_name"));
                s.setGender(rSet.getInt("gender"));
                s.setDate_of_birth(rSet.getString("date_of_birth"));
                s.setAddress(rSet.getString("address"));
                s.setPhone(rSet.getString("phone"));
                s.setEmail(rSet.getString("email"));
                s.setPoint_GPA(rSet.getFloat("point_GPA"));
            }
            rSet.close();
            state.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }
    public void delete(String id) {
        Student student = getById(id);
        if (student == null) {
            System.out.println("Mã sinh viên " + id + " không tồn tại!");
        }
        try {
            Connection conn = MyConnection.getConnection();
            final String sql = String.format("DELETE FROM `students` WHERE id = '%s'", id);
            Statement stmt = conn.createStatement();
            int resultSet = stmt.executeUpdate(sql);

            String result = (resultSet == 0) ? "không thể xóa sinh viên đó!" : "xóa sinh viên có mã " + id + " thành công!";
            System.out.println(result);

            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void update(String id, Student s) {
        try {
            Connection conn = MyConnection.getConnection();
            final String sql = String.format("UPDATE IGNORE students SET full_name ='%s',gender ='%d',date_of_birth ='%s',address='%s',phone = '%s',email = '%s', point_GPA='%f' WHERE id = '%s'",
                    s.getFull_name(),s.getGender(),s.getDate_of_birth(),s.getAddress(),s.getPhone(),s.getEmail(),s.getPoint_GPA(), id);
            System.out.println(sql);
            Statement state = conn.createStatement();
            int rs = state.executeUpdate(sql);
            String result = (rs == 0) ? "Cập nhật không thành công!" : "Cập nhật thành công!";
            System.out.println(result);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static List<Student> getAllGPADesc() {
        List<Student> studentList = new ArrayList<>();
        try {
            Connection conn = MyConnection.getConnection();
            final String sql = "SELECT * FROM students ORDER BY point_GPA ASC";
            Statement state = conn.createStatement();
            ResultSet rSet = state.executeQuery(sql);
            while (rSet.next()) {
                Student s = new Student();
                s.setId(rSet.getString("id"));
                s.setFull_name(rSet.getString("full_name"));
                s.setGender(rSet.getInt("gender"));
                s.setDate_of_birth(rSet.getString("date_of_birth"));
                s.setAddress(rSet.getString("address"));
                s.setPhone(rSet.getString("phone"));
                s.setEmail(rSet.getString("email"));
                s.setPoint_GPA(rSet.getFloat("point_GPA"));
                studentList.add(s);
            }
            rSet.close();
            state.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentList;
    }
    public static List<Student> getAllGPAHn() {
        List<Student> studentList = new ArrayList<>();
        try {

            Connection conn = MyConnection.getConnection();
            final String sql = "SELECT * FROM students WHERE address = 'Hà Nội' and point_GPA > 2.5 and gender = 0";
            Statement state = conn.createStatement();
            ResultSet rSet = state.executeQuery(sql);

            while (rSet.next()) {
                Student s = new Student();
                s.setId(rSet.getString("id"));
                s.setFull_name(rSet.getString("full_name"));
                s.setGender(rSet.getInt("gender"));
                s.setDate_of_birth(rSet.getString("date_of_birth"));
                s.setAddress(rSet.getString("address"));
                s.setPhone(rSet.getString("phone"));
                s.setEmail(rSet.getString("email"));
                s.setPoint_GPA(rSet.getFloat("point_GPA"));
                studentList.add(s);
            }
            rSet.close();
            state.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentList;
    }

    public static List<Student> sortFullName(List<Student> studentList) {
        return studentList.stream().sorted((o1, o2)->o1.getFull_name().compareTo(o2.getFull_name())).collect(Collectors.toList());
    }
}
