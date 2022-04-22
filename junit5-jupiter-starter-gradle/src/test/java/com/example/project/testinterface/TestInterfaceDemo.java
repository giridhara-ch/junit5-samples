package com.example.project.testinterface;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestInterfaceDemo implements TestLifecycleLogger, TestExecutionLogger, TestInterfaceDynamicTestsDemo {
    @Test
    void isEqualValue() {
        assertEquals(1, "a".length(), "is always equal");
    }
}
