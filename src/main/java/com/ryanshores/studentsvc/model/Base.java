package com.ryanshores.studentsvc.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.NoArgsConstructor;

import java.time.Instant;

@MappedSuperclass
@NoArgsConstructor
public class Base {

    private final Instant createdDt = Instant.now();
    @Id
    @GeneratedValue()
    private Long id;
    private Instant updateDt = Instant.now();

    public Long getId() {
        return id;
    }
}
