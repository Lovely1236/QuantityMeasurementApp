package com.example.QuantityMeasurementApp;
import java.util.Objects;
// Generic immutable quantity class supporting arithmetic operations.
public final class Quantity<U extends IMeasurable> {
    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {
        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");
        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid numeric value");
        this.value =value;
        this.unit =unit;
    }
    public double getValue(){
        return value;
    }

    public U getUnit() {
        return unit;
    }
    // VALIDATION 

    private void validateOperand(Quantity<U> other) {
        if (other == null)
            throw new IllegalArgumentException("Quantity cannot be null");
        if (other.unit == null)
            throw new IllegalArgumentException("Operand unit cannot be null");

        // cross-category prevention
        if (!this.unit.getClass().equals(other.unit.getClass()))
            throw new IllegalArgumentException("Cross-category operation not allowed");

        if (!Double.isFinite(other.value))
            throw new IllegalArgumentException("Invalid operand value");
    }
    private double toBase() {
        return unit.convertToBaseUnit(value);
    }

    private static double round2(double v) {
        return Math.round(v * 100.0) / 100.0;
    }
              // ADD (UC11 existing) 
       public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

       
       
    public Quantity<U> add(Quantity<U> other, U targetUnit){
        validateOperand(other);
        if (targetUnit== null)
            throw new IllegalArgumentException("Target unit cannot be null");
        double baseResult = this.toBase() + other.toBase();
        double converted = targetUnit.convertFromBaseUnit(baseResult);

        return new Quantity<>(round2(converted), targetUnit);
    }
    // =================== SUBTRACT (UC12) ===================
        public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, this.unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {

        validateOperand(other);

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double baseResult = this.toBase() - other.toBase();
        double converted = targetUnit.convertFromBaseUnit(baseResult);

        return new Quantity<>(round2(converted), targetUnit);
    }
    // ===================== DIVIDE (UC12) ===================
        /* Returns dimensionless ratio */
    public double divide(Quantity<U> other){
        validateOperand(other);
        double divisorBase =other.toBase();
        if (divisorBase ==0.0)
            throw new ArithmeticException("Division by zero");

        return this.toBase() / divisorBase;
    }
    // =================== equals/hash =======================
      @Override
    public boolean equals(Object o){
        if (this ==o) return true;
        if (!(o instanceof Quantity<?> q)) return false;
        
        
        if (!unit.getClass().equals(q.unit.getClass()))
            return false;
        double base1 = this.unit.convertToBaseUnit(this.value);
        double base2 = q.unit.convertToBaseUnit(q.value);

        
        
        return Math.abs(base1 - base2) < 0.0001;
    }

    @Override
    public int hashCode(){
        return Objects.hash(round2(unit.convertToBaseUnit(value)), unit.getClass());
    }

    @Override
    public String toString(){
        return "Quantity(" + value + ", " + unit + ")";
    }
}