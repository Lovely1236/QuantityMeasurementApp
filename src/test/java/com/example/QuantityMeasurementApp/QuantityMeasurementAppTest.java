package com.example.QuantityMeasurementApp;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    private static final double EPS=1e-6;

    private QuantityWeight w(double v, WeightUnit u) {
        return new QuantityWeight(v, u);
    }

    private QuantityMeasurementApp.QuantityLength l(double v, LengthUnit u) {
        return new QuantityMeasurementApp.QuantityLength(v, u);
    }
    // EQUALITY TESTS
    @Test
    void testEquality_KilogramToKilogram_SameValue() {
        assertTrue(w(1, WeightUnit.KILOGRAM).equals(w(1, WeightUnit.KILOGRAM)));
    }

    @Test
    void testEquality_KilogramToKilogram_DifferentValue() {
        assertFalse(w(1, WeightUnit.KILOGRAM).equals(w(2, WeightUnit.KILOGRAM)));
    }
    @Test
    void testEquality_KilogramToGram_EquivalentValue() {
        assertTrue(w(1, WeightUnit.KILOGRAM).equals(w(1000, WeightUnit.GRAM)));
    }
    @Test
    void testEquality_GramToKilogram_EquivalentValue() {
        assertTrue(w(1000, WeightUnit.GRAM).equals(w(1, WeightUnit.KILOGRAM)));
    }

    @Test
    void testEquality_KilogramToPound_EquivalentValue() {
        assertTrue(w(1, WeightUnit.KILOGRAM)
                .equals(w(2.20462, WeightUnit.POUND)));
    }
    @Test
    void testEquality_PoundToGram_EquivalentValue() {
        assertTrue(w(1, WeightUnit.POUND)
                .equals(w(453.592, WeightUnit.GRAM)));
    }

    @Test
    void testEquality_WeightVsLength_Incompatible(){
        assertFalse(w(1, WeightUnit.KILOGRAM)
                .equals(l(1, LengthUnit.FEET)));
    }

    @Test
    void testEquality_NullComparison(){
        assertFalse(w(1, WeightUnit.KILOGRAM).equals(null));
    }

    @Test
    void testEquality_SameReference(){
        QuantityWeight x = w(5, WeightUnit.KILOGRAM);
        assertTrue(x.equals(x));
    }

    @Test
    void testEquality_ZeroValue() {
        assertTrue(w(0, WeightUnit.KILOGRAM).equals(w(0, WeightUnit.GRAM)));
    }

    @Test
    void testEquality_NegativeWeight() {
        assertTrue(w(-1, WeightUnit.KILOGRAM).equals(w(-1000, WeightUnit.GRAM)));
    }

    @Test
    void testEquality_LargeWeightValue() {
        assertTrue(w(1000, WeightUnit.KILOGRAM)
                .equals(w(1_000_000, WeightUnit.GRAM)));
    }

    @Test
    void testEquality_SmallWeightValue(){
        assertTrue(w(0.001, WeightUnit.KILOGRAM)
                .equals(w(1, WeightUnit.GRAM)));
    }
    // CONVERSION TESTS
    @Test
    void testConversion_KilogramToGram() {
        assertTrue(w(1, WeightUnit.KILOGRAM)
                .convertTo(WeightUnit.GRAM)
                .equals(w(1000, WeightUnit.GRAM)));
    }

    @Test
    void testConversion_PoundToKilogram(){
        assertTrue(w(2.20462, WeightUnit.POUND)
                .convertTo(WeightUnit.KILOGRAM)
                .equals(w(1, WeightUnit.KILOGRAM)));
    }

    @Test
    void testConversion_KilogramToPound(){
        assertTrue(w(1, WeightUnit.KILOGRAM)
                .convertTo(WeightUnit.POUND)
                .equals(w(2.20462, WeightUnit.POUND)));
    }

    @Test
    void testConversion_SameUnit(){
        assertTrue(w(5, WeightUnit.KILOGRAM)
                .convertTo(WeightUnit.KILOGRAM)
                .equals(w(5, WeightUnit.KILOGRAM)));
    }

    @Test
    void testConversion_ZeroValue() {
        assertTrue(w(0, WeightUnit.KILOGRAM)
                .convertTo(WeightUnit.GRAM)
                .equals(w(0, WeightUnit.GRAM)));
    }
    @Test
    void testConversion_NegativeValue() {
        assertTrue(w(-1, WeightUnit.KILOGRAM)
                .convertTo(WeightUnit.GRAM)
                .equals(w(-1000, WeightUnit.GRAM)));
    }
    @Test
    void testConversion_RoundTrip(){

        QuantityWeight original= w(1.5, WeightUnit.KILOGRAM);

        QuantityWeight roundTrip =
                original.convertTo(WeightUnit.GRAM)
                        .convertTo(WeightUnit.KILOGRAM);

        assertEquals(original, roundTrip);
    }
    // ADDITION (IMPLICIT UNIT)
        @Test
    void testAddition_SameUnit_KilogramPlusKilogram() {
        assertTrue(w(1, WeightUnit.KILOGRAM)
                .add(w(2, WeightUnit.KILOGRAM))
                .equals(w(3, WeightUnit.KILOGRAM)));
    }
    @Test
    void testAddition_CrossUnit_KilogramPlusGram() {
        assertTrue(w(1, WeightUnit.KILOGRAM)
                .add(w(1000, WeightUnit.GRAM))
                .equals(w(2, WeightUnit.KILOGRAM)));
    }
    @Test
    void testAddition_CrossUnit_PoundPlusKilogram() {
        assertTrue(w(2.20462, WeightUnit.POUND)
                .add(w(1, WeightUnit.KILOGRAM))
                .equals(w(4.40924, WeightUnit.POUND)));
    }
    // ADDITION (EXPLICIT TARGET)

    @Test
    void testAddition_ExplicitTargetUnit_Gram() {
        assertTrue(w(1, WeightUnit.KILOGRAM)
                .add(w(1000, WeightUnit.GRAM), WeightUnit.GRAM)
                .equals(w(2000, WeightUnit.GRAM)));
    }
    @Test
    void testAddition_Commutativity() {

        QuantityWeight a= w(1, WeightUnit.KILOGRAM);
        QuantityWeight b= w(1000, WeightUnit.GRAM);
        assertEquals(
                a.add(b).convertTo(WeightUnit.KILOGRAM),
                b.add(a).convertTo(WeightUnit.KILOGRAM)
        );
    }
    @Test
    void testAddition_WithZero() {
        assertTrue(w(5, WeightUnit.KILOGRAM)
                .add(w(0, WeightUnit.GRAM))
                .equals(w(5, WeightUnit.KILOGRAM)));
    }
    @Test
    void testAddition_NegativeValues() {
        assertTrue(w(5, WeightUnit.KILOGRAM)
                .add(w(-2000, WeightUnit.GRAM))
                .equals(w(3, WeightUnit.KILOGRAM)));
    }
    @Test
    void testAddition_LargeValues() {
        assertTrue(w(1e6, WeightUnit.KILOGRAM)
                .add(w(1e6, WeightUnit.KILOGRAM))
                .equals(w(2e6, WeightUnit.KILOGRAM)));
    }
    // VALIDATION TESTS
    @Test
    void testNullUnitConstructor() {
        assertThrows(IllegalArgumentException.class,
                () -> new QuantityWeight(1, null));
    }
    @Test
    void testInvalidValueConstructor() {
        assertThrows(IllegalArgumentException.class,
                () -> new QuantityWeight(Double.NaN, WeightUnit.KILOGRAM));
    }

}