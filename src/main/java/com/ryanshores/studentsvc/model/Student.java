package com.ryanshores.studentsvc.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class Student extends Base {

    private String name;

    private boolean active;

    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
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
}
