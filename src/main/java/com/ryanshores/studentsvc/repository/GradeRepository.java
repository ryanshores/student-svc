package com.ryanshores.studentsvc.repository;

import com.ryanshores.studentsvc.model.Grade;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends CrudRepository<Grade, Long> {
}
