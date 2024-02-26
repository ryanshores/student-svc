package com.ryanshores.studentsvc.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.util.Objects;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Grade extends Base {
    private String name;
    private Double score;
    private Double weight;

    @ManyToOne
    private Student student;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grade grade = (Grade) o;
        return Objects.equals(name, grade.name) && Objects.equals(score, grade.score) && Objects.equals(weight, grade.weight) && Objects.equals(student, grade.student);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, score, weight, student);
    }
}
