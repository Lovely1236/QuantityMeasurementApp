import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest{
    // ================= SUBTRACTION TESTS ==================
    @Test
    void testSubtraction_SameUnit_FeetMinusFeet(){
        var r = new Quantity<>(10.0, LengthUnit.FEET)
                .subtract(new Quantity<>(5.0, LengthUnit.FEET));
        assertEquals(new Quantity<>(5.0, LengthUnit.FEET), r);
    }
    @Test
    void testSubtraction_SameUnit_LitreMinusLitre(){
        var r = new Quantity<>(10.0, VolumeUnit.LITRE)
                .subtract(new Quantity<>(3.0, VolumeUnit.LITRE));
        assertEquals(new Quantity<>(7.0, VolumeUnit.LITRE), r);
    }
    @Test
    void testSubtraction_CrossUnit_FeetMinusInches() {
        var r = new Quantity<>(10.0, LengthUnit.FEET)
                .subtract(new Quantity<>(6.0, LengthUnit.INCH));
        assertEquals(9.5, r.getValue(), 0.01);
    }

    
    @Test
    void testSubtraction_CrossUnit_InchesMinusFeet() {
        var r = new Quantity<>(120.0, LengthUnit.INCH)
                .subtract(new Quantity<>(5.0, LengthUnit.FEET));
        assertEquals(60.0, r.getValue(), 0.01);
    }
    @Test
    void testSubtraction_ExplicitTargetUnit_Feet(){
        var r = new Quantity<>(10.0, LengthUnit.FEET)
                .subtract(new Quantity<>(6.0, LengthUnit.INCH), LengthUnit.FEET);
        assertEquals(9.5, r.getValue(), 0.01);
    }
    

    @Test
    void testSubtraction_ExplicitTargetUnit_Inches(){
        var r = new Quantity<>(10.0, LengthUnit.FEET)
                .subtract(new Quantity<>(6.0, LengthUnit.INCH), LengthUnit.INCH);
        assertEquals(114.0, r.getValue(), 0.01);
    }
    @Test
    void testSubtraction_ExplicitTargetUnit_Millilitre() {
        var r = new Quantity<>(5.0, VolumeUnit.LITRE)
                .subtract(new Quantity<>(2.0, VolumeUnit.LITRE), VolumeUnit.MILLILITRE);
        assertEquals(3000.0, r.getValue(), 0.01);
    }

    @Test
    void testSubtraction_ResultingInNegative() {
        var r = new Quantity<>(5.0, LengthUnit.FEET)
                .subtract(new Quantity<>(10.0, LengthUnit.FEET));
        assertEquals(-5.0, r.getValue(), 0.01);
    }

    @Test
    void testSubtraction_ResultingInZero() {
        var r = new Quantity<>(10.0, LengthUnit.FEET)
                .subtract(new Quantity<>(120.0, LengthUnit.INCH));
        assertEquals(0.0, r.getValue(), 0.01);
    }

    @Test
    void testSubtraction_WithZeroOperand() {
        var r = new Quantity<>(5.0, LengthUnit.FEET)
                .subtract(new Quantity<>(0.0, LengthUnit.INCH));
        assertEquals(5.0, r.getValue(), 0.01);
    }
    

    @Test
    void testSubtraction_WithNegativeValues() {
        var r = new Quantity<>(5.0, LengthUnit.FEET)
                .subtract(new Quantity<>(-2.0, LengthUnit.FEET));
        assertEquals(7.0, r.getValue(), 0.01);
    }
    @Test
    void testSubtraction_NonCommutative() {
        var a = new Quantity<>(10.0, LengthUnit.FEET);
        var b = new Quantity<>(5.0, LengthUnit.FEET);
        assertNotEquals(a.subtract(b), b.subtract(a));
    }
    @Test
    void testSubtraction_WithLargeValues() {
        var r = new Quantity<>(1e6, WeightUnit.KILOGRAM)
                .subtract(new Quantity<>(5e5, WeightUnit.KILOGRAM));
        assertEquals(5e5, r.getValue(), 0.01);
    }

    @Test
    void testSubtraction_WithSmallValues() {
        var r = new Quantity<>(0.001, LengthUnit.FEET)
                .subtract(new Quantity<>(0.0005, LengthUnit.FEET));
        assertEquals(0.0005, r.getValue(), 0.0001);
    }

    @Test
    void testSubtraction_NullOperand() {
        var q = new Quantity<>(10.0, LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, () -> q.subtract(null));
    }

    @Test
    void testSubtraction_NullTargetUnit() {
        var q = new Quantity<>(10.0, LengthUnit.FEET);
        var o = new Quantity<>(5.0, LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, () -> q.subtract(o, null));
    }

    @Test
    void testSubtraction_CrossCategory() {
        var q = new Quantity<>(10.0, LengthUnit.FEET);
        var w = new Quantity<>(5.0, WeightUnit.KILOGRAM);
        assertThrows(IllegalArgumentException.class, () -> q.subtract((Quantity) w));
    }
    @Test
    void testSubtraction_ChainedOperations() {
        var r = new Quantity<>(10.0, LengthUnit.FEET)
                .subtract(new Quantity<>(2.0, LengthUnit.FEET))
                .subtract(new Quantity<>(1.0, LengthUnit.FEET));
        assertEquals(7.0, r.getValue(), 0.01);
    }
    // ==================== DIVISION TESTS ==================
        @Test
    void testDivision_SameUnit(){
        assertEquals(5.0,
                new Quantity<>(10.0, LengthUnit.FEET)
                        .divide(new Quantity<>(2.0, LengthUnit.FEET)), 0.0001);
    }
    @Test
    void testDivision_CrossUnit() {
        assertEquals(1.0,
                new Quantity<>(24.0, LengthUnit.INCH)
                        .divide(new Quantity<>(2.0, LengthUnit.FEET)), 0.0001);
    }

    @Test
    void testDivision_RatioGreaterThanOne() {
        assertEquals(2.0,
                new Quantity<>(10.0, LengthUnit.FEET)
                        .divide(new Quantity<>(5.0, LengthUnit.FEET)), 0.0001);
    }
    @Test
    void testDivision_RatioLessThanOne() {
        assertEquals(0.5,
                new Quantity<>(5.0, LengthUnit.FEET)
                        .divide(new Quantity<>(10.0, LengthUnit.FEET)), 0.0001);
    }
    @Test
    void testDivision_RatioEqualToOne() {
        assertEquals(1.0,
                new Quantity<>(10.0, LengthUnit.FEET)
                        .divide(new Quantity<>(10.0, LengthUnit.FEET)), 0.0001);
    }

    @Test
    void testDivision_NonCommutative(){
        var a = new Quantity<>(10.0, LengthUnit.FEET);
        var b = new Quantity<>(5.0, LengthUnit.FEET);
        assertNotEquals(a.divide(b), b.divide(a));
    }

    @Test
    void testDivision_ByZero(){
        var q = new Quantity<>(10.0, LengthUnit.FEET);
        var zero = new Quantity<>(0.0, LengthUnit.FEET);
        assertThrows(ArithmeticException.class, () -> q.divide(zero));
    }
    @Test
    void testDivision_NullOperand() {
        var q = new Quantity<>(10.0, LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, () -> q.divide(null));
    }
    @Test
    void testDivision_CrossCategory(){
        var q = new Quantity<>(10.0, LengthUnit.FEET);
        var w = new Quantity<>(5.0, WeightUnit.KILOGRAM);
        assertThrows(IllegalArgumentException.class, () -> q.divide((Quantity) w));
    }

    @Test
    void testDivision_LargeRatio() {
        assertEquals(1e6,
                new Quantity<>(1e6, WeightUnit.KILOGRAM)
                        .divide(new Quantity<>(1.0, WeightUnit.KILOGRAM)), 0.1);
    }

    @Test
    void testDivision_SmallRatio(){
        assertEquals(1e-6,
                new Quantity<>(1.0, WeightUnit.KILOGRAM)
                        .divide(new Quantity<>(1e6, WeightUnit.KILOGRAM)), 1e-9);
    }
    // ==================== INTEGRATION =====================
    @Test
    void testSubtractionAddition_Inverse() {
        var a = new Quantity<>(10.0, LengthUnit.FEET);
        var b = new Quantity<>(3.0, LengthUnit.FEET);
        assertEquals(a, a.add(b).subtract(b));
    }
    @Test
    void testSubtractionAndDivision_Integration(){
        var r = new Quantity<>(10.0, LengthUnit.FEET)
                .subtract(new Quantity<>(2.0, LengthUnit.FEET))
                .divide(new Quantity<>(4.0, LengthUnit.FEET));
        assertEquals(2.0, r, 0.0001);
    }

    @Test
    void testImmutability_Subtraction() {
        var a = new Quantity<>(10.0, LengthUnit.FEET);
        var b = new Quantity<>(5.0, LengthUnit.FEET);
       
        a.subtract(b);
        assertEquals(10.0, a.getValue());
    }
    @Test
    void testImmutability_Division(){
        var a = new Quantity<>(10.0, LengthUnit.FEET);
        var b = new Quantity<>(2.0, LengthUnit.FEET);
        
        a.divide(b);
        assertEquals(10.0, a.getValue());
    }
}