package dao;

import connection.MyConnection;
import model.Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {
    public static List<Student> getAll() {
        List<Student> studentList = new ArrayList<>();
        try {
            Connection con = MyConnection.getConnection();
            final String sql = "SELECT * FROM students";
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Student s = new Student();
                s.setId(resultSet.getString("id"));
                s.setFull_name(resultSet.getString("full_name"));
                s.setGender(resultSet.getInt("gender"));
                s.setDate_of_birth(resultSet.getString("date_of_birth"));
                s.setAddress(resultSet.getString("address"));
                s.setPhone(resultSet.getString("phone"));
                s.setEmail(resultSet.getString("email"));
                s.setPoint_GPA(resultSet.getDouble("point_GPA"));
                studentList.add(s);
            }
            resultSet.close();
            statement.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentList;
    }

    public static void InsertStud(Student s) {
        try {
            Connection con = MyConnection.getConnection();
            String sql = String.format("INSERT INTO students VALUES ('%s','%s','%d','%s','%s','%s','s','%f')",
                    s.getId(), s.getFull_name(), s.getGender(), s.getDate_of_birth(), s.getAddress(), s.getPhone(), s.getEmail(), s.getPoint_GPA()
            );
            Statement statement = con.createStatement();
            int resultSet = statement.executeUpdate(sql);

            if (resultSet == 0) {
                System.out.println("Thêm không thành công!");
            } else {
                System.out.println("Thêm thành công!");
            }
            statement.close();
            con.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
