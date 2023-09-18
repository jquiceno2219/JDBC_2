package org.example.mapping.mappers;

import org.example.domain.models.Teacher;
import org.example.mapping.dtos.TeacherDTO;

import java.util.List;
import java.util.stream.Collectors;

public class TeacherMapper {

    public static TeacherDTO mapFrom(Teacher source) {
        return new TeacherDTO(source.getId(), source.getName(), source.getEmail());
    }

    public static Teacher mapFrom(TeacherDTO source) {
        return new Teacher(source.id(), source.name(), source.email());
    }

    public static List<TeacherDTO> mapFrom(List<Teacher> source) {
        return source.parallelStream()
                .map(TeacherMapper::mapFrom)
                .collect(Collectors.toList());

    }

    public static List<Teacher> mapFromDto(List<TeacherDTO> source){
        return source.parallelStream().map(TeacherMapper::mapFrom).collect(Collectors.toList());
    }

}