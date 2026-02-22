package com.example.QuantityMeasurementApp;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class QuantityMeasurementAppTest{
    private static final double EPS = 1e-6;
    private QuantityMeasurementApp.QuantityLength q(double v, LengthUnit u){
        return new QuantityMeasurementApp.QuantityLength(v, u);
    }
    // UC8 – ENUM CONSTANT TESTS
    @Test
    void testLengthUnitEnum_FeetConstant(){
        assertEquals(1.0, LengthUnit.FEET.getConversionFactor(), EPS);
    }
    @Test
    void testLengthUnitEnum_InchesConstant(){
        assertEquals(1.0/12.0, LengthUnit.INCH.getConversionFactor(), EPS);
    }
    @Test
    void testLengthUnitEnum_YardsConstant(){
        assertEquals(3.0, LengthUnit.YARDS.getConversionFactor(), EPS);
    }
    @Test
    void testLengthUnitEnum_CentimetersConstant() {
        assertEquals(0.393701/12.0, LengthUnit.CENTIMETERS.getConversionFactor(), EPS);
    }
    // UC8 – TO BASE UNIT
       @Test
    void testConvertToBaseUnit_FeetToFeet(){
        assertEquals(5.0,
                LengthUnit.FEET.convertToBaseUnit(5.0),
                EPS);
    }
    @Test
    void testConvertToBaseUnit_InchesToFeet(){
        assertEquals(1.0,
                LengthUnit.INCH.convertToBaseUnit(12.0),
                EPS);
    }

    @Test
    void testConvertToBaseUnit_YardsToFeet(){
        assertEquals(3.0,
                LengthUnit.YARDS.convertToBaseUnit(1.0),
                EPS);
    }
    @Test
    void testConvertToBaseUnit_CentimetersToFeet() {
        assertEquals(1.0,
                LengthUnit.CENTIMETERS.convertToBaseUnit(30.48),
                1e-5);
    }
    // UC8 – FROM BASE UNIT
        @Test
    void testConvertFromBaseUnit_FeetToFeet() {
        assertEquals(2.0,
                LengthUnit.FEET.convertFromBaseUnit(2.0),
                EPS);
    }
    @Test
    void testConvertFromBaseUnit_FeetToInches(){
        assertEquals(12.0,
                LengthUnit.INCH.convertFromBaseUnit(1.0),
                EPS);
    }
    @Test
    void testConvertFromBaseUnit_FeetToYards(){
        assertEquals(1.0,
                LengthUnit.YARDS.convertFromBaseUnit(3.0),
                EPS);
    }
    @Test
    void testConvertFromBaseUnit_FeetToCentimeters(){
        assertEquals(30.48,
                LengthUnit.CENTIMETERS.convertFromBaseUnit(1.0),
                1e-5);
    }
    // UC8 – QUANTITY BEHAVIOR
        @Test
    void testQuantityLengthRefactored_Equality(){
        assertTrue(
                q(1.0, LengthUnit.FEET)
                        .equals(q(12.0, LengthUnit.INCH))
        );
    }
    @Test
    void testQuantityLengthRefactored_ConvertTo(){

        var result= q(1.0, LengthUnit.FEET)
                .convertTo(LengthUnit.INCH);

        assertTrue(result.equals(q(12.0, LengthUnit.INCH)));
    }
    @Test
    void testQuantityLengthRefactored_Add(){

        var result= q(1.0, LengthUnit.FEET)
                .add(q(12.0, LengthUnit.INCH), LengthUnit.FEET);

        assertTrue(result.equals(q(2.0, LengthUnit.FEET)));
    }
    @Test
    void testQuantityLengthRefactored_AddWithTargetUnit(){

        var result= q(1.0, LengthUnit.FEET)
                .add(q(12.0, LengthUnit.INCH), LengthUnit.YARDS);

        assertTrue(result.equals(q(2.0/3.0, LengthUnit.YARDS)));
    }
    // VALIDATION TESTS
        @Test
    void testQuantityLengthRefactored_NullUnit(){
        assertThrows(IllegalArgumentException.class,
                () -> new QuantityMeasurementApp.QuantityLength(1.0, null));
    }

    @Test
    void testQuantityLengthRefactored_InvalidValue(){
        assertThrows(IllegalArgumentException.class,
                () -> new QuantityMeasurementApp.QuantityLength(Double.NaN, LengthUnit.FEET));
    }
    // ROUND TRIP CONVERSION
       @Test
    void testRoundTripConversion_RefactoredDesign(){
        double original= 7.25;
        double inches=QuantityMeasurementApp.QuantityLength.convert(
                original, LengthUnit.FEET, LengthUnit.INCH);

        double back= QuantityMeasurementApp.QuantityLength.convert(
                inches, LengthUnit.INCH, LengthUnit.FEET);

        assertEquals(original, back, EPS);
    }
    // BACKWARD COMPATIBILITY
    @Test
    void testBackwardCompatibility_UC1Equality() {
        assertTrue(q(2, LengthUnit.FEET).equals(q(24, LengthUnit.INCH)));
    }
    @Test
    void testBackwardCompatibility_UC5Conversion() {

        double r=QuantityMeasurementApp.QuantityLength.convert(
                1, LengthUnit.YARDS, LengthUnit.INCH);

        assertEquals(36.0, r, EPS);
    }
    @Test
    void testBackwardCompatibility_UC6Addition() {

        var result= q(1, LengthUnit.FEET)
                .add(q(12, LengthUnit.INCH));

        assertTrue(result.equals(q(2, LengthUnit.FEET)));
    }
    @Test
    void testBackwardCompatibility_UC7AdditionWithTargetUnit() {

        var result=q(1, LengthUnit.FEET)
                .add(q(12, LengthUnit.INCH), LengthUnit.INCH);

        assertTrue(result.equals(q(24, LengthUnit.INCH)));
    }

}