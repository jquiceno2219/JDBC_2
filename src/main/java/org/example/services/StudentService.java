package org.example.services;

import org.example.mapping.dtos.StudentDTO;

import java.util.List;

public interface StudentService {

    List<StudentDTO> list();
    StudentDTO byId(Long id);
    void save(StudentDTO t);
    void delete(Long id);

}