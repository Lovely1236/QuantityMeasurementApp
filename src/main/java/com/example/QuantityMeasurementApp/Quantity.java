package com.example.QuantityMeasurementApp;
import java.util.Objects;
public final class Quantity<U extends IMeasurable> {

    private static final double EPS =1e-6;
    private final double value;
    private final U unit;
    public Quantity(double value, U unit) {

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Value must be finite");

        if (unit==null)
            throw new IllegalArgumentException("Unit cannot be null");

        this.value = value;
        this.unit = unit;
    }

    private double toBase() {
        return unit.convertToBaseUnit(value);
    }

    // ---------- CONVERSION ----------
    public Quantity<U> convertTo(U targetUnit) {

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double base = toBase();
        double converted = targetUnit.convertFromBaseUnit(base);

        return new Quantity<>(converted, targetUnit);
    }

    // ---------- ADD (default → this.unit) ----------
    public Quantity<U> add(Quantity<U> other) {

        if (other == null)
            throw new IllegalArgumentException("Other quantity cannot be null");

        double sumBase = this.toBase() + other.toBase();
        double result = this.unit.convertFromBaseUnit(sumBase);

        return new Quantity<>(result, this.unit);
    }

    // ---------- ADD WITH TARGET ----------
    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        if (other == null)
            throw new IllegalArgumentException("Other quantity cannot be null");

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double sumBase=this.toBase() + other.toBase();
        double result = targetUnit.convertFromBaseUnit(sumBase);

        return new Quantity<>(result, targetUnit);
    }

    // ---------- EQUALITY ----------
    @Override
    public boolean equals(Object obj) {

        if (this== obj) return true;
        if (obj== null || getClass() != obj.getClass()) return false;

        Quantity<?> other=(Quantity<?>) obj;

        // prevent length vs weight comparison
        if (!this.unit.getClass().equals(other.unit.getClass()))
            return false;

        return Math.abs(this.toBase() - other.toBase()) < EPS;
    }

    @Override
    public int hashCode(){
        return Objects.hash(Math.round(toBase() / EPS));
    }

    @Override
    public String toString() {
        return value + " " + unit.getUnitName();
    }
}