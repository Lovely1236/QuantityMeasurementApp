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
    public void lengthFeetEqualsInches() {

        QuantityDTO feet = new QuantityDTO(
                1.0,
                QuantityDTO.LengthUnit.FEET.getUnitName(),
                QuantityDTO.LengthUnit.FEET.getMeasurementType());

        QuantityDTO inches = new QuantityDTO(
                12.0,
                QuantityDTO.LengthUnit.INCHES.getUnitName(),
                QuantityDTO.LengthUnit.INCHES.getMeasurementType());

        assertTrue(controller.performComparison(feet, inches));
    }

    @Test
    public void lengthYardsEqualsFeet() {

        QuantityDTO yards = new QuantityDTO(
                1.0,
                QuantityDTO.LengthUnit.YARDS.getUnitName(),
                QuantityDTO.LengthUnit.YARDS.getMeasurementType());

        QuantityDTO feet = new QuantityDTO(
                3.0,
                QuantityDTO.LengthUnit.FEET.getUnitName(),
                QuantityDTO.LengthUnit.FEET.getMeasurementType());

        assertTrue(controller.performComparison(yards, feet));
    }

    // ================= WEIGHT COMPARISON =================

    @Test
    public void weightKilogramEqualsGrams() {

        QuantityDTO kg = new QuantityDTO(
                1.0,
                QuantityDTO.WeightUnit.KILOGRAM.getUnitName(),
                QuantityDTO.WeightUnit.KILOGRAM.getMeasurementType());

        QuantityDTO gram = new QuantityDTO(
                1000.0,
                QuantityDTO.WeightUnit.GRAM.getUnitName(),
                QuantityDTO.WeightUnit.GRAM.getMeasurementType());

        assertTrue(controller.performComparison(kg, gram));
    }

    // ================= LENGTH CONVERSION =================

    @Test
    public void convertFeetToInches() {

        QuantityDTO feet = new QuantityDTO(
                2.0,
                QuantityDTO.LengthUnit.FEET.getUnitName(),
                QuantityDTO.LengthUnit.FEET.getMeasurementType());

        QuantityDTO inches = new QuantityDTO(
                0,
                QuantityDTO.LengthUnit.INCHES.getUnitName(),
                QuantityDTO.LengthUnit.INCHES.getMeasurementType());

        QuantityDTO result = controller.performConversion(feet, inches);

        assertEquals(24.0, result.getValue());
    }

    @Test
    public void convertYardsToInches() {

        QuantityDTO yards = new QuantityDTO(
                1.0,
                QuantityDTO.LengthUnit.YARDS.getUnitName(),
                QuantityDTO.LengthUnit.YARDS.getMeasurementType());

        QuantityDTO inches = new QuantityDTO(
                0,
                QuantityDTO.LengthUnit.INCHES.getUnitName(),
                QuantityDTO.LengthUnit.INCHES.getMeasurementType());

        QuantityDTO result = controller.performConversion(yards, inches);

        assertEquals(36.0, result.getValue());
    }

    // ================= WEIGHT CONVERSION =================

    @Test
    public void convertKilogramsToGrams() {

        QuantityDTO kg = new QuantityDTO(
                2.0,
                QuantityDTO.WeightUnit.KILOGRAM.getUnitName(),
                QuantityDTO.WeightUnit.KILOGRAM.getMeasurementType());

        QuantityDTO gram = new QuantityDTO(
                0,
                QuantityDTO.WeightUnit.GRAM.getUnitName(),
                QuantityDTO.WeightUnit.GRAM.getMeasurementType());

        QuantityDTO result = controller.performConversion(kg, gram);

        assertEquals(2000.0, result.getValue());
    }

    // ================= ADDITION =================

    @Test
    public void addLengthFeetAndInches() {

        QuantityDTO feet = new QuantityDTO(
                1.0,
                QuantityDTO.LengthUnit.FEET.getUnitName(),
                QuantityDTO.LengthUnit.FEET.getMeasurementType());

        QuantityDTO inches = new QuantityDTO(
                6.0,
                QuantityDTO.LengthUnit.INCHES.getUnitName(),
                QuantityDTO.LengthUnit.INCHES.getMeasurementType());

        QuantityDTO result = controller.performAddition(feet, inches);

        assertEquals(1.5, result.getValue());
    }

    @Test
    public void addWeightKilogramsAndGrams() {

        QuantityDTO kg = new QuantityDTO(
                1.0,
                QuantityDTO.WeightUnit.KILOGRAM.getUnitName(),
                QuantityDTO.WeightUnit.KILOGRAM.getMeasurementType());

        QuantityDTO gram = new QuantityDTO(
                500.0,
                QuantityDTO.WeightUnit.GRAM.getUnitName(),
                QuantityDTO.WeightUnit.GRAM.getMeasurementType());

        QuantityDTO result = controller.performAddition(kg, gram);

        assertEquals(1.5, result.getValue());
    }

    // ================= SUBTRACTION =================

    @Test
    public void subtractWeightsSameUnit() {

        QuantityDTO w1 = new QuantityDTO(
                5.0,
                QuantityDTO.WeightUnit.KILOGRAM.getUnitName(),
                QuantityDTO.WeightUnit.KILOGRAM.getMeasurementType());

        QuantityDTO w2 = new QuantityDTO(
                2.0,
                QuantityDTO.WeightUnit.KILOGRAM.getUnitName(),
                QuantityDTO.WeightUnit.KILOGRAM.getMeasurementType());

        QuantityDTO result = controller.performSubtraction(w1, w2);

        assertEquals(3.0, result.getValue());
    }

    @Test
    public void subtractVolumesSameUnit() {

        QuantityDTO v1 = new QuantityDTO(
                5.0,
                QuantityDTO.VolumeUnit.LITRE.getUnitName(),
                QuantityDTO.VolumeUnit.LITRE.getMeasurementType());

        QuantityDTO v2 = new QuantityDTO(
                2.0,
                QuantityDTO.VolumeUnit.LITRE.getUnitName(),
                QuantityDTO.VolumeUnit.LITRE.getMeasurementType());

        QuantityDTO result = controller.performSubtraction(v1, v2);

        assertEquals(3.0, result.getValue());
    }

    // ================= DIVISION =================

    @Test
    public void divideWeightsSameUnit() {

        QuantityDTO w1 = new QuantityDTO(
                10.0,
                QuantityDTO.WeightUnit.KILOGRAM.getUnitName(),
                QuantityDTO.WeightUnit.KILOGRAM.getMeasurementType());

        QuantityDTO w2 = new QuantityDTO(
                5.0,
                QuantityDTO.WeightUnit.KILOGRAM.getUnitName(),
                QuantityDTO.WeightUnit.KILOGRAM.getMeasurementType());

        double result = controller.performDivision(w1, w2);

        assertEquals(2.0, result);
    }

    @Test
    public void divideVolumesSameUnit() {

        QuantityDTO v1 = new QuantityDTO(
                10.0,
                QuantityDTO.VolumeUnit.LITRE.getUnitName(),
                QuantityDTO.VolumeUnit.LITRE.getMeasurementType());

        QuantityDTO v2 = new QuantityDTO(
                5.0,
                QuantityDTO.VolumeUnit.LITRE.getUnitName(),
                QuantityDTO.VolumeUnit.LITRE.getMeasurementType());

        double result = controller.performDivision(v1, v2);

        assertEquals(2.0, result);
    }

    // ================= TEMPERATURE =================

    @Test
    public void testTemperatureComparison() {

        QuantityDTO celsius = new QuantityDTO(
                25.0,
                QuantityDTO.TemperatureUnit.CELSIUS.getUnitName(),
                QuantityDTO.TemperatureUnit.CELSIUS.getMeasurementType());

        QuantityDTO fahrenheit = new QuantityDTO(
                77.0,
                QuantityDTO.TemperatureUnit.FAHRENHEIT.getUnitName(),
                QuantityDTO.TemperatureUnit.FAHRENHEIT.getMeasurementType());

        assertTrue(controller.performComparison(celsius, fahrenheit));
    }

    @Test
    public void testTemperatureConversion() {

        QuantityDTO celsius = new QuantityDTO(
                100.0,
                QuantityDTO.TemperatureUnit.CELSIUS.getUnitName(),
                QuantityDTO.TemperatureUnit.CELSIUS.getMeasurementType());

        QuantityDTO fahrenheit = new QuantityDTO(
                0,
                QuantityDTO.TemperatureUnit.FAHRENHEIT.getUnitName(),
                QuantityDTO.TemperatureUnit.FAHRENHEIT.getMeasurementType());

        QuantityDTO result = controller.performConversion(celsius, fahrenheit);

        assertEquals(212.0, result.getValue());
    }

    @Test
    public void testTemperatureUnsupportedAddition() {

        QuantityDTO celsius = new QuantityDTO(
                25.0,
                QuantityDTO.TemperatureUnit.CELSIUS.getUnitName(),
                QuantityDTO.TemperatureUnit.CELSIUS.getMeasurementType());

        QuantityDTO fahrenheit = new QuantityDTO(
                77.0,
                QuantityDTO.TemperatureUnit.FAHRENHEIT.getUnitName(),
                QuantityDTO.TemperatureUnit.FAHRENHEIT.getMeasurementType());

        assertThrows(
                QuantityMeasurementException.class,
                () -> controller.performAddition(celsius, fahrenheit));
    }

    // ================= CROSS CATEGORY =================

    @Test
    public void preventCrossTypeComparison() {

        QuantityDTO length = new QuantityDTO(
                1.0,
                QuantityDTO.LengthUnit.FEET.getUnitName(),
                QuantityDTO.LengthUnit.FEET.getMeasurementType());

        QuantityDTO weight = new QuantityDTO(
                1.0,
                QuantityDTO.WeightUnit.KILOGRAM.getUnitName(),
                QuantityDTO.WeightUnit.KILOGRAM.getMeasurementType());

        assertFalse(controller.performComparison(length, weight));
    }

    @Test
    public void preventCrossTypeAddition() {

        QuantityDTO length = new QuantityDTO(
                1.0,
                QuantityDTO.LengthUnit.FEET.getUnitName(),
                QuantityDTO.LengthUnit.FEET.getMeasurementType());

        QuantityDTO weight = new QuantityDTO(
                1.0,
                QuantityDTO.WeightUnit.KILOGRAM.getUnitName(),
                QuantityDTO.WeightUnit.KILOGRAM.getMeasurementType());

        assertThrows(
                QuantityMeasurementException.class,
                () -> controller.performAddition(length, weight));
    }

}