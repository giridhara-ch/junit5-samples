package com.example.project;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Test Info Demo")
class TestInfoDemo {

    TestInfoDemo(TestInfo testInfo) {
        System.out.println(testInfo.toString());
        assertEquals("Test Info Demo", testInfo.getDisplayName());
    }

    @Test
    @DisplayName("Test case to validate the display name match in method")
    @Tag("my-local-tag")
    void testMethod(TestInfo testInfo) {
        System.out.println("In test method:\n" + testInfo.toString());
        assertEquals("Test case to validate the display name match in method", testInfo.getDisplayName());
        assertEquals(Set.of("my-local-tag"), testInfo.getTags());
    }
}
