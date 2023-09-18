package org.example.services;

import org.example.mapping.dtos.SubjectDTO;

import java.util.List;

public interface SubjectService {

    List<SubjectDTO> list();
    SubjectDTO byId(Long id);
    void save(SubjectDTO subject);

    void delete(Long id);

}