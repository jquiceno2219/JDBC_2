package org.example.services.impl;

import org.example.mapping.dtos.TeacherDTO;
import org.example.repository.impl.TeacherRepositoryImpl;
import org.example.services.TeacherService;

import java.util.List;

public class TeacherServiceImpl implements TeacherService {
    TeacherRepositoryImpl repo =  new TeacherRepositoryImpl();
    @Override
    public List<TeacherDTO> list() {
        return repo.list();
    }

    @Override
    public TeacherDTO byId(Long id) {
        return repo.byId(id);
    }

    @Override
    public void save(TeacherDTO teacher) {
        repo.save(teacher);
    }

    @Override
    public void delete(Long id) {
        repo.delete(id);
    }
}