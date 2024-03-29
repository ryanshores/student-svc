package com.ryanshores.studentsvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ryanshores.studentsvc.model.Grade;
import com.ryanshores.studentsvc.model.Student;
import com.ryanshores.studentsvc.model.exception.StudentNotFoundException;
import com.ryanshores.studentsvc.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
        var grade = Grade.builder()
                .name("Test")
                .score(10.0)
                .weight(1.0)
                .build();
        var student = Student.builder()
                .name("Mark")
                .active(true)
                .grades(List.of(grade))
                .build();

        given(service.getById(1L)).willReturn(student);

        // when
        mockMvc.perform(get("/students/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value(student.getName()))
                .andExpect(jsonPath("grade").value(student.getGrade()));

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

    @Test
    void updateStudent_forSavedStudent_returnsUpdated() throws Exception {

        // given
        var student = Student.builder().name("Mark").build();
        given(service.save(student)).willReturn(student);
        given(service.getById(1)).willReturn(student);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(student);

        // when
        mockMvc.perform(post("/students/1").contentType(APPLICATION_JSON_UTF8).content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value(student.getName()));

        // then

    }
}
