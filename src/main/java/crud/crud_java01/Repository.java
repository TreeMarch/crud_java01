package crud.crud_java01;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class Repository {
    private final String MYSQL_CONNECTION_STRING = "jdbc:mysql://localhost:3306/crud_java01";
    private final String MYSQL_USERNAME = "root";
    private final String MYSQL_PASSWORD = "";

    public ArrayList<Student> findAll(){
        ArrayList<Student> students = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(MYSQL_CONNECTION_STRING, MYSQL_USERNAME, MYSQL_PASSWORD);
            String prSql = "SELECT * FROM  student_information";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(prSql);
            while (resultSet.next()) {
                String code = resultSet.getString("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                Student obj = new Student();
                obj.setId(code);
                obj.setName(name);
                obj.setEmail(email);
                students.add(obj);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public Object save(Student student){
        try {
            Connection connection = DriverManager.getConnection(MYSQL_CONNECTION_STRING,MYSQL_USERNAME,MYSQL_PASSWORD);
            String sql = "INSERT INTO student_information (id, name, email) VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, student.getId());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setString(3, student.getEmail());
            preparedStatement.executeUpdate();
            connection.close();
            JOptionPane.showMessageDialog(null,"Save"); // hiển thị thông báo
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return student;
    }

    public void delete(Student student){
        try {
            Connection connection = DriverManager.getConnection(MYSQL_CONNECTION_STRING,MYSQL_USERNAME,MYSQL_PASSWORD);
            String sql = "DELETE FROM student_information WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, student.getId());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Object update(Student student){
        try{
            Connection connection = DriverManager.getConnection(MYSQL_CONNECTION_STRING,MYSQL_USERNAME,MYSQL_PASSWORD);
            String sql = "UPDATE student_information SET name = ?, email = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getEmail());
            preparedStatement.setString(3, student.getId());
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null,"Update");
            connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e);
        }
        return student;
    }

}
