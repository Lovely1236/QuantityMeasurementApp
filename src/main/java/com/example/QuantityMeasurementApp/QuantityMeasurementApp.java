package com.example.QuantityMeasurementApp;
public class QuantityMeasurementApp {
    public static <U extends IMeasurable> void demonstrateEquality(
            Quantity<U> q1, Quantity<U> q2) {

        System.out.println(q1 + " == " + q2 + " ? " + q1.equals(q2));
    }
    public static <U extends IMeasurable> void demonstrateConversion(
            Quantity<U> q, U target){
        System.out.println(q + " -> " + q.convertTo(target));
    }
    public static <U extends IMeasurable> void demonstrateAddition(
            Quantity<U> q1, Quantity<U> q2, U target) {
        System.out.println(q1 + " + " + q2 + " = " + q1.add(q2, target));
    }

    public static void main(String[] args){

        // LENGTH
        Quantity<LengthUnit> length1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> length2 = new Quantity<>(6.0, LengthUnit.INCH);

        System.out.println("Subtract implicit: " + length1.subtract(length2));
        System.out.println("Subtract explicit: " + length1.subtract(length2, LengthUnit.INCH));
        System.out.println("Divide length: " + length1.divide(new Quantity<>(2.0, LengthUnit.FEET)));

        // WEIGHT
        Quantity<WeightUnit> w1 = new Quantity<>(10.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(5000.0, WeightUnit.GRAM);
        System.out.println("Weight subtract: " + w1.subtract(w2));
        System.out.println("Weight divide: " + w1.divide(new Quantity<>(5.0, WeightUnit.KILOGRAM)));

        
        
        // VOLUME
        Quantity<VolumeUnit> v1 = new Quantity<>(5.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(500.0, VolumeUnit.MILLILITRE);
        System.out.println("Volume subtract: " + v1.subtract(v2));
        System.out.println("Volume divide: " + v1.divide(new Quantity<>(10.0, VolumeUnit.LITRE)));
    }
}