package com.ryanshores.studentsvc;

import com.ryanshores.studentsvc.model.Student;
import com.ryanshores.studentsvc.repository.StudentRepository;
import com.ryanshores.studentsvc.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

@SpringBootTest(webEnvironment = NONE)
public class StudentCacheTest {

    private final StudentService service;

    @MockBean
    private StudentRepository repo;

    @Autowired
    StudentCacheTest(StudentService service) {
        this.service = service;
    }

    @Test
    void getStudentById_forMultipleRequests_isRetrievedFromCache() {

        // given
        long id = 123L;
        given(repo.findById(id)).willReturn(Optional.of(new Student(id, "Mark")));

        // when
        service.getById(id);
        service.getById(id);
        service.getById(id);

        // then
        then(repo).should(times(1)).findById(id);
    }
}
