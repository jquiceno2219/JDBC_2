package org.example.services.impl;

import org.example.mapping.dtos.SubjectDTO;
import org.example.repository.impl.SubjectRepositoryImpl;
import org.example.services.SubjectService;

import java.util.List;

public class SubjectServiceImpl implements SubjectService {
    SubjectRepositoryImpl repo = new SubjectRepositoryImpl();

    @Override
    public List<SubjectDTO> list() {
        return repo.list();
    }

    @Override
    public SubjectDTO byId(Long id) {
        return repo.byId(id);
    }

    @Override
    public void save(SubjectDTO subject) {
        repo.save(subject);
    }


    @Override
    public void delete(Long id) {
        repo.delete(id);
    }
}