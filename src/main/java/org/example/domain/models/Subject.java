package org.example.domain.models;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Subject {
    private long id;
    private String name;
    private Teacher teacher;
}