package com.ryanshores.studentsvc.controller;

import com.ryanshores.studentsvc.model.Grade;
import com.ryanshores.studentsvc.model.Student;
import com.ryanshores.studentsvc.model.exception.StudentNotFoundException;
import com.ryanshores.studentsvc.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class StudentController {

    private final StudentService service;

    private final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @GetMapping("/students/{id}")
    Student getStudent(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping("/students/{id}")
    Student updateStudent(@PathVariable Long id,
                          @RequestBody Student student) {
        return service.save(student);
    }

    @PostMapping("/students/{id}/grade")
    Student addGrade(@PathVariable Long id,
                     @RequestBody Grade grade) {
        return service.addGrade(id, grade);
    }

    @DeleteMapping("/students/{id}/grade/{gradeId}")
    Student removeGrade(@PathVariable Long id,
                        @PathVariable Long gradeId) {
        return service.removeGrade(id, gradeId);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    void notFound(StudentNotFoundException exception) {
        logger.error("Not Found", exception);
    }
}
