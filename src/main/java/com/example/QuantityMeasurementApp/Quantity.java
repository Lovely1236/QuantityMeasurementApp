package com.example.QuantityMeasurementApp;
import java.util.Objects;
import java.util.function.DoubleBinaryOperator;

public final class Quantity<U extends IMeasurable> {
    private final double value;
    private final U unit;
    public Quantity(double value, U unit) {
        if(unit == null)
            throw new IllegalArgumentException("Unit cannot be null");
        if(!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid numeric value");
        this.value = value;
        this.unit = unit;
    }
    public double getValue() {
        return value;
    }

    
    public U getUnit() {
        return unit;
    }
    // ================= BASE CONVERSION =================
    private double toBase(){
        return unit.convertToBaseUnit(value);
    }
    private static double round2(double v) {
        return Math.round(v * 100.0) / 100.0;
    }

    // ================= VALIDATION =================
    private void validate(Quantity<U> other){
        if(other == null)
            throw new IllegalArgumentException("Quantity cannot be null");

        if(!this.unit.getClass().equals(other.unit.getClass()))
            throw new IllegalArgumentException("Cross-category operation not allowed");
        if(!Double.isFinite(other.value))
            throw new IllegalArgumentException("Invalid operand value");
    }
    // ================= OPERATION ENUM =================

    private enum ArithmeticOperation{
        ADD((a, b) -> a + b), SUBTRACT((a, b) -> a - b),
        DIVIDE((a, b) ->{
            if (b == 0.0)
                throw new ArithmeticException("Division by zero");
            return a / b;
        });
        private final DoubleBinaryOperator operator;
        ArithmeticOperation(DoubleBinaryOperator operator) {
            this.operator = operator;
        }
        double compute(double a, double b) {
            return operator.applyAsDouble(a, b);
        }
    }
    // ================= CENTRAL HELPER =================

    private double perform(Quantity<U> other, ArithmeticOperation op) {
        validate(other);
        return op.compute(this.toBase(), other.toBase());
    }
    private Quantity<U> result(double baseValue, U targetUnit) {
        double converted = targetUnit.convertFromBaseUnit(baseValue);
        return new Quantity<>(round2(converted), targetUnit);
    }
    // ================= ADD =================

    public Quantity<U> add(Quantity<U> other){
        return result(perform(other, ArithmeticOperation.ADD), this.unit);
    }
    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        return result(perform(other, ArithmeticOperation.ADD), targetUnit);
    }
    // ================= SUBTRACT =================

    public Quantity<U> subtract(Quantity<U> other) {
        return result(perform(other, ArithmeticOperation.SUBTRACT), this.unit);
    }
    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");
        
        return result(perform(other, ArithmeticOperation.SUBTRACT), targetUnit);
    }

    // ================= DIVIDE =================

    public double divide(Quantity<U> other) {
        return perform(other, ArithmeticOperation.DIVIDE);
    }

    // ================= EQUALS =================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Quantity<?> q)) return false;
        if (!unit.getClass().equals(q.unit.getClass()))
            return false;

        double base1 = this.unit.convertToBaseUnit(this.value);
        double base2 = q.unit.convertToBaseUnit(q.value);

        return Math.abs(base1 - base2) < 0.0001;
    }
    // ================= HASH =================

    @Override
    public int hashCode(){
        return Objects.hash(round2(unit.convertToBaseUnit(value)), unit.getClass());
    }
    // ================= STRING =================

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}