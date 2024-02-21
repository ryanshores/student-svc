package com.ryanshores.studentsvc;

import com.ryanshores.studentsvc.model.Student;
import com.ryanshores.studentsvc.model.exception.StudentNotFoundException;
import com.ryanshores.studentsvc.repository.StudentRepository;
import com.ryanshores.studentsvc.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.BDDAssertions.then;

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
        then(student.getName()).isEqualTo(savedStudent.getName());
        then(student.getId()).isEqualTo(savedStudent.getId());
    }

    @Test
    void getStudentById_forUnsavedStudent_exceptionThrown() {

        // given
        long badId = -1L;

        // when
        Throwable throwable = catchThrowable(() -> service.getById(badId));

        // then
        then(throwable).isInstanceOf(StudentNotFoundException.class);
    }
}
