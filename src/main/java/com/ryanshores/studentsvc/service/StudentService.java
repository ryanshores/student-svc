package com.ryanshores.studentsvc.service;

import com.ryanshores.studentsvc.model.Student;
import com.ryanshores.studentsvc.model.exception.StudentNotFoundException;
import com.ryanshores.studentsvc.repository.StudentRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Cacheable("students")
    public Student getById(long id) {
        return studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
    }

}
