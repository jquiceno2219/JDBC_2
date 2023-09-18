package org.example.domain.models;

import lombok.Getter;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Student {
    private long id;
    private String name;
    private String email;
    private String semester;
    private String career;
}