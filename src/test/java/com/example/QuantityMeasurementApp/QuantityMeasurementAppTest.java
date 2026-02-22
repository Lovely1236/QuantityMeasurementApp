package com.example.QuantityMeasurementApp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class QuantityMeasurementAppTest {
    private static final double EPS=1e-6;
    @Test
    void testConversion_FeetToInches(){
        assertEquals(12.0,
                QuantityMeasurementApp.QuantityLength.convert(
                        1, QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.INCH), EPS);
    }
    @Test
    void testConversion_InchesToFeet(){
        assertEquals(2.0,
                QuantityMeasurementApp.QuantityLength.convert(
                        24, QuantityMeasurementApp.LengthUnit.INCH,
                        QuantityMeasurementApp.LengthUnit.FEET), EPS);
    }
    @Test
    void testConversion_YardsToInches(){
        assertEquals(36.0,
                QuantityMeasurementApp.QuantityLength.convert(
                        1, QuantityMeasurementApp.LengthUnit.YARDS,
                        QuantityMeasurementApp.LengthUnit.INCH), EPS);
    }
    @Test
    void testConversion_CentimetersToInches(){
        assertEquals(1.0,
                QuantityMeasurementApp.QuantityLength.convert(
                        2.54, QuantityMeasurementApp.LengthUnit.CENTIMETERS,
                        QuantityMeasurementApp.LengthUnit.INCH), 1e-5);
    }
    @Test
    void testConversion_RoundTrip() {
        double v=5.5;
        double inches=
                QuantityMeasurementApp.QuantityLength.convert(
                        v,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.INCH);

        double back=
                QuantityMeasurementApp.QuantityLength.convert(
                        inches,
                        QuantityMeasurementApp.LengthUnit.INCH,
                        QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals(v, back, EPS);
    }
    @Test
    void testConversion_Zero(){
        assertEquals(0.0,
                QuantityMeasurementApp.QuantityLength.convert(
                        0, QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.INCH), EPS);
    }
    @Test
    void testConversion_Negative(){
        assertEquals(-12.0,
                QuantityMeasurementApp.QuantityLength.convert(
                        -1, QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.INCH), EPS);
    }
    @Test
    void testConversion_InvalidUnit(){
        assertThrows(IllegalArgumentException.class,
                () -> QuantityMeasurementApp.QuantityLength.convert(
                        1, null,
                        QuantityMeasurementApp.LengthUnit.FEET));
    }
    @Test
    void testConversion_NaN(){
        assertThrows(IllegalArgumentException.class,
                () -> QuantityMeasurementApp.QuantityLength.convert(
                        Double.NaN,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.INCH));
    }
}