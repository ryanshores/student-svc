package com.ryanshores.studentsvc;

import com.ryanshores.studentsvc.model.Grade;
import com.ryanshores.studentsvc.model.Student;
import com.ryanshores.studentsvc.model.exception.StudentNotFoundException;
import com.ryanshores.studentsvc.repository.StudentRepository;
import com.ryanshores.studentsvc.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class StudentServiceTest {

    private final StudentRepository repo;
    private final StudentService service;

    @Autowired
    StudentServiceTest(StudentRepository studentRepository, StudentService studentService) {
        this.repo = studentRepository;
        this.service = studentService;
    }

    @Test
    void getStudentById_forSavedStudent_isReturned() {

        // given
        Student savedStudent = repo.save(Student.builder().name("Mark").build());

        // when
        Student student = service.getById(savedStudent.getId());

        // then
        assertEquals(savedStudent, student);
    }

    @Test
    void getStudentById_forUnsavedStudent_exceptionThrown() {

        // given
        long badId = -1L;

        // when
        assertThrows(StudentNotFoundException.class, () -> service.getById(badId));

        // then
    }

    @Test
    void addGrade_forSavedStudent_isAdded() {

        // given
        var grade = Grade.builder().build();
        var student = repo.save(Student.builder().build());

        // when
        var updatedStudent = service.addGrade(student.getId(), grade);

        // then
        assertEquals(1, updatedStudent.getGrades().size());
    }
}
