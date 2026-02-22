package com.example.QuantityMeasurementApp;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    private static final double EPS = 1e-6;

    private QuantityMeasurementApp.QuantityLength q(double v,
            QuantityMeasurementApp.LengthUnit u) {
        return new QuantityMeasurementApp.QuantityLength(v, u);
    }

    // ---------- EXPLICIT TARGET UNIT TESTS ----------

    @Test
    void testAddition_ExplicitTargetUnit_Feet() {
        assertTrue(q(1, QuantityMeasurementApp.LengthUnit.FEET)
                .add(q(12, QuantityMeasurementApp.LengthUnit.INCH),
                        QuantityMeasurementApp.LengthUnit.FEET)
                .equals(q(2, QuantityMeasurementApp.LengthUnit.FEET)));
    }

    @Test
    void testAddition_ExplicitTargetUnit_Inches() {
        assertTrue(q(1, QuantityMeasurementApp.LengthUnit.FEET)
                .add(q(12, QuantityMeasurementApp.LengthUnit.INCH),
                        QuantityMeasurementApp.LengthUnit.INCH)
                .equals(q(24, QuantityMeasurementApp.LengthUnit.INCH)));
    }

    @Test
    void testAddition_ExplicitTargetUnit_Yards() {

        var result = q(1, QuantityMeasurementApp.LengthUnit.FEET)
                .add(q(12, QuantityMeasurementApp.LengthUnit.INCH),
                        QuantityMeasurementApp.LengthUnit.YARDS);

        assertTrue(result.equals(
                q(2.0/3.0, QuantityMeasurementApp.LengthUnit.YARDS)));
    }
    @Test
    void testAddition_ExplicitTargetUnit_Commutativity() {

        var a = q(1, QuantityMeasurementApp.LengthUnit.FEET);
        var b = q(12, QuantityMeasurementApp.LengthUnit.INCH);

        assertTrue(a.add(b, QuantityMeasurementApp.LengthUnit.YARDS)
                .equals(b.add(a, QuantityMeasurementApp.LengthUnit.YARDS)));
    }

    @Test
    void testAddition_ExplicitTargetUnit_WithZero() {

        assertTrue(q(5, QuantityMeasurementApp.LengthUnit.FEET)
                .add(q(0, QuantityMeasurementApp.LengthUnit.INCH),
                        QuantityMeasurementApp.LengthUnit.YARDS)
                .equals(
                        q(5, QuantityMeasurementApp.LengthUnit.FEET)
                                .convertTo(QuantityMeasurementApp.LengthUnit.YARDS)));
    }

    @Test
    void testAddition_ExplicitTargetUnit_NullTarget() {

        assertThrows(IllegalArgumentException.class,
                () -> q(1, QuantityMeasurementApp.LengthUnit.FEET)
                        .add(q(12, QuantityMeasurementApp.LengthUnit.INCH), null));
    }
}