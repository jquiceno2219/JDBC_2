package org.example.services;

import org.example.mapping.dtos.GradesDTO;

import java.util.List;

public interface GradesService {

    List<GradesDTO> list();
    GradesDTO byId(Long id);
    void save(GradesDTO t);
    void delete(Long id);

}