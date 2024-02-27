package com.ryanshores.studentsvc.service;

import com.ryanshores.studentsvc.model.Grade;
import com.ryanshores.studentsvc.model.Student;
import com.ryanshores.studentsvc.model.exception.GradeNotFoundException;
import com.ryanshores.studentsvc.model.exception.StudentNotFoundException;
import com.ryanshores.studentsvc.repository.GradeRepository;
import com.ryanshores.studentsvc.repository.StudentRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    private final GradeRepository gradeRepository;

    public StudentService(StudentRepository studentRepository, GradeRepository gradeRepository) {
        this.studentRepository = studentRepository;
        this.gradeRepository = gradeRepository;
    }

    @Cacheable("students")
    public Student getById(long id) {
        return studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public Student addGrade(Long studentId, Grade grade) {
        var student = studentRepository.findById(studentId).orElseThrow(StudentNotFoundException::new);

        student.addGrade(grade);

        return studentRepository.save(student);
    }

    public Student removeGrade(Long studentId, Long gradeId) {
        var student = getById(studentId);

        var grade = gradeRepository.findById(gradeId).orElseThrow(GradeNotFoundException::new);

        student.removeGrade(grade);

        return studentRepository.save(student);
    }

}
