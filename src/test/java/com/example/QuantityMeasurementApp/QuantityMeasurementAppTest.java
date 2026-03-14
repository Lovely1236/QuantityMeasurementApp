package com.example.QuantityMeasurementApp;

import org.junit.jupiter.api.Test;
import com.example.QuantityMeasurementApp.exception.*;
import com.example.QuantityMeasurementApp.controller.QuantityMeasurementController;
import com.example.QuantityMeasurementApp.dto.QuantityDTO;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final QuantityMeasurementController controller =
            QuantityMeasurementApp.getInstance().controller;

    // ================= LENGTH COMPARISON =================

    @Test
    public void lengthFeetEqualsInches() throws QuantityMeasurementException {
        QuantityDTO feet = new QuantityDTO(1.0, "FEET", "LENGTH");
        QuantityDTO inches = new QuantityDTO(12.0, "INCH", "LENGTH");
        assertTrue(controller.performComparison(feet, inches));
    }

    @Test
    public void lengthYardsEqualsFeet() throws QuantityMeasurementException {
        QuantityDTO yards = new QuantityDTO(1.0, "YARDS", "LENGTH");
        QuantityDTO feet = new QuantityDTO(3.0, "FEET", "LENGTH");
        assertTrue(controller.performComparison(yards, feet));
    }

    @Test
    public void lengthCentimetersEqualsInches() throws QuantityMeasurementException {
        QuantityDTO cm = new QuantityDTO(2.54, "CENTIMETERS", "LENGTH");
        QuantityDTO inch = new QuantityDTO(1.0, "INCH", "LENGTH");
        assertTrue(controller.performComparison(cm, inch));
    }

    // ================= WEIGHT COMPARISON =================

    @Test
    public void weightKilogramEqualsGrams() throws QuantityMeasurementException {
        QuantityDTO kg = new QuantityDTO(1.0, "KILOGRAM", "WEIGHT");
        QuantityDTO gram = new QuantityDTO(1000.0, "GRAM", "WEIGHT");
        assertTrue(controller.performComparison(kg, gram));
    }

    @Test
    public void weightPoundsEqualsGrams() throws QuantityMeasurementException {
        QuantityDTO pounds = new QuantityDTO(1.0, "POUND", "WEIGHT");
        QuantityDTO grams = new QuantityDTO(453.592, "GRAM", "WEIGHT");
        assertTrue(controller.performComparison(pounds, grams));
    }

    // ================= VOLUME COMPARISON =================

    @Test
    public void volumeOneMillilitreEqualsOneMillilitre() throws QuantityMeasurementException {
        QuantityDTO ml1 = new QuantityDTO(1.0, "MILLILITRE", "VOLUME");
        QuantityDTO ml2 = new QuantityDTO(1.0, "MILLILITRE", "VOLUME");
        assertTrue(controller.performComparison(ml1, ml2));
    }

    @Test
    public void volumeLitreEqualsMillilitres() throws QuantityMeasurementException {
        QuantityDTO litre = new QuantityDTO(1.0, "LITRE", "VOLUME");
        QuantityDTO ml = new QuantityDTO(1000.0, "MILLILITRE", "VOLUME");
        assertTrue(controller.performComparison(litre, ml));
    }

    @Test
    public void volumeGallonEqualsLitres() throws QuantityMeasurementException {
        QuantityDTO gallon = new QuantityDTO(1.0, "GALLON", "VOLUME");
        QuantityDTO litres = new QuantityDTO(3.78541, "LITRE", "VOLUME");
        assertTrue(controller.performComparison(gallon, litres));
    }

    // ================= LENGTH CONVERSION =================

    @Test
    public void convertFeetToInches() throws QuantityMeasurementException {
        QuantityDTO feet = new QuantityDTO(2.0, "FEET", "LENGTH");
        QuantityDTO inches = new QuantityDTO(0, "INCH", "LENGTH");
        QuantityDTO result = controller.performConversion(feet, inches);
        assertEquals(24.0, result.getValue());
    }

    @Test
    public void convertYardsToInches() throws QuantityMeasurementException {
        QuantityDTO yards = new QuantityDTO(1.0, "YARDS", "LENGTH");
        QuantityDTO inches = new QuantityDTO(0, "INCH", "LENGTH");
        QuantityDTO result = controller.performConversion(yards, inches);
        assertEquals(36.0, result.getValue());
    }

    @Test
    public void convertCentimetersToInches() throws QuantityMeasurementException {
        QuantityDTO cm = new QuantityDTO(5.08, "CENTIMETERS", "LENGTH");
        QuantityDTO inches = new QuantityDTO(0, "INCH", "LENGTH");
        QuantityDTO result = controller.performConversion(cm, inches);
        assertEquals(2.0, result.getValue());
    }

    // ================= WEIGHT CONVERSION =================

    @Test
    public void convertKilogramsToGrams() throws QuantityMeasurementException {
        QuantityDTO kg = new QuantityDTO(2.0, "KILOGRAM", "WEIGHT");
        QuantityDTO gram = new QuantityDTO(0, "GRAM", "WEIGHT");
        QuantityDTO result = controller.performConversion(kg, gram);
        assertEquals(2000.0, result.getValue());
    }

    @Test
    public void convertPoundsToGrams() throws QuantityMeasurementException {
        QuantityDTO pounds = new QuantityDTO(1.0, "POUND", "WEIGHT");
        QuantityDTO grams = new QuantityDTO(0, "GRAM", "WEIGHT");
        QuantityDTO result = controller.performConversion(pounds, grams);
        assertEquals(453.592, result.getValue());
    }

    // ================= VOLUME CONVERSION =================

    @Test
    public void convertLitresToMillilitres() throws QuantityMeasurementException {
        QuantityDTO litres = new QuantityDTO(2.0, "LITRE", "VOLUME");
        QuantityDTO ml = new QuantityDTO(0, "MILLILITRE", "VOLUME");
        QuantityDTO result = controller.performConversion(litres, ml);
        assertEquals(2000.0, result.getValue());
    }

    @Test
    public void convertGallonsToLitres() throws QuantityMeasurementException {
        QuantityDTO gallon = new QuantityDTO(1.0, "GALLON", "VOLUME");
        QuantityDTO litres = new QuantityDTO(0, "LITRE", "VOLUME");
        QuantityDTO result = controller.performConversion(gallon, litres);
        assertEquals(3.78541, result.getValue(), 0.001);
    }

    // ================= ADDITION =================

    @Test
    public void addLengthFeetAndInches() throws QuantityMeasurementException {
        QuantityDTO feet = new QuantityDTO(1.0, "FEET", "LENGTH");
        QuantityDTO inches = new QuantityDTO(6.0, "INCH", "LENGTH");
        QuantityDTO result = controller.performAddition(feet, inches);
        assertEquals(1.5, result.getValue());
    }

    @Test
    public void addWeightKilogramsAndGrams() throws QuantityMeasurementException {
        QuantityDTO kg = new QuantityDTO(1.0, "KILOGRAM", "WEIGHT");
        QuantityDTO gram = new QuantityDTO(500.0, "GRAM", "WEIGHT");
        QuantityDTO result = controller.performAddition(kg, gram);
        assertEquals(1.5, result.getValue());
    }

    @Test
    public void addVolumeLitresAndMillilitres() throws QuantityMeasurementException {
        QuantityDTO litre = new QuantityDTO(1.0, "LITRE", "VOLUME");
        QuantityDTO ml = new QuantityDTO(500.0, "MILLILITRE", "VOLUME");
        QuantityDTO result = controller.performAddition(litre, ml);
        assertEquals(1.5, result.getValue());
    }

    // ================= SUBTRACTION =================

    @Test
    public void subtractWeightsSameUnit() throws QuantityMeasurementException {
        QuantityDTO w1 = new QuantityDTO(5.0, "KILOGRAM", "WEIGHT");
        QuantityDTO w2 = new QuantityDTO(2.0, "KILOGRAM", "WEIGHT");
        QuantityDTO result = controller.performSubtraction(w1, w2);
        assertEquals(3.0, result.getValue());
    }

    @Test
    public void subtractVolumesSameUnit() throws QuantityMeasurementException {
        QuantityDTO v1 = new QuantityDTO(5.0, "LITRE", "VOLUME");
        QuantityDTO v2 = new QuantityDTO(2.0, "LITRE", "VOLUME");
        QuantityDTO result = controller.performSubtraction(v1, v2);
        assertEquals(3.0, result.getValue());
    }

    @Test
    public void subtractLengthDifferentUnits() throws QuantityMeasurementException {
        QuantityDTO feet = new QuantityDTO(2.0, "FEET", "LENGTH");
        QuantityDTO inches = new QuantityDTO(6.0, "INCH", "LENGTH");
        QuantityDTO result = controller.performSubtraction(feet, inches);
        assertEquals(1.5, result.getValue());
    }

    // ================= DIVISION =================

    @Test
    public void divideWeightsSameUnit() throws QuantityMeasurementException {
        QuantityDTO w1 = new QuantityDTO(10.0, "KILOGRAM", "WEIGHT");
        QuantityDTO w2 = new QuantityDTO(5.0, "KILOGRAM", "WEIGHT");
        double result = controller.performDivision(w1, w2);
        assertEquals(2.0, result);
    }

    @Test
    public void divideVolumesSameUnit() throws QuantityMeasurementException {
        QuantityDTO v1 = new QuantityDTO(10.0, "LITRE", "VOLUME");
        QuantityDTO v2 = new QuantityDTO(5.0, "LITRE", "VOLUME");
        double result = controller.performDivision(v1, v2);
        assertEquals(2.0, result);
    }

    @Test
    public void divideLengthDifferentUnits() throws QuantityMeasurementException {
        QuantityDTO feet = new QuantityDTO(2.0, "FEET", "LENGTH");
        QuantityDTO inches = new QuantityDTO(6.0, "INCH", "LENGTH");
        double result = controller.performDivision(feet, inches);
        assertEquals(4.0, result);
    }

    // ================= TEMPERATURE =================

    @Test
    public void testTemperatureComparison() throws QuantityMeasurementException {
        QuantityDTO celsius = new QuantityDTO(25.0, "CELSIUS", "TEMPERATURE");
        QuantityDTO fahrenheit = new QuantityDTO(77.0, "FAHRENHEIT", "TEMPERATURE");
        assertTrue(controller.performComparison(celsius, fahrenheit));
    }

    @Test
    public void testTemperatureConversion() throws QuantityMeasurementException {
        QuantityDTO celsius = new QuantityDTO(100.0, "CELSIUS", "TEMPERATURE");
        QuantityDTO fahrenheit = new QuantityDTO(0, "FAHRENHEIT", "TEMPERATURE");
        QuantityDTO result = controller.performConversion(celsius, fahrenheit);
        assertEquals(212.0, result.getValue());
    }

    @Test
    public void testTemperatureUnsupportedAddition() throws QuantityMeasurementException {
        QuantityDTO celsius = new QuantityDTO(25.0, "CELSIUS", "TEMPERATURE");
        QuantityDTO celsius2 = new QuantityDTO(40.0, "CELSIUS", "TEMPERATURE");
        assertThrows(
                QuantityMeasurementException.class,
                () -> controller.performAddition(celsius, celsius2));
    }

    // ================= CROSS CATEGORY =================

    @Test
    public void preventCrossTypeComparison() throws QuantityMeasurementException {
        QuantityDTO length = new QuantityDTO(1.0, "FEET", "LENGTH");
        QuantityDTO weight = new QuantityDTO(1.0, "KILOGRAM", "WEIGHT");
        assertThrows(
                QuantityMeasurementException.class,
                () -> controller.performComparison(length, weight));
    }

    @Test
    public void preventCrossTypeAddition() throws QuantityMeasurementException {
        QuantityDTO length = new QuantityDTO(1.0, "FEET", "LENGTH");
        QuantityDTO weight = new QuantityDTO(1.0, "KILOGRAM", "WEIGHT");
        assertThrows(
                QuantityMeasurementException.class,
                () -> controller.performAddition(length, weight));
    }

    @Test
    public void preventCrossTypeSubtraction() throws QuantityMeasurementException {
        QuantityDTO length = new QuantityDTO(1.0, "FEET", "LENGTH");
        QuantityDTO weight = new QuantityDTO(1.0, "KILOGRAM", "WEIGHT");
        assertThrows(
                QuantityMeasurementException.class,
                () -> controller.performSubtraction(length, weight));
    }

    @Test
    public void preventCrossTypeDivision() throws QuantityMeasurementException {
        QuantityDTO length = new QuantityDTO(10.0, "FEET", "LENGTH");
        QuantityDTO weight = new QuantityDTO(5.0, "KILOGRAM", "WEIGHT");
        assertThrows(
                QuantityMeasurementException.class,
                () -> controller.performDivision(length, weight));
    }

    // ================= EDGE CASES =================

    @Test
    public void divisionByZeroThrowsException() throws QuantityMeasurementException {
        QuantityDTO w1 = new QuantityDTO(10.0, "KILOGRAM", "WEIGHT");
        QuantityDTO w2 = new QuantityDTO(0.0, "KILOGRAM", "WEIGHT");
        assertThrows(
                QuantityMeasurementException.class,
                () -> controller.performDivision(w1, w2));
    }

    @Test
    public void invalidUnitThrowsException() throws QuantityMeasurementException {
        QuantityDTO q1 = new QuantityDTO(1.0, "FEET", "LENGTH");
        QuantityDTO q2 = new QuantityDTO(1.0, "INVALID_UNIT", "LENGTH");
        assertThrows(
                QuantityMeasurementException.class,
                () -> controller.performComparison(q1, q2));
    }

    @Test
    public void zeroValueComparison() throws QuantityMeasurementException {
        QuantityDTO zero1 = new QuantityDTO(0.0, "FEET", "LENGTH");
        QuantityDTO zero2 = new QuantityDTO(0.0, "INCH", "LENGTH");
        assertTrue(controller.performComparison(zero1, zero2));
    }

    @Test
    public void negativeValueHandling() throws QuantityMeasurementException {
        QuantityDTO negative = new QuantityDTO(-1.0, "FEET", "LENGTH");
        QuantityDTO positive = new QuantityDTO(1.0, "FEET", "LENGTH");
        assertFalse(controller.performComparison(negative, positive));
    }
}