package com.ryanshores.studentsvc;

import com.ryanshores.studentsvc.utility.Math;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MathTest {

    private Math math;

    @BeforeEach
    void initTest() {
        math = new Math();
    }

    @Test
    void testDivide() {
        assertEquals(10, math.divide(100, 10));
    }

    @Test
    void testDivideByZero() {
        assertThrows(ArithmeticException.class, () -> math.divide(1, 0));
    }
}
