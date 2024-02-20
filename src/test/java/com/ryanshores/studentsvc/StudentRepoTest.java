package com.ryanshores.studentsvc;

import com.ryanshores.studentsvc.model.Student;
import com.ryanshores.studentsvc.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.lang.reflect.Array;
import java.util.Arrays;

import static org.assertj.core.api.BDDAssertions.then;

@DataJpaTest
public class StudentRepoTest {

    private final StudentRepository studentRepository;

    private final TestEntityManager testEntityManager;


    @Autowired
    public StudentRepoTest(StudentRepository studentRepository, TestEntityManager testEntityManager) {
        this.studentRepository = studentRepository;
        this.testEntityManager = testEntityManager;
    }

    @Test
    void testStudentByName_returnsStudentDetails() {

        // given
        Student savedStudent = studentRepository.save(new Student(null, "Mark"));

        // when
        Student student = studentRepository.getStudentByName(savedStudent.getName());

        // then
        then(student.getId()).isNotNull();
        then(student.getName()).isEqualTo("Mark");
    }

    @Test
    void getAvgGradeForStudent_CalculateAve() {

        // given
        Student mark = Student.builder().name("Mark").active(true).grade(100.0).build();
        Student susan = Student.builder().name("Susan").active(false).grade(80.0).build();
        Student peter = Student.builder().name("Peter").active(true).grade(50.0).build();
        Arrays.asList(mark, susan, peter).forEach(testEntityManager::persistFlushFind);

        // when
        Double avgGrade = studentRepository.getAvgGradeForActiveStudents();

        // then
        then(avgGrade).isEqualTo(75);
    }

    @Test
    void getAvgGradeForStudent_CalculateAve() {

        // given
        Student mark = Student.builder().name("Mark").active(true).grade(100.0).build();
        Student susan = Student.builder().name("Susan").active(false).grade(80.0).build();
        Student peter = Student.builder().name("Peter").active(true).grade(50.0).build();
        Arrays.asList(mark, susan, peter).forEach(testEntityManager::persistFlushFind);

        // when
        Double avgGrade = studentRepository.getAvgGradeForActiveStudents();

        // then
        then(avgGrade).isEqualTo(75);
    }
}
