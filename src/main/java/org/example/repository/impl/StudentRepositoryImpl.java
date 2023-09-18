package org.example.repository.impl;

import org.example.conexion.ConexionBD;
import org.example.domain.models.Student;
import org.example.mapping.dtos.StudentDTO;
import org.example.mapping.mappers.StudentMapper;
import org.example.repository.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static java.sql.DriverManager.getConnection;

/*
public class Student {
    private long id;
    private String name;
    private String email;
    private String semester;
    private String career;
}
 */

public class StudentRepositoryImpl implements Repository<StudentDTO> {
    private Connection getConnection() throws SQLException {
        return ConexionBD.getInstance();
    }

    private Student createStudent(ResultSet resultSet) throws
            SQLException {
        Student student = new Student();
        student.setId(resultSet.getLong("id"));
        student.setName(resultSet.getString("name"));
        student.setEmail(resultSet.getString("email"));
        student.setSemester(resultSet.getString("semester"));
        student.setCareer(resultSet.getString("career"));
        return student;
    }

    @Override
    public List<StudentDTO> list() {
        List<Student> studentList = new ArrayList<>();
        try (Statement statement = getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * from students")) {
            while (resultSet.next()) {
                Student student = createStudent(resultSet);
                studentList.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return StudentMapper.mapFrom(studentList);
    }

    @Override
    public StudentDTO byId(Long id) {
        Student student = null;
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement("SELECT * FROM students WHERE id =?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                student = createStudent(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return StudentMapper.mapFrom(student);
    }

    @Override
    public void save(StudentDTO student) {
        String sql;
        if ( student.id() > 0) {
            sql = "UPDATE students SET name=?, email=?, semester=?, career=? WHERE id=?";
        } else {
            sql = "INSERT INTO student(nombre, email, semester, career) VALUES(?, ?, ?, ?)";
        }
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, student.name());
            stmt.setString(2, student.email());
            stmt.setString(3, student.semester());
            stmt.setString(4, student.career());

            if (student.id() > 0) {
                stmt.setLong(5, student.id());
            }

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement stmt = getConnection().prepareStatement("DELETE FROM students WHERE id = ?")) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
