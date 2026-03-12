package com.example.QuantityMeasurementApp;
public class QuantityMeasurementApp {
    // ===== Generic Demonstrations =====
    public static <U extends IMeasurable> void demonstrateEquality(
            Quantity<U> q1, Quantity<U> q2) {
        System.out.println(q1 + " == " + q2 + " ? " + q1.equals(q2));
    }
    public static <U extends IMeasurable> void demonstrateConversion(
            Quantity<U> q, U target) {
        System.out.println(q + " -> " + q.convertTo(target));
    }
    public static <U extends IMeasurable> void demonstrateAddition(
            Quantity<U> q1, Quantity<U> q2, U target) {
        System.out.println(q1 + " + " + q2 + " = " + q1.add(q2, target));
    }
    public static void main(String[] args) {
        // ================= LENGTH =================
        System.out.println("===== LENGTH =====");
        Quantity<LengthUnit> length1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> length2 = new Quantity<>(6.0, LengthUnit.INCH);

        demonstrateEquality(length1, length2);
        demonstrateConversion(length1, LengthUnit.INCH);
        demonstrateAddition(length1, length2, LengthUnit.FEET);

        System.out.println("Subtract implicit: " + length1.subtract(length2));
        System.out.println("Subtract explicit: " + length1.subtract(length2, LengthUnit.INCH));
        System.out.println("Divide length: " + length1.divide(new Quantity<>(2.0, LengthUnit.FEET)));

        // ================= WEIGHT =================
        System.out.println("\n===== WEIGHT =====");
        Quantity<WeightUnit> w1 = new Quantity<>(10.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(5000.0, WeightUnit.GRAM);

        demonstrateEquality(w1, w2);
        demonstrateConversion(w1, WeightUnit.GRAM);
        demonstrateAddition(w1, w2, WeightUnit.KILOGRAM);

        System.out.println("Weight subtract: " + w1.subtract(w2));
        System.out.println("Weight divide: " + w1.divide(new Quantity<>(5.0, WeightUnit.KILOGRAM)));

        // ================= VOLUME =================
        System.out.println("\n===== VOLUME =====");

        Quantity<VolumeUnit> v1 = new Quantity<>(5.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(500.0, VolumeUnit.MILLILITRE);
        demonstrateEquality(v1, v2);
        demonstrateConversion(v1, VolumeUnit.MILLILITRE);
        demonstrateAddition(v1, v2, VolumeUnit.LITRE);

        System.out.println("Volume subtract: " + v1.subtract(v2));
        System.out.println("Volume divide: " + v1.divide(new Quantity<>(10.0, VolumeUnit.LITRE)));


        // ================= TEMPERATURE (UC14) =================
        System.out.println("\n===== TEMPERATURE =====");
        Quantity<TemperatureUnit> t1 = new Quantity<>(0.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> t2 = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

        // Equality
        demonstrateEquality(t1, t2);
        // Conversion
        demonstrateConversion(t1, TemperatureUnit.FAHRENHEIT);
        demonstrateConversion(t2, TemperatureUnit.CELSIUS);

        // Unsupported arithmetic operations
        try {
            System.out.println("Temperature add: " + t1.add(t2));
        } catch (UnsupportedOperationException e) {
            System.out.println("Expected error (add): " + e.getMessage());
        }
        
        try {
            System.out.println("Temperature subtract: " + t1.subtract(t2));
        } catch (UnsupportedOperationException e) {
            System.out.println("Expected error (subtract): " + e.getMessage());
        }

        
        
        try {
            System.out.println("Temperature divide: " + t1.divide(t2));
        } catch (UnsupportedOperationException e) {
            System.out.println("Expected error (divide): " + e.getMessage());
        }
    }
}