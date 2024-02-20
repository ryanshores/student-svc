package com.ryanshores.studentsvc.repository;

import com.ryanshores.studentsvc.model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Long> {

    Student getStudentByName(String name);

    @Query("select avg (s.grade) from Student s where s.active")
    Double getAvgGradeForActiveStudents();

    List<Student> getStudentsByActiveTrue();
}
