package com.ryanshores.studentsvc.service;

import com.ryanshores.studentsvc.model.Student;
import com.ryanshores.studentsvc.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student getById(long id) {
        return studentRepository.findById(id).orElse(null);
    }

}
