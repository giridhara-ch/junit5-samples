package com.example.project;

import com.example.domain.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.concurrent.CountDownLatch;

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AssertionsDemo {
    private final Calculator calculator = new Calculator();
    private final Person person = new Person("Hari", "Krishna");

    private static String greeting() {
        return "Hello there";
    }

    @Test
    void standardAssertions() {
        assertEquals(2, calculator.add(1, 1));
        assertEquals(5, calculator.multiply(1, 5));
        assertTrue('a' < 'b', () -> "Assertion messages can be lazily evaluated -- " + "to avoid constructing complex messages unnecessarily.");
    }

    @Test
    void groupedAssertions() {
        assertAll(() -> assertEquals("Hari", person.getFirstName()), () -> assertEquals("Krishna", person.getLastName()));
    }

    @Test
    void dependentAssertions() {
        assertAll("Properties", () -> {
            String fName = person.getFirstName();
            assertNotNull(fName);
            // Executed only if the previous assertion is valid.
            assertAll("first name", () -> assertTrue(fName.startsWith("H")), () -> assertTrue(fName.endsWith("i")));
        }, () -> {
            // Grouped assertion, so processed independently
            // of results of first name assertions.
            String lastName = person.getLastName();
            assertNotNull(lastName);

            // Executed only if the previous assertion is valid.
            assertAll("last name", () -> assertTrue(lastName.startsWith("K")), () -> assertTrue(lastName.endsWith("a")));
        });
    }

    @Test
    void exceptionTesting() {
        Exception exception = assertThrows(ArithmeticException.class, () -> calculator.divide(1, 0));
        assertEquals("/ by zero", exception.getMessage());

    }

    @Test
    void timeoutNotExceeded() {
        assertTimeout(ofMillis(100), () -> {
        });
    }

    @Test
    void timeoutNotExceededWithResult() {
        String result = assertTimeout(ofMillis(100), () -> "sample result string");
        assertEquals("sample result string", result);
    }

    @Test
    void timeoutNotExceededWithMethod() {
        String result = assertTimeout(ofMillis(100), AssertionsDemo::greeting);
        assertEquals("Hello there", result);
    }

    @Test
    void timeoutExceeded() {
        assertTimeout(ofMillis(10), () -> Thread.sleep(20));
    }

    @Test
    void timeoutExceededWithPreemptiveTermination() {
        assertTimeoutPreemptively(ofMillis(10), () -> new CountDownLatch(1).await());
    }

}
