package com.ryanshores.studentsvc;

import com.ryanshores.studentsvc.model.Grade;
import com.ryanshores.studentsvc.model.Student;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {

    @Test
    void studentEquals_WhenEqual_True() {
        var student1 = testStudent();
        var student2 = testStudent();

        assertEquals(student1, student2);
    }

    @Test
    void studentEquals_WhenActiveNotEqual_False() {
        var student1 = testStudent();
        var student2 = testStudent();
        student2.setActive(false);

        assertNotEquals(student1, student2);
    }

    @Test
    void studentEquals_WhenNameNotEqual_False() {
        var student1 = testStudent();
        var student2 = testStudent();
        student2.setName("New" + student2.getName());

        assertNotEquals(student1, student2);
    }

    void studentEquals_WhenGradeNotEqual_False() {
        var student1 = testStudent();
        var student2 = testStudent();
        var newGrade = Grade.builder().score(80.0).build();
        student2.addGrade(newGrade);

        assertNotEquals(student1, student2);
    }

    private Student testStudent() {
        var grade = Grade.builder().score(100.0).build();
        var student = Student.builder()
                .name("Test")
                .active(true).build();
        student.addGrade(grade);
        return student;
    }

}
