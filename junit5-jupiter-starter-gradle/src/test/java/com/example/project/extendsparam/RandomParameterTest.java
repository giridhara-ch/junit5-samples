package com.example.project.extendsparam;

import com.example.project.extendsparam.RandomParametersExtension.Random;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ExtendWith(RandomParametersExtension.class)
class RandomParameterTest {

    @Test
    void injectInteger(@Random int i, @Random int j) {
        assertNotEquals(i, j);
    }

    @Test
    void injectDouble(@Random double i, @Random double j) {
        assertNotEquals(i, j);
    }

    @Test
    void injectFloat(@Random float i, @Random float j) {
        assertNotEquals(i, j);
    }


}
