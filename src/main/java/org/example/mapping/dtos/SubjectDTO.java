package org.example.mapping.dtos;

import org.example.domain.models.Teacher;

public record SubjectDTO(long id, String name, Teacher teacher) {
}
