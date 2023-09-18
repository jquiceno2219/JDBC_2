package org.example.mapping.mappers;

import org.example.domain.models.Subject;
import org.example.mapping.dtos.SubjectDTO;

import java.util.List;
import java.util.stream.Collectors;

public class SubjectMapper {
    public static SubjectDTO mapFrom(Subject source) {
        return new SubjectDTO(source.getId(), source.getName(), source.getTeacher());
    }

    public static Subject mapFrom(SubjectDTO source) {
        return new Subject(source.id(), source.name(), source.teacher());
    }

    public static List<SubjectDTO> mapFrom(List<Subject> source){
        return source.parallelStream()
                .map(SubjectMapper::mapFrom)
                .collect(Collectors.toList());
    }

    public static List<Subject> mapFromDto(List<SubjectDTO> source){
        return source.parallelStream().map(SubjectMapper::mapFrom).collect(Collectors.toList());
    }
}
