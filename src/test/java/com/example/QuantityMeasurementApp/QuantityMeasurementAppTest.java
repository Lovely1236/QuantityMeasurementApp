package com.example.QuantityMeasurementApp;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class QuantityMeasurementAppTest {
    private static final double EPS = 1e-6;
    private QuantityMeasurementApp.QuantityLength q(double v,
            QuantityMeasurementApp.LengthUnit u){
        return new QuantityMeasurementApp.QuantityLength(v, u);
    }
    @Test
    void testFeetEquality(){
        assertTrue(q(1, QuantityMeasurementApp.LengthUnit.FEET)
                .equals(q(1, QuantityMeasurementApp.LengthUnit.FEET)));
    }
    @Test
    void testInchesEquality(){
        assertTrue(q(5, QuantityMeasurementApp.LengthUnit.INCH)
                .equals(q(5, QuantityMeasurementApp.LengthUnit.INCH)));
    }
    @Test
    void testFeetInchesComparison(){
        assertTrue(q(1, QuantityMeasurementApp.LengthUnit.FEET)
                .equals(q(12, QuantityMeasurementApp.LengthUnit.INCH)));
    }
    @Test
    void testFeetInequality(){
        assertFalse(q(1, QuantityMeasurementApp.LengthUnit.FEET)
                .equals(q(2, QuantityMeasurementApp.LengthUnit.FEET)));
    }
    @Test
    void testInchesInequality(){
        assertFalse(q(1, QuantityMeasurementApp.LengthUnit.INCH)
                .equals(q(2, QuantityMeasurementApp.LengthUnit.INCH)));
    }
    @Test
    void yardEquals36Inches(){
        assertTrue(q(1, QuantityMeasurementApp.LengthUnit.YARDS)
                .equals(q(36, QuantityMeasurementApp.LengthUnit.INCH)));
    }
    @Test
    void threeFeetEqualsOneYard(){
        assertTrue(q(3, QuantityMeasurementApp.LengthUnit.FEET)
                .equals(q(1, QuantityMeasurementApp.LengthUnit.YARDS)));
    }
    @Test
    void centimeterEqualsPoint393701Inches(){
        assertTrue(q(1, QuantityMeasurementApp.LengthUnit.CENTIMETERS)
                .equals(q(0.393701, QuantityMeasurementApp.LengthUnit.INCH)));
    }
    @Test
    void equalsReturnsFalseForNull(){
        assertFalse(q(1, QuantityMeasurementApp.LengthUnit.FEET).equals(null));
    }
    @Test
    void referenceEqualitySameObject(){
        var x = q(5, QuantityMeasurementApp.LengthUnit.FEET);
        assertTrue(x.equals(x));
    }
    @Test
    void reflexiveSymmetricAndTransitiveProperty(){
        var a = q(1, QuantityMeasurementApp.LengthUnit.YARDS);
        var b = q(3, QuantityMeasurementApp.LengthUnit.FEET);
        var c = q(36, QuantityMeasurementApp.LengthUnit.INCH);
        assertTrue(a.equals(b));
        assertTrue(b.equals(c));
        assertTrue(a.equals(c));
    }
    //UC5--CONVERSION
    @Test
    void convertFeetToInches(){
        double r = QuantityMeasurementApp.QuantityLength.convert(
                1,
                QuantityMeasurementApp.LengthUnit.FEET,
                QuantityMeasurementApp.LengthUnit.INCH);
        assertEquals(12.0, r, EPS);
    }
    @Test
    void convertYardsToFeet(){
        double r = QuantityMeasurementApp.QuantityLength.convert(
                3,
                QuantityMeasurementApp.LengthUnit.YARDS,
                QuantityMeasurementApp.LengthUnit.FEET);
        assertEquals(9.0, r, EPS);
    }
    @Test
    void convertInchesToYards(){
        double r = QuantityMeasurementApp.QuantityLength.convert(
                36,
                QuantityMeasurementApp.LengthUnit.INCH,
                QuantityMeasurementApp.LengthUnit.YARDS);
        assertEquals(1.0, r, EPS);
    }
    @Test
    void convertCentimeterToInches() {
        double r = QuantityMeasurementApp.QuantityLength.convert(
                2.54,
                QuantityMeasurementApp.LengthUnit.CENTIMETERS,
                QuantityMeasurementApp.LengthUnit.INCH);
        assertEquals(1.0, r, 1e-5);
    }
    @Test
    void conversionRoundTrip(){
        double v = 7.25;
        double inches = QuantityMeasurementApp.QuantityLength.convert(
                v,
                QuantityMeasurementApp.LengthUnit.FEET,
                QuantityMeasurementApp.LengthUnit.INCH);

        double back = QuantityMeasurementApp.QuantityLength.convert(
                inches,
                QuantityMeasurementApp.LengthUnit.INCH,
                QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals(v, back, EPS);
    }
    @Test
    void conversionInvalidUnitThrows(){
        assertThrows(IllegalArgumentException.class,
                () -> QuantityMeasurementApp.QuantityLength.convert(
                        1, null,
                        QuantityMeasurementApp.LengthUnit.FEET));
    }
    //UC6--ADDITION
    @Test
    void testAddition_SameUnit_FeetPlusFeet(){
        assertTrue(q(1, QuantityMeasurementApp.LengthUnit.FEET)
                .add(q(2, QuantityMeasurementApp.LengthUnit.FEET))
                .equals(q(3, QuantityMeasurementApp.LengthUnit.FEET)));
    }
    @Test
    void testAddition_SameUnit_InchPlusInch() {
        assertTrue(q(6, QuantityMeasurementApp.LengthUnit.INCH)
                .add(q(6, QuantityMeasurementApp.LengthUnit.INCH))
                .equals(q(12, QuantityMeasurementApp.LengthUnit.INCH)));
    }
    @Test
    void testAddition_CrossUnit_FeetPlusInches() {
        assertTrue(q(1, QuantityMeasurementApp.LengthUnit.FEET)
                .add(q(12, QuantityMeasurementApp.LengthUnit.INCH))
                .equals(q(2, QuantityMeasurementApp.LengthUnit.FEET)));
    }

    @Test
    void testAddition_CrossUnit_InchPlusFeet(){
        assertTrue(q(12, QuantityMeasurementApp.LengthUnit.INCH)
                .add(q(1, QuantityMeasurementApp.LengthUnit.FEET))
                .equals(q(24, QuantityMeasurementApp.LengthUnit.INCH)));
    }
    @Test
    void testAddition_CrossUnit_YardPlusFeet() {
        assertTrue(q(1, QuantityMeasurementApp.LengthUnit.YARDS)
                .add(q(3, QuantityMeasurementApp.LengthUnit.FEET))
                .equals(q(2, QuantityMeasurementApp.LengthUnit.YARDS)));
    }
    @Test
    void testAddition_CrossUnit_CentimeterPlusInch(){
        assertTrue(q(2.54, QuantityMeasurementApp.LengthUnit.CENTIMETERS)
                .add(q(1, QuantityMeasurementApp.LengthUnit.INCH))
                .equals(q(5.08, QuantityMeasurementApp.LengthUnit.CENTIMETERS)));
    }
    @Test
    void testAddition_WithZero() {
        assertTrue(q(5, QuantityMeasurementApp.LengthUnit.FEET)
                .add(q(0, QuantityMeasurementApp.LengthUnit.INCH))
                .equals(q(5, QuantityMeasurementApp.LengthUnit.FEET)));
    }
    @Test
    void testAddition_NegativeValues() {
        assertTrue(q(5, QuantityMeasurementApp.LengthUnit.FEET)
                .add(q(-2, QuantityMeasurementApp.LengthUnit.FEET))
                .equals(q(3, QuantityMeasurementApp.LengthUnit.FEET)));
    }
    @Test
    void testAddition_NullSecondOperand() {
        assertThrows(IllegalArgumentException.class,
                () -> q(1, QuantityMeasurementApp.LengthUnit.FEET).add(null));
    }
    @Test
    void testAddition_LargeValues(){
        assertTrue(q(1e6, QuantityMeasurementApp.LengthUnit.FEET)
                .add(q(1e6, QuantityMeasurementApp.LengthUnit.FEET))
                .equals(q(2e6, QuantityMeasurementApp.LengthUnit.FEET)));
    }
    @Test
    void testAddition_SmallValues(){
        assertTrue(q(0.001, QuantityMeasurementApp.LengthUnit.FEET)
                .add(q(0.002, QuantityMeasurementApp.LengthUnit.FEET))
                .equals(q(0.003, QuantityMeasurementApp.LengthUnit.FEET)));
    }
}