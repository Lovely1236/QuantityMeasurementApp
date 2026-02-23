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
        // ---------- LENGTH ----------
        Quantity<LengthUnit> f= new Quantity<>(1, LengthUnit.FEET);
        Quantity<LengthUnit>i = new Quantity<>(12, LengthUnit.INCH);

        demonstrateEquality(f, i);
        demonstrateConversion(f, LengthUnit.INCH);
        demonstrateAddition(f, i, LengthUnit.FEET);
        // ---------- WEIGHT ----------
        Quantity<WeightUnit> kg= new Quantity<>(1, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> g= new Quantity<>(1000, WeightUnit.GRAM);
        demonstrateEquality(kg, g);
        demonstrateConversion(kg, WeightUnit.GRAM);
        demonstrateAddition(kg, g, WeightUnit.KILOGRAM);
    }
}