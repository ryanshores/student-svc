package com.ryanshores.studentsvc;

import com.ryanshores.studentsvc.model.Grade;
import com.ryanshores.studentsvc.model.Student;
import com.ryanshores.studentsvc.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Arrays;
import java.util.List;

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
        Student savedStudent = studentRepository.save(Student.builder().name("Mark").build());

        // when
        Student student = studentRepository.getStudentByName(savedStudent.getName());

        // then
        then(student.getName()).isEqualTo("Mark");
    }

    @Test
    void getAvgGradeForStudent_CalculateAve() {

        // given
        getStudents();

        // when
        Double avgGrade = studentRepository.getAvgGradeForActiveStudents();

        // then
        then(avgGrade).isEqualTo(75);
    }

    @Test
    void testStudentsByActive_returnsActiveStudents() {

        // given
        getStudents();

        // when
        List<Student> studentsByActive = studentRepository.getStudentsByActiveTrue();

        // then
        then(studentsByActive).isNotNull();
        then((long) studentsByActive.size()).isEqualTo(2);
    }

    private List<Student> getStudents() {
        Student mark = Student.builder().name("Mark").active(true).build();
        mark.addGrade(getGrade(100.0));
        Student susan = Student.builder().name("Susan").active(false).build();
        susan.addGrade(getGrade(80.0));
        Student peter = Student.builder().name("Peter").active(true).build();
        peter.addGrade(getGrade(50.0));

        var list = Arrays.asList(mark, susan, peter);

        list.forEach(testEntityManager::persistFlushFind);

        return list;
    }

    private Grade getGrade(Double value) {
        return Grade.builder().score(value).build();
    }
}
