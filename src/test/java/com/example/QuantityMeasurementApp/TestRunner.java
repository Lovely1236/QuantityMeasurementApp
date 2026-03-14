package com.example.QuantityMeasurementApp;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Method;

public class TestRunner {
    public static void main(String[] args) {
        Class<?> testClass = QuantityMeasurementAppTest.class;
        int testCount = 0;
        int passedCount = 0;
        int failedCount = 0;
        
        System.out.println("========== Running QuantityMeasurementAppTest ==========\n");
        
        for (Method method : testClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Test.class)) {
                testCount++;
                try {
                    QuantityMeasurementAppTest testInstance = new QuantityMeasurementAppTest();
                    method.invoke(testInstance);
                    System.out.println("✓ PASSED: " + method.getName());
                    passedCount++;
                } catch (Exception e) {
                    System.out.println("✗ FAILED: " + method.getName());
                    System.out.println("  Error: " + e.getCause());
                    failedCount++;
                }
            }
        }
        
        System.out.println("\n========== Test Results ==========");
        System.out.println("Total Tests: " + testCount);
        System.out.println("Passed: " + passedCount);
        System.out.println("Failed: " + failedCount);
        
        if (failedCount == 0) {
            System.out.println("\n✓ All tests passed!");
        } else {
            System.out.println("\n✗ Some tests failed!");
        }
    }
}
