package org.example.repository.impl;

import org.example.conexion.ConexionBD;
import org.example.domain.models.Teacher;
import org.example.mapping.dtos.TeacherDTO;
import org.example.mapping.mappers.TeacherMapper;
import org.example.repository.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class TeacherRepositoryImpl implements Repository<TeacherDTO> {

    private Connection getConnection() throws SQLException {
        return ConexionBD.getInstance();
    }


    private Teacher createTeacher(ResultSet resultSet) throws
            SQLException {
        Teacher teachers = new Teacher();
        teachers.setId(resultSet.getLong("id"));
        teachers.setName(resultSet.getString("name"));
        teachers.setEmail(resultSet.getString("email"));
        return teachers;
    }

    @Override
    public List<TeacherDTO> list() {
        List<Teacher> teacherList = new ArrayList<>();
        try (Statement statement = getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * from teachers")) {
    while (resultSet.next()) {
            Teacher teacher = createTeacher(resultSet);
            teacherList.add(teacher);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return TeacherMapper.mapFrom(teacherList);
}
@Override
    public TeacherDTO byId(Long id) {
        Teacher teacher = null;
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement("SELECT * FROM teachers WHERE id =?")) {
                preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            teacher = createTeacher(resultSet);
        }
        resultSet.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return TeacherMapper.mapFrom(teacher);
}

    @Override
    public void save(TeacherDTO teacher) {
        String sql;
        //no necesita la comparación a null ya que los primitivos no pueden ser null
        if (teacher.id()>0) {
            sql = "UPDATE teachers SET name=?, email=? WHERE id=?";
        } else {
            sql = "INSERT INTO teachers(name, email) VALUES(?, ?)";
        }
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, teacher.name());
            stmt.setString(2, teacher.email());

            if (teacher.id() > 0) {
                stmt.setLong(3, teacher.id());
            }

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement stmt = getConnection().prepareStatement("DELETE FROM teachers WHERE id = ?")){
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

}
