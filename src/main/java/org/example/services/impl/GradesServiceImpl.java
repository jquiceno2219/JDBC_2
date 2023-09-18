package org.example.services.impl;

import org.example.mapping.dtos.GradesDTO;
import org.example.repository.impl.GradesRepositoryImpl;
import org.example.services.GradesService;

import java.util.List;

public class GradesServiceImpl implements GradesService {
    GradesRepositoryImpl repo = new GradesRepositoryImpl();
    @Override
    public List<GradesDTO> list() {
        return repo.list();
    }

    @Override
    public GradesDTO byId(Long id) {
        return repo.byId(id);
    }

    @Override
    public void save(GradesDTO grades) {
        repo.save(grades);
    }

    @Override
    public void delete(Long id) {
        repo.delete(id);
    }
}