package org.example.services;

import org.example.mapping.dtos.TeacherDTO;

import java.util.List;

public interface TeacherService {

    List<TeacherDTO> list();
    TeacherDTO byId(Long id);
    void save(TeacherDTO t);
    void delete(Long id);

}