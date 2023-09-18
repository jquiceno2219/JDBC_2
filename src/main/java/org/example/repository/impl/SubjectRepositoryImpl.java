package org.example.repository.impl;

import org.example.conexion.ConexionBD;
import org.example.domain.models.Student;
import org.example.domain.models.Subject;
import org.example.domain.models.Teacher;
import org.example.mapping.dtos.SubjectDTO;
import org.example.mapping.dtos.TeacherDTO;
import org.example.mapping.mappers.SubjectMapper;
import org.example.repository.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectRepositoryImpl implements Repository<SubjectDTO> {

    private Connection getConnection() throws SQLException {
        return ConexionBD.getInstance();
    }

    private Subject createSubject(ResultSet rs) throws SQLException {
        Subject subject = new Subject();
    subject.setId(rs.getLong("id"));
    subject.setName(rs.getString("name"));
    Teacher teacher = new Teacher();
        teacher.setId(rs.getLong("id"));
        teacher.setName(rs.getString("name"));
        teacher.setEmail(rs.getString("email"));
    subject.setTeacher(teacher);

    return subject;
    }


    @Override
    public List<SubjectDTO> list() {
        List<Subject> subjectList = new ArrayList<>();
        try (Statement statement = getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT subject.name as 'Subject', teachers.name as 'Teacher', teachers.email " +
                             "FROM subject INNER JOIN teachers ON subject.teacher_id=teachers.id")) {
            while (resultSet.next()) {
                Subject subject = createSubject(resultSet);
                subjectList.add(subject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return SubjectMapper.mapFrom(subjectList);
    }

    @Override
    public SubjectDTO byId(Long id) {
        Subject subject = null;
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(
             "SELECT subject.name as 'Subject', teachers.name as 'Teacher', teachers.email " +
                        "FROM subject INNER JOIN teachers ON subject.teacher_id=teachers.id WHERE subject.id = ?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                subject = createSubject(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return SubjectMapper.mapFrom(subject);
    }

    @Override
    public void save(SubjectDTO subject) {
        String sql;
        if (subject.id() > 0) {
            sql = "UPDATE subject SET name=?, teacher_id = ? WHERE id=?";
        } else {
            sql = "INSERT INTO subject(name, teacher_id) VALUES(?, ?)";
        }
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, subject.name());

            if (subject.id() > 0) {
                stmt.setLong(2, subject.id());
            }

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement stmt = getConnection().prepareStatement("DELETE FROM subject WHERE id = ?")) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
