package com.ryanshores.studentsvc.repository;

import com.ryanshores.studentsvc.model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

    Student getStudentByName(String name);

    @Query("select avg (g.score) from Student s join Grade g on s.id = g.student.id where s.active")
    Double getAvgGradeForActiveStudents();

    List<Student> getStudentsByActiveTrue();
}
