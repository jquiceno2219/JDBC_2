package org.example.mapping.dtos;

import org.example.domain.models.*;

public record GradesDTO(long id, Student student, Subject subject, double grade, String term) {
}