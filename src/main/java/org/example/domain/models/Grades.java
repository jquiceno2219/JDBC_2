package org.example.domain.models;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Grades {
    private long id;
    private Student student;
    private Subject subject;
    private double grade;
    private String term;
}