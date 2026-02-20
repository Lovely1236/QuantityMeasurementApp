package com.example.QuantityMeasurementApp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class QuantityMeasurementAppTest {
    private QuantityMeasurementApp.QuantityLength q(double v,
            QuantityMeasurementApp.LengthUnit u) {
        return new QuantityMeasurementApp.QuantityLength(v, u);
    }
         //YARD TESTS
    @Test
    void testEquality_YardToYard_SameValue() {
        assertTrue(q(1, QuantityMeasurementApp.LengthUnit.YARDS)
                .equals(q(1, QuantityMeasurementApp.LengthUnit.YARDS)));
    }

    @Test
    void testEquality_YardToYard_DifferentValue() {
        assertFalse(q(1, QuantityMeasurementApp.LengthUnit.YARDS)
                .equals(q(2, QuantityMeasurementApp.LengthUnit.YARDS)));
    }

    @Test
    void testEquality_YardToFeet_EquivalentValue() {
        assertTrue(q(1, QuantityMeasurementApp.LengthUnit.YARDS)
                .equals(q(3, QuantityMeasurementApp.LengthUnit.FEET)));
    }

    @Test
    void testEquality_YardToInches_EquivalentValue() {
        assertTrue(q(1, QuantityMeasurementApp.LengthUnit.YARDS)
                .equals(q(36, QuantityMeasurementApp.LengthUnit.INCH)));
    }

    @Test
    void testEquality_YardToFeet_NonEquivalentValue() {
        assertFalse(q(1, QuantityMeasurementApp.LengthUnit.YARDS)
                .equals(q(2, QuantityMeasurementApp.LengthUnit.FEET)));
    }
        //CM TESTS
    @Test
    void testEquality_CentimeterToCentimeter_SameValue() {
        assertTrue(q(2, QuantityMeasurementApp.LengthUnit.CENTIMETERS)
                .equals(q(2, QuantityMeasurementApp.LengthUnit.CENTIMETERS)));
    }

    @Test
    void testEquality_CentimeterToInch_EquivalentValue() {
        assertTrue(q(1, QuantityMeasurementApp.LengthUnit.CENTIMETERS)
                .equals(q(0.393701, QuantityMeasurementApp.LengthUnit.INCH)));
    }
    @Test
    void testEquality_CentimeterToFeet_NonEquivalentValue() {
        assertFalse(q(1, QuantityMeasurementApp.LengthUnit.CENTIMETERS)
                .equals(q(1, QuantityMeasurementApp.LengthUnit.FEET)));
    }
           //TRANSITIVE 
    @Test
    void testEquality_MultiUnit_TransitiveProperty() {

        var yard = q(1, QuantityMeasurementApp.LengthUnit.YARDS);
        var feet = q(3, QuantityMeasurementApp.LengthUnit.FEET);
        var inch = q(36, QuantityMeasurementApp.LengthUnit.INCH);

        assertTrue(yard.equals(feet));
        assertTrue(feet.equals(inch));
        assertTrue(yard.equals(inch));
    }
    @Test
    void testEquality_NullUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> new QuantityMeasurementApp.QuantityLength(1, null));
    }

    @Test
    void testEquality_SameReference() {
        var q = q(1, QuantityMeasurementApp.LengthUnit.YARDS);
        assertTrue(q.equals(q));
    }

    @Test
    void testEquality_NullComparison() {
        var q = q(1, QuantityMeasurementApp.LengthUnit.YARDS);
        assertFalse(q.equals(null));
    }
           //COMPLEX
    @Test
    void testEquality_AllUnits_ComplexScenario() {

        var yard = q(2, QuantityMeasurementApp.LengthUnit.YARDS);
        var feet = q(6, QuantityMeasurementApp.LengthUnit.FEET);
        var inch = q(72, QuantityMeasurementApp.LengthUnit.INCH);

        assertTrue(yard.equals(feet));
        assertTrue(feet.equals(inch));
        assertTrue(yard.equals(inch));
    }
}
