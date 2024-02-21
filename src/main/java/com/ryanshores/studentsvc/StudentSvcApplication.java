package com.ryanshores.studentsvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class StudentSvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentSvcApplication.class, args);
    }

}
