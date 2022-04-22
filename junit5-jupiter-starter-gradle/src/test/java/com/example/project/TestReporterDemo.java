package com.example.project;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestReporter;

import java.util.Map;

class TestReporterDemo {
    @Test
    void reportSingleValue(TestReporter testReporter) {
        testReporter.publishEntry("a sample message");
    }

    @Test
    void reportKeyValuePair(TestReporter testReporter) {
        testReporter.publishEntry("sample key", "sample value");
    }

    @Test
    void reportMultipleKeyValuePairs(TestReporter testReporter) {
        testReporter.publishEntry(Map.of("key1", "val1", "key2", "val2"));
    }
}
