package org.example.mapping.mappers;

import org.example.domain.models.Student;
import org.example.mapping.dtos.StudentDTO;

import java.util.List;
import java.util.stream.Collectors;

public class StudentMapper {
    public static StudentDTO mapFrom(Student source){
        return new StudentDTO(source.getId(), source.getName(), source.getEmail(),
                source.getSemester(), source.getCareer());
    }

    public static Student mapFrom(StudentDTO source){
        return new Student(source.id(), source.name(), source.email(), source.semester(),source.career());
    }

    public static List<StudentDTO> mapFrom(List<Student> source){
        return source.parallelStream()
                .map(StudentMapper::mapFrom)
                .collect(Collectors.toList());
    }

    public static List<Student> mapFromDto(List<StudentDTO> source){
        return source.parallelStream().map(StudentMapper::mapFrom).collect(Collectors.toList());
    }
}
