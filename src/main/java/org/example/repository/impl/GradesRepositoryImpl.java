package org.example.repository.impl;

import org.example.conexion.ConexionBD;
import org.example.domain.models.Grades;
import org.example.domain.models.Student;
import org.example.domain.models.Subject;
import org.example.domain.models.Teacher;
import org.example.mapping.dtos.GradesDTO;
import org.example.mapping.mappers.GradesMapper;
import org.example.repository.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GradesRepositoryImpl implements Repository<GradesDTO> {
    /*public class Grades {
    private long id;
    private Student student;
    private Subject subject;
    private double grade;
    private String term;
}
*/

    private Connection getConnection() throws SQLException {
        return ConexionBD.getInstance();
    }

    private Grades createGrades(ResultSet resultSet) throws
            SQLException {
        Grades grades = new Grades();
        grades.setId(resultSet.getLong("id"));
            Student student = new Student();
            student.setId(resultSet.getLong("student"));
            student.setName(resultSet.getString("name"));
            student.setEmail(resultSet.getString("email"));
            student.setSemester(resultSet.getString("semester"));
            student.setCareer(resultSet.getString("career"));
        grades.setStudent(student);
                Subject subject = new Subject();
                subject.setId(resultSet.getLong("id"));
                subject.setName(resultSet.getString("name"));
        grades.setSubject(subject);

        grades.setGrade(resultSet.getDouble("grade"));
        grades.setTerm(resultSet.getString("term"));
        return grades;
    }

    @Override
    public List<GradesDTO> list() {
        List<Grades> gradesList = new ArrayList<>();
        try (Statement statement = getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT students.name as 'Student', subject.name as 'Subject'," +
                     " g.grade, g.term FROM `grades` as g INNER JOIN subject ON g.subject_id=subject.id" +
                     " INNER JOIN students ON g.student_id=students.id")) {
            while (resultSet.next()) {
                Grades grades = createGrades(resultSet);
                gradesList.add(grades);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return GradesMapper.mapFrom(gradesList);
    }

    @Override
    public GradesDTO byId(Long id) {
        Grades grades = null;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(
                "SELECT students.name as 'Student', subject.name as 'Subject'," +
                " g.grade, g.term FROM `grades` as g INNER JOIN subject ON g.subject_id=subject.id" +
                " INNER JOIN students ON g.student_id=students.id WHERE g.id=?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                grades = createGrades(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return GradesMapper.mapFrom(grades);
    }

    @Override
    public void save(GradesDTO grades) {
        String sql;
        if (grades.id() > 0) {
            sql = "UPDATE grades SET student_id=?, subject_id=?, grade=?, term=? WHERE id=?";
        } else {
            sql = "INSERT INTO grades(student_id, subject_id, grade, term) VALUES(?, ?, ?, ?)";
        }
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setLong(1, grades.id());;
            stmt.setLong(2, grades.subject().getId());

            if (grades.id() > 0) {
                stmt.setLong(3, grades.id());
            }

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement stmt = getConnection().prepareStatement("DELETE FROM grades WHERE id = ?")) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
