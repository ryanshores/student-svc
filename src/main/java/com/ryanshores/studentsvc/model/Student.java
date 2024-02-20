package com.ryanshores.studentsvc.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue()
    private Long id;

    private String name;

    private boolean active;

    private Double grade;

    public Student(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
