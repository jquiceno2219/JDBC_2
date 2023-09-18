package org.example.domain.models;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Teacher {
    private long id;
    private String name;
    private String email;
}