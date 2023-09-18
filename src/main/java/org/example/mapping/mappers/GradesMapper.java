package org.example.mapping.mappers;

import org.example.domain.models.Grades;
import org.example.mapping.dtos.GradesDTO;

import java.util.List;
import java.util.stream.Collectors;

public class GradesMapper {
    public static GradesDTO mapFrom(Grades source){
        return new GradesDTO(source.getId(), source.getStudent(),
                source.getSubject(), source.getGrade(), source.getTerm());
    }

    public static Grades mapFrom(GradesDTO source){
        return new Grades(source.id(), source.student(), source.subject(), source.grade(), source.term());
    }
    public static List<GradesDTO> mapFrom(List<Grades> source){
        return source.parallelStream()
                .map(GradesMapper::mapFrom)
                .collect(Collectors.toList());
    }
    public static List<Grades> mapFromDto(List<GradesDTO> source){
        return source.parallelStream()
                .map(GradesMapper::mapFrom)
                .collect(Collectors.toList());
    }
}
