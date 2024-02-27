package com.ryanshores.studentsvc.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Student extends Base {

    private String name;

    private boolean active;

    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Grade> grades = new ArrayList<>();

    public void addGrade(Grade grade) {
        grades.add(grade);
        grade.setStudent(this);
    }

    public void removeGrade(Grade grade) {
        grades.remove(grade);
        grade.setStudent(null);
    }

    public Double getGrade() {
        var gradeValues = grades.stream().map(grade -> grade.getScore()).toArray(Double[]::new);

        Double sum = 0.0;

        for (var i : gradeValues)
            sum += i;

        return sum / grades.stream().count();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        var f = active == student.active;
        var g = Objects.equals(name, student.name);
        var h = grades.stream().allMatch(thisGrade -> student.grades.stream().anyMatch(matchGrade -> matchGrade.equals(thisGrade)));

        return f && g && h;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, active, grades);
    }
}
