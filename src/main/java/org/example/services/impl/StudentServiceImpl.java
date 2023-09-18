package org.example.services.impl;

import org.example.mapping.dtos.StudentDTO;
import org.example.repository.impl.StudentRepositoryImpl;
import org.example.services.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {

    StudentRepositoryImpl repo = new StudentRepositoryImpl();
    @Override
    public List<StudentDTO> list() {
        return repo.list();
    }

    @Override
    public StudentDTO byId(Long id) {
        return repo.byId(id);
    }

    @Override
    public void save(StudentDTO student) {
        repo.save(student);
    }

    @Override
    public void delete(Long id) {
        repo.delete(id);
    }
}