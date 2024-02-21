package com.ryanshores.studentsvc;

import com.ryanshores.studentsvc.model.Student;
import com.ryanshores.studentsvc.model.exception.StudentNotFoundException;
import com.ryanshores.studentsvc.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@RequiredArgsConstructor
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService service;

    @Test
    void getStudent_forSavedStudent_isReturned() throws Exception {

        // given
        given(service.getById(1l)).willReturn(Student.builder().id(1l).name("Mark").grade(10.0).build());

        // when
        mockMvc.perform(get("/students/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1l))
                .andExpect(jsonPath("name").value("Mark"))
                .andExpect(jsonPath("grade").value(10));

        // then

    }

    @Test
    void getStudent_forMissingStudent_status404() throws Exception {

        // given
        given(service.getById(anyLong())).willThrow(StudentNotFoundException.class);

        // when
        mockMvc.perform(get("/students/1"))
                .andExpect(status().isNotFound());

        // then

    }
}
