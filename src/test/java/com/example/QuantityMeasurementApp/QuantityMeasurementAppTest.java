package com.example.QuantityMeasurementApp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPS = 1e-6;

           // Factory Methods 
    private Quantity<VolumeUnit> V(double v, VolumeUnit u) {
        return new Quantity<>(v, u);
    }

    private Quantity<LengthUnit> L(double v, LengthUnit u) {
        return new Quantity<>(v, u);
    }

    private Quantity<WeightUnit> W(double v, WeightUnit u) {
        return new Quantity<>(v, u);
    }

    //LENGTH TESTS

    @Test
    void testLengthEquality_CrossUnit() {
        assertTrue(L(1, LengthUnit.FEET)
                .equals(L(12, LengthUnit.INCH)));
    }

    @Test
    void testLengthConversion() {
        Quantity<LengthUnit> result =
                L(1, LengthUnit.FEET).convertTo(LengthUnit.INCH);

        assertTrue(result.equals(L(12, LengthUnit.INCH)));
    }

    @Test
    void testLengthAddition_DefaultUnit() {
        Quantity<LengthUnit> result =
                L(1, LengthUnit.FEET).add(L(12, LengthUnit.INCH));

        assertTrue(result.equals(L(2, LengthUnit.FEET)));
    }

    @Test
    void testLengthAddition_TargetUnit() {
        Quantity<LengthUnit> result =
                L(1, LengthUnit.FEET)
                        .add(L(12, LengthUnit.INCH), LengthUnit.INCH);

        assertTrue(result.equals(L(24, LengthUnit.INCH)));
    }

    // WEIGHT TESTS
    
    
    
    

    @Test
    void testWeightEquality_CrossUnit() {
        assertTrue(W(1, WeightUnit.KILOGRAM)
                .equals(W(1000, WeightUnit.GRAM)));
    }

    @Test
    void testWeightConversion() {
        Quantity<WeightUnit> result =
                W(1, WeightUnit.KILOGRAM)
                        .convertTo(WeightUnit.GRAM);

        assertTrue(result.equals(W(1000, WeightUnit.GRAM)));
    }

    @Test
    void testWeightAddition_DefaultUnit() {
        Quantity<WeightUnit> result =
                W(1, WeightUnit.KILOGRAM)
                        .add(W(1000, WeightUnit.GRAM));

        assertTrue(result.equals(W(2, WeightUnit.KILOGRAM)));
    }

    @Test
    void testWeightAddition_TargetUnit() {
        Quantity<WeightUnit> result =
                W(1, WeightUnit.KILOGRAM)
                        .add(W(1000, WeightUnit.GRAM), WeightUnit.GRAM);

        assertTrue(result.equals(W(2000, WeightUnit.GRAM)));
    }

             //VOLUME TESTS 
    @Test
    void testVolumeEquality_CrossUnit(){
        assertTrue(V(1, VolumeUnit.LITRE)
                .equals(V(1000, VolumeUnit.MILLILITRE)));
    }

    @Test
    void testVolumeConversion(){
        Quantity<VolumeUnit> result =
                V(1, VolumeUnit.LITRE)
                        .convertTo(VolumeUnit.MILLILITRE);

        assertTrue(result.equals(V(1000, VolumeUnit.MILLILITRE)));
    }

    @Test
    void testVolumeAddition_TargetUnit(){
        Quantity<VolumeUnit> result =
                V(1, VolumeUnit.LITRE)
                        .add(V(1000, VolumeUnit.MILLILITRE), VolumeUnit.MILLILITRE);

        assertTrue(result.equals(V(2000, VolumeUnit.MILLILITRE)));
    }

    //CROSS CATEGORY SAFETY
    @Test
    void testCrossCategoryEquality_ShouldBeFalse(){
        Quantity<LengthUnit> length = L(1, LengthUnit.FEET);
        Quantity<WeightUnit> weight = W(1, WeightUnit.KILOGRAM);

        assertFalse(length.equals(weight));
    }
    //CONSTRUCTOR VALIDATION
    @Test
    void testConstructor_NullUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(1.0, null));
    }

    @Test
    void testConstructor_NaNValue() {
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(Double.NaN, LengthUnit.FEET));
    }

    @Test
    void testConstructor_InfiniteValue(){
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(Double.POSITIVE_INFINITY, LengthUnit.FEET));
    }
//ROUND TRIP

    @Test
    void testRoundTrip_Length(){
        Quantity<LengthUnit> original = L(5.5, LengthUnit.FEET);
        Quantity<LengthUnit> back =
                original.convertTo(LengthUnit.INCH)
                        .convertTo(LengthUnit.FEET);
        assertTrue(original.equals(back));
    }
    @Test
    void testRoundTrip_Weight(){
        Quantity<WeightUnit> original = W(2.3, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> back =
                original.convertTo(WeightUnit.POUND)
                        .convertTo(WeightUnit.KILOGRAM);
        assertTrue(original.equals(back));
    }

    //EDGE CASES 
    @Test
    void testZeroConversion(){
        Quantity<LengthUnit> zero =
                L(0, LengthUnit.FEET)
                        .convertTo(LengthUnit.INCH);

        assertTrue(zero.equals(L(0, LengthUnit.INCH)));
    }
    @Test
    void testNegativeAddition(){
        Quantity<LengthUnit> result =
                L(5, LengthUnit.FEET)
                        .add(L(-2, LengthUnit.FEET));
        assertTrue(result.equals(L(3, LengthUnit.FEET)));
    }
    @Test
    void testLargeValues(){
        Quantity<WeightUnit> result =
                W(1e6, WeightUnit.KILOGRAM)
                        .add(W(1e6, WeightUnit.KILOGRAM));
        assertTrue(result.equals(W(2e6, WeightUnit.KILOGRAM)));
    }
// HASHCODE CONTRACT 
    @Test
    void testEqualsHashCodeConsistency(){
        Quantity<LengthUnit> q1 = L(1, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = L(12, LengthUnit.INCH);
        assertTrue(q1.equals(q2));
        assertEquals(q1.hashCode(), q2.hashCode());
    }
}