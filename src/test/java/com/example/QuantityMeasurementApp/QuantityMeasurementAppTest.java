package com.example.QuantityMeasurementApp; 
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class QuantityMeasurementAppTest{
    private static final double EPS = 0.01;

    // ================= TEMPERATURE EQUALITY =================
    @Test
    void testTemperatureEquality_CelsiusToCelsius(){
        assertTrue(
                new Quantity<>(0.0, TemperatureUnit.CELSIUS)
                        .equals(new Quantity<>(0.0, TemperatureUnit.CELSIUS))
        );
    }
    @Test
    void testTemperatureEquality_FahrenheitToFahrenheit(){
        assertTrue(
                new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT)
                        .equals(new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT))
        );
    }
    @Test
    void testTemperatureEquality_CelsiusToFahrenheit_Zero(){
        assertTrue(
                new Quantity<>(0.0, TemperatureUnit.CELSIUS)
                        .equals(new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT))
        );
    }

    @Test
    void testTemperatureEquality_CelsiusToFahrenheit_BoilingPoint(){
        assertTrue(
                new Quantity<>(100.0, TemperatureUnit.CELSIUS)
                        .equals(new Quantity<>(212.0, TemperatureUnit.FAHRENHEIT))
        );
    }

    @Test
    void testTemperatureEquality_NegativeForty(){
        assertTrue(
                new Quantity<>(-40.0, TemperatureUnit.CELSIUS)
                        .equals(new Quantity<>(-40.0, TemperatureUnit.FAHRENHEIT))
        );
    }

    @Test
    void testTemperatureEquality_ReflexiveProperty(){
        Quantity<TemperatureUnit> t =
                new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        assertTrue(t.equals(t));
    }

    @Test
    void testTemperatureEquality_SymmetricProperty(){

        Quantity<TemperatureUnit> a =
                new Quantity<>(0.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> b =
                new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

        assertTrue(a.equals(b));
        assertTrue(b.equals(a));
    }
    @Test
    void testTemperatureInequality_DifferentValues() {

        assertFalse(
                new Quantity<>(50.0, TemperatureUnit.CELSIUS)
                        .equals(new Quantity<>(100.0, TemperatureUnit.CELSIUS))
        );
    }
    // ================= TEMPERATURE CONVERSION =================

    @Test
    void testConversion_CelsiusToFahrenheit() {

        Quantity<TemperatureUnit> result =
                new Quantity<>(100.0, TemperatureUnit.CELSIUS)
                        .convertTo(TemperatureUnit.FAHRENHEIT);

        assertEquals(212.0, result.getValue(), EPS);
    }

    @Test
    void testConversion_FahrenheitToCelsius() {

        Quantity<TemperatureUnit> result =
                new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT)
                        .convertTo(TemperatureUnit.CELSIUS);

        assertEquals(0.0, result.getValue(), EPS);
    }

    @Test
    void testConversion_RoundTrip() {

        Quantity<TemperatureUnit> original =
                new Quantity<>(25.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> back =
                original.convertTo(TemperatureUnit.FAHRENHEIT)
                        .convertTo(TemperatureUnit.CELSIUS);

        assertEquals(original.getValue(), back.getValue(), EPS);
    }

    @Test
    void testConversion_NegativeTemperature() {

        Quantity<TemperatureUnit> result =
                new Quantity<>(-40.0, TemperatureUnit.CELSIUS)
                        .convertTo(TemperatureUnit.FAHRENHEIT);

        assertEquals(-40.0, result.getValue(), EPS);
    }

    @Test
    void testConversion_LargeTemperature() {

        Quantity<TemperatureUnit> result =
                new Quantity<>(1000.0, TemperatureUnit.CELSIUS)
                        .convertTo(TemperatureUnit.FAHRENHEIT);

        assertEquals(1832.0, result.getValue(), EPS);
    }

    @Test
    void testConversion_SameUnit() {

        Quantity<TemperatureUnit> result =
                new Quantity<>(25.0, TemperatureUnit.CELSIUS)
                        .convertTo(TemperatureUnit.CELSIUS);

        assertEquals(25.0, result.getValue(), EPS);
    }

    // ================= UNSUPPORTED OPERATIONS =================

    @Test
    void testTemperatureUnsupported_Add() {

        assertThrows(
                UnsupportedOperationException.class,
                () -> new Quantity<>(100.0, TemperatureUnit.CELSIUS)
                        .add(new Quantity<>(50.0, TemperatureUnit.CELSIUS))
        );
    }

    @Test
    void testTemperatureUnsupported_Subtract() {

        assertThrows(
                UnsupportedOperationException.class,
                () -> new Quantity<>(100.0, TemperatureUnit.CELSIUS)
                        .subtract(new Quantity<>(50.0, TemperatureUnit.CELSIUS))
        );
    }

    @Test
    void testTemperatureUnsupported_Divide(){

        assertThrows(
                UnsupportedOperationException.class,
                () -> new Quantity<>(100.0, TemperatureUnit.CELSIUS)
                        .divide(new Quantity<>(50.0, TemperatureUnit.CELSIUS))
        );
    }

    @Test
    void testTemperatureUnsupported_AddDifferentUnits() {

        assertThrows(
                UnsupportedOperationException.class,
                () -> new Quantity<>(100.0, TemperatureUnit.CELSIUS)
                        .add(new Quantity<>(122.0, TemperatureUnit.FAHRENHEIT))
        );
    }

    // ================= CROSS CATEGORY TESTS =================

    @Test
    void testTemperatureVsLengthComparison(){

        assertFalse(
                new Quantity<>(100.0, TemperatureUnit.CELSIUS)
                        .equals(new Quantity<>(100.0, LengthUnit.FEET))
        );
    }
    @Test
    void testTemperatureVsWeightComparison() {

        assertFalse(
                new Quantity<>(50.0, TemperatureUnit.CELSIUS)
                        .equals(new Quantity<>(50.0, WeightUnit.KILOGRAM))
        );
    }

    @Test
    void testTemperatureVsVolumeComparison() {

        assertFalse(
                new Quantity<>(25.0, TemperatureUnit.CELSIUS)
                        .equals(new Quantity<>(25.0, VolumeUnit.LITRE))
        );
    }

    // ================= OPERATION SUPPORT METHODS =================

    @Test
    void testTemperatureUnitSupportsArithmeticFalse() {

        assertFalse(TemperatureUnit.CELSIUS.supportsArithmetic());
    }

    @Test
    void testLengthUnitSupportsArithmeticTrue() {

        assertTrue(LengthUnit.FEET.supportsArithmetic());
    }

    @Test
    void testWeightUnitSupportsArithmeticTrue() {

        assertTrue(WeightUnit.KILOGRAM.supportsArithmetic());
    }

    @Test
    void testVolumeUnitSupportsArithmeticTrue() {

        assertTrue(VolumeUnit.LITRE.supportsArithmetic());
    }

    // ================= EDGE CASES =================

    @Test
    void testTemperatureAbsoluteZero() {

        Quantity<TemperatureUnit> result =
                new Quantity<>(-273.15, TemperatureUnit.CELSIUS)
                        .convertTo(TemperatureUnit.FAHRENHEIT);

        assertEquals(-459.67, result.getValue(), EPS);
    }

    @Test
    void testTemperaturePrecision() {

        Quantity<TemperatureUnit> result =
                new Quantity<>(50.0, TemperatureUnit.CELSIUS)
                        .convertTo(TemperatureUnit.FAHRENHEIT);

        assertEquals(122.0, result.getValue(), EPS);
    }

    @Test
    void testTemperatureSmallDifference() {

        Quantity<TemperatureUnit> a =
                new Quantity<>(0.0001, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> b =
                new Quantity<>(32.00018, TemperatureUnit.FAHRENHEIT);

        assertTrue(a.equals(b));
    }

    // ================= VALIDATION =================

    @Test
    void testConstructor_NullUnit() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Quantity<>(100.0, null)
        );
    }
    @Test
    void testConstructor_InvalidValue(){

        assertThrows(
                IllegalArgumentException.class,
                () -> new Quantity<>(Double.NaN, TemperatureUnit.CELSIUS)
        );
    }
    @Test
    void testEquals_NullComparison(){

        Quantity<TemperatureUnit> t =
                new Quantity<>(100.0, TemperatureUnit.CELSIUS);

        assertFalse(t.equals(null));
    }

}